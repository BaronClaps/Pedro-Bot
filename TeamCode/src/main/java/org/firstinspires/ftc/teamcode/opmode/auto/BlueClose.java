package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.config.pedroPathing.follower.Follower;
import org.firstinspires.ftc.teamcode.config.runmodes.Auto;

@Autonomous(name = "Blue Close", group = "Blue")
public class BlueClose extends OpMode {

    Auto auto;

    @Override
    public void init() {
        auto = new Auto(hardwareMap, telemetry, new Follower(hardwareMap), true, true);
        auto.init();
    }

    @Override
    public void init_loop() {
        auto.init_loop();
    }

    @Override
    public void start() {
        auto.start();
    }

    @Override
    public void loop() {
        auto.update();
    }


}
