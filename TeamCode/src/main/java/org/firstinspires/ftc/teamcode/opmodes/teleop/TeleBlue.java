package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Blue TeleOp", group = "Final")
public class TeleBlue extends TeleAbstract {
    @Override
    public int getAlliance() { return -1; }
}