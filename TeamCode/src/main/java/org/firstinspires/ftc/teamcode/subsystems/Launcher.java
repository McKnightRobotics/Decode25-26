package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Launcher {
    private final double FEED_TIME_SECONDS = 0.20;
    private final double STOP_SPEED = 0.0;
    private final double FULL_SPEED = 1.0;
    private final double LAUNCHER_TARGET_VELOCITY = 1125;
    private final double LAUNCHER_MIN_VELOCITY = 1075;

    private DcMotorEx launcher;

    private CRServo leftFeeder, rightFeeder;

    ElapsedTime feederTimer = new ElapsedTime();

    private enum LaunchState {
        IDLE,
        SPIN_UP,
        LAUNCH,
        LAUNCHING,
    }

    private LaunchState launchState;

    public void init(HardwareMap hwMap) {
        launcher = hwMap.get(DcMotorEx.class, "launcher");
        leftFeeder = hwMap.get(CRServo.class, "left_feeder");
        rightFeeder = hwMap.get(CRServo.class, "right_feeder");

        launcher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        launcher.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        launcher.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(
                300, 0, 0, 10));

        leftFeeder.setDirection(DcMotorSimple.Direction.REVERSE);

    }
}
