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

@Autonomous(name="Auto_Red_Back_R", group ="Concept")
//@Disabled
public class Auto_Red_Back_R extends LinearOpMode {

    public static final String TAG = "Vuforia VuMark Sample";

    private ElapsedTime runtime = new ElapsedTime();
    DcMotor motor_zuoqian;
    DcMotor motor_youqian;
    DcMotor motor_zuohou;
    DcMotor motor_youhou;

    Servo servo_catching_block_1;
    Servo servo_catching_block_2;

    Servo servo_kicking_ball;
    Servo servo_kicking_ball_2;

    DcMotor motor_raising;

    double servo_position_block_1 = 0.78;
    double servo_position_block_2 = 0.00;
    double power_raising = 0.50;

    double servo_position_ball = 1;

    double power_zuoqian;
    double power_youqian;
    double power_zuohou;
    double power_youhou;

    int count = 0;

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

    public void kicking_ball(double servo_position_ball,double servo_position_ball_2){
        servo_kicking_ball.setPosition(servo_position_ball);
        servo_kicking_ball_2.setPosition(servo_position_ball_2);
    }

    public void raising(double power_raising){
        motor_raising.setPower(power_raising);
    }

    public void cube(RelicRecoveryVuMark vuMark){
        qianjin(0.4);//前进

        sleep(750);

        qianjin(0.2);//缓停

        houtui(0.35);//轻怼平衡板定位

        sleep(600);

        qianjin(0);

        sleep(400);

        qianjin(0.5);

        sleep(300);

        qianjin(0);

        sleep(400);

        if (vuMark == LEFT){
            zuopingyi(1);//左平移

            sleep(920);
        }

        else if (vuMark == CENTER){//done
            zuopingyi(1);//左平移

            sleep(525);
        }

        else if (vuMark == RIGHT){
            zuopingyi(1);//左平移

            sleep(320);
        }

        qianjin(0);

        sleep(500);

        youzhuan(0.3);

        sleep(170);

        qianjin(0);

        sleep(250);

        qianjin(0.4);//前进一点点

        sleep(200);

        qianjin(0);

        raising(-1);//下降滑轨

        sleep(950);

        raising(0.08);//停止滑轨

        catching_block(0.35, 0.15);//松开方块夹子

        sleep(300);

        qianjin(0.4);//往前怼

        sleep(900);

        //以下为sao操作，主要是左右摇摆，把方块摆进对应密码箱
        houtui(0.3);//后退一点点

        sleep(120);

        youzhuan(0.4);//右转

        sleep(600);

        zuozhuan(0.4);//左转

        sleep(600);

        qianjin(0.3);//往前推一点点

        sleep(380);

        houtui(1);

        sleep(150);

        qianjin(0);
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
        servo_kicking_ball_2 = hardwareMap.get(Servo.class,"servo_kicking_ball_2");

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

        catching_block(0.78, 0.0);

        sleep(300);

        kicking_ball(0.7,0.51);

        sleep(500);

        kicking_ball(0.84,0.51);

        sleep(400);

        raising(1);//抬升滑轨

        sleep(1200);

        raising(0.08);//卡住滑轨

        sleep(500);

        if (sensorColor.blue() > sensorColor.red()) {//判断为 蓝色宝石
            kicking_ball(0.84,0.65);

            sleep(300);

            kicking_ball(0.15,0.51);
        }

        else if (sensorColor.blue() < sensorColor.red()) {//判断为 红色宝石
            kicking_ball(0.84,0.3);

            sleep(300);

            kicking_ball(0.15,0.51);
        }


        relicTrackables.activate();

        while (opModeIsActive()) {
            cube(RIGHT);

            break;
            }

            telemetry.update();
        }

        String format(OpenGLMatrix transformationMatrix) {
            return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
        }
}



