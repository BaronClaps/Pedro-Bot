package org.firstinspires.ftc.teamcode.config.vision.eocvsim;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

public class ColorPicker extends OpenCvPipeline {

    /*
     * These are our variables that will be
     * modifiable from the variable tuner.
     */
    public Scalar lower_white = new Scalar(29.5, 81, 84); // Adjusted lower bound (potentially include more yellow shades)
    public Scalar upper_white = new Scalar(29.5, 255, 255);

    public Scalar lower_gray = new Scalar(0, 0, 50); // Adjust based on your gray floor color
    public Scalar upper_gray = new Scalar(180, 255, 200); // Adjust based on your gray floor color

    private Mat hsvMat = new Mat();
    private Mat binaryMat = new Mat();
    private Mat grayMask = new Mat();
    private Mat invertedGrayMask = new Mat();
    private Mat maskedInputMat = new Mat();
    private Mat edges = new Mat();
    private int x;
    private int y;

    // New variables for contours
    private List<MatOfPoint> contours = new ArrayList<>();
    private Scalar contourColor = new Scalar(0, 255, 0); // Green for contours

    private Telemetry telemetry = null;

    public ColorPicker(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    @Override
    public Mat processFrame(Mat input) {
        return null;
    }
}
