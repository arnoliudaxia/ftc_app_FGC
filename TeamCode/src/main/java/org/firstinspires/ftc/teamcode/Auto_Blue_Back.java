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
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.ConceptVuforiaNavigation;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import static org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark.CENTER;
import static org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark.LEFT;
import static org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark.RIGHT;

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

@Autonomous(name="Auto_Blue_Back", group ="Concept")
//@Disabled
public class Auto_Blue_Back extends LinearOpMode {

    public static final String TAG = "Vuforia VuMark Sample";

    private ElapsedTime runtime = new ElapsedTime();
    DcMotor motor_zuoqian;
    DcMotor motor_youqian;
    DcMotor motor_zuohou;
    DcMotor motor_youhou;

    Servo servo_catching_block_1;
    Servo servo_catching_block_2;

    Servo servo_kicking_ball;

    DcMotor motor_raising;

    double servo_position_block_1 = 0.70;
    double servo_position_block_2 = 0.00;
    double power_raising = 0.50;

    double servo_position_ball = 1;

    double power_zuoqian;
    double power_youqian;
    double power_zuohou;
    double power_youhou;

    OpenGLMatrix lastLocation = null;

    VuforiaLocalizer vuforia;

    ColorSensor sensorColor;
    DistanceSensor sensorDistance;



    public void qianjin(double power){
        motor_zuoqian.setPower(-power);
        motor_youqian.setPower(power);
        motor_zuohou.setPower(power);
        motor_youhou.setPower(power);
    }

    public void houtui(double power){
        motor_zuoqian.setPower(power);
        motor_youqian.setPower(-power);
        motor_zuohou.setPower(-power);
        motor_youhou.setPower(-power);
    }

    public void zuopingyi(double power){
        motor_zuoqian.setPower(power);
        motor_youqian.setPower(power);
        motor_zuohou.setPower(power);
        motor_youhou.setPower(-power);
    }

    public void youpingyi(double power){
        motor_zuoqian.setPower(-power);
        motor_youqian.setPower(-power);
        motor_zuohou.setPower(-power);
        motor_youhou.setPower(power);
    }

    public void zuozhuan(double power){
        motor_zuoqian.setPower(power);
        motor_youqian.setPower(power);
        motor_zuohou.setPower(-power);
        motor_youhou.setPower(power);
    }

    public void youzhuan(double power){
        motor_zuoqian.setPower(-power);
        motor_youqian.setPower(-power);
        motor_zuohou.setPower(power);
        motor_youhou.setPower(-power);
    }

    public void catching_block(double servo_block_position_1,double servo_block_position_2) {
        servo_catching_block_1.setPosition(servo_block_position_1);
        servo_catching_block_2.setPosition(servo_block_position_2);
    }

    public void kicking_ball(double servo_position_ball){
        servo_kicking_ball.setPosition(servo_position_ball);
    }

    public void raising(double power_raising){
        motor_raising.setPower(power_raising);
    }

    public void cube(RelicRecoveryVuMark vuMark){
        if (vuMark == RIGHT){
            youpingyi(1);//左平移

            sleep(930);
        }

        else if (vuMark == CENTER){//done
            youpingyi(1);//左平移

            sleep(540);
        }

        else if (vuMark == LEFT){
            youpingyi(1);//左平移

            sleep(260);
        }

        qianjin(0);

        sleep(500);

        /*youzhuan(0.3);

        sleep(200);

        qianjin(0);

        sleep(250);*/

        qianjin(0);

        raising(-1);//下降滑轨

        sleep(950);

        raising(0.08);//停止滑轨

        catching_block(0.35, 0.15);//松开方块夹子

        sleep(300);

        qianjin(0.4);//往前怼

        sleep(750);

        //以下为sao操作，主要是左右摇摆，把方块摆进对应密码箱
        houtui(0.3);//后退一点点

        sleep(120);

        zuozhuan(0.4);//左转

        sleep(600);

        youzhuan(0.4);//右转

        sleep(600);

        qianjin(0.3);//往前推一点点

        sleep(380);
    }

