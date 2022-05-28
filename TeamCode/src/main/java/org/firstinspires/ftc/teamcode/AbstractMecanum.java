package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.*;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@Config
public class AbstractMecanum extends OpMode {
    public enum DriveMode{ FIELDCENTRIC, ROBOTCENTRIC}
    private DcMotor leftFront, leftRear, rightRear, rightFront;
    private double x, y, rx;
    MecanumBot bot;
    private ElapsedTime runtime;
    private DriveMode mode;
    private BNO055IMU imu;
    private Button yButton, xButton;
    BNO055IMU.Parameters imuParameters;
    Orientation angles;

    @Override
    public void init() {
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
        rightRear = hardwareMap.get(DcMotorEx.class, "rightRear");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
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
            double deg = -angles.firstAngle;
            double rad = deg * Math.PI/180;
            double temp = y * Math.cos(rad) + x * Math.sin(rad);
            x = -y * Math.sin(rad) + x * Math.cos(rad);
            y = temp;
        }
        double power = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        double leftFrontPower = (y + x + rx) * power;
        double leftRearPower = (y - x + rx) * power;
        double rightFrontPower = (y - x - rx) * power;
        double rightRearPower = (y + x - rx) * power;
        double max = Math.max(1.0, Math.max(leftFrontPower, Math.max(leftRearPower, Math.max(rightFrontPower, rightRearPower))));
        powerMotors(leftFrontPower/max, leftRearPower/max, rightFrontPower/max, rightRearPower/max);

        leftFront.setPower(leftFrontPower);
        leftRear.setPower(leftRearPower);
        rightFront.setPower(rightFrontPower);
        rightRear.setPower(rightRearPower);

        if(yButton.pressed()){
            imu.initialize(imuParameters);
        }

        if(xButton.pressed()) {
            if (mode.equals(DriveMode.FIELDCENTRIC)) {
                mode = DriveMode.ROBOTCENTRIC;
            } else {
                mode = DriveMode.FIELDCENTRIC;
            }
        }
        telemetry.addData("Runtime:", runtime.toString());
    }
}