package org.firstinspires.ftc.teamcode;
//ONE TILE DISTANCE IS 350 MILLISECONDS AT 1 POWER
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous (name = "Autonomous")
public class Autonomous extends LinearOpMode {

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor arm;
    public Servo claw;
    public Servo flip1;

    int position;

    @Override
    public void runOpMode() throws InterruptedException {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        arm = hardwareMap.get(DcMotor.class, "arm");
        claw = hardwareMap.get(Servo.class, "claw");
        flip1 = hardwareMap.get(Servo.class, "flip1");

        // Set encoder for arm


        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
//-170
        /* strafeLeft( 0.3F, 500);
         sleep(1000);
         flipClawBack(0.3);
         sleep(200);*/

        moveBackward((float)0.3, 825);
        sleep(200);
        flipClaw(0.3);
        sleep(250);
        moveArm(1100);
        sleep(250);
        moveBackward((float)0.3,300);
        sleep(250);
        moveClaw(0);
        sleep(250);
        moveForward(0.3F,350);
        sleep(250);
        flipClawBack(0.3);
        sleep(250);
        moveArm(0);
        sleep(250);
        strafeLeft(.5F,1400);
        sleep(250);
        moveBackward(0.5F, 1275);
        sleep(250);
        strafeLeft(0.5F,400);
        sleep(250);
        moveForward(0.5F,1500);
        sleep(250);
        turnLeft(0.3F, 80);
        moveBackward(0.5F, 1550);
        sleep(250);
        strafeLeft(0.5F, 600);
        sleep(250);
        moveForward(0.5F, 1500);
        sleep(1000);

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
    public void moveClaw (double position){
        claw.setPosition(position);
    }
    public void moveArm (double power, int time){
        arm.setPower(power);
        sleep(time);
        arm.setPower(0);
    }
    public void moveArmBack (double power, int time){
        arm.setDirection(DcMotorSimple.Direction.REVERSE);
        arm.setPower(power);
        sleep(time);
        arm.setPower(0);
    }
    public void flipClaw (double position){
        flip1.setPosition(position);
    }
    public void flipClawBack (double position){
        flip1.setDirection(Servo.Direction.REVERSE);
        flip1.setPosition(position);
    }

    public void moveArm(int ticks) {
        arm.setTargetPosition(ticks); // Define target position
        arm.setPower(0.3); // Reduced power for slower movement
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION); // Move to position using encoders

        while (arm.isBusy()) {
            telemetry.addData("Arm Position", arm.getCurrentPosition());
            telemetry.update();
        }

        arm.setPower(0); // Stop motor once target is reached
    }

}
