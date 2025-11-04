package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.Launcher;
import org.firstinspires.ftc.teamcode.subsystems.Drive;
@Autonomous(name="Decode Auto")
public class Auto extends LinearOpMode {
    Drive drive;
    Launcher launcher;
    @Override
    public void runOpMode() throws InterruptedException {
        drive = new Drive(hardwareMap);
        launcher = new Launcher();
        launcher.init(hardwareMap);
        launcher.startLauncher();

        launcher.updateState();
        waitForStart();
        while (opModeIsActive()) {}
    }
}
