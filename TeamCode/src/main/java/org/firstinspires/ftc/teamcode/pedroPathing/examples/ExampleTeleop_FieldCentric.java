package org.firstinspires.ftc.teamcode.pedroPathing.examples;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.pedroPathing.examples.subsystems.servoSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.follower.Follower;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Pose;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierLine;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierPoint;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.MathFunctions;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Path;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.PathBuilder;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Point;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Vector;

/**
 * This is an example teleop that showcases movement and control of three servos and robot-centric driving.
 *
 * @author Baron Henderson - 24122 Tomorrow Team & 20077 The Indubitables
 * @version 1.1, 7/23/2024
 */

@TeleOp(name = "Example Field-Centric Teleop", group = "Examples")
public class ExampleTeleop_FieldCentric extends OpMode {
    private Follower follower;
    private servoSubsystem servoSubsystem;
    private Vector driveVector;
    private Vector headingVector;
    private final Pose startPose = new Pose(0,0,0);

    Gamepad.RumbleEffect rumbleEffect = new Gamepad.RumbleEffect.Builder()
            .addStep(0.0, 1.0, 500)  //  Rumble right motor 100% for 500 mSec
            .addStep(0.0, 0.0, 300)  //  Pause for 300 mSec
            .addStep(1.0, 0.0, 250)  //  Rumble left motor 100% for 250 mSec
            .addStep(0.0, 0.0, 250)  //  Pause for 250 mSec
            .addStep(1.0, 0.0, 250)  //  Rumble left motor 100% for 250 mSec
            .build();

    Gamepad.LedEffect rgbEffect = new Gamepad.LedEffect.Builder()
            .addStep(1, 0, 0, 250) // Show red for 250ms
            .addStep(0, 1, 0, 250) // Show green for 250ms
            .addStep(0, 0, 1, 250) // Show blue for 250ms
            .addStep(1, 1, 1, 250) // Show white for 250ms
            .build();

    /** This method is call once when init is played, it initializes the follower and subsystems **/
    @Override
    public void init() {
        follower = new Follower(hardwareMap, false);
        follower.setStartingPose(startPose);

        driveVector = new Vector();
        headingVector = new Vector();

        servoSubsystem = new servoSubsystem(hardwareMap);

        servoSubsystem.sStart();
    }

    /** This method is called continuously after Init while waiting to be started. **/
    @Override
    public void init_loop() {
    }

    /** This method is called once at the start of the OpMode. **/
    @Override
    public void start() {
    }

    /** This is the main loop of the opmode and runs continuously after play **/
    @Override
    public void loop() {

        /** Movement Sector **/
        driveVector.setOrthogonalComponents(-gamepad1.left_stick_y, -gamepad1.left_stick_x);
        driveVector.setMagnitude(MathFunctions.clamp(driveVector.getMagnitude(), 0, 1));
        headingVector.setComponents(-gamepad1.right_stick_x, follower.getPose().getHeading());
        follower.setMovementVectors(new Vector(), headingVector, driveVector);
        follower.update();

        if (follower.getPose().getY() >= 5) {
            servoSubsystem.sPos(0.5);
        }

        if (follower.getPose().getY() <= -5) {
            servoSubsystem.sEnd();
        }

        if (follower.getPose().getX() >= 5) {
            servoSubsystem.sPos(0.75);
        }

        if (follower.getPose().getX() <= -5) {
            servoSubsystem.sPos(0.25);
        }

        if (gamepad1.a) {
            follower.setPose(new Pose (follower.getPose().getX(), follower.getPose().getY(), 0));
        }

        if (gamepad1.dpad_down) {
            follower.setPose(new Pose (0, 0, Math.toRadians(180)));
        }

        if (gamepad1.dpad_up) {
            follower.setPose(new Pose (0, 0, 0));
        }

        if (gamepad1.b) {
//            https://gm0.org/en/latest/docs/software/tutorials/gamepad.html?highlight=rumble#gamepad-feedback

//            gamepad1.rumble(0.25, 0.25, 500);
//            gamepad1.runRumbleEffect(rumbleEffect);

//            gamepad1.setLedColor(0.25, 0.25, 0.25, 500);
//            gamepad1.runLedEffect(rgbEffect);
        }



        telemetry.addData("X", follower.getPose().getX());
        telemetry.addData("Y", follower.getPose().getY());
        telemetry.addData("Heading", Math.toDegrees(follower.getPose().getHeading()));
    }


    /** We do not use this because everything automatically should disable **/
    @Override
    public void stop() {
    }

}