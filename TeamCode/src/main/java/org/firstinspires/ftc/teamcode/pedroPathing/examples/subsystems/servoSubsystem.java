package org.firstinspires.ftc.teamcode.pedroPathing.examples.subsystems;
import static org.firstinspires.ftc.teamcode.pedroPathing.examples.RobotConstants.*;

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
    public void sStart() {
        s.setPosition(sStart);
    }
    public void sEnd() {
        s.setPosition(sEnd);
    }


}
