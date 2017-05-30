
/*
  这段代码是使用伺服作为挡板后，弹射2个球的自动程序
  在此，特别感谢重庆三十七中的程序员Rarcher为我们在自动程序搭建上的所有帮助
*/
package org.firstinspires.ftc.teamcode;

import android.graphics.Bitmap;

import for_camera_opmodes.LinearOpModeCamera;

public class bluelight1 extends  LinearOpModeCamera {

    /* Declare OpMode members. */
    int ds2 = 2;
    TuringEchoRobotHardware   robot           = new TuringEchoRobotHardware();
    String colorString = "NONE";

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        setCameraDownsampling(1);
        telemetry.addLine("Wait for camera to finish initializing!");
        telemetry.update();
        startCamera();
        telemetry.addLine("Camera ready!");
        telemetry.update();
        waitForStart();
        forward1();
        right1();
        back1();
        left1();
        forward2();
        rightlittle1();
        left2();
        rightlittle2();
        opp();




    }
    public void forward1() throws InterruptedException {
        robot.WL.setPower(0.4);
        robot.WR.setPower(0.4);
        Thread.sleep(1700);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
        Thread.sleep(200);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
    }


    public void right1() throws InterruptedException {
        robot.WF.setPower(0.4);
        robot.WB.setPower(0.4);
        Thread.sleep(2300);
        robot.WF.setPower(0);
        robot.WB.setPower(0);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
        Thread.sleep(500);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
    }
    public void back1() throws InterruptedException {
        robot.WL.setPower(-0.4);
        robot.WR.setPower(-0.4);
        Thread.sleep(700);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
        Thread.sleep(200);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
    }

    public void left1() throws  InterruptedException{
        robot.WF.setPower(-0.4);
        robot.WB.setPower(-0.4);
        Thread.sleep(400);
        robot.WF.setPower(0);
        robot.WB.setPower(0);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
        Thread.sleep(200);
        robot.WL.setPower(0);
        robot.WR.setPower(0);}


    public void forward2() throws InterruptedException {
        robot.WL.setPower(0.2);
        robot.WR.setPower(0.2);
        Thread.sleep(1500);
        robot.WF.setPower(0);
        robot.WB.setPower(0);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
        Thread.sleep(200);
        robot.WL.setPower(0);
        robot.WR.setPower(0);}


    public void rightlittle1() throws InterruptedException {
        robot.WF.setPower(0.4);
        robot.WB.setPower(0.4);
        Thread.sleep(800);
        robot.WF.setPower(0);
        robot.WB.setPower(0);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
        Thread.sleep(200);
        robot.WL.setPower(0);
        robot.WR.setPower(0);}
    public void left2() throws  InterruptedException{
        robot.WF.setPower(-0.4);
        robot.WB.setPower(-0.4);
        Thread.sleep(400);
        robot.WF.setPower(0);
        robot.WB.setPower(0);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
        Thread.sleep(300);
        robot.WL.setPower(0);
        robot.WR.setPower(0);}
    public void rightlittle2() throws InterruptedException {
        robot.WF.setPower(0.4);
        robot.WB.setPower(0.4);
        Thread.sleep(800);
        robot.WF.setPower(0);
        robot.WB.setPower(0);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
        Thread.sleep(200);
        robot.WL.setPower(0);
        robot.WR.setPower(0);}
    public void opp () throws InterruptedException{
        while (opModeIsActive()) {
            if (imageReady()) { // only do this if an image has been returned from the camera
                int redValue = 0;
                int blueValue = 0;
                int greenValue = 0;

                // get image, rotated so (0,0) is in the bottom left of the preview window
                Bitmap rgbImage;
                rgbImage = convertYuvImageToRgb(yuvImage, width, height,ds2);

                for (int x = 0; x < rgbImage.getWidth(); x++) {
                    for (int y = 0; y < rgbImage.getHeight(); y++) {
                        int pixel = rgbImage.getPixel(x, y);
                        redValue += red(pixel);
                        blueValue += blue(pixel);
                        greenValue += green(pixel);
                    }
                }
                int color = highestColor(redValue, blueValue,greenValue);

                switch (color) {
                    case 0:
                        colorString = "RED";
                        telemetry.addData("Color:", "Color detected is: " + colorString);
                        telemetry.update();
                        robot.WF.setPower(-0.4);
                        robot.WB.setPower(-0.4);
                        Thread.sleep(1000);
                        robot.WF.setPower(0);
                        robot.WB.setPower(0);
                        robot.WL.setPower(0.4);
                        robot.WR.setPower(0.4);
                        Thread.sleep(2000);
                        robot.WL.setPower(0);
                        robot.WR.setPower(0);
                        stopCamera();
                        resetStartTime();

                    case 1:colorString = "GREEN";
                        telemetry.addData("Color:", "Color detected is: " + colorString);
                        telemetry.update();
                        robot.WF.setPower(-0.4);
                        robot.WB.setPower(-0.4);
                        Thread.sleep(800);
                        robot.WF.setPower(0);
                        robot.WB.setPower(0);
                        robot.WF.setPower(0.4);
                        robot.WB.setPower(0.4);
                        Thread.sleep(1000);
                        robot.WF.setPower(0);
                        robot.WB.setPower(0);
                        robot.WF.setPower(-0.4);
                        robot.WB.setPower(-0.4);
                        Thread.sleep(800);
                        robot.WF.setPower(0);
                        robot.WB.setPower(0);
                        robot.WF.setPower(0.4);
                        robot.WB.setPower(0.4);
                        Thread.sleep(1000);
                        robot.WF.setPower(0);
                        robot.WB.setPower(0);
                        stopCamera();
                        resetStartTime();

                    case 2:
                        colorString = "BLUE";
                        telemetry.addData("Color:", "Color detected is: " + colorString);
                        telemetry.update();
                        robot.WF.setPower(-0.4);
                        robot.WB.setPower(-0.4);
                        Thread.sleep(800);
                        robot.WF.setPower(0);
                        robot.WB.setPower(0);
                        robot.WF.setPower(0.4);
                        robot.WB.setPower(0.4);
                        Thread.sleep(1000);
                        robot.WF.setPower(0);
                        robot.WB.setPower(0);
                        robot.WF.setPower(-0.4);
                        robot.WB.setPower(-0.4);
                        Thread.sleep(800);
                        robot.WF.setPower(0);
                        robot.WB.setPower(0);
                        robot.WF.setPower(0.4);
                        robot.WB.setPower(0.4);
                        Thread.sleep(1000);
                        robot.WF.setPower(0);
                        robot.WB.setPower(0);
                        stopCamera();
                        resetStartTime();

    }}}}}





