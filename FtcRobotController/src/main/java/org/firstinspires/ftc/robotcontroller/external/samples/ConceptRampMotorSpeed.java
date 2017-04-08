package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * This OpMode ramps a single motor speed up and down repeatedly until Stop is pressed.　该模式利用一个电机来实现反复上下动作，直到按“stop“键才会停止。
 * The code is structured as a LinearOpMode　该代码被构造为一个LinearOpMode
 *
 * This code assumes a DC motor configured with the name "left_drive" as is found on a pushbot.
 　该代码假定一个直流电机配置了一个名称“left_drive”，如在pushbot上。
 *
 * INCREMENT sets how much to increase/decrease the power each cycle
 * CYCLE_MS sets the update period.
 INCREMENT设置每个周期增加/减少功耗的程度
  * CYCLE_MS设置更新周期。
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 使用Android Studio复制此类，并使用新名称将其粘贴到团队的代码文件夹中。
  *删除或注释掉@Disabled行将此opmode添加到Driver Station OpMode列表
 */
@Autonomous(name = "Concept: Ramp Motor Speed", group = "Concept")
@Disabled
public class ConceptRampMotorSpeed extends LinearOpMode {

    static final double INCREMENT   = 0.01;     // amount to ramp motor each CYCLE_MS cycle　每个CYCLE_MS周期激活电机的量
    static final int    CYCLE_MS    =   50;     // period of each cycle　每个周期的时间
    static final double MAX_FWD     =  1.0;     // Maximum FWD power applied to motor　施加到电机的最大FWD功率
    static final double MAX_REV     = -1.0;     // Maximum REV power applied to motor　施加到电机的最大ＲＥＶ功率

    // Define class members　定义分层
    DcMotor motor;
    double  power   = 0;
    boolean rampUp  = true;


    @Override
    public void runOpMode() {

        // Connect to motor (Assume standard left wheel)
        // Change the text in quotes to match any motor name on your robot.
        motor = hardwareMap.dcMotor.get("left_drive");

        // Wait for the start button
        telemetry.addData(">", "Press Start to run Motors." );
        telemetry.update();
        waitForStart();

        // Ramp motor speeds till stop pressed.
        while(opModeIsActive()) {

            // Ramp the motors, according to the rampUp variable.
            if (rampUp) {
                // Keep stepping up until we hit the max value.
                power += INCREMENT ;
                if (power >= MAX_FWD ) {
                    power = MAX_FWD;
                    rampUp = !rampUp;   // Switch ramp direction
                }
            }
            else {
                // Keep stepping down until we hit the min value.
                power -= INCREMENT ;
                if (power <= MAX_REV ) {
                    power = MAX_REV;
                    rampUp = !rampUp;  // Switch ramp direction
                }
            }

            // Display the current value
            telemetry.addData("Motor Power", "%5.2f", power);
            telemetry.addData(">", "Press Stop to end test." );
            telemetry.update();

            // Set the motor to the new power and pause;
            motor.setPower(power);
            sleep(CYCLE_MS);
            idle();
        }

        // Turn off motor and signal done;
        motor.setPower(0);
        telemetry.addData(">", "Done");
        telemetry.update();

    }
}
