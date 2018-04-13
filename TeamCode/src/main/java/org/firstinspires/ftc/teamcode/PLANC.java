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
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import android.app.Activity;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.View;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import java.util.Locale;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.MatrixF;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.ArrayList;
import java.util.List;



@TeleOp(name="PLANC", group="Linear Opmode")
public class PLANC extends LinearOpMode {

    public static final String TAG = "Vuforia Navigation Sample";


    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    Servo servo_catching_block_1;
    Servo servo_catching_block_2;

    Servo servo_kicking_ball;

    DcMotor motor_raising;

    double servo_position_1 = 0.40;
    double servo_position_2 = 0.00;
    double power_raising = 0.50;



    //* private DcMotor rightDrive = null;
    DcMotor Leftfront;
    DcMotor Rightfront;
    DcMotor Leftrear;
    DcMotor Rightrear;

    double power1 = 0.8;

    public void Forward(Double power) {
        Leftfront.setPower(power);
        Rightfront.setPower(power);
        Leftrear.setPower(power);
        Rightrear.setPower(power);
    }

    public void Rear(Double power) {
        Leftfront.setPower(power);
        Leftrear.setPower(-power);
        Rightfront.setPower(-power);
        Rightrear.setPower(power);
    }

    public void Left(Double power) {
        Leftfront.setPower(-power);
        Leftrear.setPower(power);
        Rightfront.setPower(power);
        Rightrear.setPower(-power);
    }

    public void Right(Double power) {
        Leftfront.setPower(power);
        Leftrear.setPower(-power);
        Rightfront.setPower(-power);
        Rightrear.setPower(power);
    }

    public void Shunshi(Double power) {
        Leftfront.setPower(-power);
        Leftrear.setPower(-power);
        Rightfront.setPower(power);
        Rightrear.setPower(power);
    }

    public void Nishi(Double power) {
        Leftfront.setPower(power);
        Leftrear.setPower(power);
        Rightfront.setPower(-power);
        Rightrear.setPower(-power);
    }

    public void Catch(Double powera, Double powerb) {
        servo_position_1 = powera;
        servo_position_2 = powerb;
        servo_catching_block_1.setPosition(servo_position_1);
        servo_catching_block_2.setPosition(servo_position_2);
    }

    public void Release(Double powera, Double powerb) {
        servo_position_1 = powera;
        servo_position_2 = powerb;
        servo_catching_block_1.setPosition(servo_position_1);
        servo_catching_block_2.setPosition(servo_position_2);
    }



