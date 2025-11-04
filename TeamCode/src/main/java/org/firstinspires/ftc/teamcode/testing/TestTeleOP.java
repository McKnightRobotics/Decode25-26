package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.GamepadEvents;
import org.firstinspires.ftc.teamcode.subsystems.Launcher;

@TeleOp(name="TestTeleOP")
public class TestTeleOP extends LinearOpMode {

    //init
   Drive drive;
   Launcher launcher;
   GamepadEvents controls;
    @Override
    public void runOpMode() throws InterruptedException {
        drive = new Drive(hardwareMap);
        launcher = new Launcher();
        launcher.init(hardwareMap);
        controls = new GamepadEvents(gamepad1);
        double forward;
        double strafe;
        double rotate;
        waitForStart();
        while (opModeIsActive()) {
            forward = controls.left_stick_y;
            strafe = controls.left_stick_x;
            rotate = controls.right_stick_x;
            drive.drive(forward, rotate, strafe);


            // launcher button
            if (gamepad1.a) {
                launcher.startLauncher();// comment
            }


            launcher.updateState();
            controls.update();
        }
    }
}
