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

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.ConceptVuforiaNavigation;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.Locale;

/**
 * This 2016-2017 OpMode illustrates the basics of using the Vuforia localizer to determine
 * positioning and orientation of robot on the FTC field.
 * The code is structured as a LinearOpMode
 *
 * Vuforia uses the phone's camera to inspect it's surroundings, and attempt to locate target images.
 *
 * When images are located, Vuforia is able to determine the position and orientation of the
 * image relative to the camera.  This sample code than combines that information with a
 * knowledge of where the target images are on the field, to determine the location of the camera.
 *
 * This example assumes a "diamond" field configuration where the red and blue alliance stations
 * are adjacent on the corner of the field furthest from the audience.
 * From the Audience perspective, the Red driver station is on the right.
 * The two vision target are located on the two walls closest to the audience, facing in.
 * The Stones are on the RED side of the field, and the Chips are on the Blue side.
 *
 * A final calculation then uses the location of the camera on the robot to determine the
 * robot's location and orientation on the field.
 *
 * @see VuforiaLocalizer
 * @see VuforiaTrackableDefaultListener
 * see  ftc_app/doc/tutorial/FTC_FieldCoordinateSystemDefinition.pdf
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list.
 *
 * IMPORTANT: In order to use this OpMode, you need to obtain your own Vuforia license key as
 * is explained below.
 */


/**
 * This OpMode illustrates the basics of using the Vuforia engine to determine
 * the identity of Vuforia VuMarks encountered on the field. The code is structured as
 * a LinearOpMode. It shares much structure with {@link ConceptVuforiaNavigation}; we do not here
 * duplicate the core Vuforia documentation found there, but rather instead focus on the
 * differences between the use of Vuforia for navigation vs VuMark identification.
 *
 * @see ConceptVuforiaNavigation
 * @see VuforiaLocalizer
 * @see VuforiaTrackableDefaultListener
 * see  ftc_app/doc/tutorial/FTC_FieldCoordinateSystemDefinition.pdf
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list.
 *
 * IMPORTANT: In order to use this OpMode, you need to obtain your own Vuforia license key as
 * is explained in {@link ConceptVuforiaNavigation}.
 */

