package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.GamepadEvents;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.LED;
import org.firstinspires.ftc.teamcode.subsystems.Launcher;

@TeleOp(name="TestTeleOP")
public class TestTeleOP extends LinearOpMode {

    //init
   Drive drive;
   Launcher launcher;
   GamepadEvents controls;
   LED led;
   Intake intake;
    @Override
    public void runOpMode() throws InterruptedException {
        drive = new Drive(hardwareMap);
        launcher = new Launcher();
        launcher.init(hardwareMap);
        controls = new GamepadEvents(gamepad1);
        intake = new Intake(hardwareMap);
        led = new LED(hardwareMap);
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
            if (controls.a.onPress()) {
                launcher.startLauncherMotor(1);
                launcher.startFeeder(1);
            }
            if (controls.b.onPress()) {
                intake.toggleIntake();
            }
            if (controls.y.onPress()) {
                intake.changePower();
            }
            led.setLEDcolor(controls.left_trigger.getTriggerValue());

            //launcher.updateState();
            controls.update();
            telemetry.addData("isIntakeOn set to", intake.getIsIntakeOn());
            telemetry.addData("Power is now set to: ", intake.getPower());
            telemetry.addData("LED should be: ", controls.left_trigger.getTriggerValue());
            telemetry.update();
        }
       // fiji water
    }
}
