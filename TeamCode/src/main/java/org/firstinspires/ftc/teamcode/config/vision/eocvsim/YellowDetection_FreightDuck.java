package org.firstinspires.ftc.teamcode.config.vision.eocvsim;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

public class YellowDetection_FreightDuck extends OpenCvPipeline {

    /*
     * These are our variables that will be
     * modifiable from the variable tuner.
     */
    public Scalar lower_yellow = new Scalar(20, 50, 50); // Adjusted lower bound (potentially include more yellow shades)
    public Scalar upper_yellow = new Scalar(40, 255, 255);

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

    public YellowDetection_FreightDuck(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    @Override
    public Mat processFrame(Mat input) {
        // Clear contours before processing new frame
        contours.clear();
        x = 0;
        y = 0;

        Imgproc.cvtColor(input, hsvMat, Imgproc.COLOR_RGB2HSV);

        // Optional noise reduction (adjust kernel size as needed)
        Imgproc.GaussianBlur(hsvMat, hsvMat, new Size(3, 3), 0);

        // Create yellow mask
        Core.inRange(hsvMat, lower_yellow, upper_yellow, binaryMat);

        // Create gray mask
        Core.inRange(hsvMat, lower_gray, upper_gray, grayMask);

        // Invert gray mask
        Core.bitwise_not(grayMask, invertedGrayMask);

        // Combine inverted gray mask with yellow mask
        Core.bitwise_and(binaryMat, invertedGrayMask, binaryMat);

        // Apply mask to original image (optional for edge detection)
        Core.bitwise_and(input, input, maskedInputMat, binaryMat);

        // Find edges (Canny edge detection)
        Imgproc.Canny(maskedInputMat, edges, 75, 200); // Adjust thresholds as needed

        // Find contours
        Imgproc.findContours(edges, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        // Find the largest contour
        int largestContourIndex = -1;
        double maxArea = 0;
        for (int i = 0; i < contours.size(); i++) {
            double area = Imgproc.contourArea(contours.get(i));
            if (area > maxArea) {
                maxArea = area;
                largestContourIndex = i;
            }
        }

        // Draw bounding rectangle around the largest contour
        if (largestContourIndex != -1) {
            Rect rect = Imgproc.boundingRect(contours.get(largestContourIndex));
            Imgproc.rectangle(maskedInputMat, rect, new Scalar(0, 255, 0), 2);

            // Print x and y coordinates of the center of the rectangle
            x = rect.x + rect.width / 2;
            y = rect.y + rect.height / 2;



        }

        telemetry.addData("Center coordinates:", x + ", " + y);




        return maskedInputMat;
    }
}
