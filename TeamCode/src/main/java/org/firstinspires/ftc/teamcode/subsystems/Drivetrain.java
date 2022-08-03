package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.Subsystem;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.qualcomm.robotcore.hardware.*;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;

public class Drivetrain implements Subsystem {
    public static SampleMecanumDrive drive;
    private DcMotorEx leftFront, leftRear, rightRear, rightFront;
    double y, x, rx, power, leftFrontPower, leftRearPower, rightFrontPower, rightRearPower, max, deg, rad, temp;
    private RevIMU imu;
    private Mode mode;

    enum Mode{FIELD, ROBOT}

    public Drivetrain(HardwareMap hardwareMap) {
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
        rightRear = hardwareMap.get(DcMotorEx.class, "rightRear");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");

        leftRear.setDirection(DcMotorEx.Direction.REVERSE);
        leftFront.setDirection(DcMotorEx.Direction.REVERSE);

        imu = new RevIMU(hardwareMap);
        mode = Mode.FIELD;
    }

    public void setMode(Mode m){
        mode = m;
    }
    public void recenter(){imu.reset();}
    public void switchModes(){
        if(mode.equals(Mode.ROBOT)){
            mode = Mode.FIELD;
        }else{
            mode = Mode.ROBOT;
        }
    }

    public String getMode(){
        if(mode == Mode.FIELD){
            return "FIELD CENTRIC";
        }else{
            return "ROBOT CENTRIC";
        }
    }

    public void drive(GamepadEx gamepad){
        y = Math.pow(gamepad.getLeftY(), 3);
        x = Math.pow(gamepad.getLeftX(), 3);
        rx = Math.pow(gamepad.getRightX(), 3);

        if(mode == Mode.FIELD) {
            deg = -imu.getHeading();
            rad = deg * Math.PI/180;
            temp = y * Math.cos(rad) + x * Math.sin(rad);
            x = -y * Math.sin(rad) + x * Math.cos(rad);
            y = temp;
        }
        power = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        leftFrontPower = (y + x + rx) * power;
        leftRearPower = (y - x + rx) * power;
        rightFrontPower = (y - x - rx) * power;
        rightRearPower = (y + x - rx) * power;
        max = Math.max(1.0, Math.max(leftFrontPower, Math.max(leftRearPower, Math.max(rightFrontPower, rightRearPower))));
        powerMotors(leftFrontPower/max, leftRearPower/max, rightFrontPower/max, rightRearPower/max);
    }

    private void powerMotors(double leftFrontPower, double leftRearPower, double rightFrontPower, double rightRearPower){
        leftFront.setPower(leftFrontPower);
        leftRear.setPower(leftRearPower);
        rightFront.setPower(rightFrontPower);
        rightRear.setPower(rightRearPower);
    }
}