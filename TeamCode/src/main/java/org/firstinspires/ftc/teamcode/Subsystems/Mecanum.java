package org.firstinspires.ftc.teamcode.Subsystems;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.hardware.*;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

class Mecanum {
    private DcMotorEx leftFront, leftRear, rightRear, rightFront;
    private RevIMU imu;
    private Mode mode;

    enum Mode{FIELD, ROBOT}

    Mecanum(HardwareMap hardwareMap) {
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
    void recenter(){imu.reset();}
    void flop(){
        if(mode.equals(Mode.ROBOT)){
            mode = Mode.FIELD;
        }else{
            mode = Mode.ROBOT;
        }
    }

    String getMode(){
        if(mode == Mode.FIELD){
            return "FIELD CENTRIC";
        }else{
            return "ROBOT CENTRIC";
        }
    }

    void drive(GamepadEx gamepad){
        if(mode == Mode.FIELD) {
            float deg = -imu.getHeading();
            float rad = deg * Math.PI/180;
            float temp = y * Math.cos(rad) + x * Math.sin(rad);
            x = -y * Math.sin(rad) + x * Math.cos(rad);
            y = temp;
        }
        float power = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        float leftFrontPower = (y + x + rx) * power;
        float leftRearPower = (y - x + rx) * power;
        float rightFrontPower = (y - x - rx) * power;
        float rightRearPower = (y + x - rx) * power;
        float max = Math.max(1.0, Math.max(leftFrontPower, Math.max(leftRearPower, Math.max(rightFrontPower, rightRearPower))));
        powerMotors(leftFrontPower/max, leftRearPower/max, rightFrontPower/max, rightRearPower/max);
    }

    void powerMotors(float leftFrontPower, float leftRearPower, float rightFrontPower, float rightRearPower){
        leftFront.setPower(leftFrontPower);
        leftRear.setPower(leftRearPower);
        rightFront.setPower(rightFrontPower);
        rightRear.setPower(rightRearPower);
    }
}