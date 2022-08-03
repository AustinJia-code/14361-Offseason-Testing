package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.path.EmptyPathSegmentException;
import com.acmerobotics.roadrunner.trajectory.*;
import com.qualcomm.robotcore.eventloop.opmode.*;
import static org.firstinspires.ftc.teamcode.subsystems.Drivetrain.*;
import static java.lang.Math.*;

import java.util.*;

@Autonomous
public class AutoRedRight extends AutoAbstract {

    public static Pose2d INIT = new Pose2d(0, 0, toRadians(0));

    public void build(){
        drive.setPoseEstimate(INIT);

        Trajectory
                t1 = drive.trajectoryBuilder(new Pose2d(0, 0, 0))
                .strafeLeft(10)
                .build();
        Trajectory
                t2 = drive.trajectoryBuilder(t1.end())
                .forward(10)
                .build();
        Trajectory
                t3 = drive.trajectoryBuilder(t2.end())
                .lineTo(new Vector2d(0, 0))
                .build();

        Trajectory
                t4 = drive.trajectoryBuilder(t3.end())
                .lineToLinearHeading(new Pose2d(20, 20, Math.toRadians(90)))
                .build();
        Trajectory
                t5 = drive.trajectoryBuilder(t4.end())
                .splineTo(new Vector2d(40, 40), Math.toRadians(90))
                .build();
        Trajectory
                t6 = drive.trajectoryBuilder(t5.end())
                .splineToConstantHeading(new Vector2d(20, 20), Math.toRadians(90))
                .build();
        Trajectory
                t7 = drive.trajectoryBuilder(t6.end())
                .splineToLinearHeading(new Pose2d(40, 40, Math.toRadians(0)), Math.toRadians(90))
                .build();
    }

    @Override
    public void setCameraPosition(){
        this.cameraPosition = -1;
    }
    @Override
    public void execute(int TSEPosition){

    }
}
