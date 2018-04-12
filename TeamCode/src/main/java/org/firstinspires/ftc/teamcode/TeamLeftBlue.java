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
import android.text.method.Touch;
import android.view.View;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.TouchSensor;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import java.util.Locale;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;


@Autonomous(name="TeamLeftBlue", group ="Concept")
//@Disabled
public class TeamLeftBlue extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    public static final String TAG = "Vuforia VuMark Sample";
    OpenGLMatrix lastLocation = null;


    VuforiaLocalizer vuforia;
    TouchSensor touchSensor;
    ColorSensor sensorColor;
    DistanceSensor sensorDistance;


    Servo servo_catching_block_1;
    Servo servo_catching_block_2;

    Servo servo_kicking_ball;

    DcMotor motor_raising;

    double servo_position_1 = 0.40;
    double servo_position_2 = 0.00;
    double power_raising = 0.50;
    double servo_position_ball = 1;

    //* private DcMotor rightDrive = null;
    DcMotor Leftfront;
    DcMotor Rightfront;
    DcMotor Leftrear;
    DcMotor Rightrear;

    int counter = 0;
    double power1 = 0.8;

    public void Ball1() {
        if (servo_position_ball <= 1) {
            servo_position_ball = servo_position_ball + 0.02;
        }
        sleep(50);
    }

    public void Ball2() {
        if (servo_position_ball >= 0) {
            servo_position_ball = servo_position_ball - 0.02;
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

///////////////////////////////////////////////////////////////////////////////////////////////////Vuforia/////////////////////////////////////////////////////

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "AXQcjoz/////AAABmZHq2ALQ5EDvr0pzYI/5xQECaIBP91rdgvvcHBhGafOACEo+OAVegw1I17Nerx7pn1DrJUPHJpMfw3bQDeC7m6G/IqWOb1348XHM1HjY1tspPk7koyhWM4DKCQKfvWZZfvZ0EcZeXY4eJ5I/Ytm7o+yZTGQ50FcAbqYac0yL7M/iifDK8NYnDOaEs0bKm9gYp6TJvLblJBPqjRoGfZU288tqMT2ZxiIaqY8n4EsM0t3OuJh8aKWZF9qGJ1I879nu2MCbERZr1akfHR1uimsrgKHl05BbZmcXrIYjGgWD3BirUAU26fqyUraTtD2ms1EV1XwVTwnrhQdzxbOaO056NFgNv1HmTxQSoJ28qIuTD+f3";


        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;//表示调用后置摄像头；
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);


        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary
        //加载数据

        servo_catching_block_1 = hardwareMap.servo.get("servo_catching_block_1");
        servo_catching_block_2 = hardwareMap.servo.get("servo_catching_block_2");

        motor_raising = hardwareMap.dcMotor.get("motor_raising");

        servo_kicking_ball = hardwareMap.get(Servo.class, "servo_kicking_ball");

        Catch(0.00, 0.40);//init

        servo_kicking_ball.setPosition(servo_position_ball);

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
        telemetry.addData(">", "按 Play 就开始");
        telemetry.update();


        waitForStart();

        relicTrackables.activate();
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
           /* if (digitalTouch.getState()) {
                telemetry.addData("Digital Touch", "Is Not Pressed");
            } else {
                telemetry.addData("Digital Touch", "Is Pressed");
            }*/
///////////////////////////////////////////////////////////////////////////////////////////////////Vuforia/////////////////////////////////////////////////////////////


            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {


                telemetry.addData("VuMark", "%s visible", vuMark);


                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) relicTemplate.getListener()).getPose();
                telemetry.addData("Pose", format(pose));

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
            Ball1();
            Right(0.8);
            sleep(40);
                if (sensorColor.red() > sensorColor.blue()) {

                    Shunshi(0.8);
                    sleep(90);
                    Ball2();
                    Nishi(0.8);
                    sleep(90);
                }
                else  {
                    

                    Nishi(0.8);
                    sleep(90);
                    Ball2();
                    Shunshi(0.8);
                    sleep(90);
                }
                stop();
                sleep(10);
                if (vuMark == RelicRecoveryVuMark.LEFT) {
                    Rear(0.8);
                    sleep(180);
                    Left(0.8);
                    sleep(50);
                    Nishi(0.8);
                    sleep(100);
                    Forward(0.8);
                    sleep(60);
                    Right(0.8);
                    sleep(30);
                    Left(0.8);
                    sleep(30);
                    Release(0.40, 0.00);
                }
                if (vuMark == RelicRecoveryVuMark.CENTER) {
                    Rear(0.8);
                    sleep(180);
                    Left(0.8);
                    sleep(100);
                    Nishi(0.8);
                    sleep(100);
                    Forward(0.8);
                    sleep(60);
                    Right(0.8);
                    sleep(30);
                    Left(0.8);
                    sleep(30);
                    Release(0.40, 0.00);
                }

                if (vuMark == RelicRecoveryVuMark.RIGHT) {
                    Rear(0.8);
                    sleep(180);
                    Left(0.8);
                    sleep(150);
                    Nishi(0.8);
                    sleep(100);
                    Forward(0.8);
                    sleep(60);
                    Right(0.8);
                    sleep(30);
                    Left(0.8);
                    sleep(30);
                    Release(0.40, 0.00);
                }
                Rear(0.8);
                sleep(300);
                Nishi(0.6);
                sleep(100);
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
                            Left(0.6);
                            sleep(20);
                            Right(0.69);
                            sleep(30);
                            Release(0.35, 0.15);
                            sleep(20);
                            Release(0.40,0.00);
                            sleep(10);
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
                            Left(0.6);
                            sleep(20);
                            Right(0.6);
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
                        Forward(0.8);

                }
            if (runtime.equals(30)) {
                    stop();}

        /*  if (gamepad2.dpad_up) {
            if (servo_position_ball <= 1) {
                servo_position_ball = servo_position_ball + 0.02;
            }
            sleep(50);
        }
        if (gamepad2.dpad_down) {
            if (servo_position_ball >= 0) {
                servo_position_ball = servo_position_ball - 0.02;
            }
            sleep(50);
        }
        servo_kicking_ball.setPosition(servo_position_ball);
        if (gamepad2.x) {
            servo_position_1 = 0.00;
            servo_position_2 = 0.40;
            servo_catching_block_1.setPosition(servo_position_1);
            servo_catching_block_2.setPosition(servo_position_2);
        } else if (gamepad2.b) {
            servo_position_1 = 0.40;
            servo_position_2 = 0.00;
            servo_catching_block_1.setPosition(servo_position_1);
            servo_catching_block_2.setPosition(servo_position_2);
        }

        if (gamepad2.y) {
            power_raising = 1.0;
            motor_raising.setPower(power_raising);
        } else if (gamepad2.a) {
            power_raising = -1.0;
            motor_raising.setPower(power_raising);
        } else {
            power_raising = 0.08;
            motor_raising.setPower(power_raising);
        }

        if (gamepad2.x) {
            power1 = 0.3;
        }
        if (gamepad1.left_stick_y < 0) {
            power1 = 0.8;

            Leftfront.setPower(power1);
            Rightfront.setPower(power1);
            Leftrear.setPower(power1);
            Rightrear.setPower(power1);
        }


        if (gamepad1.left_stick_y > 0) {
            power1 = 0.8;
            Leftfront.setPower(power1);
            Leftrear.setPower(-power1);
            Rightfront.setPower(-power1);
            Rightrear.setPower(power1);
        }
        //hou tui
        if (gamepad1.left_stick_x > 0) {
            power1 = 0.8;
            Leftfront.setPower(power1);
            Leftrear.setPower(-power1);
            Rightfront.setPower(-power1);
            Rightrear.setPower(power1);
        }

        if (gamepad1.left_stick_x < 0) {
            power1 = 0.8;
            Leftfront.setPower(-power1);
            Leftrear.setPower(power1);
            Rightfront.setPower(power1);
            Rightrear.setPower(-power1);
        }
               /* if (gamepad1.left_stick_x < 0 && gamepad1.left_stick_y < 0) {
                    power1 = 0.5;
                    Leftfront.setPower(power1);
                    Leftrear.setPower(power1);
                    Rightfront.setPower(power1);
                    Rightrear.setPower(-power1);
                }//向左上
                if (gamepad1.left_stick_x < 0 && gamepad1.left_stick_y > 0) {
                    power1 = 0.5;
                    Leftfront.setPower(-power1);
                    Leftrear.setPower(-power1);
                    Rightfront.setPower(power1);
                    Rightrear.setPower(-power1);
                }//向左下
                if (gamepad1.left_stick_x > 0 && gamepad1.left_stick_y > 0) {
                    power1 = 0.5;
                    Leftfront.setPower(power1);
                    Leftrear.setPower(-power1);
                    Rightfront.setPower(-power1);
                    Rightrear.setPower(-power1);
                }//向右下
                if (gamepad1.left_stick_x > 0 && gamepad1.left_stick_y < 0) {
                    power1 = 0.5;
                    Leftfront.setPower(power1);
                    Leftrear.setPower(-power1);
                    Rightfront.setPower(power1);
                    Rightrear.setPower(power1);
                }//向右上
                if (gamepad1.left_stick_x == 0 && gamepad1.left_stick_y == 0) {
                    power1 = 0;
                    Leftfront.setPower(power1);
                    Leftrear.setPower(power1);
                    Rightfront.setPower(power1);
                    Rightrear.setPower(power1);
                }//stop
                if (gamepad1.a) {
                power1 = 0.5;
                Leftfront.setPower(power1);
                Leftrear.setPower(power1);
                Rightfront.setPower(-power1);
                Rightrear.setPower(-power1);
                }
                if (gamepad1.b) {
                power1 = 0.5;
                Leftfront.setPower(-power1);
                Leftrear.setPower(-power1);
                Rightfront.setPower(power1);
                Rightrear.setPower(power1);
                }//shun shi zheng*/


            else {
                telemetry.addData("VuMark", "not visible");
                telemetry.addData("Status", "Run Time: " + runtime.toString());
                // telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);

                telemetry.update();
            }
        }
    }

    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
}