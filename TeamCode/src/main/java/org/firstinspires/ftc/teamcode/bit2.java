
/*
  这段代码是使用伺服作为挡板后，弹射2个球的自动程序
  在此，特别感谢重庆三十七中的程序员Rarcher为我们在自动程序搭建上的所有帮助
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class bit2 extends  LinearOpMode {

    /* Declare OpMode members. */
    TuringEchoRobotHardware robot = new TuringEchoRobotHardware();

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();
        forward1();

    }

    public void forward1() throws InterruptedException {
        robot.WL.setPower(0.4);
        robot.WR.setPower(0.4);
        Thread.sleep(2700);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
        Thread.sleep(200);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
    }
}


