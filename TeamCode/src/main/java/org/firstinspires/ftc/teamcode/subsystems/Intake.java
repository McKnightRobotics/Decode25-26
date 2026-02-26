package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;

//intake motor
public class Intake {
    DcMotorEx intake;
    boolean isIntakeOn = false;
    double power = 1;
    public Intake(HardwareMap HW) {
        intake = HW.get(DcMotorEx.class, "intake");
        intake.setDirection(DcMotorSimple.Direction.REVERSE);

    }

   public void intakeOn() {
        intake.setPower(power);
   }
   public void intakeOff() {
        intake.setPower(0);
   }
   public void toggleIntake() {
        if (!isIntakeOn) {
            intakeOn();
        }
        else {
            intakeOff();
        }
        isIntakeOn = !isIntakeOn;
   }
   public boolean getIsIntakeOn()  {
        return isIntakeOn;
   }
   public double getPower()
   {
        return power;
   }
   public void changePower() {
        //Default power is 1, this is if the battery is too low for full power usage.
       if (power > 0.5) {
           power -= 0.1;
       }
       else {
           power = 1;
       }
   }
}
