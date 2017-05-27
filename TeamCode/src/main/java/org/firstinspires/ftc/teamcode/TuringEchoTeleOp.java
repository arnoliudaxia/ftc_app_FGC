/*
  这段代码是TuringEcho手动程序。一号手柄控制移动以及转向。二号手柄控制弹射校准以及弹射，并包含扎带收集以及扎带反转。
  还包含舵机（伺服）技术，采用挡板控制进球数量。
  至此，特别感谢重庆三十七中的程序员Rarcher在手动程序问题的解决上给予我们的帮助
*/



package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;




@TeleOp(name="TuringEchoTeleOp", group="TuringEcho")
public class TuringEchoTeleOp extends LinearOpMode {

    /* Declare OpMode members. */
    TuringEchoRobotHardware1 robot = new TuringEchoRobotHardware1();              // Use a K9'shardware


    public void runOpMode() throws InterruptedException {
        double forward1;
        double col;
        double left;
        double right;
        double wp;
        double wp2;
        double sht;


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
            wp = gamepad1.right_stick_x/2.2;

            robot.WR.setPower(-forward1 - left + right);
            robot.WL.setPower(-forward1 + left - right);
            robot.WF.setPower(wp - left +right);
            robot.WB.setPower(wp - left +right);

            if (gamepad2.left_bumper) {
            robot.col.setPower(-0.4);
            }
            if (gamepad2.right_bumper){
               robot.col.setPower(0.4);
            }
            if (gamepad2.y){
                robot.col.setPower(0);
            }
            if (gamepad2.a){
                robot.sht.setPower(1);
                Thread.sleep(400);
                robot.sht.setPower(0);
                robot.sht.setPower(0.2);
                Thread.sleep(800);
                robot.sht.setPower(0);

            }

           }

         }
    }

