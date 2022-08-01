package org.firstinspires.ftc.teamcode;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.hardware.*;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

class MecanumBot {
    private DcMotorEx leftFront, leftRear, rightRear, rightFront;
    private RevIMU imu;
    private Mode mode;

    enum Mode{FIELD, ROBOT}

    MecanumBot(HardwareMap hardwareMap) {
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
        rightRear = hardwareMap.get(DcMotorEx.class, "rightRear");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        imu = new RevIMU(HardwareMap);
        mode = FIELD;
    }

    void setMode(Mode m){
        mode = m;
    }

    void flop(){
        if(mode.equals(Mode.ROBOT)){
            mode = Mode.FIELD;
        }else{
            mode = Mode.ROBOT;
        }
    }

    Mode getMode(){
        return mode;
    }

    void drive(GamepadEx gamepad, double angle){
        if(mode == Mode.FIELD) {
            fieldMove(gamepad.getLeftX(), gamepad.getLeftY(), gamepad.getRightX(), angle);
        }else{
            robotMove(gamepad.getLeftX(), gamepad.getLeftY(), gamepad.getRightX());
        }
    }

    void fieldMove(double x, double y, double rx, double angle){
        double deg = -angle;
        double rad = deg * Math.PI/180;
        double temp = y * Math.cos(rad) + x * Math.sin(rad);
        x = -y * Math.sin(rad) + x * Math.cos(rad);
        y = temp;
        robotMove(x, y, rx);
    }
    void robotMove(double x, double y, double rx) {
        double power = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        double leftFrontPower = (y + x + rx) * power;
        double leftRearPower = (y - x + rx) * power;
        double rightFrontPower = (y - x - rx) * power;
        double rightRearPower = (y + x - rx) * power;
        double max = Math.max(1.0, Math.max(leftFrontPower, Math.max(leftRearPower, Math.max(rightFrontPower, rightRearPower))));
        powerMotors(leftFrontPower/max, leftRearPower/max, rightFrontPower/max, rightRearPower/max);
    }
    void powerMotors(double leftFrontPower, double leftRearPower, double rightFrontPower, double rightRearPower){
        leftFront.setPower(leftFrontPower);
        leftRear.setPower(leftRearPower);
        rightFront.setPower(rightFrontPower);
        rightRear.setPower(rightRearPower);
    }
}