package org.firstinspires.ftc.teamcode.commands;

import org.opencv.core.*;
import org.opencv.imgproc.*;
import org.openftc.easyopencv.*;

public class Detector extends OpenCvPipeline {
    private int width, height;
    private double borderLeftX;     //fraction of pixels from the left side of the cam to skip
    private double borderRightX;    //fraction of pixels from the right of the cam to skip
    private double borderTopY;      //fraction of pixels from the top of the cam to skip
    private double borderBottomY;   //fraction of pixels from the bottom of the cam to skip

    private int TSEPosition;

    public Detector(double borderLeftX, double borderRightX, double borderTopY, double borderBottomY){
        this.borderLeftX = borderLeftX;
        this.borderRightX = borderRightX;
        this.borderTopY = borderTopY;
        this.borderBottomY = borderBottomY;
    }
    public Mat processFrame(Mat input){
        width = input.width();
        height = input.height();
        try {
            //process image
            //position = ?
        } catch (Exception e) {
        }
        return input;
    }

    public int snapshotAnalysis(){
        return TSEPosition;
    }
}