@Autonomous(name="TeamRightRed_left", group ="Concept")
//@Disabled
public class TeamRightRed_left extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    public static final String TAG = "Vuforia VuMark Sample";
    OpenGLMatrix lastLocation = null;


    TouchSensor touchSensor;
    ColorSensor sensorColor;
    DistanceSensor sensorDistance;


    Servo servo_catching_block_1;
    Servo servo_catching_block_2;

    Servo servo_kicking_ball;


    Servo servo_kicking_ball2;
    DcMotor motor_raising;

    double servo_position_1 = 0.40;
    double servo_position_2 = 0.00;
    double power_raising = 0.50;
    double servo_position_ball = 0.1;

    //* private DcMotor rightDrive = null;
    DcMotor Leftfront;
    DcMotor Rightfront;
    DcMotor Leftrear;
    DcMotor Rightrear;

    int counter = 0;
    double power1 = 0.8;

    public void Ball1() {
        if (servo_position_ball >= 0.2) {
            servo_position_ball = servo_position_ball - 0.02;
            servo_kicking_ball.setPosition(servo_position_ball);

        }
        sleep(50);
    }

    public void Ball2() {
        if (servo_position_ball <= 0.8) {
            servo_position_ball = servo_position_ball + 0.02;

            servo_kicking_ball.setPosition(servo_position_ball);
            servo_kicking_ball2.setPosition(0.85);

        }
        sleep(50);
    }

    public void Raisng() {
        power_raising = 1.0;
        motor_raising.setPower(power_raising);
    }

    public void Down() {
        power_raising = -1.0;
        motor_raising.setPower(power_raising);
    }

    public void Stable() {
        power_raising = 0.08;
        motor_raising.setPower(power_raising);
    }

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

    public void Stop() {
        Leftfront.setPower(0.0);
        Leftrear.setPower(0.0);
        Rightfront.setPower(0.0);
        Rightrear.setPower(0.0);
    }




    @Override
    public void runOpMode() {
        /*
         * To start up Vuforia, tell it the view that we wish to use for camera monitor (on the RC phone);
         * If no camera monitor is desired, use the parameterless constructor instead (commented out below).
         */
///////////////////////////////////////////////////////////////////////////////////////////////////Vuforia/////////////////////////////////////////////////////



        servo_catching_block_1 = hardwareMap.servo.get("servo_catching_block_1");
        servo_catching_block_2 = hardwareMap.servo.get("servo_catching_block_2");

        motor_raising = hardwareMap.dcMotor.get("motor_raising");


        servo_kicking_ball2 =hardwareMap.servo.get("servo_kicking_ball2");
        servo_kicking_ball2.setPosition(0.15);

        servo_kicking_ball = hardwareMap.get(Servo.class, "servo_kicking_ball");



        servo_kicking_ball.setPosition(servo_position_ball);

        Catch(0.30, 0.10);//init

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
        telemetry.addData(">", "按 Play 就开始");
        telemetry.update();


        waitForStart();


//////////////////////////////////////////////////////////////////////////////////////////////////////颜色传感器/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        sensorColor = hardwareMap.get(ColorSensor.class, "sensor_color_distance");
        // get a reference to the distance sensor that shares the same name.
        sensorDistance = hardwareMap.get(DistanceSensor.class, "sensor_color_distance");
        // hsvValues is an array that will hold the hue, saturation, and value information.
        float hsvValues[] = {0F, 0F, 0F};
        // values is a reference to the hsvValues array.
        final float values[] = hsvValues;
        // sometimes it helps to multiply the raw RGB values with a scale factor
        // to amplify/attentuate the measured values.
        final double SCALE_FACTOR = 255;
        // get a reference to the RelativeLayout so we can change the background
        // color of the Robot Controller app to match the hue detected by the RGB sensor.
        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);
//////////////////////////////////////////////////////////////////////////////////////////////////触觉传感器/////////////////////////////////////////////////////////
        touchSensor = hardwareMap.get(TouchSensor.class, "sensor_touch");
        int counter = 0;
        waitForStart();
        runtime.reset();
        // run until the end of the match (driver presses STOP)
///////////////////////////////////////////////////////////////////////////////////////////////////主程序///////////////////////////////////////////////////////
        while (opModeIsActive()) {
            /**
             * See if any of the instances of {@link relicTemplate} are currently visible.
             * {@link RelicRecoveryVuMark} is an enum which can have the following values:
             * UNKNOWN, LEFT, CENTER, and RIGHT. When a VuMark is visible, something other than
             * UNKNOWN will be returned by {@link RelicRecoveryVuMark#from(VuforiaTrackable)}.
             */

            /* if (digitalTouch.getState()) {
                telemetry.addData("Digital Touch", "Is Not Pressed");
            } else {
                telemetry.addData("Digital Touch", "Is Pressed");
            }*/
///////////////////////////////////////////////////////////////////////////////////////////////////Vuforia/////////////////////////////////////////////////////////////








///////////////////////////////////////////////////////////////////////////////////////////////////触觉传感器////////////////////////////////////////////////
                if (touchSensor.isPressed())
                    telemetry.addData("Touch", "Is Pressed");
                else
                    telemetry.addData("Touch", "Is Not Pressed");

                telemetry.update();

                // convert the RGB values to HSV values.
                // multiply by the SCALE_FACTOR.
                // then cast it back to int (SCALE_FACTOR is a double)
                Color.RGBToHSV((int) (sensorColor.red() * SCALE_FACTOR),
                        (int) (sensorColor.green() * SCALE_FACTOR),
                        (int) (sensorColor.blue() * SCALE_FACTOR),
                        hsvValues);

                // send the info back to driver station using telemetry function.
                telemetry.addData("Distance (cm)",
                        String.format(Locale.US, "%.02f", sensorDistance.getDistance(DistanceUnit.CM)));
                telemetry.addData("Alpha", sensorColor.alpha());
                telemetry.addData("Red  ", sensorColor.red());
                telemetry.addData("Blue ", sensorColor.blue());
                telemetry.addData("Hue", hsvValues[0]);

                // change the background color to match the color detected by the RGB sensor.
                // pass a reference to the hue, saturation, and value array as an argument
                // to the HSVToColor method.
                relativeLayout.post(new Runnable() {
                    public void run() {
                        relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values));
                    }
                });

                telemetry.update();
            }

            // Set the panel back to the default color
            relativeLayout.post(new Runnable() {
                public void run() {
                    relativeLayout.setBackgroundColor(Color.WHITE);
                }
            });
