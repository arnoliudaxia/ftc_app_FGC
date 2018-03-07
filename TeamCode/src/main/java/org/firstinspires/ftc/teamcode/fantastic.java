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

@TeleOp(name="fantastic", group="Linear Opmode")
//@Disabled
public class fantastic extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    DcMotor motor_zuoqian;
    DcMotor motor_youqian;
    DcMotor motor_zuohou;
    DcMotor motor_youhou;

    DcMotor motor_catching_baby_1;
    DcMotor motor_catching_baby_2;

    Servo servo_catching_baby_1;
    Servo servo_catching_baby_2;

    Servo servo_catching_block_1;
    Servo servo_catching_block_2;

    DcMotor motor_raising;

    double servo_block_position_1 = 0.35;
    double servo_block_position_2 = 0.15;
    double power_raising = 0.50;

    double power_zuoqian;
    double power_youqian;
    double power_zuohou;
    double power_youhou;

    double servo_baby_position_1 = 0;
    double servo_baby_position_2 = 0.8;

    boolean arm_safe_case = true;
    boolean catching_baby_case = false;
    boolean robot_case_1 = false;
    boolean robot_case_2 = false;

    double PowerMode = 1;

    public void qianjin(double power,double power_mode){
        double FinalPower = Range.clip(power/power_mode, 0, 1);
        motor_zuoqian.setPower(-FinalPower);
        motor_youqian.setPower(FinalPower);
        motor_zuohou.setPower(FinalPower);
        motor_youhou.setPower(FinalPower);
    }

    public void houtui(double power,double power_mode){
        double FinalPower = Range.clip(power/power_mode, 0, 1);
        motor_zuoqian.setPower(FinalPower);
        motor_youqian.setPower(-FinalPower);
        motor_zuohou.setPower(-FinalPower);
        motor_youhou.setPower(-FinalPower);
    }

    public void zuopingyi(double power,double power_mode){
        double FinalPower = Range.clip(power/power_mode, 0, 1);
        motor_zuoqian.setPower(FinalPower);
        motor_youqian.setPower(FinalPower);
        motor_zuohou.setPower(FinalPower);
        motor_youhou.setPower(-FinalPower);
    }

    public void youpingyi(double power,double power_mode){
        double FinalPower = Range.clip(power/power_mode, 0, 1);
        motor_zuoqian.setPower(-FinalPower);
        motor_youqian.setPower(-FinalPower);
        motor_zuohou.setPower(-FinalPower);
        motor_youhou.setPower(FinalPower);
    }

    public void zuozhuan(double power,double power_mode){
        double FinalPower = Range.clip(power/power_mode, -1, 1);
        motor_zuoqian.setPower(FinalPower);
        motor_youqian.setPower(FinalPower);
        motor_zuohou.setPower(-FinalPower);
        motor_youhou.setPower(FinalPower);
    }

    public void youzhuan(double power,double power_mode){
        double FinalPower = Range.clip(power/power_mode, -1, 1);
        motor_zuoqian.setPower(-FinalPower);
        motor_youqian.setPower(-FinalPower);
        motor_zuohou.setPower(FinalPower);
        motor_youhou.setPower(-FinalPower);
    }

    public void catching_block(double servo_block_position_1,double servo_block_position_2){
        servo_catching_block_1.setPosition(servo_block_position_1);
        servo_catching_block_2.setPosition(servo_block_position_2);
    }

    public void motor_catching_baby(double power_baby_1,double power_baby_2){
        motor_catching_baby_1.setPower(power_baby_1);
        motor_catching_baby_2.setPower(power_baby_2);
    }

    public void servo_catching_baby_1(double servo_baby_position_1){
        servo_catching_baby_1.setPosition(servo_baby_position_1);
    }

    public void servo_catching_baby_2(double servo_baby_position_2){
        servo_catching_baby_2.setPosition(servo_baby_position_2);
    }

    public void raising(double power_raising){
        motor_raising.setPower(power_raising);
    }

    public double switch_PowerMode(){
        if(gamepad1.x){
            return (2.5);
        }

        else if(gamepad1.a){
            return (1);
        }

        else {
            return (PowerMode);
        }

    }

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        motor_zuoqian  = hardwareMap.get(DcMotor.class, "motor_zuoqian");
        motor_youqian = hardwareMap.get(DcMotor.class, "motor_youqian");
        motor_zuohou = hardwareMap.get(DcMotor.class, "motor_zuohou");
        motor_youhou = hardwareMap.get(DcMotor.class, "motor_youhou");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        motor_zuoqian.setDirection(DcMotor.Direction.FORWARD);
        motor_zuohou.setDirection(DcMotor.Direction.FORWARD);
        motor_youqian.setDirection(DcMotor.Direction.REVERSE);
        motor_youhou.setDirection(DcMotor.Direction.REVERSE);

        servo_catching_block_1 = hardwareMap.servo.get("servo_catching_block_1");
        servo_catching_block_2 = hardwareMap.servo.get("servo_catching_block_2");

        motor_raising = hardwareMap.dcMotor.get("motor_raising");

        motor_catching_baby_1  = hardwareMap.get(DcMotor.class, "motor_catching_baby_1");
        motor_catching_baby_2 = hardwareMap.get(DcMotor.class, "motor_catching_baby_2");

        servo_catching_baby_1 = hardwareMap.get(Servo.class,"servo_catching_baby_1");
        servo_catching_baby_2 = hardwareMap.get(Servo.class,"servo_catching_baby_2");

        servo_catching_baby_2.setPosition(servo_baby_position_2);

        servo_catching_block_1.setPosition(servo_block_position_1);
        servo_catching_block_2.setPosition(servo_block_position_2);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        power_zuoqian = 0;
        power_youqian = 0;
        power_zuohou = 0;
        power_youhou = 0;

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
                PowerMode = switch_PowerMode();

                if (gamepad1.left_stick_y != 0 || gamepad1.left_stick_x != 0) {
                    power_zuoqian = gamepad1.left_stick_y - gamepad1.left_stick_x;
                    power_youqian = gamepad1.left_stick_y + gamepad1.left_stick_x;
                    power_zuohou = gamepad1.left_stick_y + gamepad1.left_stick_x;
                    power_youhou = gamepad1.left_stick_y - gamepad1.left_stick_x;

                    motor_zuoqian.setPower(power_zuoqian / PowerMode);
                    motor_youqian.setPower(-power_youqian / PowerMode);
                    motor_zuohou.setPower(-power_zuohou / PowerMode);
                    motor_youhou.setPower(-power_youhou / PowerMode);
                }

                if (gamepad1.dpad_up || gamepad1.dpad_left || gamepad1.dpad_right || gamepad1.dpad_down) {
                    if (gamepad1.dpad_up) {
                        qianjin(0.7, PowerMode);
                    } else if (gamepad1.dpad_down) {
                        houtui(0.7, PowerMode);
                    } else if (gamepad1.dpad_left) {
                        zuopingyi(0.7, PowerMode);
                    } else if (gamepad1.dpad_right) {
                        youpingyi(0.7, PowerMode);
                    }
                }


                if (gamepad1.left_bumper || gamepad1.right_bumper) {
                    if (gamepad1.left_bumper) {
                        zuopingyi(1, 1);
                    } else if (gamepad1.right_bumper) {
                        youpingyi(1, 1);
                    }
                }

                if (gamepad1.left_trigger != 0 || gamepad1.right_trigger != 0) {
                    zuozhuan(gamepad1.left_trigger - gamepad1.right_trigger, PowerMode+0.5);
                } else {
                    qianjin(0, PowerMode);
                }



                if (gamepad2.left_trigger == 1 && gamepad2.right_trigger == 1) {
                    arm_safe_case = false;
                }

                if (gamepad2.right_stick_button) {
                    arm_safe_case = true;
                    motor_catching_baby(0, 0);
                }

                if (gamepad2.x) {
                    catching_block(0.00, 0.5);
                } else if (gamepad2.b) {
                    catching_block(0.35, 0.15);
                }

                if (gamepad2.y) {
                    raising(1);
                } else if (gamepad2.a) {
                    raising(-1);
                } else {
                    raising(0.08);
                }

                //ARM!ARM!ARM!ARM!ARM!ARM!ARM!ARM!ARM!ARM!ARM!ARM!
                if (gamepad2.right_trigger != 0) {
                    if (servo_baby_position_1 <= 1) {
                        servo_baby_position_1 = servo_baby_position_1 + 0.02;
                    }
                    sleep(50);
                }

                if (gamepad2.left_trigger != 0) {
                    if (servo_baby_position_1 >= 0) {
                        servo_baby_position_1 = servo_baby_position_1 - 0.02;
                    }
                    sleep(50);
                }

                servo_catching_baby_1(servo_baby_position_1);

                if (gamepad2.right_bumper && !catching_baby_case) {
                    servo_catching_baby_2(0.12);
                    catching_baby_case =true;

                    while (gamepad2.right_bumper){
                        sleep(100);
                    }
                }

                if (gamepad2.right_bumper && catching_baby_case) {
                    servo_catching_baby_2(0.8);
                    catching_baby_case =false;

                    while (gamepad2.right_bumper){
                        sleep(100);
                    }
                }

                if (gamepad2.dpad_up && !arm_safe_case) {
                    motor_catching_baby(0.8, -0.48);
                } else {
                    motor_catching_baby_1.setPower(0);
                }

                if (gamepad2.dpad_down && !arm_safe_case) {
                    motor_catching_baby(-0.8, 0.2);
                }

                if(gamepad1.x && gamepad1.y && gamepad1.a && gamepad1.b){
                    break;
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
                telemetry.addData("Motors", "zuoqian (%.2f), youqian (%.2f),zuohou (%.2f),youhou (%.2f)", power_zuoqian, power_youqian, power_zuohou, power_youhou);
                telemetry.update();
        }
    }
}
