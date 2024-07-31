package org.firstinspires.ftc.teamcode.config.runmodes;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.config.pedroPathing.pathGeneration.BezierCurve;
import org.firstinspires.ftc.teamcode.config.pedroPathing.pathGeneration.PathChain;
import org.firstinspires.ftc.teamcode.config.subsystem.servoSubsystem;
import org.firstinspires.ftc.teamcode.config.subsystem.visionSubsystem;
import org.firstinspires.ftc.teamcode.config.pedroPathing.follower.Follower;
import org.firstinspires.ftc.teamcode.config.pedroPathing.localization.Pose;
import org.firstinspires.ftc.teamcode.config.pedroPathing.pathGeneration.BezierLine;
import org.firstinspires.ftc.teamcode.config.pedroPathing.pathGeneration.Path;
import org.firstinspires.ftc.teamcode.config.pedroPathing.pathGeneration.PathBuilder;
import org.firstinspires.ftc.teamcode.config.pedroPathing.pathGeneration.Point;

public class Auto {
    enum Navigation {
        LEFT,
        RIGHT,
        CENTER
    }

    enum RobotStart {
        BLUE_CLOSE,
        BLUE_FAR,
        RED_CLOSE,
        RED_FAR
    }

    private Navigation nav;
    private RobotStart startLocation;

    private visionSubsystem vision;
    private servoSubsystem servo;
    private Follower follower;


    private boolean isBlue;
    private boolean isClose;

    public Path purple;
    public PathChain yellow, park;
    private Pose startPose, purplePose, yellowPose, parkPose;

    public Auto(HardwareMap hardwareMap, Telemetry telemetry, Follower follower, boolean isBlue, boolean isClose) {
            servo = new servoSubsystem(hardwareMap);
            vision = new visionSubsystem(hardwareMap, telemetry);

            this.follower = follower;

            this.isBlue = isBlue;
            this.isClose = isClose;

            startLocation = isBlue ? (isClose ? RobotStart.BLUE_CLOSE : RobotStart.BLUE_FAR) : (isClose ? RobotStart.RED_CLOSE : RobotStart.RED_FAR);

            switch (startLocation) {
                case BLUE_CLOSE:
                    startPose = new Pose(8.5, 84, 0);
                    break;
                case BLUE_FAR:
                    startPose = new Pose(8.5, 36, 0);
                    break;
                case RED_CLOSE:
                    startPose = new Pose(144-8.5, 84, 0);
                    break;
                case RED_FAR:
                    startPose = new Pose(144-8.5, 36, 0);
                    break;
            }

    }

    public void init() {

    }

    public void init_loop() {

    }

    public void start() {
        createPoses();
        buildPaths();

    }

    public void update() {
        follower.update();
    }

    public void createPoses() {

    }

    public void buildPaths() {
        purple = new Path(new BezierLine(new Point(startPose), new Point(purplePose)));
        purple.setLinearHeadingInterpolation(startPose.getHeading(), purplePose.getHeading());

        park = new PathBuilder()
            .addPath(new Path(new BezierCurve(new Point(yellowPose), new Point(yellowPose.getX(), 108, Point.CARTESIAN), new Point(parkPose.getX(), 108, Point.CARTESIAN))))
            .setLinearHeadingInterpolation(yellowPose.getHeading(), parkPose.getHeading())
            .build();

        if (isClose) {
            yellow = new PathBuilder()
                .addPath(new Path(new BezierLine(new Point(purplePose), new Point(yellowPose))))
                .setConstantHeadingInterpolation(yellowPose.getHeading())
                .build();

        } else {

        }
    }



    public void followPath(Path path) {
        follower.followPath(path);
    }

    public void runAction(Runnable runnable) {
        runnable.run();
    }
}
