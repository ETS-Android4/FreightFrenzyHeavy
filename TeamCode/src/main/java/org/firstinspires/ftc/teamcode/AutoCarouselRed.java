package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name="Red Carousel")
public class AutoCarouselRed extends AutoCarouselBlue {
    @Override
    public void hardwareSetup() {
        // Initialize driving motors with right and left reversed
        rightTread = hardwareMap.dcMotor.get("frontLeft");
        leftTread = hardwareMap.dcMotor.get("frontRight");

        //Set Driving Directions
        rightTread.setDirection(DcMotor.Direction.REVERSE);
        leftTread.setDirection(DcMotor.Direction.FORWARD);


        //Initialize and set direction of carousel motor
        carousel = hardwareMap.dcMotor.get("carousel");
        carousel.setDirection(DcMotor.Direction.REVERSE);

        leftIntake = hardwareMap.dcMotor.get("intake");
        rightIntake = hardwareMap.dcMotor.get("intake");
        pulley = hardwareMap.dcMotor.get("ladder");
        bucket = hardwareMap.dcMotor.get("bucket");

        bucket.setDirection(DcMotorSimple.Direction.FORWARD);
        leftIntake.setDirection(DcMotorSimple.Direction.FORWARD);
        rightIntake.setDirection(DcMotorSimple.Direction.FORWARD);
        pulley.setDirection(DcMotorSimple.Direction.REVERSE);

        //set all motors to actively brake when assigned power is 0
        rightTread.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftTread.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        carousel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bucket.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftIntake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightIntake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pulley.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftTread.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightTread.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bucket.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftIntake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightIntake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        pulley.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Use encoders to regulate speed
        leftTread.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightTread.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bucket.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftIntake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightIntake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        pulley.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //update telemetry
        telemetry.addData("Status:", "Setup Complete");
        telemetry.update();
    }
}
