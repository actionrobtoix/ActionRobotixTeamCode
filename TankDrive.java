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
    public CRServo claw;
    public CRServo rotary;


    @Override
    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        arm = hardwareMap.get(DcMotor.class, "arm");
        claw = hardwareMap.get(CRServo.class, "claw");
        rotary = hardwareMap.get(CRServo.class, "Rotary");

        // Reverse the left motors
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {

        if(gamepad2.a){
            claw.setPower(-1);
        }
        if(gamepad2.b){
            claw.setPower(1);
        }
        else {
            claw.setPower(0);
        }

        if(gamepad1.a){
            rotary.setPower(-1);
        }
        if(gamepad1.b){
            rotary.setPower(1);
        }
        else {
            rotary.setPower(0);
        }


        double forward = 1;
        double back = -1;
        if(gamepad1.left_bumper) {
            backLeft.setPower(forward);
            backRight.setPower(back);
            frontLeft.setPower(back);
            frontRight.setPower(forward);
        }
        else if(gamepad1.right_bumper)
        {

            backLeft.setPower(back);
            backRight.setPower(forward);
            frontLeft.setPower(forward);
            frontRight.setPower(back);

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
