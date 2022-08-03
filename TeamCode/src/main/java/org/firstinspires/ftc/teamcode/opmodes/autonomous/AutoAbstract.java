package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.path.EmptyPathSegmentException;
import com.acmerobotics.roadrunner.trajectory.*;
import com.qualcomm.robotcore.eventloop.opmode.*;

import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Webcam;

import java.util.*;

@Autonomous
public abstract class AutoAbstract extends LinearOpMode {

    int cameraPosition;
    Drivetrain bot;
    Webcam webcam;

    @Override
    public void runOpMode(){
        //init
        telemetry.addLine("Status: Initializing");
        telemetry.update();

        bot = new Drivetrain(hardwareMap);
        webcam = new Webcam(hardwareMap);

        build();

        //init loop
        while(!(isStarted() || isStopRequested())){
            telemetry.addLine("STATUS: INITIALIZED");
            telemetry.update();
        }

        execute(webcam.getAnalysis());

    }

    public abstract void setCameraPosition();
    public abstract void build();
    public abstract void execute(int TSEPosition);
}
