package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.Launcher;
import org.firstinspires.ftc.teamcode.subsystems.Drive;
@Autonomous(name="Decode Auto")
public class Auto extends LinearOpMode {
    Drive drive;
    Launcher launcher;
    ElapsedTime time;
    @Override
    public void runOpMode() throws InterruptedException {
        drive = new Drive(hardwareMap);
        launcher = new Launcher();
        launcher.init(hardwareMap);
        time = new ElapsedTime();

        //Recommendations for the Auto
        //(1) Figure out if the feeders are spinning in the right direction
        //(2) Check your resetTimer logic in updateStates
        //(3) See if you can get a 3 ball auto working consistently
        //(4) Add a Drive Capability to the robot, it can be time-based as we will make changes
        // to the robot design after League meet 2

        waitForStart();
        time.reset();
        //launcher.startLauncher();

        while (time.seconds() < 3) {
            launcher.startLauncherMotor(0.2);
        }
        time.reset();
        while (opModeIsActive()) {

            if (time.seconds() < 3) {
                launcher.startLauncherMotor(0.2);
                launcher.startFeeder(1);
                telemetry.addData("velocity:", launcher.getPower());
                telemetry.update();
            }
            else {
                launcher.startLauncherMotor(0);
                launcher.startFeeder(0);
            }
            telemetry.addData("velocity:", launcher.getPower());
            telemetry.update();
        }
    }
}

