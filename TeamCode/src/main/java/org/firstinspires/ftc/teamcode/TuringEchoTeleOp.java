package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * This OpMode uses the common HardwareK9bot class to define the devices on the robot.
 * All device access is managed through the HardwareK9bot class. (See this class for device names)
 * The code is structured as a LinearOpMode
 *
 * This particular OpMode executes a basic Tank Drive Teleop for the K9 bot
 * It raises and lowers the arm using the Gampad Y and A buttons respectively.
 * It also opens and closes the claw slowly using the X and B buttons.
 *
 * Note: the configuration of the servos is such that
 * as the arm servo approaches 0, the arm position moves up (away from the floor).
 * Also, as the claw servo approaches 0, the claw opens up (drops the game element).
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name=" TuringEchoTeleOp", group="TuringEcho")
public class TuringEchoTeleOp extends LinearOpMode {

    /* Declare OpMode members. */
    TuringEchoRobotHardware   robot           = new TuringEchoRobotHardware();              // Use a K9'shardware

    @Override
    public void runOpMode() throws InterruptedException {
        double forward1;
        double forward2;
        double left;
        double right;

        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();


        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
            forward1 = gamepad1.left_stick_y / 2;
            forward2 = gamepad1.right_stick_y / 2;
            right = gamepad1.left_trigger / 4;
            left = gamepad1.right_trigger / 4;
            robot.WL.setPower(forward1 + forward2 - left + right);
            robot.WR.setPower(forward1 + forward2 + left - right);
            if (gamepad2.left_bumper)
                robot.col.setPower(0.4);
            if (gamepad2.right_bumper)
                robot.col.setPower(-0.4);
            if (gamepad2.a)
                robot.sht.setPower(1);
            if (gamepad2.b)
                robot.sht.setPower(0);
            if (gamepad2.x)
                robot.sht.setPower(0.1);
            if (gamepad2.y)
                robot.col.setPower(0);
            //if (gamepad2.x)
            //robot.sht.setPower(1);
            //if (gamepad1.b)
            //  robot.sht.setPower(0);
            //if (gamepad2.y)
            //  robot.col2.setPower(1);
            //if (gamepad1.a)
            //  robot.col2.setPower(0);


            // Pause for metronome tick.  40 mS each cycle = update 25 times a second.
            robot.waitForTick(40);
        }}}
