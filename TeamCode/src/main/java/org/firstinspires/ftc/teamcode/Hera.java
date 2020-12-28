package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Hera {
    Telemetry telemetry;
    String HERA_CAPTION = "Hera Status";
    DriveTrain driveTrain;
    Shooter shooter;
    Conveyer conveyer;
    Intake intake;
    LinearOpMode opMode;
    Gamepad gamepad1 = new Gamepad();
    Gamepad gamepad2 = new Gamepad();
    HardwareInnov8Hera hwmap;

    public Hera(Telemetry telemetry, HardwareMap hwmap, LinearOpMode opMode) {
        this.opMode = opMode;
        this.hwmap = new HardwareInnov8Hera(hwmap);
        this.telemetry = telemetry;
        driveTrain = new DriveTrain(this.telemetry, this.hwmap, this.opMode);
        shooter = new Shooter(this.telemetry, this.hwmap, this.opMode);
        conveyer = new Conveyer(this.telemetry, this.hwmap, this.opMode);
        intake = new Intake(this.telemetry, this.hwmap, this.opMode);
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
            shooter.teleopUpdate(gamepad1, gamepad2);
            conveyer.teleopUpdate(gamepad1, gamepad2);
            intake.teleopUpdate(gamepad1, gamepad2);
            this.telemetry.update();
        }
    }
    

    public void forwardTwelveInches() {
        
        driveTrain.goForward(12);
    }

}
