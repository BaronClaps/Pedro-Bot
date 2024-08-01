package org.firstinspires.ftc.teamcode.config.util;

public class ActionStorage {
    public Action preparePurple = new Action(this::preparePurple);
    public Action scorePurple = new Action(this::scorePurple);
    public Action prepareYellow = new Action(this::prepareYellow);
    public Action scoreYellow = new Action(this::scoreYellow);
    public Action preparePark = new Action(this::preparePark);
    public Action finishPark = new Action(this::finishPark);

    public void preparePurple() {}
    public void scorePurple() {}
    public void prepareYellow() {}
    public void scoreYellow() {}
    public void preparePark() {}
    public void finishPark() {}
}
