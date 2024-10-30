package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
@TeleOp
public class TankDrive extends OpMode {
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor arm;
    public CRServo servo;
    @Override
    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        arm = hardwareMap.get(DcMotor.class, "arm");
        servo = hardwareMap.get(CRServo.class, "claw");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    @Override
    public void loop() {
        double leftPower = -gamepad1.left_stick_y;
        double rightPower = -gamepad1.right_stick_y;
        float armUpPower = -gamepad2.right_stick_y;
        float armDownPower = -gamepad2.left_stick_y;


        leftPower = leftPower * leftPower * leftPower;
        rightPower = rightPower * rightPower * rightPower;
        armUpPower = armUpPower * armUpPower * armUpPower;
        armDownPower = armDownPower * armDownPower * armDownPower;
        frontLeft.setPower(leftPower);
        frontRight.setPower(rightPower);
        backLeft.setPower(leftPower);
        backRight.setPower(rightPower);
        arm.setPower(armUpPower);
        arm.setPower(armDownPower);


        if (gamepad2.a) {
            servo.setPower(1);
        }

        if (gamepad2.b) {
            servo.setPower(-1);
        }

        sleep(1000);
    }

    private void sleep(int i) {
    }
}
