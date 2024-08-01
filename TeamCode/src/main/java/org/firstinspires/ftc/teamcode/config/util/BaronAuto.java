package org.firstinspires.ftc.teamcode.config.util;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public abstract class BaronAuto extends OpMode {
    public ActionState actionState;
    public static int pathState;

    @Override
    public void init() {
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
    }

    @Override
    public void loop() {
    }

    @Override
    public void stop() {
    }


    abstract public void pathUpdate();
    abstract public void actionUpdate();

    abstract public void setPathState(int x);
    abstract public void setActionState(String state);
}
