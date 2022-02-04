package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="Red TeleOp")
public class TeleOpRed extends org.firstinspires.ftc.teamcode.TeleOp {
    @Override
    public void hardwareSetup() {
        super.hardwareSetup();
        carousel.setDirection(DcMotor.Direction.REVERSE);
    }
}
