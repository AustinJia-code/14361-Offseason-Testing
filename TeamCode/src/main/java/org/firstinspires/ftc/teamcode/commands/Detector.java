package org.firstinspires.ftc.teamcode.commands;

import org.opencv.core.*;
import org.opencv.imgproc.*;
import org.openftc.easyopencv.*;

public class Detector extends OpenCvPipeline {
    private int width, height;
    private int position;
    public Detector(int width, int height){
        this.width = width;
        this.height = height;
    }
    public Mat processFrame(Mat input){
        Mat mat = new Mat();
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);
        if(mat.empty()){ return input; }

        int position = 0;

        return mat;
    }
    public int snapshotAnalysis(){
        return position;
    }
}
