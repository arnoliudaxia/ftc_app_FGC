package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name=" TuringEchoCentre", group="TuringEchoCentre")
public class centre extends  LinearOpMode {

    /* Declare OpMode members. */
    TuringEchoRobotHardware   robot           = new TuringEchoRobotHardware();

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();
        forward1();
        turnleft();
        forward2();
        stay();
        turnright();
        forward3();


    }
    public void  forward1() throws InterruptedException{
        robot.WL.setPower(-0.5);
        robot.WR.setPower(-0.5);
        Thread.sleep(1600);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
    }
    public void turnleft () throws InterruptedException{
        robot.WL.setPower(0.6);
        robot.WR.setPower(-0.6);
        Thread.sleep(200);
        robot.WL.setPower(0);
        robot.WL.setPower(0);}

    public void  forward2() throws InterruptedException{
        robot.WL.setPower(-0.5);
        robot.WR.setPower(-0.5);
        Thread.sleep(300);
        robot.WL.setPower(0);
        robot.WR.setPower(0);}

    public void  stay() throws InterruptedException{
        robot.WL.setPower(0);
        robot.WR.setPower(0);
        Thread.sleep(1000);

    }

    public void turnright () throws InterruptedException{
        robot.WL.setPower(-0.6);
        robot.WR.setPower(0.6);
        Thread.sleep(200);
        robot.WL.setPower(0);
        robot.WL.setPower(0);}


    public void  forward3() throws InterruptedException{
    robot.WL.setPower(-0.5);
    robot.WR.setPower(-0.5);
    Thread.sleep(1000);
    robot.WL.setPower(0);
    robot.WR.setPower(0);
    }





    }


