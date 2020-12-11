package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Shooter {

    String SHOOTER_TRAIN_CAPTION = "Shooter Status";
    Telemetry telemetry;
    HardwareInnov8Hera hera;
    LinearOpMode opMode;

    public Shooter(Telemetry telemetry, HardwareInnov8Hera hera, LinearOpMode opMode) {

        this.opMode = opMode;
        this.hera = hera;
        this.telemetry = telemetry;
    }

    public void teleopUpdate(Gamepad gamepad1, Gamepad gamepad2) {
        long time = 0;
        long endTime = 0;
        if (gamepad2.y) {
            hera.shooterMotor.setPower(1);
        }

        if (gamepad2.a) {
            hera.shooterMotor.setPower(0);
        }

        if (Math.abs(gamepad2.left_stick_y) > .25) {
            hera.shooterMotor.setPower(gamepad2.left_stick_y);
        }

        if (hera.ringTouchSensor.isPressed()) {
            hera.ringPusher.setPosition(0);
            time = System.currentTimeMillis();
            endTime = time + 2000;
        }

        if (System.currentTimeMillis() >= endTime) {
            hera.ringPusher.setPosition(1);
        }
    }

    public void shoot() {


    }

}
