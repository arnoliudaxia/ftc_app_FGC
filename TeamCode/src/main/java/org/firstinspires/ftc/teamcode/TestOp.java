/*
  这段代码是TuringEcho手动程序。一号手柄控制移动以及转向。二号手柄控制弹射校准以及弹射，并包含扎带收集以及扎带反转。
  还包含舵机（伺服）技术，采用挡板控制进球数量。
  至此，特别感谢重庆三十七中的程序员Rarcher在手动程序问题的解决上给予我们的帮助
*/



package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


public class TestOp extends LinearOpMode {

    /* Declare OpMode members. */
    Test robot = new Test();              // Use a K9'shardware



    public void runOpMode() throws InterruptedException {
        double baffle1;

        robot.init(hardwareMap);


        telemetry.addData("Say", "Hello Echo" + "老板好！！！");    //
        telemetry.update();


        waitForStart();

        while (opModeIsActive()) {

            if (gamepad2.a){
                robot.baffle1.setPosition(0.5);


            }
            else
                robot.baffle1.setPosition(0);
        }
    }}

