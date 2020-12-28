package org.firstinspires.ftc.teamcode;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake {

    String INTAKE_CAPTION = "Intake Status";
    Telemetry telemetry;
    HardwareInnov8Hera hera;
    LinearOpMode opMode;


    public Intake(Telemetry telemetry, HardwareInnov8Hera hera, LinearOpMode opMode) {

        this.opMode = opMode;
        this.hera = hera;
        this.telemetry = telemetry;
    }

    public void showData(String caption, String value) {
        this.telemetry.addData(caption, value);
        this.telemetry.update();
        Log.d(caption, value);
    }

    public void teleopUpdate(Gamepad gamepad1, Gamepad gamepad2) {
        if (Math.abs(gamepad2.right_stick_y) > .25) {
            hera.intakeMotor.setPower(-gamepad2.right_stick_y);
            showData("INTAKE_STATUS: ", "Intake is running");
        }

        if (gamepad1.b || gamepad2.b) {
            if (hera.intakeMotor.getPower() > .25) {
                hera.intakeMotor.setPower(0);
            }
            else {
                hera.intakeMotor.setPower(1);
            }
        }
    }
}