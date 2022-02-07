package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


@Autonomous(name="New Auto for Match Red")
public class AutoNewForMatchRed extends org.firstinspires.ftc.teamcode.AutoNewForMatchBlue{

    @Override
    public void runOpMode(){
//        super.runOpMode();
//        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
//        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
//        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
//        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        hardwareSetup();
        imuSetup();
        waitForStart();
        ladder.setPower(.5);
        sleep(1000);
        ladder.setPower(0);
        encoderDrive(1, -30, -30, -18, -30);
        encoderDrive(0.6, -24, 0, 0, -24);
        encoderDrive(1, -48, -48, -48, -48);
        while(!floor.isPressed()){
            ladder.setPower(-.5);
        }





    }
}
