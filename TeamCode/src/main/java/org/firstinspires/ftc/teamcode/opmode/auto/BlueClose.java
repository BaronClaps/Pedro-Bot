package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.config.pedroPathing.follower.Follower;
import org.firstinspires.ftc.teamcode.config.runmodes.Auto;
import org.firstinspires.ftc.teamcode.config.util.BaronAuto;
import org.firstinspires.ftc.teamcode.config.util.ActionState;

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
        actionUpdate();
    }

    @Override
    public void stop() {
    }

    @Override
    public void pathUpdate() {
        switch (pathState) {
            case 0:
                setActionState("preparePurple");
                auto.followPath(auto.purple);
                if (!auto.isBusy()) {
                    setActionState("scorePurple");
                }
                setPathState(1);
                break;

            case 1:
                setActionState("prepareYellow");
                auto.followPath(auto.yellow);
                if (!auto.isBusy()) {
                    setActionState("scoreYellow");
                }
                setPathState(2);
                break;

            case 2:
                setActionState("preparePark");
                auto.followPath(auto.park);
                if (!auto.isBusy()) {
                    setActionState("finishPark");
                }
                setPathState(-1);
                break;
        }
    }

    @Override
    public void actionUpdate() {
        switch (actionState) {
            case preparePurple:
                auto.actionStorage.preparePurple.runAction();
                setActionState("placeHolder");
                break;
            case scorePurple:
                auto.actionStorage.scorePurple.runAction();
                setActionState("placeHolder");
                break;
            case prepareYellow:
                auto.actionStorage.prepareYellow.runAction();
                setActionState("placeHolder");
                break;
            case scoreYellow:
                auto.actionStorage.scoreYellow.runAction();
                setActionState("placeHolder");
                break;
            case preparePark:
                auto.actionStorage.preparePark.runAction();
                setActionState("placeHolder");
                break;
            case finishPark:
                auto.actionStorage.finishPark.runAction();
                setActionState("placeHolder");
                break;
        }

    }

    @Override
    public void setPathState(int x) {
        pathState = x;
    }

    @Override
    public void setActionState(String state) {
        try {
            actionState = ActionState.valueOf(state);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Invalid State. Input: " + state);
        }
    }


}


