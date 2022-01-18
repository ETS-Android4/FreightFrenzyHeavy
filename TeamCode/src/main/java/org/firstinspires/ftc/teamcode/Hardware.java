package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Hardware extends LinearOpMode {


    // Good Luck!
    //You should put constants here

    protected DcMotor frontLeft, frontRight, backLeft, backRight, carousel, ladder, intake, bucket;
    static final double     COUNTS_PER_MOTOR_REV    = 1680 ;    // Needs to be fixed based on the motors
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference. Not sure what it is
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    // TODO: Needs tuning
    static final double ARM_MOVE_REVOLUTIONS = 5;
    static final double ARM_ROTATE_REVOLUTIONS = 5;
    static final double CAROUSEL_POWER = 0.5;
    //Use the following if the carousel motor is not encoded
    static final long CAROUSEL_MILLIS = 1000;
    /* Use the following if the carousel motor is encoded
    static final double CAROUSEL_INCHES_TO_TURN = 50; // Just over the circumference of the carousel
    static final double CAROUSEL_DIAMETER_INCHES = 3; //Might need to be adjusted
    static final double CAROUSEL_MOTOR_REVOLUTIONS = CAROUSEL_INCHES_TO_TURN / (CAROUSEL_DIAMETER_INCHES * Math.PI); */




    public ElapsedTime runtime = new ElapsedTime();


    // Setup your drivetrain (Define your motors etc.)
    public void hardwareSetup(boolean isMirrorDriving, boolean isBlueAlliance) {

        // Initialize and set direction of driving motors
        if (!isMirrorDriving) {
            frontLeft = hardwareMap.dcMotor.get("frontLeft");
            frontRight = hardwareMap.dcMotor.get("frontRight");
            backRight = hardwareMap.dcMotor.get("backRight");
            backLeft = hardwareMap.dcMotor.get("backLeft");

            //Set Driving Directions
            frontLeft.setDirection(DcMotor.Direction.REVERSE);
            backLeft.setDirection(DcMotor.Direction.REVERSE);
            frontRight.setDirection(DcMotor.Direction.FORWARD);
            backRight.setDirection(DcMotor.Direction.FORWARD);
        } else {
            frontRight = hardwareMap.dcMotor.get("frontLeft");
            frontLeft = hardwareMap.dcMotor.get("frontRight");
            backLeft = hardwareMap.dcMotor.get("backRight");
            backRight = hardwareMap.dcMotor.get("backLeft");

            //Set Driving Directions
            frontRight.setDirection(DcMotor.Direction.REVERSE);
            backRight.setDirection(DcMotor.Direction.REVERSE);
            frontLeft.setDirection(DcMotor.Direction.FORWARD);
            backLeft.setDirection(DcMotor.Direction.FORWARD);
        }

        //Initialize and set direction of carousel motor
        carousel = hardwareMap.dcMotor.get("carousel");

        // TODO: flip the direction if it's going backwards
        carousel.setDirection(isBlueAlliance ? DcMotor.Direction.FORWARD : DcMotor.Direction.REVERSE);

        intake = hardwareMap.dcMotor.get("intake");
        ladder = hardwareMap.dcMotor.get("ladder");
        bucket = hardwareMap.dcMotor.get("bucket");

        //set motor directions
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        carousel.setDirection(DcMotor.Direction.FORWARD);
        bucket.setDirection(DcMotorSimple.Direction.FORWARD);
        intake.setDirection(DcMotorSimple.Direction.FORWARD);
        ladder.setDirection(DcMotorSimple.Direction.REVERSE);

        //set all motors to actively brake when assigned power is 0
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        carousel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bucket.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ladder.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bucket.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ladder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Use encoders to regulate speed
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bucket.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ladder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //update telemetry
        telemetry.addData("Status:", "Setup Complete");
        telemetry.update();
    }

    public void encoderToSpecificPos(DcMotor motor, int pos , double power){
        motor.setTargetPosition(pos);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(power);
        telemetry.addData("strafe", motor.getCurrentPosition());
        telemetry.update();

        while (motor.isBusy() && opModeIsActive()){
            idle();
        }

        motor.setPower(0);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
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
        //Use the following if the motor is not encoded
        carousel.setPower(CAROUSEL_POWER);
        telemetry.addData("Status","Delivering Duck/TSE");
        telemetry.update();
        sleep(CAROUSEL_MILLIS);
        carousel.setPower(0);
        telemetry.addData("Status","Finished Delivery");
        telemetry.update();
        //Use the following if the motor is encoded
        //singleMotorEncoderDrive(carousel, CAROUSEL_POWER, REVOLUTIONS_TO_TURN, 10);
    }

    public void singleMotorEncoderDrive(DcMotor motor, double power, double revolutions, int timeoutS) {
        double newMotorTarget;
        if (opModeIsActive()) {
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

    public void makeVertical(double power) {
        int currentPos = intake.getCurrentPosition();
        int ticksToMove = (int)( COUNTS_PER_MOTOR_REV/2 - (currentPos % (COUNTS_PER_MOTOR_REV/2)));
        int goalPos = (currentPos + ticksToMove);

        telemetry.addData("current",intake.getCurrentPosition());
        telemetry.addData("goal",goalPos);
        telemetry.addData("difference",ticksToMove);
        telemetry.update();
        intake.setTargetPosition(goalPos);
        intake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        intake.setPower(power);

        while (intake.isBusy()) {
            idle();
        }

        intake.setPower(0);
        intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public int encoderDrive(double maxPower, double frontRightInches, double frontLeftInches, double backLeftInches, double backRightInches){


        if (opModeIsActive()) {

            //calculate and set target positions
            // stop and reset the encoders? Maybe not. Might want to get position and add from there

            double newFRTarget = frontRight.getCurrentPosition() + (frontRightInches * COUNTS_PER_INCH);
            double newFLTarget = frontLeft.getCurrentPosition() + (frontLeftInches * COUNTS_PER_INCH);
            double newBLTarget = backLeft.getCurrentPosition() + (backLeftInches * COUNTS_PER_INCH);
            double newBRTarget = backRight.getCurrentPosition() + (backRightInches * COUNTS_PER_INCH);

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
