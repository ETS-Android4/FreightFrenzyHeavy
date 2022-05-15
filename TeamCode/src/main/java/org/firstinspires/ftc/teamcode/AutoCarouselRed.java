package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name="Red Carousel")
public class AutoCarouselRed extends AutoCarouselBlue {
    @Override
    public void hardwareSetup() {
        super.hardwareSetup();

        // Initialize driving motors with right and left reversed
        rightTread = hardwareMap.dcMotor.get("frontLeft");
        leftTread = hardwareMap.dcMotor.get("frontRight");

        //Set Driving Directions
        rightTread.setDirection(DcMotor.Direction.REVERSE);
        leftTread.setDirection(DcMotor.Direction.FORWARD);

        //Initialize and set direction of carousel motor
        carousel = hardwareMap.dcMotor.get("carousel");
        carousel.setDirection(DcMotor.Direction.REVERSE);

        leftTread.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightTread.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Use encoders to regulate speed
        leftTread.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightTread.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //update telemetry
        telemetry.addData("Status:", "Setup Complete");
        telemetry.update();
    }
}
