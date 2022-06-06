package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
public class AbstractMecanum extends OpMode {

    private MecanumBot bot;
    private ElapsedTime runtime;
    private RevIMU imu;
    private GamepadEx driver1;
    private int alliance;

    public void setAlliance() {
        alliance = -1;
    }
    public String getAlliance(){
        return alliance == -1 ? "RED" : "BLUE";
    }

    @Override
    public void init() {
        telemetry.addLine("Status: Initializing");
        telemetry.update();

        setAlliance();
        bot = new MecanumBot(hardwareMap, MecanumBot.Mode.FIELD);
        runtime = new ElapsedTime();
        imu = new RevIMU(hardwareMap);
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
        bot.drive(driver1, imu.getHeading());

        if(driver1.wasJustPressed(GamepadKeys.Button.Y)){
            imu.reset();
        }

        if(driver1.wasJustPressed(GamepadKeys.Button.X)) {
            bot.flop();
        }
        telemetry.addData("Runtime:", runtime.toString());
    }
}