package org.firstinspires.ftc.teamcode;
public class TeleOp extends Hardware{
    @Override
    public void runOpMode() throws InterruptedException {

        hardwareSetup();

        telemetry.addData("Status:","Initialized");
        telemetry.update();

        waitForStart();
        // I'm doing random controls for now. Adjust as needed. Just want to have this basically written.
        while (opModeIsActive()){
            // Tank drive? Or do we want something else.
            setDrivingPower(gamepad1.left_stick_y,gamepad1.right_stick_y);

            if (gamepad1.a){
                moveArmToOtherSide();
            }
            if (gamepad1.b){
                deliverDuck();
            }
            if (gamepad1.x){
                fullRotateArm();
            }


        }


    }
}
