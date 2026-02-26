package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.Drive;

@Autonomous(name="use this one")
public class AutoNew extends LinearOpMode {

    Drive drive;
    ElapsedTime time;

    @Override
    public void runOpMode() throws InterruptedException {
        drive = new Drive(hardwareMap);
        time = new ElapsedTime();

        waitForStart();
        time.reset();
        while (time.seconds() < 1) {
            drive.drive(0.5,0,0);
        }
        drive.drive(0,0,0);
        while (opModeIsActive()) {}

    }
}
