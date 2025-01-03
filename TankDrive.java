
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
    public CRServo flip1;

    @Override
    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        arm = hardwareMap.get(DcMotor.class, "arm");
        flip1 = hardwareMap.get(CRServo.class, "flip1");
        claw = hardwareMap.get(Servo.class, "claw");
        // Reverse the left motors
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    @Override
    public void loop() {
// claw control

        if (gamepad1.a) {
            claw.setPosition(1);
        }
        if (gamepad1.b) {
            claw.setPosition(0);
        }
        if (gamepad2.a) {
            //flip2.setPosition(1);
            flip1.setPower(-1);
        }

        if(gamepad2.b) {
            flip1.setPower(0.5);
        }
        else {
            flip1.setPower(0);
        }

        /*if(gamepad2.y) {
            flip1.setPosition(0.2);
        }
        if (gamepad2.x) {
            flip1.setPosition(0.4);
        }*/


        // Tank drive control

        double leftPower = (-gamepad1.left_stick_y)/1.25;
        double rightPower = (-gamepad1.right_stick_y)/1.25;
        // claw control

        // Arm control
        double armPower = (-gamepad2.right_stick_y)/4;

        // Apply cubic scaling
        leftPower = leftPower * leftPower * leftPower;
        rightPower = rightPower * rightPower * rightPower;

        // Set power for the drive motors
        frontLeft.setPower(leftPower);
        frontRight.setPower(rightPower);
        backLeft.setPower(leftPower);
        backRight.setPower(rightPower);
        // set actual value for arm and claw
        arm.setPower(armPower);
    }
}
