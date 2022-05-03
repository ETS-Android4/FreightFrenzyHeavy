package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Blue TeleOp")
public class TeleOp extends Hardware {
    boolean getIsBlueAlliance() { return true; }

    @Override
    public void runOpMode() throws InterruptedException {
        boolean lowering = false;

        telemetry.addData("Status","Intializing");
        telemetry.update();
        hardwareSetup();
        telemetry.addData("Status","Waiting for Start");
        telemetry.update();
        //Wait until the play button is pressed
        waitForStart();
        telemetry.addData("Status","Match in Progress");
        telemetry.update();
        // I'm doing random controls for now. Adjust as needed. Just want to have this basically written.
//        boolean firstStrafe = true; //used to make sure that encoder doesn't get confused about its location
        boolean clawOpen = true; //used to make sure that code doesn't get confused about whether claw is open or not
        int pos;
        int direction = 0;
        Button gamepad2x = new Button(false);
        Button gamepad2b = new Button(false);
        Button gamepad2y = new Button(false);
        Button gamepad2LeftTrigger = new Button(false);
        Button gamepad2RightTrigger = new Button(false);
//        gamepad2.left
        gamepad2x.update(gamepad2.x);
        gamepad2b.update(gamepad2.b);
        gamepad2y.update(gamepad2.y);
        gamepad2LeftTrigger.update(gamepad2.left_trigger > 0);
        gamepad2RightTrigger.update(gamepad2.right_trigger > 0);
        while (opModeIsActive()) {
            gamepad2x.update(gamepad2.x);
            gamepad2b.update(gamepad2.b);
            gamepad2y.update(gamepad2.y);
            gamepad2LeftTrigger.update(gamepad2.left_trigger > 0);
            gamepad2RightTrigger.update(gamepad2.right_trigger > 0);

                if (gamepad2.x) {
                    setIntakePower(-.6);
                } else if (gamepad2.y) {
                    setIntakePower(0.6);
                } else if(gamepad2LeftTrigger.isCurrentlyPressed()){
                    setIntakePower(0.6);
                } else if(gamepad2LeftTrigger.isNewlyReleased()){
                    setIntakePower(0);
                } else if(gamepad2RightTrigger.isCurrentlyPressed()){
                    setIntakePower(-0.6);
                } else if(gamepad2RightTrigger.isNewlyReleased()){
                   setIntakePower(0);
                }

            updateMotor(leftIntake);
            updateMotor(rightIntake);

//            if (gamepad2.y && (floor.isPressed() || ladder.getCurrentPosition() > 4000)) {
//                intake.setPower(0.6);
//            } else if(gamepad2y.isNewlyReleased() && (floor.isPressed() || ladder.getCurrentPosition() > 4000)) {
//                intake.setPower(0);
//                makeVertical(-0.8);
//            } else if(!intake.isBusy()) {
//                intake.setPower(0);
//                intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            }


            // Tank drive? Or do we want something else.
            setDrivingPowers(-gamepad1.left_stick_y,-gamepad1.right_stick_y);

            //carousel
            if (gamepad2.a) {
                carousel.setPower(1);
            } else{
                carousel.setPower(0);
            }
            //Spinning intake

//            if(gamepad2.x){
//            } else if(!intake.isBusy())
//            {
//                intake.setPower(0);
//                intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            }

            // Bucket TODO: test the run to position thingy and get rid of magic numbers
            if(bucket.getMode() == DcMotor.RunMode.RUN_USING_ENCODER) {
                if (gamepad2.left_bumper) {
                    bucket.setPower(0.4);
                } else if (gamepad2.right_bumper) {
                    bucket.setPower(-0.4);
                } else {
                    bucket.setPower(0);
                }
            } else if (!pulley.isBusy()) {
                bucket.setPower(0);
                bucket.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }

            // Auto back for the bucket
            if(gamepad2b.isNewlyPressed()){
                encoderToSpecificPos(bucket,-3,0.7);
                lowering = true;
                pulley.setPower(-1);
            }

            updateMotor(bucket);

            telemetry.addData("bucket", bucket.getCurrentPosition());
            telemetry.addData("leftIntake", leftIntake.getCurrentPosition());
            telemetry.addData("rightIntake", leftIntake.getCurrentPosition());
            telemetry.addData("ladder", pulley.getCurrentPosition());
            telemetry.addData("lowering", lowering);
           telemetry.update();

        }
    }
}
