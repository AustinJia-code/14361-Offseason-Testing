package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import static org.firstinspires.ftc.teamcode.util.Alliance.BLUE;

@TeleOp(name = "Blue TeleOp", group = "Test")
public class BlueTeleOp extends AbstractMecanum {
    @Override
    public void setAlliance() {
        this.alliance = BLUE;
    }
}