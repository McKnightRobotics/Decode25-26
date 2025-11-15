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

        //Recommendations for the Auto
        //(1) Figure out if the feeders are spinning in the right direction
        //(2) Check your resetTimer logic in updateStates
        //(3) See if you can get a 3 ball auto working consistently
        //(4) Add a Drive Capability to the robot, it can be time-based as we will make changes
        // to the robot design after League meet 2


        waitForStart();
        launcher.startLauncher();
        while (opModeIsActive()) {

            launcher.updateState();
        }
    }
}

