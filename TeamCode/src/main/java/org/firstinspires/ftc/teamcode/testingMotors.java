package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp(name = "Testing")
public class testingMotors extends LinearOpMode {

    DcMotor frontLeft;

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        frontLeft =  hardwareMap.dcMotor.get("backRight");
        frontLeft.setPower(0.5);
        while(opModeIsActive()) {

        }

    }


}
