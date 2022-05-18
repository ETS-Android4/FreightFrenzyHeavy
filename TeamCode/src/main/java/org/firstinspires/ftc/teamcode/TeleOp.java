package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Blue TeleOp")
public class TeleOp extends Hardware {
    boolean getIsBlueAlliance() { return true; }

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status","Intializing");
        telemetry.update();
        hardwareSetup();
        telemetry.addData("Status","Waiting for Start");
        telemetry.update();
        //Wait until the play button is pressed
        waitForStart();
        telemetry.addData("Status","Match in Progress");
        telemetry.update();

        Button gamepad2x = new Button(false);
        Button gamepad2a = new Button(false);
        Button gamepad2y = new Button(false);
        gamepad2x.update(gamepad2.x);
        gamepad2a.update(gamepad2.a);
        gamepad2y.update(gamepad2.y);

        boolean tryingToGrab = false; // make an Intake class in future years that takes care of this
        while (opModeIsActive()) {
            gamepad2x.update(gamepad2.x);
            gamepad2a.update(gamepad2.a);
            gamepad2y.update(gamepad2.y);

            //turn intake on/off when button A is pressed
            if(gamepad2a.isNewlyPressed()) {
                tryingToGrab = !tryingToGrab;
            }

            //push freight out when B is held down
            if(gamepad2.b) {
                setIntakePower(-0.3);
                tryingToGrab = false;
            } else if (tryingToGrab) {
                setIntakePower(0.3);
            } else {
                setIntakePower(0);
            }

            // Tank drive
            setDrivingPowers(-gamepad1.left_stick_y,-gamepad1.right_stick_y);

            //carousel
            if (gamepad1.a) {
                carousel.setPower(-1);
            } else{
                carousel.setPower(0);
            }

            //bucket
            if (gamepad2.x){
                setBucketPosition(0.75);
            } else if (gamepad2.y){
                setBucketPosition(0.25);
            }

            //pulley
            if (gamepad2.dpad_up){
                pulley.setPower(0.5);
            } else if (gamepad2.dpad_down){
                pulley.setPower(-.5);
            } else { pulley.setPower(0);}

            //telemetry
            telemetry.addData("carousel power", carousel.getPower());
            telemetry.addData("leftIntake", leftIntake.getCurrentPosition());
            telemetry.addData("rightIntake", rightIntake.getCurrentPosition());
            telemetry.addData("pulley", pulley.getCurrentPosition());
            telemetry.addData("tryingtograb",tryingToGrab);
            telemetry.update();

        }
    }
}