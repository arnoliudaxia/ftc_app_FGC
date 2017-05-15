package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a K9 robot.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * Motor channel:  Left  drive motor:        "left_drive"
 * Motor channel:  Right drive motor:        "right_drive"
 * Servo channel:  Servo to raise/lower arm: "arm"
 * Servo channel:  Servo to open/close claw: "claw"
 *
 * Note: the configuration of the servos is such that:
 *   As the arm servo approaches 0, the arm position moves up (away from the floor).
 *   As the claw servo approaches 0, the claw opens up (drops the game element).
 */
public class TuringEchoRobotHardware1
{
    /* Public OpMode members. */
    public DcMotor  WL   = null;
    public DcMotor  WR  = null;
    public DcMotor  WF = null;
    public DcMotor  WB = null;
    public DcMotor  col= null;
    public DcMotor  sht= null;


    /* Local OpMode members. */
    HardwareMap hwMap  = null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public TuringEchoRobotHardware1() {
    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) throws InterruptedException {
        // save reference to HW Map
        hwMap = ahwMap;

        // Define and Initialize Motors
        WL   = hwMap.dcMotor.get("WL");
        WR   = hwMap.dcMotor.get("WR");
        WF  = hwMap.dcMotor.get("WF");
        WB  = hwMap.dcMotor.get("WB");
        col = hwMap.dcMotor.get("col");
        sht = hwMap.dcMotor.get("sht");
        WR.setDirection(DcMotor.Direction.REVERSE);
        WB.setDirection(DcMotor.Direction.REVERSE);



        //c1 = hwMap.colorSensor.get("c1");

        // Set all motors to zero power
        WL.setPower(0);
        WR.setPower(0);
        WF.setPower(0);
        WB.setPower(0);
        col.setPower(0);
        sht.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        WL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        WR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        WF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        WB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        col.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        sht.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Define and initialize ALL installed servos.

    }
    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for va`rying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     */
    public void waitForTick(long periodMs) {

        long remaining = periodMs - (long) period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0) {
            try {
                Thread.sleep(remaining);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Reset the cycle clock for the next pass.
        period.reset();
    }


}