    @Override
    public void runOpMode() {



        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        servo_catching_block_1 = hardwareMap.servo.get("servo_catching_block_1");
        servo_catching_block_2 = hardwareMap.servo.get("servo_catching_block_2");

        motor_raising = hardwareMap.dcMotor.get("motor_raising");

        servo_kicking_ball = hardwareMap.get(Servo.class, "servo_kicking_ball");

        servo_catching_block_1.setPosition(servo_position_1);
        servo_catching_block_2.setPosition(servo_position_2);//init



        // Wait for the game to start (driver presses PLAY)

        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        Leftfront = hardwareMap.get(DcMotor.class, "Leftfront");
        Rightfront = hardwareMap.get(DcMotor.class, "Rightfront");
        Leftrear = hardwareMap.get(DcMotor.class, "Leftrear");
        Rightrear = hardwareMap.get(DcMotor.class, "Rightrear");
        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        Leftfront.setDirection(DcMotor.Direction.FORWARD);
        Leftrear.setDirection(DcMotor.Direction.FORWARD);
        Rightfront.setDirection(DcMotor.Direction.REVERSE);
        Rightrear.setDirection(DcMotor.Direction.REVERSE);
//////////////////////////////////////////////////////////////////////////////////////////////////////颜色传感器/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
        if (gamepad2.x) {
            servo_position_1 = 0.00;
            servo_position_2 = 0.40;
            servo_catching_block_1.setPosition(servo_position_1);
            servo_catching_block_2.setPosition(servo_position_2);
        } else if (gamepad2.b) {
            servo_position_1 = 0.30;
            servo_position_2 = 0.10;
            servo_catching_block_1.setPosition(servo_position_1);
            servo_catching_block_2.setPosition(servo_position_2);
        }

        if (gamepad2.y) {
            power_raising = 1.0;
            motor_raising.setPower(power_raising);
        } else  if (gamepad2.a) {
            power_raising = -1.0;
            motor_raising.setPower(power_raising);

        }else {
            power_raising = 0.08;
            motor_raising.setPower(power_raising);
        }

            if (gamepad1.left_trigger == 0) {
                if (gamepad1.left_stick_y < 0 && gamepad1.left_stick_x == 0) {
                    power1 = 0.8;

                    Leftfront.setPower(power1);
                    Rightfront.setPower(power1);
                    Leftrear.setPower(power1);
                    Rightrear.setPower(power1);
                }


                if (gamepad1.left_stick_y > 0 && gamepad1.left_stick_x == 0) {
                    power1 = 0.8;
                    Leftfront.setPower(-power1);
                    Leftrear.setPower(-power1);
                    Rightfront.setPower(-power1);
                    Rightrear.setPower(-power1);
                }
                //hou tui
                if (gamepad1.left_stick_x > 0 && gamepad1.left_stick_y == 0) {
                    power1 = 0.8;
                    Leftfront.setPower(power1);
                    Leftrear.setPower(-power1);
                    Rightfront.setPower(-power1);
                    Rightrear.setPower(power1);
                }

                if (gamepad1.left_stick_x < 0 && gamepad1.left_stick_y == 0) {
                    power1 = 0.8;
                    Leftfront.setPower(-power1);
                    Leftrear.setPower(power1);
                    Rightfront.setPower(power1);
                    Rightrear.setPower(-power1);
                }
                /*if (gamepad1.left_stick_x < 0 && gamepad1.left_stick_y < 0 && gamepad1.left_stick_y > -0.5) {
                    power1 = 0.8;
                    Leftfront.setPower(power1);
                    Leftrear.setPower(power1);
                    Rightfront.setPower(-power1);
                    Rightrear.setPower(power1);
                }//向左上
                if (gamepad1.left_stick_x < 0 && gamepad1.left_stick_y > 0 && gamepad1.left_stick_y < 0.5) {
                    power1 = 0.8;
                    Leftfront.setPower(-power1);
                    Leftrear.setPower(power1);
                    Rightfront.setPower(power1);
                    Rightrear.setPower(power1);
                }//向左下
                if (gamepad1.left_stick_x > 0 && gamepad1.left_stick_y > 0 && gamepad1.left_stick_y <0.5) {
                    power1 = 0.8;
                    Leftfront.setPower(power1);
                    Leftrear.setPower(-power1);
                    Rightfront.setPower(power1);
                    Rightrear.setPower(power1);
                }//向右下
                if (gamepad1.left_stick_x > 0 && gamepad1.left_stick_y < 0 && gamepad1.left_stick_y > -0.5) {
                    power1 = 0.8;
                    Leftfront.setPower(power1);
                    Leftrear.setPower(power1);
                    Rightfront.setPower(power1);
                    Rightrear.setPower(-power1);
                }//向右上*/
                if (gamepad1.left_stick_x == 0 && gamepad1.left_stick_y == 0) {
                    power1 = 0;
                    Leftfront.setPower(power1);
                    Leftrear.setPower(power1);
                    Rightfront.setPower(power1);
                    Rightrear.setPower(power1);
                }//stop}
                if (gamepad1.x) {
                    power1 = 0.7;
                    Leftfront.setPower(power1);
                    Leftrear.setPower(power1);
                    Rightfront.setPower(-power1);
                    Rightrear.setPower(-power1);
                }
                if (gamepad1.b) {
                    power1 = 0.7;
                    Leftfront.setPower(-power1);
                    Leftrear.setPower(-power1);
                    Rightfront.setPower(power1);
                    Rightrear.setPower(power1);
                } }
                else {
                if (gamepad1.y) {
                    power1 = 0.6;
                    Leftfront.setPower(power1);
                    Rightfront.setPower(power1);
                    Leftrear.setPower(power1);
                    Rightrear.setPower(power1);
                }
                if (gamepad1.a) {
                    power1 = 0.6;
                    Leftfront.setPower(power1);
                    Leftrear.setPower(-power1);
                    Rightfront.setPower(-power1);
                    Rightrear.setPower(power1);
                }
                //hou tui
                if (gamepad1.x) {
                    power1 = 0.6;
                    Leftfront.setPower(power1);
                    Leftrear.setPower(-power1);
                    Rightfront.setPower(-power1);
                    Rightrear.setPower(power1);
                }
                if (gamepad1.b) {
                    power1 = 0.6;
                    Leftfront.setPower(-power1);
                    Leftrear.setPower(power1);
                    Rightfront.setPower(power1);
                    Rightrear.setPower(-power1);
                } else {
                    power1 = 0;
                    Leftfront.setPower(-power1);
                    Leftrear.setPower(power1);
                    Rightfront.setPower(power1);
                    Rightrear.setPower(-power1);
                }}
                if(gamepad1.right_trigger != 0){
                    if (gamepad1.y) {
                     Forward(0.8);sleep(80);
                    }
                    if (gamepad1.a) {
                    Rear(0.8);sleep(80);
                    }
                    //hou tui
                    if (gamepad1.x) {
                    Left(0.8);sleep(80);
                    }
                    if (gamepad1.b) {
                    Right(0.8);sleep(80);
                    }
                    else{stop();}
                }
            }

        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        // telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
        telemetry.update();
    }

    /**
     * A simple utility that extracts positioning information from a transformation matrix
     * and formats it in a form palatable to a human being.
     */

    }

