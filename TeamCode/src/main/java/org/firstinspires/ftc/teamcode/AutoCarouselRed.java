package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name="Red Carousel")
public class AutoCarouselRed extends Hardware {
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status","Intializing");
        telemetry.update();
        hardwareSetup();
        telemetry.addData("Status","Waiting for Start");
        telemetry.update();
        //Wait until the play button is pressed
        waitForStart();
        telemetry.addData("Status","Match in Progress");
        telemetry.update();

        //drive backward
        leftTread.setPower(-0.8);
        rightTread.setPower(-0.8);
        sleep(250);
        leftTread.setPower(0);
        rightTread.setPower(0);

        //spin carousel
        carousel.setPower(1);
        sleep(10000);
        carousel.setPower(0);
    }
}
