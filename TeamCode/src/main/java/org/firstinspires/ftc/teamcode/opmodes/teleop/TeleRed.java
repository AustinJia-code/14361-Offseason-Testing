package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Red TeleOp", group = "Final")
public class TeleRed extends TeleAbstract {
    @Override
    public int getAlliance() { return 1; }
}