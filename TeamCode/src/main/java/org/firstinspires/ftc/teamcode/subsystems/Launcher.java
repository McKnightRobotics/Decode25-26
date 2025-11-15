package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.R;

public class Launcher {
    //TODO: I think the Issue with the seconds of the Feed_Time
    private final double FEED_TIME_SECONDS = 0.20;
    private final double STOP_SPEED = 0.0;
    private final double FULL_SPEED = 0.65;
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
        launcher.setDirection(DcMotorSimple.Direction.REVERSE);

        launcher.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(
                300, 0, 0, 10));

        //TODO: Priority for Work Day: Check which feeder is going is the wrong direction
        leftFeeder.setDirection(CRServo.Direction.FORWARD);
        rightFeeder.setDirection(CRServo.Direction.REVERSE);

        launchState = LaunchState.IDLE;
        stopFeeder();
        stopLauncher();


    }


    public void stopFeeder() {
        leftFeeder.setPower(STOP_SPEED);
        rightFeeder.setPower(STOP_SPEED);
    }

    public void updateState() {
        switch (launchState) {
            case IDLE:
                break;
            case SPIN_UP:
                launcher.setVelocity(LAUNCHER_TARGET_VELOCITY);
                if (launcher.getVelocity() >= LAUNCHER_MIN_VELOCITY) {
                    launchState = LaunchState.LAUNCH;
                }
                break;
            case LAUNCH:
                leftFeeder.setPower(FULL_SPEED);
                rightFeeder.setPower(FULL_SPEED);
                feederTimer.reset();
                if (feederTimer.seconds() >= 5) {
                    launchState = LaunchState.LAUNCHING;
                }

                break;
            case LAUNCHING:
                if (feederTimer.seconds() > FEED_TIME_SECONDS) {
                    stopFeeder();
                    stopLauncher();
                    launchState = LaunchState.IDLE;
                }
                break;
        }
    }

    //Notice how this method just changes the state of the enum
    //You can create a new method to power the launcher motor directly
    public void startLauncher() {
        if (launchState == LaunchState.IDLE) {
            launchState = LaunchState.SPIN_UP;
        }
    }


    public void stopLauncher() {
        stopFeeder();
        launcher.setVelocity(STOP_SPEED);
        launchState = LaunchState.IDLE;
    }

    public String getState() {
        return launchState.toString();
    }

    public double getVelocity() {
        return launcher.getVelocity();
    }

    public void startFeeder(double power) {
        rightFeeder.setPower(power);
        leftFeeder.setPower(power);
    }
}
