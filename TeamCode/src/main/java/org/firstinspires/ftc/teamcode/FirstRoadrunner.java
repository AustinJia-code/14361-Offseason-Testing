package org.firstinspires.ftc.teamcode;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.path.EmptyPathSegmentException;
import com.acmerobotics.roadrunner.trajectory.*;
import com.qualcomm.robotcore.eventloop.opmode.*;
import org.firstinspires.ftc.teamcode.drive.*;
import java.util.*;

@Autonomous
public class FirstRoadrunner extends LinearOpMode {
    //public SampleMecanumDrive drive;
    Queue<Trajectory> trajectoryQueue;

    @Override
    public void runOpMode() throws InterruptedException{
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        trajectoryQueue = new LinkedList<>();

        try {
            Trajectory t1 = drive.trajectoryBuilder(new Pose2d(0, 0, 0))
                    .strafeLeft(10)
                    .build();
            Trajectory t2 = drive.trajectoryBuilder(t1.end())
                    .forward(10)
                    .build();

            Trajectory t3 = drive.trajectoryBuilder(t2.end())
                    .lineTo(new Vector2d(0, 0))
                    .build();

            Trajectory t4 = drive.trajectoryBuilder(t3.end())
                    .lineToLinearHeading(new Pose2d(20, 20, Math.toRadians(90)))
                    .build();
            Trajectory t5 = drive.trajectoryBuilder(t4.end())
                    .splineTo(new Vector2d(40, 40), Math.toRadians(90))
                    .build();
            Trajectory t6 = drive.trajectoryBuilder(t5.end())
                    .splineToConstantHeading(new Vector2d(20, 20), Math.toRadians(90))
                    .build();
            Trajectory t7 = drive.trajectoryBuilder(t6.end())
                    .splineToLinearHeading(new Pose2d(40, 40, Math.toRadians(0)), Math.toRadians(90))
                    .build();

            trajectoryQueue.add(t1);
            trajectoryQueue.add(t2);
            trajectoryQueue.add(t3);
            trajectoryQueue.add(t4);
            trajectoryQueue.add(t5);
            trajectoryQueue.add(t6);
            trajectoryQueue.add(t7);
        } catch(Exception EmptyPathSegmentException){
        //TEST 3
        }
        waitForStart();

        while(!trajectoryQueue.isEmpty()) {
            drive.followTrajectory(trajectoryQueue.poll());
            sleep(1000);
        }
    }
}
