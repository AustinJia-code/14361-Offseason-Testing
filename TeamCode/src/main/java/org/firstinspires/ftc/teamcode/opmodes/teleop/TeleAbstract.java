package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.gamepad.*;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

@Config
public class TeleAbstract extends OpMode {

    private Drivetrain bot;
    private ElapsedTime runtime;
    private GamepadEx driver1;
    private int alliance;

    public void setAlliance() { alliance = -1; }
    public String getAlliance(){
        return alliance == -1 ? "RED" : "BLUE";
    }

    @Override
    public void init() {
        telemetry.addLine("Status: Initializing");
        telemetry.update();

        setAlliance();
        bot = new Drivetrain(hardwareMap);
        runtime = new ElapsedTime();
        driver1 = new GamepadEx(gamepad1);
    }

    @Override
    public void init_loop(){
        telemetry.addLine("Status: Initialized");
        telemetry.addData("Alliance:", getAlliance());
        telemetry.update();
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        driver1.readButtons();
        bot.drive(driver1);

        if(driver1.wasJustPressed(GamepadKeys.Button.Y)){ bot.recenter(); }
        if(driver1.wasJustPressed(GamepadKeys.Button.X)){ bot.flop(); }

        telemetry.addData("Runtime:", runtime.toString());
        telemetry.addData("Mode:", bot.getMode());
    }
}