/////////////////////////////////////////////////////////////////////////////////////////////// Automatic /////////////////////////////////////////////////////////////////
                Right(0.8);
                sleep(100);
                Stop();
                sleep(500);
                Ball1();
                if (sensorColor.red() > sensorColor.blue()) {

                    servo_kicking_ball2.setPosition(0.00);
                    stop();
                    sleep(500);

                    Ball2();
                    stop();
                    sleep(500);




/////////////////////////////////////////////

                    Forward(0.8);
                    sleep(400);
                    stop();
                    sleep(500);



                    Left(0.8);
                    sleep(200);
                    stop();
                    sleep(500);

                        Left(0.8);
                        sleep(920);
                        stop();
                        sleep(500);


                }
               else {

                    servo_kicking_ball2.setPosition(0.30);
                    stop();
                    sleep(500);

                    Ball2();
                    stop();
                    sleep(500);
                /////////////////////////////

                    Forward(0.8);
                    sleep(400);
                    stop();
                    sleep(500);



                    Left(0.8);
                    sleep(200);
                    stop();
                    sleep(500);

                        Left(0.8);
                        sleep(920);
                        stop();
                        sleep(500);




                }




                Stop();
                sleep(10);


/*
            switch ("Touch") {
                    case "Is Pressed":
                        while (counter < 2) {
                            counter = counter + 1;
                            Stop();
                            sleep(10);
                            Catch(0.30, 0.10);
                            sleep(50);
                            Nishi(0.8);
                            sleep(80);
                            Left(0.8);
                            sleep(20*counter);
                            Forward(0.8);
                            sleep(350);
                            Left(0.4);
                            sleep(20);
                            Right(0.4);
                            sleep(30);
                            Release(0.35, 0.15);
                            sleep(20);
                            Release(0.40,0.00);
                            sleep(10);
                            Rear(0.8);
                            sleep(200);
                            Shunshi(0.8);
                            sleep(80);
                        }
                        while (counter < 4 && counter > 2) {
                            Stop();
                            sleep(10);
                            Catch(0.30, 0.10);
                            sleep(50);
                            Nishi(0.8);
                            sleep(80);
                            Left(0.8);
                            sleep(20*counter);
                            Forward(0.8);
                            sleep(300);
                            Raisng();//////////////////////////////////////////////////
                            sleep(50);
                            Stable();//////////////////////////////////////////////////
                            Left(0.4);
                            sleep(20);
                            Right(0.4);
                            sleep(30);
                            Release(0.35, 0.15);
                            sleep(20);
                            Release(0.40,0.00);
                            sleep(10);
                            Down();
                            Rear(0.8);
                            sleep(50);
                            Shunshi(0.8);
                            sleep(80);
                            counter = counter + 1;
                        }
                    case "Is Not Pressed":
                        Forward(0.8);   }*/

                        if (runtime.equals(30)) { stop();}

            else {
                telemetry.addData("VuMark", "not visible");
                telemetry.addData("Status", "Run Time: " + runtime.toString());
                // telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            }
            telemetry.update();
        }



    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
}