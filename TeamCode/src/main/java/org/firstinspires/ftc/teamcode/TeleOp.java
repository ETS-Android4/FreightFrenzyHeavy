package org.firstinspires.ftc.teamcode;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Blue TeleOp")
public class TeleOp extends Hardware{
    boolean getIsBlueAlliance() {return true;}
    
    @Override
    public void runOpMode() throws InterruptedException {
        //Initialize hardware devices
        hardwareSetup(false, getIsBlueAlliance());

        telemetry.addData("Status:","Initialized");
        telemetry.update();
        waitForStart();
        // I'm doing random controls for now. Adjust as needed. Just want to have this basically written.
        while (opModeIsActive()){
            // Tank drive
            setDrivingPower(-gamepad1.left_stick_y, -gamepad1.right_stick_y);

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
