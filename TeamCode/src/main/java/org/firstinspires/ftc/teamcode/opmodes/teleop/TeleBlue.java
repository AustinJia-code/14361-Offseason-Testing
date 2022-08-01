package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Blue TeleOp", group = "Test")
public class TeleBlue extends TeleAbstract {
    int alliance;
    @Override
    public void setAlliance() { alliance = 1; }
}