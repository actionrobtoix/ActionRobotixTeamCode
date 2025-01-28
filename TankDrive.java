
package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp
public class TankDrive extends OpMode {

    // Motors
      DcMotor frontLeft, frontRight, backLeft, backRight;
    DcMotor horizontalSlide1, horizontalSlide2, verticalSlide1, verticalSlide2;
    Servo flip1, flip2, arm1, arm2, claw;

    CRServo intake1, intake2;


    // Variables
    double armDivisor = 3;
    double driveDivisor = 1;
    int position;
    final double TPR = 537.7;
    double servoPos = 0; // Servo starting pos *CHANGE LATER
    boolean xPressed = false; // Tracks if the button was pressed
    boolean toggleState = false;
    ElapsedTime timer = new ElapsedTime();
    boolean actionInProgress = false;
    final int SLIDE_LOW = 0;
    final int SLIDE_HIGH = 1000;
    final int SlideIn = 0;
    final int SlideOut = 400;


    @Override
    public void init() {
        // Hardware map
          frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
          frontRight = hardwareMap.get(DcMotor.class, "frontRight");
          backLeft = hardwareMap.get(DcMotor.class, "backLeft");
         backRight = hardwareMap.get(DcMotor.class, "backRight");
       horizontalSlide1 = hardwareMap.get(DcMotor.class, "hSlide1");
      horizontalSlide2 = hardwareMap.get(DcMotor.class, "hSlide2");
        verticalSlide1 = hardwareMap.get(DcMotor.class, "vSlide1");
        verticalSlide2 = hardwareMap.get(DcMotor.class, "vSlide2");


        flip1 = hardwareMap.get(Servo.class, "flip1");
        flip2 = hardwareMap.get(Servo.class, "flip2");

        arm1 = hardwareMap.get(Servo.class, "arm1");



        intake1 = hardwareMap.get(CRServo.class, "intake1");
        intake2 = hardwareMap.get(CRServo.class, "intake2");
        claw = hardwareMap.get(Servo.class, "claw");


        // Reverse the left motors
         frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        // Set encoder for arm
        //  verticalSlide1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
       // verticalSlide2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //horizontalSlide1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //horizontalSlide2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);




       // verticalSlide2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       // verticalSlide1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       // horizontalSlide1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      //  horizontalSlide2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);




    }

