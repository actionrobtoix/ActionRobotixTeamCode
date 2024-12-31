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
    private DcMotor hShaft;
    private DcMotor vShaft;
    private DcMotor vShaft2;



    public Servo claw;
    public Servo rotary;
    public Servo basket;
    public Servo flip1;


    @Override
    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        hShaft = hardwareMap.get(DcMotor.class, "horizontalSlide");
        vShaft = hardwareMap.get(DcMotor.class, "verticalSlide1");
        vShaft2 = hardwareMap.get(DcMotor.class, "verticalSlide2");

        claw = hardwareMap.get(Servo.class, "claw");
        basket = hardwareMap.get(Servo.class, "basket");
        flip1 = hardwareMap.get(Servo.class, "flip1");

        // Reverse the left motors
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {
// claw control

        if (gamepad1.a) {
            claw.setPosition(1);
        }
        if (gamepad1.b) {
            claw.setPosition(0.4);
       }
        if (gamepad2.a) {
            //flip2.setPosition(1);
            flip1.setPosition(0);
        }
        if (gamepad2.b) {
            // flip2.setPosition(0);
            flip1.setPosition(0.7);
        }
        if(gamepad2.y) {
            flip1.setPosition(0.2);
        }
        if (gamepad2.x) {
            flip1.setPosition(0.4);
        }



       // telemetry.addData(flip1.getPower));



        // if(gamepad1.x){
        // basket.setPosition(1);
        //  }
        //if(gamepad1.y) {
        //   basket.setPosition(0.5);
        // }
// rotary control


        // Tank drive control

        double rightPower = -gamepad1.right_stick_y;
        double leftPower = -gamepad1.left_stick_y;

        // claw control

        // Arm control
        float hShaftPower = -gamepad2.left_stick_y;
        float vShaftPower = gamepad2.right_stick_y;


        // Apply cubic scaling
        rightPower = rightPower * rightPower * rightPower;
        leftPower = leftPower * leftPower * leftPower;
        hShaftPower = hShaftPower * hShaftPower * hShaftPower;
        vShaftPower = vShaftPower * vShaftPower * vShaftPower;


        // Set power for the drive motors


        // set actual value for slides
        hShaft.setPower(hShaftPower);
        vShaft.setPower(vShaftPower);
        vShaft2.setPower(vShaftPower);



        frontRight.setPower(rightPower);
        frontLeft.setPower(leftPower);
        backRight.setPower(rightPower);
        backLeft.setPower(leftPower);


    }
}
