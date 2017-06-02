
/*
  这段代码是使用伺服作为挡板后，弹射2个球的自动程序
  在此，特别感谢重庆三十七中的程序员Rarcher为我们在自动程序搭建上的所有帮助
*/
package org.firstinspires.ftc.teamcode;

import android.graphics.Bitmap;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import for_camera_opmodes.LinearOpModeCamera;

@Autonomous(name="bluelight", group="TuringEcho")//13.7
public class bluelight extends  LinearOpModeCamera {

    /* Declare OpMode members. */
    int ds2 = 4;
    TuringEchoRobotHardware robot = new TuringEchoRobotHardware();
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
        shoot();
        //col();
        //runwait();
        //shoot();


        robot.WL.setPower(0.4);
        robot.WR.setPower(0.4);
        Thread.sleep(1050);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
        runwait();
        left0();
        runwait();
        forward1();
        runwait();



        left1();//zhuang
        little();
        opp();
        opp2();


    }
    public void left0() throws InterruptedException {
        robot.WF.setPower(0.5);
        robot.WB.setPower(0.5);
        Thread.sleep(1400);
        robot.WF.setPower(0);
        robot.WB.setPower(0);

    }
    public void runwait() throws InterruptedException {
        robot.WL.setPower(0);
        robot.WR.setPower(0);
        Thread.sleep(500);
        robot.WL.setPower(0);
        robot.WR.setPower(0);

    }
    //public void col() throws InterruptedException {
       // robot.col.setPower(-0.4);
       // Thread.sleep(1500);
       // robot.col.setPower(0);


   // }

    public void forward1() throws InterruptedException {

        robot.WL.setPower(0.4);
        robot.WR.setPower(0.4);
        Thread.sleep(1950);
        robot.WL.setPower(0);
        robot.WR.setPower(0);

    }
    public void little() throws InterruptedException {
        robot.WF.setPower(-0.3);
        robot.WB.setPower(-0.3);
        Thread.sleep(250);
        robot.WF.setPower(0);
        robot.WB.setPower(0);

    }
    public void little2() throws InterruptedException {
        robot.WF.setPower(-0.3);
        robot.WB.setPower(-0.3);
        Thread.sleep(330);
        robot.WF.setPower(0);
        robot.WB.setPower(0);

    }
    public void shootlittle() throws InterruptedException {
        robot.WL.setPower(0.4);
        robot.WR.setPower(0.4);
        Thread.sleep(350);
        robot.WL.setPower(0);
        robot.WR.setPower(0);

    }

    public void left1() throws InterruptedException {
        robot.WF.setPower(0.5);
        robot.WB.setPower(0.5);
        Thread.sleep(2000);
        robot.WF.setPower(0);
        robot.WB.setPower(0);

    }
    public void forward2() throws InterruptedException {
        robot.WL.setPower(0.4);
        robot.WR.setPower(0.4);
        Thread.sleep(2380);
        robot.WL.setPower(0);
        robot.WR.setPower(0);

    }
    public void ball() throws InterruptedException {
        robot.WL.setPower(0.6);
        robot.WR.setPower(0.6);
        Thread.sleep(2480);
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
        robot.WF.setPower(0.4);
        robot.WB.setPower(0.4);
        Thread.sleep(1400);
        robot.WF.setPower(0);
        robot.WB.setPower(0);

    }

    public void right2() throws InterruptedException {
        robot.WF.setPower(-0.4);
        robot.WB.setPower(-0.4);
        Thread.sleep(1030);
        robot.WF.setPower(0);
        robot.WB.setPower(0);

    }
    public void right2little() throws InterruptedException {
        robot.WF.setPower(-0.4);
        robot.WB.setPower(-0.4);
        Thread.sleep(680);
        robot.WF.setPower(0);
        robot.WB.setPower(0);

    }
    public void shoot() throws InterruptedException {
        robot.sht.setPower(1);
        Thread.sleep(600);
        robot.sht.setPower(0);
        robot.sht.setPower(0.3);
        Thread.sleep(800);
        robot.sht.setPower(0);}

    public void leftlittle2() throws InterruptedException {
        robot.WF.setPower(0.4);
        robot.WB.setPower(0.4);
        Thread.sleep(1100);
        robot.WF.setPower(0);
        robot.WB.setPower(0);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
        Thread.sleep(500);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
    }
    public void zhuan() throws InterruptedException {
    robot.WF.setPower(-0.2);
        robot.WB.setPower(0.2);
        robot.WL.setPower(-0.2);
        robot.WR.setPower(0.2);
        Thread.sleep(1100);
        robot.WF.setPower(0);
        robot.WB.setPower(0);
        robot.WL.setPower(0);
        robot.WR.setPower(0);}

    public void opp() throws InterruptedException {
        while (opModeIsActive()) {
            setCameraDownsampling(2);
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
                        robot.WL.setPower(0);
                        robot.WR.setPower(0);
                        Thread.sleep(1100);
                        robot.WL.setPower(0);
                        robot.WR.setPower(0);

                        robot.WF.setPower(0.4);
                        robot.WB.setPower(0.4);
                        Thread.sleep(800);
                        robot.WF.setPower(0);
                        robot.WB.setPower(0);//you


                        robot.WF.setPower(0);
                        robot.WB.setPower(0);
                        Thread.sleep(500);
                        robot.WF.setPower(0);
                        robot.WB.setPower(0);



                        right2();
                        robot.WL.setPower(0);
                        robot.WR.setPower(0);
                        Thread.sleep(500);
                        robot.WL.setPower(0);
                        robot.WR.setPower(0);
                        forward2();
                        robot.WL.setPower(0);
                        robot.WR.setPower(0);
                        Thread.sleep(300);
                        robot.WL.setPower(0);
                        robot.WR.setPower(0);
                        leftlittle1();
                        little();
                        robot.WL.setPower(0);
                        robot.WR.setPower(0);
                        Thread.sleep(1000);
                        robot.WL.setPower(0);
                        robot.WR.setPower(0);
                    //break;
                        return;

                    case 1:
                        colorString = "GREEN";
                        //telemetry.addData("Color:", "Color detected is: " + colorString);
                        //telemetry.update();

                        robot.WF.setPower(0);
                        robot.WB.setPower(0);
                        Thread.sleep(500);
                        robot.WF.setPower(0);
                        robot.WB.setPower(0);
                        forward2();
                        leftlittle1();
                        little();
                        return;
                    //break;


                    case 2:
                        colorString = "BLUE";
                        robot.WF.setPower(0);
                        robot.WB.setPower(0);
                        Thread.sleep(500);
                        robot.WF.setPower(0);
                        robot.WB.setPower(0);
                        forward2();
                        leftlittle1();
                        little();
                        //telemetry.addData("Color:", "Color detected is: " + colorString);
                        //telemetry.update();


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
                        //telemetry.addData("2Color:", "Color detected is: " + colorString);
                        //telemetry.update();
                        robot.WL.setPower(0);
                        robot.WR.setPower(0);
                        Thread.sleep(1000);
                        robot.WL.setPower(0);
                        robot.WR.setPower(0);
                        robot.WF.setPower(0.4);
                        robot.WB.setPower(0.4);
                        Thread.sleep(1600);
                        robot.WF.setPower(0);
                        robot.WB.setPower(0);
                        robot.WF.setPower(-0.4);
                        robot.WB.setPower(-0.4);
                        Thread.sleep(1000);
                        robot.WF.setPower(0);
                        robot.WB.setPower(0);
                        robot.WF.setPower(-0.4);
                        robot.WB.setPower(-0.4);
                        Thread.sleep(700);
                        robot.WF.setPower(0);
                        robot.WB.setPower(0);
                        little2();
                        stopCamera();
                        zhuan();
                        ball();

                        return;


                    case 1:
                        colorString = "GREEN";
                        //telemetry.addData("2Color:", "Color detected is: " + colorString);
                        //telemetry.update();

                        little2();
                        stopCamera();
                        zhuan();
                        ball();
                        return;


                    case 2:
                        colorString = "BLUE";
                        //telemetry.addData("2color:", "Color detected is: " + colorString);
                        //telemetry.update();
                        little2();
                        stopCamera();
                        zhuan();
                        ball();
                        return;


                }
            }
        }
    }
}










