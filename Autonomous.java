package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import static java.lang.Thread.sleep;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous (name = "name")
public class Autonomous extends LinearOpMode{

    protected DcMotor frontLeft;
    protected DcMotor frontRight;

    @Override
    public void runOpMode() throws InterruptedException{
        frontLeft = hardwareMap.get(DcMotor.class, "left");
        frontRight = hardwareMap.get(DcMotor.class, "right");


        frontLeft.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        moveForeward(1, 1000);
    }

    public void waitForStart() {
    }

    public void moveForeward(int power, int time) throws InterruptedException {
        frontLeft.setPower(power);
        frontRight.setPower(power);

        sleep(1000);
        frontLeft.setPower(0);
        frontRight.setPower(0);

    }
}
