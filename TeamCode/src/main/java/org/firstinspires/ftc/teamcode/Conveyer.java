package org.firstinspires.ftc.teamcode;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Conveyer {

    String CONVEYER_CAPTION = "Conveyer Status";
    Telemetry telemetry;
    HardwareInnov8Hera hera;
    LinearOpMode opMode;


    public Conveyer(Telemetry telemetry, HardwareInnov8Hera hera, LinearOpMode opMode) {

        this.opMode = opMode;
        this.hera = hera;
        this.telemetry = telemetry;
    }

    public void teleopUpdate(Gamepad gamepad1, Gamepad gamepad2) {
        if (Math.abs(gamepad2.left_stick_y)  > .25) {
            hera.conveyerMotor.setPower(gamepad2.left_stick_y);
            showData("CONVEYER_STATUS: ", "Conveyer is running");
        }

        if (gamepad1.y || gamepad2.y) {
            if (hera.conveyerMotor.getPower() > .25) {
                hera.conveyerMotor.setPower(0);
            }
            else {
                hera.conveyerMotor.setPower(1);
            }
        }

    }

    public void showData(String caption, String value) {
        this.telemetry.addData(caption, value);
        this.telemetry.update();
        Log.d(caption, value);
    }
}