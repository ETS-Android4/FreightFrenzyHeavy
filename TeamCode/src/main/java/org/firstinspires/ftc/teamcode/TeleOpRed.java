package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Disabled
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Red TeleOp")
public class TeleOpRed extends TeleOp {
    @Override
    boolean getIsBlueAlliance() {return false;}
}
