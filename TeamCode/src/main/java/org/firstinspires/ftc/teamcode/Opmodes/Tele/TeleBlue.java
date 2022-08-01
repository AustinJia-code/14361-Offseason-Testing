package org.firstinspires.ftc.teamcode.ppmodes.Tele;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Blue TeleOp", group = "Test")
public class TeleBlue extends TeleAbstract {
    private int alliance;
    @Override
    public void setAlliance() {
        alliance = 1;
    }
}