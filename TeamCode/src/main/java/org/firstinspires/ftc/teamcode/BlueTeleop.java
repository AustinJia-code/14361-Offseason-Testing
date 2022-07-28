package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Blue TeleOp", group = "Test")
public class AbstractRunner extends AbstractMecanum {
    private int alliance;
    @Override
    public void setAlliance() {
        alliance = 1;
    }
}