package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class LED {
    Servo LED;
    public LED(HardwareMap HW) {
        LED = HW.get(Servo.class, "LED");
    }
    public void setLEDcolor(double colorValue) {
        LED.setPosition(colorValue);
    }
}