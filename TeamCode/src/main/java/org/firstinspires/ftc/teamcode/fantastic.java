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
 * NO EYPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
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

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 * <p>
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 * <p>
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name = "fantastic", group = "Linear Opmode")
//@Disabled
public class fantastic extends TurningEchoHardware {

    private ElapsedTime runtime = new ElapsedTime();//计时

    double ceshi = 0;
    double ceshi2 = 0;

    public void runOpMode() {
        TurningEchoHardwareConfigure();
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        servoCatchBlock(0.35, 0.38);

        servoKickBall_2.setPosition(0.44);

        tripodHead.setPosition(tripodHeadPosition);



        // Set up the parameters with which we will use our IMU. Note that integration
        // algorithm here just reports accelerations to the logcat log; it doesn't actually
        // provide positional information.
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
        // and named "imu".
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        // Set up our telemetry dashboard
        //composeTelemetry();

        waitForStart();
        runtime.reset();

        imu.startAccelerationIntegration(new Position(), new Velocity(), 1000);

        while (opModeIsActive()) {

            double PowerFL = motorFL.getPower();
            double PowerFR = motorFR.getPower();
            double PowerBL = motorBL.getPower();
            double PowerBR = motorBR.getPower();

            powerMode = switchPowerMode();//powerMode变量——SwitchpowerMode方法，powerMode变量将有1（正常速度）、2.5（慢速）的返回值

            frameContorl();

            /*if (gamepad1.dpad_up || gamepad1.dpad_left || gamepad1.dpad_right || gamepad1.dpad_down) {//dpad的底盘中速运行模式
                if (gamepad1.dpad_up) {
                    moveFix(0.7,moveStatus.xF);
                } else if (gamepad1.dpad_down) {
                    moveFix(0.7,moveStatus.xB);
                } else if (gamepad1.dpad_left) {
                    moveFix(1,moveStatus.rL);
                } else if (gamepad1.dpad_right) {
                    moveFix(1,moveStatus.rR);
                }
            }*/
            if (gamepad1.left_bumper || gamepad1.right_bumper) {//左、右平移
                if (gamepad1.left_bumper) {
                    moveFix(1, moveStatus.yL);
                } else if (gamepad1.right_bumper) {
                    moveFix(1, moveStatus.yR);
                }
            }

            if (gamepad2.left_trigger == 1 && gamepad2.right_trigger == 1) {//机械臂安全锁
                armIns = false;
                servoBabyPosition_2 = 0.8;
                servoCatchBaby_2.setPosition(servoBabyPosition_2);

                servoBallPosition_2 = 0.44;
                servoKickBall_2.setPosition(servoBallPosition_2);
            }

            if (gamepad2.right_stick_button) {//初始化机械臂
                armIns = true;
                motorCatchBaby(0, 0);
            }

            if (gamepad1.x) {//夹持方块
                servoCatchBlock(0.78, 0.0);
            } else if (gamepad1.b) {//松开方块
                servoCatchBlock(0.37, 0.36);
            }

            if (gamepad1.y) {//抬升滑轨
                lift(1);
            } else if (gamepad1.a) {//下降滑轨
                lift(-1);
            } else {//卡住滑轨
                lift(0.08);
            }
            //ARM!ARM!ARM!ARM!ARM!ARM!ARM!ARM!ARM!ARM!ARM!ARM!
            if (gamepad2.right_trigger != 0) {
                if (servoBabyPosition_1 < 1) {
                    if (gamepad2.right_trigger >= 0.9) {//快速降机械臂第三节
                        servoBabyPosition_1 = servoBabyPosition_1 + 0.02;
                    } else {
                        servoBabyPosition_1 = servoBabyPosition_1 + 0.01;//慢速降机械臂第三节
                    }
                }
                sleep(25);
            }

            if (gamepad2.left_trigger != 0) {
                if (servoBabyPosition_1 > 0) {
                    if (gamepad2.left_trigger >= 0.9) {//快速升机械臂第三节
                        servoBabyPosition_1 = servoBabyPosition_1 - 0.02;
                    } else {
                        servoBabyPosition_1 = servoBabyPosition_1 - 0.01;//慢速升机械臂第三节
                    }
                }
                sleep(25);
            }

            servoCatchBaby_1(servoBabyPosition_1);

            if (gamepad2.right_bumper && !babyIns) {//机械臂末节夹持小人
                servoCatchBaby_2(0.13);
                servoBabyPosition_2 = 0.13;
                while (gamepad2.right_bumper) {
                    babyIns = true;
                }
            }
            if (gamepad2.right_bumper && babyIns) {//机械臂末节松开小人
                servoCatchBaby_2(0.8);
                servoBabyPosition_2 = 0.8;
                while (gamepad2.right_bumper) {
                    babyIns = false;
                    idle();
                }
            }
            if (gamepad2.left_bumper) {
                if (servoBabyPosition_2 < 1) {
                    servoBabyPosition_2 = servoBabyPosition_2 + 0.03;//慢速松末节机械臂夹子
                    servoCatchBaby_2(servoBabyPosition_2);
                }

                while (gamepad2.left_bumper) {
                    idle();
                    babyIns = false;
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
            if (gamepad1.dpad_up) {
                servoKickBall_1.setPosition(0.15);
            }
            if (gamepad1.dpad_down) {
                servoKickBall_1.setPosition(0.82);
            }

            if (gamepad1.dpad_left && servoBallPosition_2 <= 1) {
                servoBallPosition_2 = servoBallPosition_2 + 0.02;
                sleep(20);
            }

            if (gamepad1.dpad_right && servoBallPosition_2 >= 0) {
                servoBallPosition_2 = servoBallPosition_2 - 0.02;
                sleep(20);
            }

            servoKickBall_2.setPosition(servoBallPosition_2);

            if (gamepad1.right_stick_y < -0.5){
                tripodHeadPosition = tripodHeadPosition + 0.02;
                tripodHead.setPosition(tripodHeadPosition);
                sleep(25);
            }

            else if (gamepad1.right_stick_y > 0.5){
                tripodHeadPosition = tripodHeadPosition - 0.02;
                tripodHead.setPosition(tripodHeadPosition);
                sleep(25);
            }

            if (gamepad1.x && gamepad1.y && gamepad1.a && gamepad1.b) {
                break;
            }

            if (gamepad1.right_stick_button){
                double Y = Range.clip(0,-10,10);
                double blankX = Range.clip(0,-0.8,0.8);
                double rPower = 0;

                double yPower;
                double xPower = Range.clip(0,-0.3,0.3);

                while (true){
                    angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                    gravity = imu.getGravity();
                    Y = Double.parseDouble(formatAngle(angles.angleUnit,angles.thirdAngle)) + 0.9;
                    blankX = Double.parseDouble(formatAngle(angles.angleUnit,angles.secondAngle)) + 0.5;

                    ceshi = Y;

                    yPower = -1.2468324983583301e-18*Y*Y*Y*Y-0.00015625000000000957*Y*Y*Y+6.938893903907228e-18*Y*Y+0.09562500000000004*Y;

                    ceshi2 = yPower;

                    //moveVar(yPower,0,0,1);
                    telemetry.addData("blankY",ceshi);
                    telemetry.addData("yPower",ceshi2);
                    telemetry.update();

                    if ((!gamepad1.right_stick_button) && (!gamepad1.left_stick_button)){
                        frameStop();
                        break;
                    }
                }
            }

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "zuoqian (%.2f), youqian (%.2f),zuohou (%.2f),youhou (%.2f)", PowerFL, PowerFR, PowerBL, PowerBR);
            telemetry.update();
        }
    }
}
