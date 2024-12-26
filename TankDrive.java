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
    public CRServo rotary;
    public Servo basket;




    @Override
    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        hShaft = hardwareMap.get(DcMotor.class, "horizontalSlide");
        vShaft = hardwareMap.get(DcMotor.class, "verticalSlide1");
        vShaft2 = hardwareMap.get(DcMotor.class, "verticalSlide2");



        claw = hardwareMap.get(CRServo.class, "claw");
        rotary = hardwareMap.get(CRServo.class, "Rotary");
        // basket = hardwareMap.get(Servo.class, "basket");
        flip1 = hardwareMap.get(CRServo.class, "flip1");
    
        flip2 = hardwareMap.get(CRServo.class, "flip2");





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
        if(gamepad1.b) {
            claw.setPower(1);
        }
        else {
              claw.setPower(0);
            
        }

         if(gamepad2.a){
           flip1.setPower(-1);
           flip2.setPower(-1);

        
        }
        if(gamepad2.b) {
            flip1.setPower(1);
           flip2.setPower(1);
        }
        else {
            flip1.setPower(0);
           flip2.setPower(0);
            
        }

        // if(gamepad1.x){
           // basket.setPosition(1);
      //  }
        //if(gamepad1.y) {
         //   basket.setPosition(0.5);
       // }
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


        // set actual value for slides
        hShaft.setPower(hShaftPower);
         vShaft.setPower(vShaftPower);
        vShaft2.setPower(vShaftPower2);

        double strafePower = 0.5; // Adjust as needed

        if (gamepad1.left_bumper) {
            // Strafe left
            backLeft.setPower(-strafePower);
            backRight.setPower(strafePower);
            frontLeft.setPower(strafePower);
            frontRight.setPower(-strafePower);
        } else if (gamepad1.right_bumper) {
            // Strafe right
            backLeft.setPower(strafePower);
            backRight.setPower(-strafePower);
            frontLeft.setPower(-strafePower);
            frontRight.setPower(strafePower);
        }
            
            frontLeft.setPower(leftPower);
            frontRight.setPower(rightPower);
            backLeft.setPower(leftPower);
            backRight.setPower(rightPower);
       
    }


}
