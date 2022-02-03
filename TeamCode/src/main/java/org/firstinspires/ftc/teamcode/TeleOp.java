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
        Button gamepad1x = new Button(false);
        Button gamepad1b = new Button(false);
        gamepad1x.update(gamepad1.x);
        while (opModeIsActive()) {
            gamepad1x.update(gamepad1.x);
            gamepad1b.update(gamepad1.b);
            if (gamepad1.x && floor.isPressed()) {
                intake.setPower(-0.6);
            } else if(gamepad1x.isNewlyReleased() && floor.isPressed()) {
                intake.setPower(0);
                makeVertical(0.8);
            } else if(!intake.isBusy()) {
                intake.setPower(0);
                intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }

            // Tank drive? Or do we want something else.
            setDrivingPower(-gamepad1.left_stick_y,-gamepad1.right_stick_y);
            if (gamepad1.a) {
                carousel.setPower(0.7);
            } else{
                carousel.setPower(0);
            }
            //Spinngin intake

//            if(gamepad1.x){
//            } else if(!intake.isBusy())
//            {
//                intake.setPower(0);
//                intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            }
            // Ladder up and down
            if (gamepad1.dpad_up && ladder.getCurrentPosition() < 8700 && !ceiling.isPressed() && !intake.isBusy() && bucket.getCurrentPosition() < 50) {
                ladder.setPower(1);
            } else if (gamepad1.dpad_down && !floor.isPressed() && !intake.isBusy() && bucket.getCurrentPosition() < 50) {
                ladder.setPower(-0.8);
            } else{
                ladder.setPower(0);
            }

            // Bucket
            if (gamepad1.left_bumper && ladder.getCurrentPosition() > 4000 && bucket.getCurrentPosition() < 450) {
                bucket.setPower(0.4);
            } else if (gamepad1.right_bumper && bucket.getCurrentPosition() > 0) {
                bucket.setPower(-0.4);
            } else {
                bucket.setPower(0);
            }
            // Auto back for the bucket
            if(gamepad1b.isNewlyPressed() &&  ladder.getCurrentPosition() > 5000){
                encoderToSpecificPos(bucket,0,0.7);
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