    @Override public void runOpMode() {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "AVBZ8J//////AAAAmUh1NI3160yckxL9jxR0wQcUr8yieqkZdNjB+5YalDuty4KXzCOkSolr6sHq3/fpV/RIj6mOgl8bULILxJBdKOoGjAMVic54WUzwQk0Le88nb3sV20pEMonnqTnWvKp/pmpe5PPJJQE2gjs58sJSX7ROIBRMsDjVhu09ep3cmmyVhdIBLjkgvKafXDVtjpzAJJ/3HDenn2ocZ10F66ZHgSg7muIuMsobb30shiby9l9E30KN8Hy6GXu8BQlaBMzy4sRclYcCApVw/hFUUNN25tCFc0ex2Zn71AWr/1DyPwEWiva0M+75k8L3Nz2NTqv2bEruKLahBbjmT2haZ0cfOhiUuDwA4bfpfTyg0iRv0hHV";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        sensorColor = hardwareMap.get(ColorSensor.class, "Ball");

        sensorDistance = hardwareMap.get(DistanceSensor.class, "Ball");

        servo_catching_block_1 = hardwareMap.servo.get("servo_catching_block_1");
        servo_catching_block_2 = hardwareMap.servo.get("servo_catching_block_2");

        motor_raising = hardwareMap.dcMotor.get("motor_raising");

        motor_zuoqian = hardwareMap.get(DcMotor.class, "motor_zuoqian");
        motor_youqian = hardwareMap.get(DcMotor.class, "motor_youqian");
        motor_zuohou = hardwareMap.get(DcMotor.class, "motor_zuohou");
        motor_youhou = hardwareMap.get(DcMotor.class, "motor_youhou");

        servo_kicking_ball = hardwareMap.get(Servo.class, "servo_kicking_ball");

        motor_zuoqian.setDirection(DcMotor.Direction.FORWARD);
        motor_zuohou.setDirection(DcMotor.Direction.FORWARD);
        motor_youqian.setDirection(DcMotor.Direction.REVERSE);
        motor_youhou.setDirection(DcMotor.Direction.REVERSE);

        servo_catching_block_1.setPosition(servo_position_block_1);
        servo_catching_block_2.setPosition(servo_position_block_2);//初始化夹持方块的夹子，自动阶段默认夹持方块（比赛开始自带一方块）

        float hsvValues[] = {0F, 0F, 0F};//颜色传感器HSV相关

        final float values[] = hsvValues;//颜色传感器HSV相关

        // sometimes it helps to multiply the raw RGB values with a scale factor
        // to amplify/attentuate the measured values.
        final double SCALE_FACTOR = 255;

        // get a reference to the RelativeLayout so we can change the background
        // color of the Robot Controller app to match the hue detected by the RGB sensor.
        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);


        telemetry.addData(">", "Press Play to start");
        telemetry.update();

        waitForStart();

        kicking_ball(0.5);

        sleep(300);

        kicking_ball(0.35);

        sleep(300);


        kicking_ball(0.2);

        sleep(300);//以上三步为 缓降 击宝石的杆子

        raising(1);//抬升滑轨

        sleep(1200);

        raising(0.08);//卡住滑轨


        relicTrackables.activate();

        while (opModeIsActive()) {

            /**
             * See if any of the instances of {@link relicTemplate} are currently visible.
             * {@link RelicRecoveryVuMark} is an enum which can have the following values:
             * UNKNOWN, LEFT, CENTER, and RIGHT. When a VuMark is visible, something other than
             * UNKNOWN will be returned by {@link RelicRecoveryVuMark#from(VuforiaTrackable)}.
             */
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);//VuMark
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {//如果壁画密码被破译

                /* Found an instance of the template. In the actual game, you will probably
                 * loop until this condition occurs, then move on to act accordingly depending
                 * on which VuMark was visible. */
                telemetry.addData("VuMark", "%s visible", vuMark);

                /* For fun, we also exhibit the navigational pose. In the Relic Recovery game,
                 * it is perhaps unlikely that you will actually need to act on this pose information, but
                 * we illustrate it nevertheless, for completeness. */

                if (sensorColor.blue() < sensorColor.red()) {//判断为 蓝色宝石
                    youzhuan(0.2);

                    sleep(320);

                    qianjin(0);

                    sleep(400);

                    zuozhuan(0.2);

                    sleep(320);
                } else if (sensorColor.blue() > sensorColor.red()) {//判断为 红色宝石

                    zuozhuan(0.2);

                    sleep(320);

                    qianjin(0);

                    sleep(400);

                    youzhuan(0.2);

                    sleep(320);

                }

                qianjin(0);

                kicking_ball(0.6);

                sleep(200);

                kicking_ball(0.8);

                sleep(200);//缓升击宝石杆子

                houtui(0.3);//轻怼平衡板定位

                sleep(1080);

                qianjin(0);

                sleep(300);

                zuozhuan(0.3);

                sleep(100);

                qianjin(0);

                sleep(400);

                qianjin(0.3);

                sleep(470);

                qianjin(0);

                sleep(400);

                houtui(0.5);

                sleep(300);

                qianjin(0);

                sleep(400);

                youzhuan(0.6);

                sleep(1430);

                qianjin(0);

                sleep(400);

                /*houtui(0.3);

                sleep(60);*/

                cube(vuMark);

                qianjin(0);

                sleep(100);

                break;
            } else {
                telemetry.addData("VuMark", "not visible");
            }


            telemetry.update();
        }
    }

        String format(OpenGLMatrix transformationMatrix) {
            return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
        }
}



