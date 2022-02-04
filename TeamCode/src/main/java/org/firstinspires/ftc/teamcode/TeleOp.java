package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOp")
public class TeleOp extends Hardware {
    boolean getIsBlueAlliance() { return true; }

    @Override
    public void runOpMode() throws InterruptedException {

        telemetry.addData("Status","Intializing");
        telemetry.update();
        hardwareSetup(false, getIsBlueAlliance());
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
        gamepad2x.update(gamepad2.x);
        while (opModeIsActive()) {
            gamepad2x.update(gamepad2.x);
            gamepad2b.update(gamepad2.b);
            gamepad2y.update(gamepad2.y);

            if (gamepad2.x && floor.isPressed()) {
                intake.setPower(-0.6);
            } else if(gamepad2x.isNewlyReleased() && floor.isPressed()) {
                intake.setPower(0);
                makeVertical(0.8);
            } else if(!intake.isBusy()) {
                intake.setPower(0);
                intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }

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
            setDrivingPower(-gamepad1.left_stick_y,-gamepad1.right_stick_y);
            if (gamepad2.a) {
                carousel.setPower(0.7);
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
            // Ladder up and down
            if (gamepad2.dpad_up && !ceiling.isPressed() && !intake.isBusy() && bucket.getCurrentPosition() < 50) {
                ladder.setPower(1);
            } else if (gamepad2.dpad_down && !floor.isPressed() && !intake.isBusy() && bucket.getCurrentPosition() < 50) {
                ladder.setPower(-0.8);
            } else{
                ladder.setPower(0);
            }

            // Bucket
            if (gamepad2.left_bumper && bucket.getCurrentPosition() < 450) {
                bucket.setPower(0.4);
            } else if (gamepad2.right_bumper && bucket.getCurrentPosition() > 0) {
                bucket.setPower(-0.4);
            } else {
                bucket.setPower(0);
            }
            // Auto back for the bucket
            if(gamepad2b.isNewlyPressed()){
                encoderToSpecificPos(bucket,-3,0.7);
                while(ladder.getCurrentPosition() > 1500){
                    ladder.setPower(1);
                }
                makeHorizontal(.5);

            }

            telemetry.addData("bucket", bucket.getCurrentPosition());
            telemetry.addData("intake", intake.getCurrentPosition());
            telemetry.addData("ladder", ladder.getCurrentPosition());
            telemetry.addData("ceiling sensor", ceiling.getValue());
            telemetry.addData("floor sensor", floor.getValue());
            telemetry.update();

        }
    }
}
