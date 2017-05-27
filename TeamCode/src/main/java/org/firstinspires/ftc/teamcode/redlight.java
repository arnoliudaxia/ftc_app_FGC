
/*
  这段代码是使用伺服作为挡板后，弹射2个球的自动程序
  在此，特别感谢重庆三十七中的程序员Rarcher为我们在自动程序搭建上的所有帮助
*/
package org.firstinspires.ftc.teamcode;

import android.graphics.Bitmap;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import for_camera_opmodes.LinearOpModeCamera;

@Autonomous(name="redlight", group="TuringEcho")
public class redlight extends  LinearOpModeCamera {

    /* Declare OpMode members. */
    int ds2 = 2;
    TuringEchoRobotHardware1 robot = new TuringEchoRobotHardware1();
    String colorString = "NONE";

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        setCameraDownsampling(1);
        telemetry.addLine("Wait for camera to finish initializing!");
        telemetry.update();
        telemetry.addLine("Camera ready!");
        telemetry.update();
        startCamera();
        waitForStart();
        forward1();
        left1();
        little();
        opp();
        opp2();


    }

    public void forward1() throws InterruptedException {

        robot.WL.setPower(0.4);
        robot.WR.setPower(0.4);
        Thread.sleep(2590);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
        Thread.sleep(300);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
    }
    public void little() throws InterruptedException {
        robot.WF.setPower(0.3);
        robot.WB.setPower(0.3);
        Thread.sleep(250);
        robot.WF.setPower(0);
        robot.WB.setPower(0);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
        Thread.sleep(200);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
    }

    public void left1() throws InterruptedException {
        robot.WF.setPower(-0.5);
        robot.WB.setPower(-0.5);
        Thread.sleep(2000);
        robot.WF.setPower(0);
        robot.WB.setPower(0);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
        Thread.sleep(200);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
    }
    public void forward2() throws InterruptedException {
        robot.WL.setPower(0.4);
        robot.WR.setPower(0.4);
        Thread.sleep(2320);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
        Thread.sleep(300);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
    }

    public void back1() throws InterruptedException {
        robot.WL.setPower(-0.4);
        robot.WR.setPower(-0.4);
        Thread.sleep(1300);
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
        Thread.sleep(600);
        robot.WF.setPower(0);
        robot.WB.setPower(0);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
        Thread.sleep(300);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
    }


    public void leftlittle1() throws InterruptedException {
        robot.WF.setPower(-0.4);
        robot.WB.setPower(-0.4);
        Thread.sleep(1400);
        robot.WF.setPower(0);
        robot.WB.setPower(0);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
        Thread.sleep(300);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
    }

    public void right2() throws InterruptedException {
        robot.WF.setPower(0.4);
        robot.WB.setPower(0.4);
        Thread.sleep(900);
        robot.WF.setPower(0);
        robot.WB.setPower(0);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
        Thread.sleep(300);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
    }
    public void right2little() throws InterruptedException {
        robot.WF.setPower(0.4);
        robot.WB.setPower(0.4);
        Thread.sleep(600);
        robot.WF.setPower(0);
        robot.WB.setPower(0);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
        Thread.sleep(300);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
    }

    public void leftlittle2() throws InterruptedException {
        robot.WF.setPower(-0.4);
        robot.WB.setPower(-0.4);
        Thread.sleep(1100);
        robot.WF.setPower(0);
        robot.WB.setPower(0);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
        Thread.sleep(300);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
    }

    public void opp() throws InterruptedException {
        while (opModeIsActive()) {
            if (imageReady()) {
                int redValue = 0;
                int blueValue = 0;
                int greenValue = 0;
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
                int color = highestColor(redValue, blueValue, greenValue);

                switch (color) {
                    case 0:
                        colorString = "RED";
                        //telemetry.addData("Color:", "Color detected is: " + colorString);
                        //telemetry.update();

                        right2little();
                        forward2();
                        leftlittle1();
                        right1();
                        leftlittle2();
                        little();
                        return;


                    case 1:
                        colorString = "GREEN";
                        //telemetry.addData("Color:", "Color detected is: " + colorString);
                        //telemetry.update();
                          robot.WF.setPower(0.4);
                          robot.WB.setPower(0.4);
                          Thread.sleep(800);
                          robot.WF.setPower(0);
                          robot.WB.setPower(0);//you


                          robot.WF.setPower(0);
                          robot.WB.setPower(0);
                          Thread.sleep(200);
                          robot.WF.setPower(0);
                          robot.WB.setPower(0);



                          right2();
                          forward2();
                          leftlittle1();
                          right1();
                          leftlittle2();
                          little();
                          return;
                    //break;
                    //break;


                    case 2:
                        colorString = "BLUE";
                        robot.WF.setPower(0.4);
                        robot.WB.setPower(0.4);
                        Thread.sleep(800);
                        robot.WF.setPower(0);
                        robot.WB.setPower(0);//you


                        robot.WF.setPower(0);
                        robot.WB.setPower(0);
                        Thread.sleep(200);
                        robot.WF.setPower(0);
                        robot.WB.setPower(0);



                        right2();
                        forward2();
                        leftlittle1();
                        right1();
                        leftlittle2();
                        little();
                        return;

                    //break;
                }
            }
        }
    }


    public void opp2() throws InterruptedException {
        while (opModeIsActive()) {
            if (imageReady()) {
                int redValue = 0;
                int blueValue = 0;
                int greenValue = 0;

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
                int color = highestColor(redValue, blueValue, greenValue);

                switch (color) {
                    case 0:
                        colorString = "RED";
                        stopCamera();
                    return;


                    case 1:
                        colorString = "GREEN";
                        robot.WF.setPower(0.4);
                        robot.WB.setPower(0.4);
                        Thread.sleep(1000);
                        robot.WF.setPower(0);
                        robot.WB.setPower(0);
                        little();
                        stopCamera();
                        return;

                    case 2:
                        colorString = "BLUE";
                        robot.WF.setPower(0.4);
                        robot.WB.setPower(0.4);
                        Thread.sleep(1000);
                        robot.WF.setPower(0);
                        robot.WB.setPower(0);
                        little();
                        stopCamera();
                        return;


                }
            }
        }
    }
}