    @Override
    public void loop() {


        if (Math.abs(gamepad2.right_stick_y) > 0.1) {
            // Switch to RUN_WITHOUT_ENCODER for joystick control
            verticalSlide1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            verticalSlide2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            double vSlidePower = -gamepad2.right_stick_y;
            verticalSlide1.setPower(vSlidePower);
            verticalSlide2.setPower(vSlidePower);
        }
        else if (gamepad2.dpad_up || gamepad2.dpad_down) {
            // Switch to RUN_TO_POSITION for encoder control
            verticalSlide1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            verticalSlide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // Set target positions based on D-pad input
            if (gamepad2.dpad_up) {
                verticalSlide1.setTargetPosition(SLIDE_HIGH);
                verticalSlide2.setTargetPosition(SLIDE_HIGH);
            } else if (gamepad2.dpad_down) {
                verticalSlide1.setTargetPosition(SLIDE_LOW);
                verticalSlide2.setTargetPosition(SLIDE_LOW);
            }

            verticalSlide1.setPower(0.8);
            verticalSlide2.setPower(0.8);
        }
        else {
            verticalSlide1.setPower(0);
            verticalSlide2.setPower(0);
        }


        if (Math.abs(gamepad2.left_stick_y) > 0.1) {
            // Switch to RUN_WITHOUT_ENCODER for joystick control
            horizontalSlide1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            horizontalSlide2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            double hSlidePower = -gamepad2.left_stick_y;
            horizontalSlide1.setPower(hSlidePower);
            horizontalSlide2.setPower(hSlidePower);
        }
        else if (gamepad2.dpad_up || gamepad2.dpad_down) {
            // Switch to RUN_TO_POSITION for encoder control
            horizontalSlide1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            horizontalSlide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // Set target positions based on D-pad input
            if (gamepad2.dpad_left) {
                horizontalSlide1.setTargetPosition(SlideIn);
                horizontalSlide2.setTargetPosition(SlideIn);
            } else if (gamepad2.dpad_right) {
                horizontalSlide1.setTargetPosition(SlideOut);
                horizontalSlide2.setTargetPosition(SlideOut);
            }

            horizontalSlide1.setPower(0.8);
            horizontalSlide2.setPower(0.8);
        }
        else {
            horizontalSlide1.setPower(0);
            horizontalSlide2.setPower(0);
        }
        // Game Pad 1
        // intake control
        if (gamepad1.cross) {
            intake1.setPower(-1);
            intake2.setPower(1);
        }
        if (gamepad1.circle) {
            intake1.setPower(1);
            intake2.setPower(-1);
        }


        if (gamepad2.square && !xPressed) {
            // Toggle the state
            toggleState = !toggleState;

            // Set `flip1` position based on the toggle state
            if (toggleState) {
              flip1.setPosition(0.3);// Position A
                flip2.setPosition(0.3);
            } else {
               flip1.setPosition(0.7); // Position B
                flip2.setPosition(0.7);
            }


            xPressed = true;
        }

        // Reset the press state when the button is released
        if (!gamepad2.square) {
            xPressed = false;
        }




       /* if (gamepad2.y)
            armDivisor = (1); // Makes arm faster; Ascent speed
        if (gamepad2.x)
            armDivisor = (3); // Makes arm slower; TeleOp speed */

        if (gamepad1.triangle)
            driveDivisor = (1); // Makes movement slower;
        if (gamepad1.square)
            driveDivisor = (2); // Makes movement faster;


        // Reset Arm



        // Strafe
        if (gamepad1.right_bumper)
         strafeRight(.60F, 100);

        if (gamepad1.left_bumper)
         strafeLeft(.60F, 100);

        // claw control
        if (gamepad2.cross) {
           claw.setPosition(1);

        }

        if (gamepad2.circle) {
            claw.setPosition(0);

        }
        // transfer

        if (gamepad2.triangle && !actionInProgress) {
            actionInProgress = true;
            timer.reset();
        }

        if (actionInProgress) {
            double elapsedTime = timer.milliseconds();

            if (elapsedTime < 500) {
                // First action (e.g., activate intake)
               claw.setPosition(1);
            } else if (elapsedTime < 1000) {
                // Second action (e.g., close claw)
                arm1.setPosition(0.5);


            } else {
                // Final action (e.g., stop intake)
                intake1.setPower(0);
                intake2.setPower(0);
                actionInProgress = false;  // Action complete, reset flag
            }
        }


        // arm specific functions

        if (gamepad1.dpad_left) {
            arm1.setPosition(0.2);
        }


        if (gamepad1.dpad_right) {
           arm1.setPosition(0.8);

        }

        if (gamepad1.dpad_up) {
            arm1.setPosition(0.5);

        }

        if (gamepad1.dpad_down) {
            arm1.setPosition(0);

        }


        // Tank drive control
        double leftPower = ((-gamepad1.left_stick_y) / driveDivisor);
       double rightPower = ((-gamepad1.right_stick_y) / driveDivisor);


        // Arm control
        // double armPower = (-gamepad2.right_stick_y/armDivisor);

        double hSlidePower = -gamepad2.left_stick_y;


        // Apply cubic scaling
       leftPower = leftPower * leftPower * leftPower;
        rightPower = rightPower * rightPower * rightPower;

        // Set power for the drive motors
         frontLeft.setPower(leftPower);
        frontRight.setPower(rightPower);
        backLeft.setPower(leftPower);
        backRight.setPower(rightPower);
        horizontalSlide1.setPower(hSlidePower);
        horizontalSlide2.setPower(hSlidePower);


        // Set actual value for arm and claw
        // arm.setPower(armPower);

    }
    public void strafeLeft (float power, int time) {
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
    // Sleep Method for Tele Op
    public void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}





/*public void moveArm(double degrees) {
    // Define ticks per revolution for your motor
    final int TPR = 537.7;

    // Convert degrees to ticks
    int ticks = (int) (degrees * TPR / 360.0);

    // Move the arm using the calculated ticks
    arm.setTargetPosition(ticks); // Set the target position
    arm.setPower(0.5); // Set the motor power
    arm.setMode(DcMotor.RunMode.RUN_TO_POSITION); // Move the motor to the target position

    position = arm.getCurrentPosition(); // Get the current position (optional)
}

/*position = arm.getCurrentPosition();
        double revolutions = position/TPR;

        double angle = revolutions * 360;
        double angleNormalized = angle % 360;*/
