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
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.external.samples.BasicOpMode_Linear;

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
public class fantastic extends TurningEchoHardwareConfig{

    private ElapsedTime runtime = new ElapsedTime();//计时

    public void runOpMode(){
        TurningEchoHardwareMap();
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        servoCatchBlock(0.35, 0.38);

        servoKickBall_2.setPosition(0.44);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            double PowerFL = motorFL.getPower();
            double PowerFR = motorFR.getPower();
            double PowerBL = motorBL.getPower();
            double PowerBR = motorBR.getPower();

                powerMode = switchPowerMode();//powerMode变量——SwitchpowerMode方法，powerMode变量将有1（正常速度）、2.5（慢速）的返回值

                if (gamepad1.left_stick_y != 0 || gamepad1.left_stick_x != 0 || gamepad1.left_trigger != 0 || gamepad1.right_trigger != 0) {//底盘平移
                    moveVar(-gamepad1.left_stick_y,gamepad1.left_stick_x,gamepad1.right_trigger - gamepad1.left_trigger,powerMode);
                }

                else {
                    moveVar(0,0,0,0);
                }

                if (gamepad1.dpad_up || gamepad1.dpad_left || gamepad1.dpad_right || gamepad1.dpad_down) {//dpad的底盘中速运行模式
                    if (gamepad1.dpad_up) {
                        moveFix(0.7,moveStatus.xF);
                    } else if (gamepad1.dpad_down) {
                        moveFix(0.7,moveStatus.xB);
                    } else if (gamepad1.dpad_left) {
                        moveFix(1,moveStatus.rL);
                    } else if (gamepad1.dpad_right) {
                        moveFix(1,moveStatus.rR);
                    }
                }

                if (gamepad1.left_bumper || gamepad1.right_bumper) {//左、右平移
                    if (gamepad1.left_bumper) {
                        moveFix(1,moveStatus.yL);
                    } else if (gamepad1.right_bumper) {
                        moveFix(1,moveStatus.yR);
                    }
                }

                if (gamepad2.left_trigger == 1 && gamepad2.right_trigger == 1) {//机械臂安全锁
                    armIns = false;

                    ServoBabyPosition_2 = 0.8;
                    servoCatchBaby_2.setPosition(ServoBabyPosition_2);

                    ServoBallPosition_2 = 0.44;
                    servoKickBall_2.setPosition(ServoBallPosition_2);
                }

                if (gamepad2.right_stick_button) {//初始化机械臂
                    armIns = true;
                    motorCatchBaby(0, 0);
                }

                if (gamepad2.x) {//夹持方块
                    servoCatchBlock(0.78, 0.0);
                } else if (gamepad2.b) {//松开方块
                    servoCatchBlock(0.37, 0.36);
                }

                if (gamepad2.y) {//抬升滑轨
                    lift(1);
                } else if (gamepad2.a) {//下降滑轨
                    lift(-1);
                } else {//卡住滑轨
                    lift(0.08);
                }

                //ARM!ARM!ARM!ARM!ARM!ARM!ARM!ARM!ARM!ARM!ARM!ARM!
                if (gamepad2.right_trigger != 0) {
                    if (ServoBabyPosition_1 < 1) {
                        if (gamepad2.right_trigger >= 0.9){//快速降机械臂第三节
                            ServoBabyPosition_1 = ServoBabyPosition_1 + 0.02;
                        }

                        else {
                            ServoBabyPosition_1 = ServoBabyPosition_1 + 0.01;//慢速降机械臂第三节
                        }
                    }
                    sleep(25);
                }

                if (gamepad2.left_trigger != 0) {
                    if (ServoBabyPosition_1 > 0) {
                        if (gamepad2.left_trigger >= 0.9){//快速升机械臂第三节
                            ServoBabyPosition_1 = ServoBabyPosition_1 - 0.02;
                        }

                        else {
                            ServoBabyPosition_1 = ServoBabyPosition_1 - 0.01;//慢速升机械臂第三节
                        }
                    }

                    sleep(25);
                }

                servoCatchBaby_1(ServoBabyPosition_1);

                if (gamepad2.right_bumper && !babyIns) {//机械臂末节夹持小人
                    servoCatchBaby_2(0.13);

                    ServoBabyPosition_2 = 0.13;

                    while (gamepad2.right_bumper){
                       babyIns =true;
                    }
                }

                if (gamepad2.right_bumper && babyIns) {//机械臂末节松开小人
                    servoCatchBaby_2(0.8);
                    ServoBabyPosition_2 = 0.8;

                    while (gamepad2.right_bumper){
                        babyIns =false;
                    }
                }

                if (gamepad2.left_bumper){
                    if (ServoBabyPosition_2 < 1){
                        ServoBabyPosition_2 = ServoBabyPosition_2 + 0.03;//慢速松末节机械臂夹子

                        servoCatchBaby_2(ServoBabyPosition_2);
                    }

                    while (gamepad2.left_bumper){
                        sleep(1);

                        babyIns =false;
                    }
                }

                if (gamepad2.dpad_up && !armIns) {
                    motorCatchBaby(0.8, -0.56);
                } else {
                    motorCatchBaby_1.setPower(0);
                }

                if (gamepad2.dpad_down && !armIns) {
                    motorCatchBaby(-0.8, 0.2);
                }

                if (gamepad1.y){
                    servoKickBall_1.setPosition(0.15);
                }

                if (gamepad1.a){
                    servoKickBall_1.setPosition(0.82);
                }

                if (gamepad1.x && ServoBallPosition_2 <= 1){
                    ServoBallPosition_2 = ServoBallPosition_2 +0.02;

                    sleep(20);
                }

                if (gamepad1.b && ServoBallPosition_2 >= 0){
                    ServoBallPosition_2 = ServoBallPosition_2 -0.02;

                    sleep(20);
                }

                servoKickBall_2.setPosition(ServoBallPosition_2);

                if(gamepad1.x && gamepad1.y && gamepad1.a && gamepad1.b){
                    break;
                }

                telemetry.addData("Status", "Run Time: " + runtime.toString());
                telemetry.addData("Motors", "zuoqian (%.2f), youqian (%.2f),zuohou (%.2f),youhou (%.2f)",PowerFL,PowerFR,PowerBL,PowerBR);
                telemetry.update();
        }
    }
}
