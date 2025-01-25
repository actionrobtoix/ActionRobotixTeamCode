package org.firstinspires.ftc.teamcode;
//ONE TILE DISTANCE IS 350 MILLISECONDS AT 1 POWER
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;



@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Autonomous")
public class Autonomous extends LinearOpMode {

    DcMotor frontLeft, frontRight, backLeft, backRight, horizontalSlide1, horizontalSlide2, verticalSlide1,verticalSlide2;
    Servo  flip1, flip2, flipClaw, arm, claw;
    CRServo intake1, intake2;

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize hardware
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        arm = hardwareMap.get(Servo.class, "arm");
        claw = hardwareMap.get(Servo.class, "claw");
        flip1 = hardwareMap.get(Servo.class, "flip1");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        
        moveBackward(0.3F, 900);

       
        followSpline(new double[]{0, 0}, new double[]{30, 60}, new double[]{60, 0}, 0.5F);

       
    }

    // Method to calculate spline points
    public double[] calculateSplinePoint(double t, double[] P0, double[] P1, double[] P2) {
        double x = Math.pow(1 - t, 2) * P0[0] + 2 * (1 - t) * t * P1[0] + Math.pow(t, 2) * P2[0];
        double y = Math.pow(1 - t, 2) * P0[1] + 2 * (1 - t) * t * P1[1] + Math.pow(t, 2) * P2[1];
        return new double[]{x, y};
    }

    // Method to follow a spline
    public void followSpline(double[] P0, double[] P1, double[] P2, float power) {
        double prevX = P0[0];
        double prevY = P0[1];

        for (double t = 0; t <= 1; t += 0.1) { // Increment t in small steps
            double[] targetPoint = calculateSplinePoint(t, P0, P1, P2);
            double targetX = targetPoint[0];
            double targetY = targetPoint[1];

            // Calculate distance and heading
            double dx = targetX - prevX;
            double dy = targetY - prevY;
            double distance = Math.hypot(dx, dy);
            double angle = Math.atan2(dy, dx);

            // Move robot towards the next point
            moveToTarget(power, angle, distance);

            prevX = targetX;
            prevY = targetY;
        }
    }

    // Method to move to a target point
    public void moveToTarget(float power, double angle, double distance) {
        double xPower = power * Math.cos(angle);
        double yPower = power * Math.sin(angle);

        frontLeft.setPower(yPower + xPower);
        frontRight.setPower(yPower - xPower);
        backLeft.setPower(yPower + xPower);
        backRight.setPower(yPower - xPower);

        sleep((int) (distance * 350)); // Adjust based on tile distance
        stopMotors();
    }

    // Stop all motors
    public void stopMotors() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
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

    public void collectIntake (int time){
        intake1.setPower(-1);
        intake2.setPower(1);
        sleep(time);
        intake1.setPower(0);
        intake2.setPower(0);

    }
    public void releaseIntake (int time){
        intake1.setPower(1);
        intake2.setPower(-1);
        sleep(time);
        intake1.setPower(0);
        intake2.setPower(0);

    }
    public void moveVSlide (double power, int time){
        verticalSlide1.setPower(power);
        verticalSlide2.setPower(power);
        sleep(time);
        verticalSlide1.setPower(0);
        verticalSlide2.setPower(0);
    }

    public void moveHorizontalSlide (double power, int time){
        horizontalSlide1.setPower(power);
        horizontalSlide2.setPower(power);
        sleep(time);
        horizontalSlide1.setPower(0);
        horizontalSlide2.setPower(0);
    }
   
    public void flipArm (double position){
        flip1.setPosition(position);
        flip2.setPosition(position);
        
    }
    

    

}
