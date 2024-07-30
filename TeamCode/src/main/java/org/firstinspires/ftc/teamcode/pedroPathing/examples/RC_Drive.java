package org.firstinspires.ftc.teamcode.pedroPathing.examples;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedroPathing.follower.Follower;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Pose;

/**
 * @author Baron Henderson - 24122 Tomorrow Team
 * @version 1.1, 7/23/2024
 */

@TeleOp(name = "Robot-Centric", group = "Examples")
public class RC_Drive extends OpMode {

    private Teleop teleop;

    @Override
    public void init() {
        teleop = new Teleop(hardwareMap, telemetry, new Follower(hardwareMap), new Pose(0,0,0), false, gamepad1, gamepad2);
    }

    @Override
    public void start() {
        teleop.start();
    }

    @Override
    public void loop() {
        teleop.update();
    }

}