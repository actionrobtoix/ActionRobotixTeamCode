
package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class TankDrive extends OpMode {

    // Motors
    DcMotor frontLeft, frontRight, backLeft, backRight, horizontalSlide1, horizontalSlide2, verticalSlide1,verticalSlide2;
    Servo  flip1, flip2, claw, flipClaw, arm;
    CRServo intake;



    // Variables
    double armDivisor = 3;
    double driveDivisor = 1;
    int position;
    final double TPR = 537.7;
    double servoPos = 0; // Servo starting pos *CHANGE LATER

    @Override
    public void init() {
        // Hardware map
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        horizontalSlide1 = hardwareMap.get(DcMotor.class, "hSlide1");
        horizontalSlide2 = hardwareMap.get(DcMotor.class, "hSlide2");
        verticalSlide1 = hardwareMap.get(DcMotor.class, "vSlide1");
        verticalSlide2 = hardwareMap.get(DcMotor.class, "vSlide2");



        flip1 = hardwareMap.get(Servo.class, "flip1");
        flip2 = hardwareMap.get(Servo.class, "flip2");
        flipClaw= hardwareMap.get(Servo.class, "flipClaw");
        arm = hardwareMap.get(Servo.class, "arm");


        intake = hardwareMap.get(CRServo.class, "intake");
        claw = hardwareMap.get(Servo.class, "claw");




        // Reverse the left motors
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        // Set encoder for arm
        //arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }
    @Override
    public void loop() {
        // Game Pad 1
        // intake control
        if (gamepad1.a)
            intake.setPower(-1);
        if (gamepad1.b)
            intake.setPower(1);

        else {
            intake.setPower(0);
        }


       /* if (gamepad2.y)
            armDivisor = (1); // Makes arm faster; Ascent speed
        if (gamepad2.x)
            armDivisor = (3); // Makes arm slower; TeleOp speed */

        if (gamepad1.y)
            driveDivisor = (1); // Makes movement slower;
        if (gamepad1.x)
            driveDivisor = (2); // Makes movement faster;

        /*
        // Reset Arm
        if (gamepad2.dpad_down)
            moveArm(0);
        //Grabbing Blocks Position
        if (gamepad2.dpad_left)
            moveArm(1950);
        // Arm Up Position
        if (gamepad2.dpad_up)
            moveArm(910);
        */

        // Strafe
        if (gamepad1.right_bumper)
            strafeRight(.60F, 100);

        if (gamepad1.left_bumper)
            strafeLeft(.60F, 100);

        // claw control
        if (gamepad2.b) {
            claw.setPosition(0);

        }

        if (gamepad2.a) {
            claw.setPosition(1);

        }
        // transfer
        if(gamepad2.y) {
            horizontalSlide1.setPower(-1);
            intake.setPower(1);
            claw.setPosition(1);
            arm.setPosition(0);
            claw.setPosition(0);
            arm.setPosition(0.5);

        }

        // arm specific functions

        if(gamepad1.dpad_left) {
            arm.setPosition(0.25); // specimen hang
        }


        if(gamepad1.dpad_right) {
            arm.setPosition(0.8); // specimen wall
        }

        if(gamepad1.dpad_up) {
            arm.setPosition(0.5); // high basket
        }

        if(gamepad1.dpad_down) {
            arm.setPosition(0);  // reset arm
        }





        // Tank drive control
        double leftPower = ((-gamepad1.left_stick_y) / driveDivisor);
        double rightPower = ((-gamepad1.right_stick_y) / driveDivisor);

        // Arm control
        // double armPower = (-gamepad2.right_stick_y/armDivisor);

        double hSlidePower = -gamepad2.left_stick_y;
        double vSlidePower = -gamepad2.right_stick_y;


        // Apply cubic scaling
        leftPower = leftPower * leftPower * leftPower;
        rightPower = rightPower * rightPower * rightPower;

        // Set power for the drive motors
        frontLeft.setPower(leftPower);
        frontRight.setPower(rightPower);
        backLeft.setPower(leftPower);
        backRight.setPower(rightPower);
        verticalSlide1.setPower(vSlidePower);
        verticalSlide2.setPower(vSlidePower);
        horizontalSlide1.setPower(hSlidePower);
        horizontalSlide2.setPower(hSlidePower);


        // Set actual value for arm and claw
        // arm.setPower(armPower);

    }

    public void strafeLeft (float power, int time) {
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeft.setPower(-power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(-power);
        sleep(time);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
    public void strafeRight (float power, int time) {
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeft.setPower(power);
        frontRight.setPower(-power);
        backLeft.setPower(-power);
        backRight.setPower(power);
        sleep(time);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
    // Sleep Method for Tele Op
    public void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
/*
public void moveArm(double degrees) {
    // Define ticks per revolution for your motor
    final int TPR = 537.7;

    // Convert degrees to ticks
    int ticks = (int) (degrees * TPR / 360.0);

    // Move the arm using the calculated ticks
    arm.setTargetPosition(ticks); // Set the target position
    arm.setPower(0.5); // Set the motor power
    arm.setMode(DcMotor.RunMode.RUN_TO_POSITION); // Move the motor to the target position

    position = arm.getCurrentPosition(); // Get the current position (optional)
}
 */
/*position = arm.getCurrentPosition();
        double revolutions = position/TPR;

        double angle = revolutions * 360;
        double angleNormalized = angle % 360;*/
