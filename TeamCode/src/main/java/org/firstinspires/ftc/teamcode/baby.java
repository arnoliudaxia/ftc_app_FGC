/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="baby", group="Linear Opmode")
//@Disabled
public class baby extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    DcMotor motor_catching_baby_1;
    DcMotor motor_catching_baby_2;

    Servo servo_catching_baby_1;
    Servo servo_catching_baby_2;

    double power_baby_1;
    double power_baby_2;

    double servo_baby_position_1 = 0;
    double servo_baby_position_2 = 0.8;

    boolean safe_case = true;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        motor_catching_baby_1  = hardwareMap.get(DcMotor.class, "motor_catching_baby_1");
        motor_catching_baby_2 = hardwareMap.get(DcMotor.class, "motor_catching_baby_2");

        servo_catching_baby_1 = hardwareMap.get(Servo.class,"servo_catching_baby_1");
        servo_catching_baby_2 = hardwareMap.get(Servo.class,"servo_catching_baby_2");

        servo_catching_baby_1.setPosition(servo_baby_position_1);
        servo_catching_baby_2.setPosition(servo_baby_position_2);

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            if(gamepad2.left_trigger == 1 && gamepad2.right_trigger == 1){//机械臂安全锁
                safe_case = false;
            }

            if(gamepad2.right_stick_button){//初始化机械臂
                safe_case = true;

                motor_catching_baby_1.setPower(0);
                motor_catching_baby_2.setPower(0);
            }

            if(gamepad2.dpad_right){//降第三节机械臂
                if(servo_baby_position_1 <= 1){
                    servo_baby_position_1 = servo_baby_position_1 + 0.02;
                }
                sleep(50);
            }

            if(gamepad2.dpad_left){//抬第三节机械臂
                if(servo_baby_position_1 >= 0){
                    servo_baby_position_1 = servo_baby_position_1 - 0.02;
                }
                sleep(50);
            }

            if(gamepad2.right_bumper){//夹小人舵机
                servo_baby_position_2 = 0.15;
                servo_catching_baby_2.setPosition(servo_baby_position_2);
            }

            if(gamepad2.left_bumper){//松小人舵机
                servo_baby_position_2 = 0.8;
                servo_catching_baby_2.setPosition(servo_baby_position_2);
            }

            servo_catching_baby_1.setPosition(servo_baby_position_1);

            if(gamepad2.dpad_up && !safe_case){//伸出机械臂
                power_baby_1 = 0.8;
                power_baby_2 = -0.5;
                motor_catching_baby_1.setPower(power_baby_1);
                motor_catching_baby_2.setPower(power_baby_2);
            }

            else{
                motor_catching_baby_1.setPower(0);
            }

            if(gamepad2.dpad_down && !safe_case){//收回机械臂
                power_baby_1 = -0.8;
                power_baby_2 = 0.2;
                motor_catching_baby_1.setPower(power_baby_1);
                motor_catching_baby_2.setPower(power_baby_2);
            }

            //else {
                //motor_catching_baby_1.setPower(0);
                //motor_catching_baby_2.setPower(0);
            //}

            // Tank Mode uses one stick to control each wheel.
            // - This requires no math, but it is hard to drive forward slowly and keep straight.
            // leftPower  = -gamepad1.left_stick_y ;
            // rightPower = -gamepad1.right_stick_y ;

            // Send calculated power to wheels

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            //telemetry.addData("Motors", "zuoqian (%.2f), youqian (%.2f),zuohou (%.2f),youhou (%.2f)",power_zuoqian,power_youqian,power_zuohou,power_youhou);
            telemetry.update();
        }
    }
}
