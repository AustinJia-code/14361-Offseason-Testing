package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys.*;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

@Config
public class TeleAbstract extends OpMode {

    private Drivetrain bot;
    private ElapsedTime runtime;
    private GamepadEx driver1;
    private int alliance;

    public int getAlliance() { return 0; } //-1 BLUE, 0 NEITHER, 1 RED
    public String allianceToString(){
        return alliance == -1 ? "BLUE" : (alliance == 0 ? "NEITHER" : "RED");
    }

    @Override
    public void init() {
        telemetry.addLine("Status: Initializing");
        telemetry.update();

        alliance = getAlliance();
        bot = new Drivetrain(hardwareMap);
        driver1 = new GamepadEx(gamepad1);
        runtime = new ElapsedTime();
    }

    @Override
    public void init_loop(){
        telemetry.addLine("Status: Initialized");
        telemetry.addData("Alliance:", allianceToString());
        telemetry.update();

        driver1.readButtons();
        if(driver1.wasJustPressed(Button.X)){ alliance = -1; }
        if(driver1.wasJustPressed(Button.B)){ alliance = 1; }
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        driver1.readButtons();
        bot.drive(driver1);

        if(driver1.wasJustPressed(Button.Y)){ bot.recenter(); }
        if(driver1.wasJustPressed(Button.X)){ bot.switchModes(); }

        telemetry.addData("Alliance:", allianceToString());
        telemetry.addData("Runtime:", runtime.toString());
        telemetry.addData("Mode:", bot.getMode());
    }
}
