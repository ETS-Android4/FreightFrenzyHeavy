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
        boolean firstStrafe = true; //used to make sure that encoder doesn't get confused about its location
        while (opModeIsActive()){
            // Tank drive? Or do we want something else.
            setDrivingPower(-gamepad1.left_stick_y,-gamepad1.right_stick_y);

            //auto-strafe -- not working yet
            if(gamepad1.a && !firstStrafe){
                encoderToSpecificPos(clawStrafe,0,0.5);
            }
            else if(gamepad1.b){
                encoderToSpecificPos(clawStrafe,4500,0.5);
                firstStrafe = false;
            }

            //manual strafe
            if(gamepad1.dpad_up){
                clawStrafe.setPower(.5);
            }
            else if(gamepad1.dpad_down) {
                clawStrafe.setPower(-.5);
            }
            else{
                clawStrafe.setPower(0);
            }

            //claw rotate
            if(gamepad1.right_bumper){
                clawRotate.setPower(.125);
            }
            else if (gamepad1.left_bumper) {
                clawRotate.setPower(-.125);
            }
            else{
                clawRotate.setPower(0);
            }

            telemetry.addData("clawRotate:",clawRotate.getCurrentPosition()); // not working
            telemetry.addData("clawStrafe", clawStrafe.getCurrentPosition());
            telemetry.update();

        }


    }
}
