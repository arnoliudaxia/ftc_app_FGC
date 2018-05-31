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

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.CryptoboxDetector;
import com.disnodeteam.dogecv.detectors.GlyphDetector;
import com.disnodeteam.dogecv.detectors.JewelDetector;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.ConceptVuforiaNavigation;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import static com.disnodeteam.dogecv.detectors.JewelDetector.JewelOrder.UNKNOWN;
import static org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark.CENTER;
import static org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark.LEFT;
import static org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark.RIGHT;


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

@Autonomous(name = "TEAutoRed2", group = "Linear Opmode")
//@Disabled
public class TEAutoRed2 extends TurningEchoHardware {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private CryptoboxDetector cryptoboxDetector = null;

    private GlyphDetector glyphDetector = null;

    private JewelDetector jewelDetector = null;

    double R;
    double rPower;

    double d;

    public void cryptoBoxCount(RelicRecoveryVuMark vuMark){
        int a = 0;
        int b = 0;
        switch (vuMark){
            case LEFT:
                a=1;
                break;
            case CENTER:
                a=2;
                break;
            case RIGHT:
                a=3;
                break;
        }
        telemetry.addData("a",a);
        telemetry.update();
        while (b<a){
            while (watcher.getPosition()>=0.85){
                watcher.setPosition(watcher.getPosition()+0.01);
                sleep(10);
            }
            while (sensorColour2.red()<=150){
                moveFix(0.2,moveStatus.xR);
            }
            while (watcher.getPosition()<=0.45){
                watcher.setPosition(watcher.getPosition()-0.01);
                sleep(10);
            }
            b++;
            autoTurnLocation(0);
        }
    }

    @Override

    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        TurningEchoHardwareConfigure();

        // Set up the parameters with which we will use our IMU. Note that integration
        // algorithm here just reports accelerations to the logcat log; it doesn't actually
        // provide positional information.
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
        // and named "imu".
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
        imu.startAccelerationIntegration(new Position(), new Velocity(), 1000);

        releaseBlock12();
        releaseBlock34();

        // Set up our telemetry dashboard
        //composeTelemetry();

        /*cryptoboxDetector = new CryptoboxDetector();
        cryptoboxDetector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        cryptoboxDetector.rotateMat = false;
        //cryptoboxDetector.enable();


        glyphDetector = new GlyphDetector();
        glyphDetector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        glyphDetector.minScore = 1;
        glyphDetector.downScaleFactor = 0.3;
        glyphDetector.speed = GlyphDetector.GlyphDetectionSpeed.SLOW;
        glyphDetector.enable();*/


//        jewelDetector = new JewelDetector();
//        jewelDetector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
//        //Jewel Detector Settings
//        jewelDetector.areaWeight = 0.02;
//        jewelDetector.detectionMode = JewelDetector.JewelDetectionMode.MAX_AREA; // PERFECT_AREA
//        //jewelDetector.perfectArea = 6500; <- Needed for PERFECT_AREA
//        jewelDetector.debugContours = true;
//        jewelDetector.maxDiffrence = 15;
//        jewelDetector.ratioWeight = 15;
//        jewelDetector.minArea = 700;
//        //jewelDetector.enable();


        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());


        // get a reference to the RelativeLayout so we can change the background
        // color of the Robot Controller app to match the hue detected by the RGB sensor.
        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);

        waitForStart();
        runtime.reset();

        imu.initialize(parameters);
        imu.startAccelerationIntegration(new Position(), new Velocity(), 1000);


        int count = 0;


