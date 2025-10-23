package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drive {
    DcMotorEx frontLeft;
    DcMotorEx backLeft;
    DcMotorEx frontRight;
    DcMotorEx backRight;

    public Drive(HardwareMap HW) {
        frontLeft = HW.get(DcMotorEx.class,"frontLeft");
        backLeft = HW.get(DcMotorEx.class,"backLeft");
        frontRight =HW.get(DcMotorEx.class,"frontRight");
        backRight = HW.get(DcMotorEx.class,"backRight");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);


        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void drive(double forward, double rotate, double strafe) {

        double largest = Math.max(Math.abs(forward), Math.abs(rotate));
        if (largest > 1.0) {
            forward /= largest;
            rotate /= largest;
        }

        frontLeft.setPower(forward + rotate - strafe);
        frontRight.setPower(forward - rotate - strafe);
        backLeft.setPower(forward + rotate + strafe);
        backRight.setPower(forward - rotate + strafe);
    }
}// I love spaces
