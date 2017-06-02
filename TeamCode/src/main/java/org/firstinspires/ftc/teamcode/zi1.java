
/*
  这段代码是使用伺服作为挡板后，弹射2个球的自动程序
  在此，特别感谢重庆三十七中的程序员Rarcher为我们在自动程序搭建上的所有帮助
*/
package org.firstinspires.ftc.teamcode;



import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name="zi1", group="TuringEcho")
public class zi1 extends LinearOpMode {

    /* Declare OpMode members. */
    TuringEchoRobotHardware robot = new TuringEchoRobotHardware();
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();
        forward1();

    }

    public void forward1() throws InterruptedException {
        robot.WF.setPower(-0.2);
        robot.WB.setPower(0.2);
        robot.WL.setPower(-0.2);
        robot.WR.setPower(0.2);
        Thread.sleep(1000);
        robot.WF.setPower(0);
        robot.WB.setPower(0);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
    }
}


