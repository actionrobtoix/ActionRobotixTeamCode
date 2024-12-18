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
    private DcMotor horizontalSlide;
    private DcMotor verticalSlide1;
    private DcMotor verticalSlide2;


    public CRServo claw;
    public CRServo rotary;


    @Override
    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        horizontalSlide = hardwareMap.get(DcMotor.class, "horizontalSlide");
        verticalSlide1 = hardwareMap.get(DcMotor.class, "verticalSlide1");
        verticalSlide2 = hardwareMap.get(DcMotor.class, "verticalSlide2");



        claw = hardwareMap.get(CRServo.class, "claw");
        rotary = hardwareMap.get(CRServo.class, "Rotary");

        // Reverse the left motors
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {

        if(gamepad1.a){
            claw.setPower(-1);
        }
        if(gamepad1.b){
            claw.setPower(1);
        }
        else {
            claw.setPower(0);
        }

        


        double forward = 1;
        double back = -1;
        if(gamepad1.left_bumper) {
            backLeft.setPower(forward);
            backRight.setPower(back);
            frontLeft.setPower(back);
            frontRight.setPower(forward);
        }
        else if(gamepad1.right_bumper) {

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
        float horizontalPower = -gamepad2.left_stick_y;
        float verticalPower1 = -gamepad2.right_stick_y;
        float verticalPower2 = -gamepad2.right_stick_y;



        // Apply cubic scaling
        leftPower = leftPower * leftPower * leftPower;
        rightPower = rightPower * rightPower * rightPower;
        horizontalPower = horizontalPower * horizontalPower * horizontalPower;
        verticalPower1  = verticalPower1  * verticalPower1  * verticalPower1;
        verticalPower2  = verticalPower2  * verticalPower2  * verticalPower2;





        // Set power for the drive motors
        frontLeft.setPower(leftPower);
        frontRight.setPower(rightPower);
        backLeft.setPower(leftPower);
        backRight.setPower(rightPower);

        // set actual value for arm and claw
        horizontalSlide.setPower(horizontalPower);
         verticalSlide1.setPower(verticalPower1);
        verticalSlide2.setPower(verticalPower2);




    }


}
