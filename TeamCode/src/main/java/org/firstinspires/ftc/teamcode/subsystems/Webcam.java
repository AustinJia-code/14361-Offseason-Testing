package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.*;
import com.qualcomm.robotcore.hardware.*;

import org.firstinspires.ftc.robotcore.external.hardware.camera.*;
import org.firstinspires.ftc.teamcode.commands.*;
import org.openftc.easyopencv.*;

public class Webcam implements Subsystem {
    private int width = 640, height = 360;
    public static double borderLeftX    = 0.0;   //fraction of pixels from the left side of the cam to skip
    public static double borderRightX   = 0.0;   //fraction of pixels from the right of the cam to skip
    public static double borderTopY     = 0.0;   //fraction of pixels from the top of the cam to skip
    public static double borderBottomY  = 0.0;   //fraction of pixels from the bottom of the cam to skip
    OpenCvWebcam webcam;
    Detector pipeline;

    public Webcam(HardwareMap hardwareMap) {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

        pipeline = new Detector(borderLeftX, borderRightX, borderTopY, borderBottomY);
        webcam.setPipeline(pipeline);

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                webcam.startStreaming(width, height, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {}
        });
        FtcDashboard dashboard = FtcDashboard.getInstance();
        FtcDashboard.getInstance().startCameraStream(webcam, 10);


    }

    public int getAnalysis(){
        return pipeline.snapshotAnalysis();
    }
}
