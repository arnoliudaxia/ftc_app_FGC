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
import com.qualcomm.robotcore.util.ElapsedTime;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.*;

import org.firstinspires.ftc.robotcontroller.external.samples.ConceptVuforiaNavigation;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import static com.disnodeteam.dogecv.detectors.JewelDetector.JewelOrder.UNKNOWN;
import static com.disnodeteam.dogecv.detectors.JewelDetector.JewelOrder.BLUE_RED;
import static com.disnodeteam.dogecv.detectors.JewelDetector.JewelOrder.RED_BLUE;


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

@Autonomous(name="TEAutoTest", group="Linear Opmode")
//@Disabled
public class TEAutoTest extends TurningEchoHardware {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private CryptoboxDetector cryptoboxDetector = null;

    private GlyphDetector glyphDetector = null;

    private JewelDetector jewelDetector = null;

    @Override

    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        TurningEchoHardwareConfigure();

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


        jewelDetector = new JewelDetector();
        jewelDetector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        //Jewel Detector Settings
        jewelDetector.areaWeight = 0.02;
        jewelDetector.detectionMode = JewelDetector.JewelDetectionMode.MAX_AREA; // PERFECT_AREA
        //jewelDetector.perfectArea = 6500; <- Needed for PERFECT_AREA
        jewelDetector.debugContours = true;
        jewelDetector.maxDiffrence = 15;
        jewelDetector.ratioWeight = 15;
        jewelDetector.minArea = 700;
        //jewelDetector.enable();


        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "AVBZ8J//////AAAAmUh1NI3160yckxL9jxR0wQcUr8yieqkZdNjB+5YalDuty4KXzCOkSolr6sHq3/fpV/RIj6mOgl8bULILxJBdKOoGjAMVic54WUzwQk0Le88nb3sV20pEMonnqTnWvKp/pmpe5PPJJQE2gjs58sJSX7ROIBRMsDjVhu09ep3cmmyVhdIBLjkgvKafXDVtjpzAJJ/3HDenn2ocZ10F66ZHgSg7muIuMsobb30shiby9l9E30KN8Hy6GXu8BQlaBMzy4sRclYcCApVw/hFUUNN25tCFc0ex2Zn71AWr/1DyPwEWiva0M+75k8L3Nz2NTqv2bEruKLahBbjmT2haZ0cfOhiUuDwA4bfpfTyg0iRv0hHV";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        // get a reference to the RelativeLayout so we can change the background
        // color of the Robot Controller app to match the hue detected by the RGB sensor.
        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);


        waitForStart();
        runtime.reset();
        imu.startAccelerationIntegration(new Position(), new Velocity(), 1000);

        relicTrackables.activate();

        sleep(500);

        while (opModeIsActive()) {
            /**
             * See if any of the instances of {@link relicTemplate} are currently visible.
             * {@link RelicRecoveryVuMark} is an enum which can have the following values:
             * UNKNOWN, LEFT, CENTER, and RIGHT. When a VuMark is visible, something other than
             * UNKNOWN will be returned by {@link RelicRecoveryVuMark#from(VuforiaTrackable)}.
             */
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);//VuMark

            tripodHeadPosition = 0;
            while (vuMark == RelicRecoveryVuMark.UNKNOWN){
                tripodHead.setPosition(tripodHeadPosition);
                tripodHeadPosition = tripodHeadPosition + 0.05;
                sleep(100);
                if (tripodHeadPosition > 1){
                    tripodHeadPosition = 0;
                }
                if (getRuntime() > 5){
                    break;
                }
            }

            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {//如果壁画密码被破译
                relicTrackables.deactivate();
                telemetry.addData("VuMark", "%s visible", vuMark);
//                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener)relicTemplate.getListener()).getPose();
//                telemetry.addData("Pose", format(pose));
                jewelDetector.enable();
                sleep(500);
                tripodHeadPosition = 0;
                while (jewelDetector.getCurrentOrder() == UNKNOWN){
                    tripodHead.setPosition(tripodHeadPosition);
                    tripodHeadPosition = tripodHeadPosition + 0.05;
                    sleep(100);
                    if (tripodHeadPosition > 1){
                        tripodHeadPosition = 0;
                    }
                    if (getRuntime() > 3){
                        break;
                    }
                }

                if (jewelDetector.getCurrentOrder().toString().equals("BLUE_RED")){
                    moveFix(0.2,moveStatus.xF);

                    sleep(500);
                }
                else if (jewelDetector.getCurrentOrder().toString().equals("RED_BLUE")){
                    moveFix(0.2,moveStatus.xB);

                    sleep(500);
                }




                break;
            }
            else {
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
