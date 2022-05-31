package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.checkerframework.checker.units.qual.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


@TeleOp(name="Mecanum Tele", group="Linear Opmode")
public class MecanumTele extends OpMode {

    public enum DriveMode{ FIELDCENTRIC, ROBOTCENTRIC}

    MecanumBot bot;
    private ElapsedTime runtime;
    private DriveMode mode;
    private double x, y, rx;
    private BNO055IMU imu;
    private Button yButton, xButton;
    BNO055IMU.Parameters imuParameters;
    Orientation angles;

    @Override
    public void init() {
        bot = new MecanumBot(hardwareMap);
        runtime = new ElapsedTime();
        mode = DriveMode.FIELDCENTRIC;
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imuParameters = new BNO055IMU.Parameters();
        imuParameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        imuParameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        imuParameters.loggingEnabled = false;
        imu.initialize(imuParameters);
        yButton = xButton = new Button(false);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }
    @Override
    public void start() {
        runtime.reset();

    }

    @Override
    public void loop() {
        x = gamepad1.left_stick_x;
        y = gamepad1.left_stick_y;
        rx = gamepad1.right_stick_x;
        yButton.update(gamepad1.y);
        xButton.update(gamepad1.x);

        if(mode.equals(DriveMode.FIELDCENTRIC)){
            bot.fieldMove(x, y, rx, -angles.firstAngle);
        }else{
            bot.robotMove(x, y, rx);
        }

        if(yButton.pressed()){
            imu.initialize(imuParameters);
        }

        if(xButton.pressed()) {
            if (mode.equals(DriveMode.FIELDCENTRIC)) {
                mode = DriveMode.ROBOTCENTRIC;
            }else{
                mode = DriveMode.FIELDCENTRIC;
            }
        }

        telemetry.addData("Runtime:", runtime.toString());
    }
}
