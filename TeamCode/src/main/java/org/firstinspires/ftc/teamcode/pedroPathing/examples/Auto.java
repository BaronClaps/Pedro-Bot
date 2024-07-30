package org.firstinspires.ftc.teamcode.pedroPathing.examples;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.pedroPathing.examples.subsystems.servoSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.examples.subsystems.visionSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.follower.Follower;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Pose;

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
    private RobotStart start;

    private visionSubsystem vision;
    private servoSubsystem servo;
    private Follower follower;
    private Pose startPose;

    private boolean isBlue;
    private boolean isClose;

    public Auto(HardwareMap hardwareMap, Telemetry telemetry, Follower follower, boolean isBlue, boolean isClose) {
            servo = new servoSubsystem(hardwareMap);
            vision = new visionSubsystem(hardwareMap, telemetry);

            this.follower = follower;

            this.isBlue = isBlue;
            this.isClose = isClose;

            start = isBlue ? (isClose ? RobotStart.BLUE_CLOSE : RobotStart.BLUE_FAR) : (isClose ? RobotStart.RED_CLOSE : RobotStart.RED_FAR);

            switch (start) {
                case BLUE_CLOSE:
                    startPose = new Pose(0, 0, 0);
                    break;
                case BLUE_FAR:
                    startPose = new Pose(0, 0, 0);
                    break;
                case RED_CLOSE:
                    startPose = new Pose(0, 0, 0);
                    break;
                case RED_FAR:
                    startPose = new Pose(0, 0, 0);
                    break;
            }





    }
}
