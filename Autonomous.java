package org.firstinspires.ftc.teamcode;
//ONE TILE DISTANCE IS 350 MILLISECONDS AT 1 POWER
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;



@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Autonomous")
public class Autonomous extends LinearOpMode {

    DcMotor frontLeft, frontRight, backLeft, backRight;
    DcMotor horizontalSlide1, horizontalSlide2, verticalSlide1,verticalSlide2;
    Servo  flip1, flip2, flipClaw, arm1, arm2, claw;
    CRServo intake1, intake2;
    int vSlideUp = 1950;
    int vSlideDown = 0;

    int vSlideSpecimen = 900;


    int hSlideOut = 1000;
    int hSlideIn = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize hardware
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
       frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");


        horizontalSlide1 = hardwareMap.get(DcMotor.class, "hSlide1");
        horizontalSlide1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        horizontalSlide1.setTargetPosition(0);
        horizontalSlide1.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        horizontalSlide2 = hardwareMap.get(DcMotor.class, "hSlide2");
        horizontalSlide2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        horizontalSlide2.setTargetPosition(0);
        horizontalSlide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);




        verticalSlide1 = hardwareMap.get(DcMotor.class, "vSlide1");
     //   verticalSlide1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      //  verticalSlide1.setTargetPosition(0);
       // verticalSlide1.setMode(DcMotor.RunMode.RUN_TO_POSITION);



        //vertical Slide encoders setup
        verticalSlide2 = hardwareMap.get(DcMotor.class, "vSlide2");
       // verticalSlide2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       // verticalSlide2.setTargetPosition(0);
       // verticalSlide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);



        arm1 = hardwareMap.get(Servo.class, "arm1");
        claw = hardwareMap.get(Servo.class, "claw");
        flip1 = hardwareMap.get(Servo.class, "flip1");
        flip2 = hardwareMap.get(Servo.class, "flip2");
        intake1 = hardwareMap.get(CRServo.class, "intake1");
        intake2 = hardwareMap.get(CRServo.class, "intake2");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        moveVerticalSlide(0.5, 3600);
        //verticalSlide1.setTargetPosition(1200);
       // verticalSlide2.setTargetPosition(1200);
        sleep(250);
        flipArm1(0.7);
        sleep(250);
        moveBackward(0.3F, 2650);
        sleep(250);
        moveClaw(1);
        sleep(250);
        moveVerticalSlide(0.8,1200);
       // flipArm1(1);


     //   sleep(250);
        //moveVerticalSlide1(1500);
        //moveVerticalSlide2(1000);
       // sleep(250);
        //flipArm1(0.7);





       // strafeLeft(0.3F,250);
      //  sleep(250);
       // verticalSlideSample();
       // sleep(250);
        //arm1.setPosition(0.8);
        //sleep(250);
        //claw.setPosition(0);
        //sleep(250);


      //  moveBackward(0.3F, 900);



       // ;xfollowSpline(new double[]{0, 0}, new double[]{30, 60}, new double[]{60, 0}, 0.5F);


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

    public void moveVerticalSlide1(int ticks) {
        verticalSlide1.setTargetPosition(ticks); // Define target position
        verticalSlide1.setPower(0.3); // Reduced power for slower movement
        verticalSlide1.setMode(DcMotor.RunMode.RUN_TO_POSITION); // Move to position using encoders

        while (verticalSlide1.isBusy()) {
            telemetry.addData("Vertical Slide 1 Position", verticalSlide1.getCurrentPosition());
            telemetry.update();
        }

        verticalSlide1.setPower(0); // Stop motor once target is reached
    }
    public void moveVerticalSlide2(int ticks) {
        verticalSlide2.setTargetPosition(ticks); // Define target position
        verticalSlide2.setPower(0.3); // Reduced power for slower movement
        verticalSlide2.setMode(DcMotor.RunMode.RUN_TO_POSITION); // Move to position using encoders

        while (verticalSlide2.isBusy()) {
            telemetry.addData("Vertical Slide 2 Position", verticalSlide2.getCurrentPosition());
            telemetry.update();
        }

        verticalSlide2.setPower(0); // Stop motor once target is reached
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

    public void moveVerticalSlide(double power, int time) {
        verticalSlide1.setPower(power);
        verticalSlide2.setPower(power);
        sleep(time);
        verticalSlide1.setPower(0);
        verticalSlide1.setPower(0);


    }

    public void verticalSlideSample() {
        verticalSlide1.setTargetPosition(vSlideUp);
        verticalSlide2.setTargetPosition(vSlideUp);
        verticalSlide1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        verticalSlide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        verticalSlide1.setPower(0.5);
        verticalSlide2.setPower(0.5);


    }
    public void verticalSlideSpecimen() {
        verticalSlide1.setTargetPosition(vSlideSpecimen);
        verticalSlide2.setTargetPosition(vSlideSpecimen);
        verticalSlide1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        verticalSlide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        verticalSlide1.setPower(0.5);
        verticalSlide2.setPower(0.5);


    }

    public void verticalSlideDown() {
        verticalSlide1.setTargetPosition(vSlideDown);
        verticalSlide2.setTargetPosition(vSlideDown);
        verticalSlide1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        verticalSlide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        verticalSlide1.setPower(0.5);
        verticalSlide2.setPower(0.5);


    }

    public void flipArm (double position){
        flip1.setPosition(position);
        flip2.setPosition(position);

    }

    public void flipArm1 (double position){
        arm1.setPosition(position);

    }




}
