package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.*;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

class MecanumBot {
    private DcMotor leftFront, leftRear, rightRear, rightFront;
    private double x, y, rx;

    MecanumBot(HardwareMap hardwareMap) {
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
        rightRear = hardwareMap.get(DcMotorEx.class, "rightRear");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
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