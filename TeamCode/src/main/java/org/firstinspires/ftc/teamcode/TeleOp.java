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
//        boolean firstStrafe = true; //used to make sure that encoder doesn't get confused about its location
        boolean clawOpen = true; //used to make sure that code doesn't get confused about whether claw is open or not
        int pos;
        int direction = 0;

        while (opModeIsActive()){

            // Tank drive? Or do we want something else.
            setDrivingPower(-gamepad1.left_stick_y,-gamepad1.right_stick_y);

            //auto-strafe -- not working yet
//            if(gamepad1.a && !firstStrafe){
//                encoderToSpecificPos(clawStrafe,0,0.5);
//            }
//            else if(gamepad1.b){
//                encoderToSpecificPos(clawStrafe,2200,0.5); //4500 is final location, any other values are for testing
//                firstStrafe = false;
//            }
//            auto-strafe: take two
//            if(gamepad2.b){
//                direction = 1;
//                pos = 4900;
//            } else if(gamepad2.a){
//                direction = -1;
//                pos = 0;
//            }
//            if((direction == 1 && clawStrafe.getCurrentPosition() <= pos) || direction == -1 && clawStrafe.getCurrentPosition() >= pos){
//                clawStrafe.setPower(.5 * direction);
//            }

            //grab
            if (gamepad2.y) {
                if (clawOpen) {
                    claw.setPosition(0.1);
                    clawOpen = false;
                    sleep(100);
                } else {
                    claw.setPosition(0.5);
                    clawOpen = true;
                    sleep(100);
                }
            }

            //carousel
            if (gamepad1.x) {
                carousel.setPower(1);
            } else if (gamepad1.y) {
                carousel.setPower(-1);
            } else {
                carousel.setPower(0);
            }
            /*
            //manual strafe
            if(gamepad2.dpad_up){
                clawStrafe.setPower(.5);
            }
            else if(gamepad2.dpad_down) {
                clawStrafe.setPower(-.5);
            }
            else{
                clawStrafe.setPower(0);
            }

            //claw rotate
            if(gamepad2.right_bumper){
                clawRotate.setPower(.25);
            }
            else if (gamepad2.left_bumper) {
                clawRotate.setPower(-.25);
            }
            else{
                clawRotate.setPower(0);
            }

            telemetry.addData("clawStrafe", clawStrafe.getCurrentPosition());
            telemetry.addData("clawRotate", clawRotate.getCurrentPosition()); */
//            telemetry.addData("clawFinger", claw.getPosition());

            telemetry.update();

        }


    }
}
