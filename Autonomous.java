package org.firstinspires.ftc.teamcode;
//ONE TILE DISTANCE IS 350 MILLISECONDS AT 1 POWER
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous (name = "Autonomous")
public class Autonomous extends LinearOpMode {

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    //private DcMotor arm;
    private DcMotor vShaft;
    private DCMotor vShaft2;
    private DcMotor hShaft;
    public Servo claw;
    public CRServo rotary;
    public CRServo basket;

    @Override
    public void runOpMode() throws InterruptedException {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        //arm = hardwareMap.get(DcMotor.class, "arm");
        vShaft = hardwareMap.get(DcMotor.class, "vShaft");
        vShaft2 = hardwareMap.get(DcMotor.class, "vShaft2");
        hShaft = hardwareMap.get(DcMotor.class, "hShaft");
        claw = hardwareMap.get(Servo.class, "claw");
        rotary = hardwareMap.get(CRServo.class, "Rotary");
        basket = hardwareMap.get(CRServo.class, "basket");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        moveForward((float) 0.3, 2950);
        sleep(250);
        turnLeft((float) 0.3,1000);
        sleep(250);
        moveForward((float) 0.3, 250);
        sleep(250);
        turnRight((float) 0.3,1200);
        sleep(250);
        moveBackward((float) 0.3, 3100);
        sleep(250);
        moveForward((float) 0.3, 2825);
        sleep(250);
        turnLeft((float) 0.3, 1200);
        sleep(250);
        moveForward((float) 0.3, 700);
        sleep(250);
        turnRight((float) 0.3, 1000);
        sleep(250);
        moveBackward((float) 0.3, 2950);
        sleep(250);
        moveForward((float) 0.3, 3150);
        sleep(250);
        turnLeft((float) 0.3, 1350);
        sleep(250);
        moveBackward((float) 0.3, 1250);
        sleep(250);
        vSlide(0.3, 1400);
        sleep(250);
        hSlide(0.3, 1400);
        sleep(250);
        strafeLeft((float) 0.3, 800);
        sleep(250);
        strafeRight((float)0.3, 800);
        sleep(250);
        rightForward((float) 0.3, 500);
        sleep(250);
        rightBackward((float) 0.3, 500);
        sleep(250);
        leftBackward((float) 0.3, 500);
        sleep(250);
        leftForward((float) 0.3, 500);
        sleep(250);
        moveClaw(-0.3, 175);
        sleep(250);
        moveRotary(0.3,175);
        sleep(250);
        moveBasket(-0.3, 150);
        sleep(250);


        /*moveBackward((float) 0.3, 900);
        sleep(250);
        turnLeft((float) 0.3, 350);
        sleep(250);
        moveForward((float) 0.3, 600);
        sleep(250);
        turnLeft((float) 0.3, 1175);
        sleep(250);
        moveForward((float) 0.5, 3200);
        sleep(250);
        turnRight((float) 1.0, 200);
        sleep(250);
        moveForward((float) 0.3, 300);
        sleep(250);*/
    }
    public void moveForward (float power, int time){
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
        sleep(time);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        //ONE TILE DISTANCE IS 350 MILLISECONDS AT 1 POWER
    }
    public void moveBackward (float power, int time){
        frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
        sleep(time);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        //ONE TILE DISTANCE IS 350 MILLISECONDS AT 1 POWER
    }
    public void turnRight (float power, int time){
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
        sleep(time);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
    public void turnLeft (float power, int time){
        frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
        sleep(time);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
    public void vSlide (double power, int time){
        vShaft.setPower(power);
        sleep(time);
        vShaft.setPower(0);
    }

    public void vSlide2 (double power, int time){
        vShaft2.setPower(power);
        sleep(time);
        vShaft2.setPower(0);
    }
    public void hSlide (double power, int time){
        hShaft.setPower(power);
        sleep(time);
        hShaft.setPower(0);
    }
    public void strafeLeft(float power, int time) {
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

    public void strafeRight(float power, int time) {

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
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

    public void rightForward (float power, int time){
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeft.setPower(power);
        backRight.setPower(power);
        sleep(time);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
    public void rightBackward (float power, int time){
        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setPower(power);
        backLeft.setPower(power);
        sleep(time);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
    public void leftBackward (float power, int time){
        frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setPower(power);
        backRight.setPower(power);
        sleep(time);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
    public void leftForward (float power, int time){
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
        sleep(time);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
    public void moveClaw (double position, int time){
        claw.setPosition(position);
        sleep(time);
        claw.setPosition(0);
    }
    public void moveRotary (double power, int time){
        rotary.setPower(power);
        sleep(time);
        rotary.setPower(0);
    }
    public void moveBasket (double power, int time){
        basket.setPower(power);
        sleep(time);
        basket.setPower(0);
    }
}
/*package org.firstinspires.ftc.teamcode;
//ONE TILE DISTANCE IS 350 MILLISECONDS AT 1 POWER
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous (name = "Autonomous")
public class Autonomous extends LinearOpMode {

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;

    @Override
    public void runOpMode() throws InterruptedException {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        moveForward((float) 0.3, 2950);
        sleep(250);
        turnLeft((float) 0.3,1375);
        sleep(250);
        moveForward((float) 0.3, 200);
        sleep(250);
        turnLeft((float) 0.3,1320);
        sleep(250);
        moveForward((float) 0.3, 2550);
        sleep(250);
        moveBackward((float) 0.3, 2500);
        sleep(250);
        turnRight((float) 0.3, 550);
        sleep(250);
        moveForward((float) 0.3, 550);
        sleep(250);
        turnLeft((float) 0.3, 500);
        sleep(250);
        moveForward((float) 0.3, 2250);
        sleep(250);
        moveBackward((float) 0.3, 900);
        sleep(250);
        turnLeft((float) 0.3, 350);
        sleep(250);
        moveForward((float) 0.3, 600);
        sleep(250);
        turnLeft((float) 0.3, 1175);
        sleep(250);
        moveForward((float) 0.5, 3200);
        sleep(250);
        turnRight((float) 1.0, 200);
        sleep(250);
        moveForward((float) 0.3, 300);
        sleep(250);
    }
    public void moveForward (float power, int time){
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
        sleep(time);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        //ONE TILE DISTANCE IS 350 MILLISECONDS AT 1 POWER
    }
    public void moveBackward (float power, int time){
        frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
        sleep(time);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        //ONE TILE DISTANCE IS 350 MILLISECONDS AT 1 POWER
    }
    public void turnRight (float power, int time){
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
        sleep(time);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
    public void turnLeft (float power, int time){
        frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
        sleep(time);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
}*/
