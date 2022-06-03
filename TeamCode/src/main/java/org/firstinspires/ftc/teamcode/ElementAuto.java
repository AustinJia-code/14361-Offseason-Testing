package org.wolfcorp.skystone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

@Autonomous(name="Auto: SkyStone Detector")
public class ElementAuto extends LinearOpMode {
    // Handle hardware stuff...

    int width = 320;
    int height = 240;
    // store as variable here so we can access the location
    ElementDetactor detector = new ElementDetactor(width);
    OpenCvCamera cam;

    @Override
    public void runOpMode() {
        // robot logic...

        // https://github.com/OpenFTC/EasyOpenCV/blob/master/examples/src/main/java/org/openftc/easyopencv/examples/InternalCameraExample.java
        // Initialize the back-facing camera
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        cam = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);
        // Connect to the camera
        cam.openCameraDevice();
        // Use the SkystoneDetector pipeline
        // processFrame() will be called to process the frame
        cam.setPipeline(detector);
        // Remember to change the camera rotation
        cam.startStreaming(width, height, OpenCvCameraRotation.SIDEWAYS_LEFT);

        //...

        ElementDetector.ElementLocation location = detector.getLocation();
        switch(ElementDetector.ElementLocation location)
         case(LEFT){
            break;
         }
         case(MIDDLE){
            break;
         }
         case(RIGHT){
            break;
         }
    }

}