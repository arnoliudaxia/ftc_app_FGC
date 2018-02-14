package org.firstinspires.ftc.teamcode;

import android.graphics.Bitmap;

import for_camera_opmodes.LinearOpModeCamera;
import android.graphics.Bitmap;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import for_camera_opmodes.LinearOpModeCamera;

@Autonomous(name="detect", group="TuringEcho")//13.7
//@Disabled

public class LinearDetectColor extends LinearOpModeCamera {


    TuringEchoRobotHardware robot = new TuringEchoRobotHardware();
    int ds2 = 2;  // additional downsampling of the image
    // set to 1 to disable further downsampling


    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        String colorString = "NONE";
        telemetry.addData("Color:", "Color detected is: " + colorString);
        telemetry.update();


        // linear OpMode, so could do stuff
        //
        //
        //
        //
        // like this too.
        /*
        motorLeft = hardwareMap.dcMotor.get("motor_1");
        motorRight = hardwareMap.dcMotor.get("motor_2");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        */

        if (isCameraAvailable()) {

            setCameraDownsampling(1);
            telemetry.addLine("Wait for camera to finish initializing!");
            telemetry.update();
            startCamera();
            telemetry.addLine("Camera ready!");
            telemetry.update();

            waitForStart();

            // LinearOpMode, so could do stuff like this too.
            /*
            motorLeft.setPower(1);  // drive forward
            motorRight.setPower(1);
            sleep(1000);            // for a second.
            motorLeft.setPower(0);  // stop drive motors.
            motorRight.setPower(0);
            sleep(1000);            // wait a second.
            */

            while (opModeIsActive()) {
                if (imageReady()) { // only do this if an image has been returned from the camera
                    int redValue = 0;
                    int blueValue = 0;
                    int greenValue = 0;

                    // get image, rotated so (0,0) is in the bottom left of the preview window
                    Bitmap rgbImage;
                    rgbImage = convertYuvImageToRgb(yuvImage, width, height, ds2);

                    for (int x = 0; x < rgbImage.getWidth(); x++) {
                        for (int y = 0; y < rgbImage.getHeight(); y++) {
                            int pixel = rgbImage.getPixel(x, y);
                            redValue += red(pixel);
                            blueValue += blue(pixel);
                            greenValue += green(pixel);
                        }
                    }
                    int color = highestColor(redValue,greenValue,blueValue);

                    switch (color) {
                        case 0:
                            colorString = "RED";
                            telemetry.addData("Color:", "Color detected is: " + colorString);
                            telemetry.update();
                            robot.WF.setPower(0.4);
                            robot.WB.setPower(0.4);
                            Thread.sleep(1000);
                            robot.WF.setPower(0);
                            robot.WB.setPower(0);
                            robot.WL.setPower(0.2);
                            robot.WR.setPower(0.2);
                            Thread.sleep(1500);
                            robot.WL.setPower(0);
                            robot.WR.setPower(0);
                            stopCamera();
                            resetStartTime();
                        break;
                        case 1:colorString = "GREEN";
                            telemetry.addData("Color:", "Color detected is: " + colorString);
                            telemetry.update();
                            robot.WF.setPower(0.4);
                            robot.WB.setPower(0.4);
                            Thread.sleep(800);
                            robot.WF.setPower(0);
                            robot.WB.setPower(0);
                            robot.WF.setPower(-0.4);
                            robot.WB.setPower(-0.4);
                            Thread.sleep(1000);
                            robot.WF.setPower(0);
                            robot.WB.setPower(0);
                            stopCamera();
                            resetStartTime();
                        break;
                        case 2:
                            colorString = "BLUE";
                            telemetry.addData("Color:", "Color detected is: " + colorString);
                            telemetry.update();
                            robot.WF.setPower(0.4);
                            robot.WB.setPower(0.4);
                            Thread.sleep(800);
                            robot.WF.setPower(0);
                            robot.WB.setPower(0);
                            robot.WF.setPower(-0.4);
                            robot.WB.setPower(-0.4);
                            Thread.sleep(1000);
                            robot.WF.setPower(0);
                            robot.WB.setPower(0);
                            stopCamera();
                            resetStartTime();
                            break;


                    }
                }
        }

        }
        else {
            colorString = "NONE";}
            telemetry.addData("Color:", "Color detected is: " + colorString);
            telemetry.update();
        sleep(10);



        }}
