package org.firstinspires.ftc.teamcode;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Blue TeleOp")
public class TeleOp extends Hardware{
    boolean getIsBlueAlliance() {return true;}
    
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
        while (opModeIsActive()){
            // Tank drive? Or do we want something else.
            setDrivingPower(-gamepad1.left_stick_y,-gamepad1.right_stick_y);
            telemetry.addData("FL pos", frontLeft.getCurrentPosition());
            telemetry.addData("BL pos", backLeft.getCurrentPosition());
            telemetry.addData("FR pos", frontRight.getCurrentPosition());
            telemetry.addData("BR pos", backRight.getCurrentPosition());
            telemetry.update();

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
