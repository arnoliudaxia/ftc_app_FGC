/*
  这段代码是TuringEcho手动程序。一号手柄控制移动以及转向。二号手柄控制弹射校准以及弹射，并包含扎带收集以及扎带反转。
  还包含舵机（伺服）技术，采用挡板控制进球数量。
  至此，特别感谢重庆三十七中的程序员Rarcher在手动程序问题的解决上给予我们的帮助
*/



package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import android.content.Context;
import android.media.MediaPlayer;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="teleop", group="TuringEcho")
public class TuringEchoTeleOp extends LinearOpMode {
    //MediaPlayer mp = MediaPlayer.create(this, R.raw.zhw);
    /* Declare OpMode members. */
    TuringEchoRobotHardware robot = new TuringEchoRobotHardware();// Use a K9'shardware


    public void runOpMode() throws InterruptedException {
        double forward1;
        double huforward;
        double huleft;
        double huright;
        double huwp;
        double col;
        double left;
        double right;
        double wp;
        //double daball;
        double wp2;
        double sht;
        double baffle1;


        robot.init(hardwareMap);

        telemetry.addData("Say", "Hello Echo 老板好！");
        telemetry.addData("Say", "参见逼圣！");
        telemetry.addData("Say", "膜拜褚乾大佬！");//
        telemetry.update();


        waitForStart();


        while (opModeIsActive()) {

            forward1 = gamepad1.left_stick_y / 2.2;
            right = gamepad1.left_trigger / 2.2;
            left = gamepad1.right_trigger / 2.2;
            wp = gamepad1.right_stick_x / 2.2;


            //huforward = gamepad2.left_stick_y / 4.4;
            //huright = gamepad2.left_trigger / 4.4;
            //huleft = gamepad2.right_trigger / 4.4;
            //huwp = gamepad2.right_stick_x / 4.4;


            robot.WR.setPower(-forward1 - left + right);
            robot.WL.setPower(-forward1 + left - right);
            robot.WF.setPower(wp - left + right);
            robot.WB.setPower(wp - left + right);



            //robot.WR.setPower(huforward + huleft - huright);
            //robot.WL.setPower(huforward - huleft + huright);
            //robot.WF.setPower(-huwp + huleft - huright);
           // robot.WB.setPower(-huwp + huleft - huright);


            if (gamepad2.x){
                robot.baffle1.setPosition(0);
            }
            else{
                robot.baffle1.setPosition(0.5);}

           // if (gamepad1.b){
               // robot.daball.setPower(0.8);

           // }
           // else {
                //robot.daball.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
           // }


            if (gamepad2.a) {
                robot.sht.setPower(1);
                Thread.sleep(600);
                robot.sht.setPower(0);
                robot.sht.setPower(0.3);
                Thread.sleep(800);
                robot.sht.setPower(0);}

            if (gamepad2.left_bumper) {
                robot.col.setPower(-0.5);
            }
            //if (gamepad2.right_bumper) {
              //  robot.col.setPower(0.4);
            //}
            if (gamepad2.y) {
                robot.col.setPower(0);

                //try {
                //mp.reset();//从新设置要播放的音乐
                //           mediaPlayer.setDataSource(file.getAbsolutePath());
                // //            mediaPlayer.prepare();//预加载音频
                //mp.start();//播放音乐
                //Thread.sleep(30000);

               // } catch (Exception e) {
                // e.printStackTrace();
                //}


            }




           }
        }
    }

















