package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class TankDrive extends OpMode {
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor arm;
    public Servo claw;


    @Override
    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        arm = hardwareMap.get(DcMotor.class, "arm");
        claw = hardwareMap.get(Servo.class, "claw");

        // Reverse the left motors
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {

        if(gamepad2.a){;
        claw.setPosition(0);
    }
        if(gamepad2.b){
            claw.setPosition(1);
//
        }





        // Tank drive control

        double leftPower = -gamepad1.left_stick_y;
        double rightPower = -gamepad1.right_stick_y;

       // claw control

        // Arm control
        float armPower = -gamepad2.right_stick_y;

        // Apply cubic scaling
        leftPower = leftPower * leftPower * leftPower;
        rightPower = rightPower * rightPower * rightPower;
        armPower = armPower * armPower * armPower;


        // Set power for the drive motors
        frontLeft.setPower(leftPower);
        frontRight.setPower(rightPower);
        backLeft.setPower(leftPower);
        backRight.setPower(rightPower);

        // set actual value for arm and claw
        arm.setPower(armPower);

        }

    }
