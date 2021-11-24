package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Hardware extends LinearOpMode {


    // Good Luck!
    //You should put constants here

    protected DcMotor frontLeft, frontRight, backLeft, backRight, clawStrafe, clawRotate, carousel ;
    protected Servo clawGrabL, clawGrabR;
    Claw claw;
    int armLocation = 0;
    int armRotation = 0;

    static final double     COUNTS_PER_MOTOR_REV    = 420 ;    // Needs to be fixed based on the motors
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference. Not sure what it is
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    // TODO: Needs tuning
    static final double ARM_MOVE_REVOLUTIONS = 5;
    static final double ARM_ROTATE_REVOLUTIONS = 5;
    static final double CAROUSEL_POWER = 0.5; //Needs to be adjusted
    static final double INCHES_TO_TURN = 50; // Just over the circumference of the carousel
    static final double CAROUSEL_DIAMETER_INCHES = 4; //Might need to be adjusted
    static final double REVOLUTIONS_TO_TURN = INCHES_TO_TURN / (CAROUSEL_DIAMETER_INCHES * Math.PI);




    public ElapsedTime runtime = new ElapsedTime();


    // Setup your drivetrain (Define your motors etc.)
    public void hardwareSetup() {



        // Define your methods of driving (Encoder drive, tank drive, etc.

        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        clawStrafe = hardwareMap.dcMotor.get("clawStrafe");
        clawRotate = hardwareMap.dcMotor.get("clawRotate");
        carousel = hardwareMap.dcMotor.get("carousel");
        clawGrabL = hardwareMap.servo.get("clawGrabL");
        clawGrabR = hardwareMap.servo.get("clawGrabR");

        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        clawStrafe.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        clawRotate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        clawStrafe.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        clawRotate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        //Use encoders
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        clawStrafe.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        clawRotate.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Status:", "Setup Complete");
        telemetry.update();

        Claw claw = new Claw(clawGrabL, clawGrabR);
    }

    public void moveArmToOtherSide(){
        if (armLocation == 0){
            singleMotorEncoderDrive(clawStrafe,0.5,ARM_MOVE_REVOLUTIONS,10);
            armLocation = 1;
        } else{
            singleMotorEncoderDrive(clawStrafe,0.5, - ARM_MOVE_REVOLUTIONS,10);
            armLocation = 0;
        }
    }

    // TODO: Arm functions redo.
    public void fullRotateArm(){
        // Will likely need acceleration control

        if (armRotation == 0){
            singleMotorEncoderDrive(clawStrafe,0.5,ARM_ROTATE_REVOLUTIONS,10);
            armRotation = 1;
        } else{
            singleMotorEncoderDrive(clawStrafe,0.5, - ARM_ROTATE_REVOLUTIONS,10);
            armRotation = 0;
        }
    }

    public void clawOpen(){
        claw.setPosition(1);
    }

    public void clawClose(){
        claw.grabCube();
    }

    public void setDrivingPower(double leftPower, double rightPower) {
        frontLeft.setPower(leftPower);
        backLeft.setPower(leftPower);
        frontRight.setPower(rightPower);
        backRight.setPower(rightPower);
    }

    public void driveForward(final double power) {
        setDrivingPower(power, power);
    }

    public void deliverDuck() {
        singleMotorEncoderDrive(carousel, CAROUSEL_POWER, REVOLUTIONS_TO_TURN, 10);
    }

    public void singleMotorEncoderDrive (DcMotor motor, double power, double revolutions, int timeoutS) {
        double newMotorTarget;
        if(opModeIsActive()) {
            //calculate target positions
            newMotorTarget = motor.getCurrentPosition() + revolutions * COUNTS_PER_MOTOR_REV;
            //set target positions
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor.setTargetPosition((int) newMotorTarget);
            //restart timer and set power
            runtime.reset();
            motor.setPower(power);
            //Display telemetry
            telemetry.addData("Status", "Moving motor to position");
            telemetry.update();
            //loop until the motor reaches target position
            while (opModeIsActive() && motor.isBusy() && runtime.seconds() < timeoutS) {
                // do nothing
                idle();
            }
            //Stop motor
            motor.setPower(0);
            //Get out of position mode
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //Clear Telemetry
            telemetry.update();
        }
    }

    public int encoderDrive(double maxPower, double frontRightInches, double frontLeftInches, double backLeftInches, double backRightInches){
        // stop and reset the encoders? Maybe not. Might want to get position and add from there
        double newFRTarget;
        double newFLTarget;
        double newBLTarget;
        double newBRTarget;

        if (opModeIsActive()){

            //calculate and set target positions
            newFRTarget = frontRight.getCurrentPosition()     +  (frontRightInches * COUNTS_PER_INCH);
            newFLTarget = frontLeft.getCurrentPosition()     +  (frontLeftInches * COUNTS_PER_INCH);
            newBLTarget = backLeft.getCurrentPosition()     +  (backLeftInches * COUNTS_PER_INCH);
            newBRTarget = backRight.getCurrentPosition()     + (backRightInches * COUNTS_PER_INCH);

            backRight.setTargetPosition((int) newBRTarget);
            frontRight.setTargetPosition((int) newFRTarget);
            frontLeft.setTargetPosition((int) newFLTarget);
            backLeft.setTargetPosition((int) newBLTarget);

            // Run to position
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // Set powers. For now I'm setting to maxPower, so be careful.
            // In the future I'd like to add some acceleration control through powers, which
            // should help with encoder accuracy. Stay tuned.
            runtime.reset();
            frontRight.setPower(maxPower);
            frontLeft.setPower(maxPower);
            backRight.setPower(maxPower);
            backLeft.setPower(maxPower);

            while (opModeIsActive() &&
                    (frontRight.isBusy() && frontLeft.isBusy() && backRight.isBusy() && backLeft.isBusy() )) {
                    // Do nothing
                idle();

            }
            // Set Zero Power
            setDrivingPower(0,0);

            // Go back to Run_Using_Encoder
            frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        return 0;
    }

    // Last thing is an empty runOpMode because it's a linearopmode
    @Override
    public void runOpMode() throws InterruptedException {

    }
}
