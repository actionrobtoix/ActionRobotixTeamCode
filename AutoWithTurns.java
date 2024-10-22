package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "auto1", group = "tutorial")

public abstract class AutoWithTurns extends LinearOpMode{
    //initialize variables here
    DcMotor leftWheel = hardwareMap.get(DcMotor.class,"left");
    DcMotor rightWheel = hardwareMap.get(DcMotor.class,"right");

    public void runOpMode() {
        waitForStart();

        turnRight(.3, 500);

        turnRight(-.5, 300);

    }

    public void turnRight(double power, long millis){
        leftWheel.setPower(power);
        rightWheel.setPower(-power);

        sleep(millis);

        leftWheel.setPower(0);
        rightWheel.setPower(0);
    }
}