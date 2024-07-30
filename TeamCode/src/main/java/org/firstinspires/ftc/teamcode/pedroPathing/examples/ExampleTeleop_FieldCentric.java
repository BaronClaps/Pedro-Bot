package org.firstinspires.ftc.teamcode.pedroPathing.examples;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedroPathing.examples.subsystems.Teleop;
import org.firstinspires.ftc.teamcode.pedroPathing.follower.Follower;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Pose;

/**
 * This is an example teleop that showcases movement and control of three servos and robot-centric driving.
 *
 * @author Baron Henderson - 24122 Tomorrow Team
 * @version 1.1, 7/23/2024
 */

@TeleOp(name = "Example Field-Centric Teleop", group = "Examples")
public class ExampleTeleop_FieldCentric extends OpMode {

    private Teleop teleop;

    @Override
    public void init() {
        teleop = new Teleop(hardwareMap, telemetry, new Follower(hardwareMap), new Pose(0,0,0), true, gamepad1, gamepad2);
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
        teleop.start();
    }

    @Override
    public void loop() {
        teleop.update();
    }

    @Override
    public void stop() {
    }

}