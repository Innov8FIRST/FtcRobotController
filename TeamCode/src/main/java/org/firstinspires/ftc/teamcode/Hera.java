package org.firstinspires.ftc.teamcode;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class Hera {
    Telemetry telemetry;
    String HERA_CAPTION = "Hera Status";
    DriveTrain driveTrain;
    OpenCV openCV;
    LinearOpMode opMode;
    Gamepad gamepad1 = new Gamepad();
    Gamepad gamepad2 = new Gamepad();
    HardwareInnov8Hera hwmap;

    public Hera(Telemetry telemetry, HardwareMap hwmap, LinearOpMode opMode) {
        this.opMode = opMode;
        this.hwmap = new HardwareInnov8Hera(hwmap, opMode);
        this.telemetry = telemetry;
        driveTrain = new DriveTrain(this.telemetry, this.hwmap, this.opMode);
        openCV = new OpenCV(this.telemetry, this.hwmap, this.opMode);
        this.telemetry.addData(HERA_CAPTION, "ready to go");
        this.telemetry.update();
    }

    public void stop() {
        this.telemetry.addData(HERA_CAPTION, "free");

        this.telemetry.update();
    }

    public void teleop(Gamepad gamepad1, Gamepad gamepad2) {

        while (this.opMode.opModeIsActive()) {
            this.telemetry.addData(HERA_CAPTION, "teleop-ing");
            driveTrain.teleopUpdate(gamepad1, gamepad2);
            this.telemetry.update();
        }
    }
    

    public void forwardTurn() {
        while(true) {
            driveTrain.goForward(1000000);
        }
//        driveTrain.turn(-90);
//        driveTrain.goForward(30);
    }

    public void ringSense() {
        while (true) {
            if (openCV.getRingNumber() == RingPosition.ONE) {
                // Drive to 2nd square
                showData("Ring Position", "One");
            } else if (openCV.getRingNumber() == RingPosition.FOUR) {
                // Drive to 3rd square
                showData("Ring Position", "Four");
            } else {
                // Drive to 1st square
                showData("Ring Position", "None");
            }
        }
    }

    public void showData(String caption, String value) {
        this.telemetry.addData(caption, value);
        this.telemetry.update();
        Log.d(caption, value);
    }

}
