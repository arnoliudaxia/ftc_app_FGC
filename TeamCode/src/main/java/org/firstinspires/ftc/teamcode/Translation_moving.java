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

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
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

@TeleOp(name="Translation_moving", group="Linear Opmode")
@Disabled
public class Translation_moving extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    DcMotor motor_zuoqian;//定义底盘电机
    DcMotor motor_youqian;
    DcMotor motor_zuohou;
    DcMotor motor_youhou;
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        motor_zuoqian  = hardwareMap.get(DcMotor.class, "motor_zuoqian");//获得底盘硬件设备
        motor_youqian = hardwareMap.get(DcMotor.class, "motor_youqian");
        motor_zuohou = hardwareMap.get(DcMotor.class, "motor_zuohou");
        motor_youhou = hardwareMap.get(DcMotor.class, "motor_youhou");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        motor_zuoqian.setDirection(DcMotor.Direction.FORWARD);//设置电机方向
        motor_zuohou.setDirection(DcMotor.Direction.FORWARD);
        motor_youqian.setDirection(DcMotor.Direction.REVERSE);
        motor_youhou.setDirection(DcMotor.Direction.REVERSE);

        double power_zuoqian;//定义底盘电机驱动功率
        double power_youqian;
        double power_zuohou;
        double power_youhou;

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        power_zuoqian = 0;//初始化电机驱动功率
        power_youqian = 0;
        power_zuohou = 0;
        power_youhou = 0;

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            if (gamepad1.left_stick_y != 0 || gamepad1.left_stick_x != 0) {//麦轮平移
                power_zuoqian = gamepad1.left_stick_y - gamepad1.left_stick_x;
                power_youqian = gamepad1.left_stick_y + gamepad1.left_stick_x;
                power_zuohou = gamepad1.left_stick_y + gamepad1.left_stick_x;
                power_youhou = gamepad1.left_stick_y - gamepad1.left_stick_x;

                motor_zuoqian.setPower(power_zuoqian);
                motor_youqian.setPower(-power_youqian);
                motor_zuohou.setPower(-power_zuohou);
                motor_youhou.setPower(-power_youhou);
            }

            if (gamepad1.left_bumper == true){//左平移
                motor_zuoqian.setPower(0.6);
                motor_youqian.setPower(0.6);
                motor_zuohou.setPower(-0.6);
                motor_youhou.setPower(0.6);
            }

            else if (gamepad1.right_bumper == true){//右平移
                motor_zuoqian.setPower(-0.6);
                motor_youqian.setPower(-0.6);
                motor_zuohou.setPower(0.6);
                motor_youhou.setPower(-0.6);
            }

            else {
                motor_zuoqian.setPower(0);
                motor_youqian.setPower(0);
                motor_zuohou.setPower(0);
                motor_youhou.setPower(0);
            }
            // Tank Mode uses one stick to control each wheel.
            // - This requires no math, but it is hard to drive forward slowly and keep straight.
            // leftPower  = -gamepad1.left_stick_y ;
            // rightPower = -gamepad1.right_stick_y ;

            // Send calculated power to wheels

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "zuoqian (%.2f), youqian (%.2f),zuohou (%.2f),youhou (%.2f)",power_zuoqian,power_youqian,power_zuohou,power_youhou);
            telemetry.update();
        }
    }
}
