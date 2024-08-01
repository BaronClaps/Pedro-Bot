package org.firstinspires.ftc.teamcode.config.util;

public class Action {

    private final Runnable runnable;

    public Action(Runnable runnable) {
        this.runnable = runnable;}

    public void runAction() {
        runnable.run();
    }
}