//        tripodHeadPosition = 0.3;//手机云台舵机角度
//        tripodHead.setPosition(tripodHeadPosition);
//        //jewelDetector.enable();//启动宝石检测
//        sleep(500);
//        count = 0;//计数
//        while (jewelDetector.getCurrentOrder() == UNKNOWN) {//当宝石顺序是未知时循环
//            tripodHead.setPosition(tripodHeadPosition);
//            tripodHeadPosition = tripodHeadPosition + 0.05;//循环转动手机云台以扫到宝石
//            sleep(400);
//            count++;
//            if (tripodHeadPosition > 0.7) {
//                tripodHeadPosition = 0.3;
//            }
//            if (count > 14) {//14*400=5.6秒，5.6秒后还未检测到小球则跳出循环
//                break;
//            }
//        }


        catchBlock34();

        servoBallPosition_1 = 0.35;

        servoKickBall_1.setPosition(servoBallPosition_1);

        sleep(600);

        while (servoBallPosition_1<=0.85){
            servoBallPosition_1 = servoBallPosition_1 + 0.05;
            servoKickBall(servoBallPosition_1,0.5);
            sleep(10);
            telemetry.addData("Red  ", sensorColour1.red());
            telemetry.addData("Green", sensorColour1.green());
            telemetry.addData("Blue ", sensorColour1.blue());
            telemetry.update();
        }
        telemetry.addData("Red  ", sensorColour1.red());
        telemetry.addData("Green", sensorColour1.green());
        telemetry.addData("Blue ", sensorColour1.blue());
        telemetry.update();

        lift(1);//抬升滑轨

        sleep(500);

        lift(0);//卡住滑轨

        sleep(500);

        if (sensorColour1.blue() < sensorColour1.red()) {//判断为 蓝色宝石
            telemetry.addData("red",0);
            telemetry.update();
            servoKickBall(0.9,0.71);

            sleep(300);

            servoKickBall(0.25,0.71);

            sleep(200);

            servoKickBall(0.25,0.57);
        }

        else if (sensorColour1.blue() > sensorColour1.red()) {//判断telemetry.addData("red",0);
            telemetry.update();//为 红色宝石
            telemetry.addData("blue",0);
            telemetry.update();
            servoKickBall(0.9,0.25);

            sleep(300);

            servoKickBall(0.25,0.25);

            sleep(200);

            servoKickBall(0.25,0.57);
        }


        VuforiaLocalizer.Parameters parameters2 = new VuforiaLocalizer.Parameters(cameraMonitorViewId);//定义新参数

        //KEY
        parameters2.vuforiaLicenseKey = "AVBZ8J//////AAAAmUh1NI3160yckxL9jxR0wQcUr8yieqkZdNjB+5YalDuty4KXzCOkSolr6sHq3/fpV/RIj6mOgl8bULILxJBdKOoGjAMVic54WUzwQk0Le88nb3sV20pEMonnqTnWvKp/pmpe5PPJJQE2gjs58sJSX7ROIBRMsDjVhu09ep3cmmyVhdIBLjkgvKafXDVtjpzAJJ/3HDenn2ocZ10F66ZHgSg7muIuMsobb30shiby9l9E30KN8Hy6GXu8BQlaBMzy4sRclYcCApVw/hFUUNN25tCFc0ex2Zn71AWr/1DyPwEWiva0M+75k8L3Nz2NTqv2bEruKLahBbjmT2haZ0cfOhiUuDwA4bfpfTyg0iRv0hHV";

        parameters2.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;//使用后置摄像头
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters2);

        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        relicTrackables.activate();//启动壁画识别

        while (opModeIsActive()) {
            /**
             * See if any of the instances of {@link relicTemplate} are currently visible.
             * {@link RelicRecoveryVuMark} is an enum which can have the following values:
             * UNKNOWN, LEFT, CENTER, and RIGHT. When a VuMark is visible, something other than
             * UNKNOWN will be returned by {@link RelicRecoveryVuMark#from(VuforiaTrackable)}.
             */
            tripodHeadPosition = 0.19;//云台舵机角度
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);//VuMark
            while (RelicRecoveryVuMark.from(relicTemplate) == RelicRecoveryVuMark.UNKNOWN) {
                if (RelicRecoveryVuMark.from(relicTemplate) != RelicRecoveryVuMark.UNKNOWN) {//如果扫到了壁画，则vuMark=壁画，
                    vuMark = RelicRecoveryVuMark.from(relicTemplate);
                }
                telemetry.addData("VuMark", "%s visible", vuMark);//打印数据
                telemetry.update();
                tripodHead.setPosition(tripodHeadPosition);//设定云台角度
                tripodHeadPosition = tripodHeadPosition + 0.07;//每次云台转动0.05角度
                sleep(700);
                count++;
                if (tripodHeadPosition > 0.54) {//让云台在0.5-0.7角度范围内转动
                    tripodHeadPosition = 0.19;
                }
                if (count > 14) {//8*700=5.6秒，5.6秒后还未扫到壁画则跳出循环
                    break;
                }
            }
            telemetry.addData("VuMark", "%s visible", vuMark);//打印壁画值
            telemetry.update();

            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {//如果壁画被破译
                relicTrackables.deactivate();
                telemetry.addData("VuMark", "%s visible", vuMark);
                telemetry.update();
//                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener)relicTemplate.getListener()).getPose();
//                telemetry.addData("Pose", format(pose));
                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                gravity = imu.getGravity();
///////////////////////////////////////////////////////////////////////////////////////////
                moveFix(0.4,moveStatus.yF);//前进

                sleep(600);

                moveFix(0.2, moveStatus.yF);//缓停

                sleep(320);

                frameStop();

                sleep(300);

                moveFix(0.35, moveStatus.yB);//轻怼平衡板定位

                sleep(500);

                frameStop();

                sleep(300);

                moveFix(0.5, moveStatus.yF);

                sleep(180);

                frameStop();

                sleep(300);

                autoTurnLocation(0);

                frameStop();

                sleep(300);



                /////////////////////////
//                int c = 0;
//                d = sensorDistance.getDistance(DistanceUnit.CM);
//                while (d > 10 || Double.toString(d).equals("NaN")) {//当d（距离）> 10cm或 未知 时
//                    moveFix(0.3, moveStatus.xL);//左平移
//                    d = sensorDistance.getDistance(DistanceUnit.CM);//获取距离传感器数值
//
//                    if (d <= 10 && sensorColour.blue() > (sensorColour.red() + 50)) {//当颜色传感器蓝色色值 > 红色色值时
//                        c++;//计数器+1
//                        moveFix(0.3, moveStatus.xL);//左平移一点以偏移该密码箱列
//                        sleep(200);
//                    }
//                    if (vuMark == RIGHT && c == 2) {//壁画为右且计数为2
//                        break;
//                    } else if (vuMark == CENTER && c == 3) {//壁画为中且计数为3
//                        break;
//                    } else if (vuMark == LEFT && c == 4) {//壁画为左且计数为4
//                        break;
//                    }
//                    autoTurnLocation(Double.parseDouble(formatAngle(angles.angleUnit, angles.firstAngle)));//自动对位
//                    moveFix(0.3, moveStatus.xR);//右平移一点
//                    sleep(100);
//                }


                /////////////////////////

                if (vuMark == LEFT) {
                    moveFix(1, moveStatus.xL);//左平移

                    sleep(920);
                } else if (vuMark == CENTER) {//done
                    moveFix(1, moveStatus.xL);//左平移

                    sleep(675);
                } else if (vuMark == RIGHT) {
                    moveFix(1, moveStatus.xL);//左平移

                    sleep(450);
                }

                frameStop();

                sleep(500);

                autoTurnLocation(0);

                frameStop();

                sleep(250);

                moveFix(0.4, moveStatus.yF);//前进一点点

                sleep(200);

                frameStop();

                motorShift.setPower(-0.15);

                sleep(300);

                lift(-1);//下降滑轨

                sleep(500);

                lift(0);//停止滑轨

                releaseBlock34();

                sleep(300);

                moveFix(0.4, moveStatus.yF);//往前怼

                sleep(900);

                //以下为sao操作，主要是左右摇摆，把方块摆进对应密码箱
                moveFix(0.3, moveStatus.yB);//后退一点点

                sleep(130);

                moveFix(0.4, moveStatus.rR);//右转

                sleep(400);

                moveFix(0.4, moveStatus.rL);//左转

                sleep(400);

                moveFix(0.3, moveStatus.yF);//往前推一点点

                sleep(380);

                frameStop();

                sleep(100);

                moveFix(0.4, moveStatus.yB);

                sleep(220);

//                while (Math.abs(Double.parseDouble(formatAngle(angles.angleUnit, angles.firstAngle))) >= 0.6) {
//                    while (true) {
//                        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
//                        gravity = imu.getGravity();
//                        R = Double.parseDouble(formatAngle(angles.angleUnit, angles.firstAngle));
//                        rPower = Range.clip(Math.abs(R / 45), 0.14, 1);
//                        telemetry.addData("rPower = ", rPower);
//                        telemetry.update();
//                        if (R >= -0.6 && R <= 0.6) {
//                            break;
//                        } else if (R < -0.6) {
//                            moveVar(0, 0, -rPower, 1);
//                        } else if (R > 0.6) {
//                            moveVar(0, 0, rPower, 1);
//                        } else break;
//                    }
//                }
//
//                frameStop();
//
//                sleep(500);
//
//                moveFix(0.7, moveStatus.rL);
//
//                sleep(850);
//
//                frameStop();
//
//                sleep(200);
//
//                imu.initialize(parameters);
//                imu.startAccelerationIntegration(new Position(), new Velocity(), 1000);
//
//
//                runtime.reset();
//                d = sensorDistance.getDistance(DistanceUnit.CM);
//
//                while ((d > 10 && getRuntime() <= 3) || Double.toString(d).equals("NaN")) {
//                    d = sensorDistance.getDistance(DistanceUnit.CM);
//                    moveFix(0.5, moveStatus.yF);
//                }
//                frameStop();
//                sleep(300);
//
//                if (d <= 10) {
//                    catchBlock();
//                }
                frameStop();

                motorShift.setPower(0);

                break;
            } else {
                telemetry.addData("VuMark", "not visible");
            }


//            telemetry.addData("Status", "Run Time: " + runtime.toString());
//            telemetry.addData("isCryptoBoxDetected", cryptoboxDetector.isCryptoBoxDetected());
//            telemetry.addData("isColumnDetected ",  cryptoboxDetector.isColumnDetected());
//            telemetry.addData("Column Left ",  cryptoboxDetector.getCryptoBoxLeftPosition());
//            telemetry.addData("Column Center ",  cryptoboxDetector.getCryptoBoxCenterPosition());
//            telemetry.addData("Column Right ",  cryptoboxDetector.getCryptoBoxRightPosition());

            //telemetry.addData("Glyph Pos X", glyphDetector.getChosenGlyphOffset());
            //telemetry.addData("Glyph Pos Offest", glyphDetector.getChosenGlyphPosition().toString());

//            telemetry.addData("Current Order", "Jewel Order: " + jewelDetector.getCurrentOrder().toString()); // Current Result
//            telemetry.addData("Last Order", "Jewel Order: " + jewelDetector.getLastOrder().toString()); // Last Known Result

            telemetry.update();

        }
    }

    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
}
