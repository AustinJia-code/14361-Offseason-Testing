package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.*;
import com.qualcomm.robotcore.hardware.*;

import org.firstinspires.ftc.robotcore.external.hardware.camera.*;
import org.firstinspires.ftc.teamcode.commands.*;
import org.openftc.easyopencv.*;

public class Webcam implements Subsystem {
    private int width = 320, height = 240;
    private int snapshotAnalysis;
    OpenCvWebcam webcam;
    Detector pipeline;

    public Webcam(HardwareMap hardwareMap) {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

        pipeline = new Detector(width, height);
        webcam.setPipeline(pipeline);

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                webcam.startStreaming(width, height, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {}
        });

        snapshotAnalysis = pipeline.snapshotAnalysis();

    }

    public int getAnalysis(){
        return pipeline.snapshotAnalysis();
    }
}
