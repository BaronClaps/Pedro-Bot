package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.config.pedroPathing.follower.Follower;
import org.firstinspires.ftc.teamcode.config.runmodes.Auto;
import org.firstinspires.ftc.teamcode.config.util.BaronAuto;

@Autonomous(name = "Blue Close", group = "Blue")
public class BlueClose extends BaronAuto {

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
        pathUpdate();
    }

    @Override
    public void pathUpdate() {
        switch (pathState) {
            case 0:
                auto.actionStorage.preparePurple.runAction();
                auto.followPath(auto.purple);
                if (auto.pathNotBusy()) {
                    auto.actionStorage.scorePurple.runAction();
                }
                setPathState(1);
                break;

            case 1:
                auto.actionStorage.prepareYellow.runAction();
                auto.followPath(auto.yellow);
                if (auto.pathNotBusy()) {
                    auto.actionStorage.scoreYellow.runAction();
                }
                setPathState(2);
                break;

            case 2:
                auto.actionStorage.preparePark.runAction();
                auto.followPath(auto.park);
                if (auto.pathNotBusy()) {
                    auto.actionStorage.finishPark.runAction();
                }
                setPathState(-1);
                break;
        }
    }

    @Override
    public void setPathState(int x) {
        pathState = x;
    }
}


