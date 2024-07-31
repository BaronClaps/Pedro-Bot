package org.firstinspires.ftc.teamcode.config.runmodes;

import static org.firstinspires.ftc.teamcode.config.FieldConstants.*;

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
import org.firstinspires.ftc.teamcode.config.vision.Navigation;

public class Auto {

    enum RobotStart {
        BLUE_CLOSE,
        BLUE_FAR,
        RED_CLOSE,
        RED_FAR
    }

    private Navigation nav;
    private RobotStart startLocation;

    public visionSubsystem vision;
    public servoSubsystem servo;
    public Follower follower;


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

    }

    public void init() {

    }

    public void init_loop() {
        vision.navUpdate(nav);
    }

    public void start() {
        vision.navUpdate(nav);
        createPoses();
        buildPaths();

    }

    public void update() {
        follower.update();
    }

    public void createPoses() {
        switch (startLocation) {
            case BLUE_CLOSE:
                startPose = blueCloseStart;
                parkPose = blueClosePark;

                switch(nav) {
                    case LEFT:
                        purplePose = blueCloseLeftPurple;
                        yellowPose = blueLeftYellow;
                    case CENTER:
                        purplePose = blueCloseCenterPurple;
                        yellowPose = blueCenterYellow;
                    case RIGHT:
                        purplePose = blueCloseRightPurple;
                        yellowPose = blueRightYellow;
                }
                break;

            case BLUE_FAR:
                startPose = blueFarStart;
                parkPose = blueFarPark;

                switch(nav) {
                    case LEFT:
                        purplePose = blueFarLeftPurple;
                        yellowPose = blueLeftYellow;
                    case CENTER:
                        purplePose = blueFarCenterPurple;
                        yellowPose = blueCenterYellow;
                    case RIGHT:
                        purplePose = blueFarRightPurple;
                        yellowPose = blueRightYellow;
                }
                break;

            case RED_CLOSE:
                startPose = redCloseStart;
                parkPose = redClosePark;

                switch(nav) {
                    case LEFT:
                        purplePose = redCloseLeftPurple;
                        yellowPose = redLeftYellow;
                    case CENTER:
                        purplePose = redCloseCenterPurple;
                        yellowPose = redCenterYellow;
                    case RIGHT:
                        purplePose = redCloseRightPurple;
                        yellowPose = redRightYellow;
                }
                break;

            case RED_FAR:
                startPose = redFarStart;
                parkPose = redFarPark;

                switch(nav) {
                    case LEFT:
                        purplePose = redFarLeftPurple;
                        yellowPose = redLeftYellow;
                    case CENTER:
                        purplePose = redFarCenterPurple;
                        yellowPose = redCenterYellow;
                    case RIGHT:
                        purplePose = redFarRightPurple;
                        yellowPose = redRightYellow;
                }
                break;
        }

    }

    public void buildPaths() {
        purple = new Path(new BezierLine(new Point(startPose), new Point(purplePose)));
        purple.setLinearHeadingInterpolation(startPose.getHeading(), purplePose.getHeading());

        if (!isClose) {
            yellow = new PathBuilder()
                    .addPath(new Path(new BezierLine(new Point(purplePose), new Point(yellowPose))))
                    .setConstantHeadingInterpolation(yellowPose.getHeading())
                    .build();
        } else {
            yellow = new PathBuilder()
                    .addPath(new Path(new BezierLine(new Point(purplePose), new Point(yellowPose))))
                    .setConstantHeadingInterpolation(yellowPose.getHeading())
                    .build();
        }

        park = new PathBuilder()
            .addPath(new Path(new BezierCurve(new Point(yellowPose), new Point(yellowPose.getX(), 108, Point.CARTESIAN), new Point(parkPose.getX(), 108, Point.CARTESIAN))))
            .setLinearHeadingInterpolation(yellowPose.getHeading(), parkPose.getHeading())
            .build();
    }



    public void followPath(Path path) {
        follower.followPath(path);
    }

    public void followPath(Path path, boolean holdEnd) {
        follower.followPath(path, holdEnd);
    }

    public void followPath(PathChain pathChain) {
        follower.followPath(pathChain);
    }

    public void followPath(PathChain pathChain, boolean holdEnd) {
        follower.followPath(pathChain, holdEnd);
    }


    public void runAction(Runnable runnable) {
        runnable.run();
    }
}
