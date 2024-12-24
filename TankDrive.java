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


    public CRServo claw;
    public CRServo rotary;
    public CRServo basket;




    @Override
    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        hShaft = hardwareMap.get(DcMotor.class, "hShaft");
        vShaft = hardwareMap.get(DcMotor.class, "vShaft");
        vShaft2 = hardwareMap.get(DcMotor.class, "vShaft2");



        claw = hardwareMap.get(CRServo.class, "claw");
        rotary = hardwareMap.get(CRServo.class, "Rotary");
        basket = hardwareMap.get(CRServo.class, "basket");



        // Reverse the left motors
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {
// claw control
        if(gamepad1.a){
            claw.setPower(-1);
        }
        if(gamepad1.b){
            claw.setPower(1);
        }
        else {
            claw.setPower(0);
        }
// rotary control
        if(gamepad2.left_bumper){
            rotary.setPower(-1);
        }
        if(gamepad1.right_bumper){
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
        float hShaftPower = -gamepad2.left_stick_y;
        float vShaftPower = -gamepad2.right_stick_y;
        float vShaftPower2 = -gamepad2.right_stick_y;




        // Apply cubic scaling
        leftPower = leftPower * leftPower * leftPower;
        rightPower = rightPower * rightPower * rightPower;
        hShaftPower = hShaftPower * hShaftPower *hShaftPower;
        vShaftPower  = vShaftPower  * vShaftPower  *  vShaftPower;
        vShaftPower2  = vShaftPower2  * vShaftPower2  * vShaftPower2;





        // Set power for the drive motors
        frontLeft.setPower(leftPower);
        frontRight.setPower(rightPower);
        backLeft.setPower(leftPower);
        backRight.setPower(rightPower);

        // set actual value for slides
        hShaft.setPower(hShaftPower);
         vShaft.setPower(vShaftPower);
        vShaft2.setPower(vShaftPower2);




    }


}
