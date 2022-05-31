package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Config
public class LibMecanum extends OpMode {
    private MecanumDrive drive;
    private Motor leftFront, leftRear, rightRear, rightFront;
    private GamepadEx driver;
    private RevIMU gyro;
    @Override
    public void init() {
        leftFront = hardwareMap.get(Motor.class, "leftFront");
        leftRear = hardwareMap.get(Motor.class, "leftRear");
        rightRear = hardwareMap.get(Motor.class, "rightRear");
        rightFront = hardwareMap.get(Motor.class, "rightFront");
        drive = new MecanumDrive(leftFront, rightFront, leftRear, rightRear);
        driver = new GamepadEx(gamepad1);
        gyro = new RevIMU(hardwareMap, "imu");
        gyro.init();
        gyro.invertGyro();
    }
    @Override
    public void loop() {
        drive.driveFieldCentric(
                driver.getLeftX(),
                driver.getLeftY(),
                driver.getRightY(),
                gyro.getAbsoluteHeading()
        );
    }
}
