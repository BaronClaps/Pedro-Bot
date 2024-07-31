package org.firstinspires.ftc.teamcode.config.subsystem;
import static org.firstinspires.ftc.teamcode.config.RobotConstants.*;

import com.kuriosityrobotics.shuttle.hardware.ServoControl;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;



public class servoSubsystem {

    private Servo s;

    public servoSubsystem(HardwareMap hardwareMap) {
        s = hardwareMap.get(Servo.class, "s");
    }

    public void sPos(double pos) {
        s.setPosition(pos);
    }

    public void init() {
        s.setPosition(sInit);
    }

    public void start() {
        s.setPosition(sStart);
    }

}
