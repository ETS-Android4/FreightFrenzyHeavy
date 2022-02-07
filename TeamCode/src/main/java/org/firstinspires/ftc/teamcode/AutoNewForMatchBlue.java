package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="New Auto for Match Blue")
public class AutoNewForMatchBlue extends Hardware {
    @Override
    public void runOpMode() {


        hardwareSetup();
        imuSetup();
        waitForStart();
        ladder.setPower(.5);
        sleep(1000);
        ladder.setPower(0);
        encoderDrive(1, -30, -30, -18, -30);
        encoderDrive(0.6, 24, 0, 0, 24);
        encoderDrive(1, -48, -48, -48, -48);
        while(!floor.isPressed()){
            ladder.setPower(-.5);
        }
//        encoderDrive(1, 24, 0,0,24);
//        encoderDrive(1, -18,-18,-18,-18);
//
//        ladder.setPower(.8);
//        sleep(3000);
//        bucket.setPower(.1);
//        sleep(2000);
//        bucket.setPower(-.5);
//        sleep(3000);
//        ladder.setPower(-.8);
//        sleep(2000);
//        encoderDrive(1, 36,36,36,36);
//        ladder.setPower(-.8);
//        sleep(1000);

    }
}
