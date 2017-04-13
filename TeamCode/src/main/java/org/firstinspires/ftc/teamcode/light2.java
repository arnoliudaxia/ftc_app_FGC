package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name=" TuringEchoLight2", group="TuringEcho")
public class light2 extends  LinearOpMode {

    /* Declare OpMode members. */
    TuringEchoRobotHardware   robot           = new TuringEchoRobotHardware();

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();
        shoot();
        Driveforward();
        turnleft();
        forward();
        right1();
        forwardbit1();
        turnleft2();
        light1();
        back1();
        light2();
        back2();
        light3();
        back3();
        turnleft3();
        forwardbit2();
        right45();
        corner();
        moca1();
        moca2();
        moca3();
        moca4();
        rengqiu();
    }



    public void shoot() throws InterruptedException {
        robot.sht.setPower(1);
        Thread.sleep(800);
        robot.sht.setPower(0);}
    public void Driveforward() throws InterruptedException {
        robot.WL.setPower(-0.2);
        robot.WR.setPower(-0.2);
        Thread.sleep(1000);
        robot.WL.setPower(0);
        robot.WR.setPower(0);}


    public void turnleft () throws InterruptedException{
        robot.WL.setPower(0.2);
        robot.WR.setPower(-0.2);
        Thread.sleep(880);
        robot.WL.setPower(0);
        robot.WL.setPower(0);}




    public void forward() throws InterruptedException {
        robot.WL.setPower(-0.2);
        robot.WR.setPower(-0.2);
        Thread.sleep(1000);
        robot.WL.setPower(0);
        robot.WR.setPower(0);


    }
    public void right1 () throws InterruptedException{
        robot.WL.setPower(-0.2);
        robot.WR.setPower(0.2);
        Thread.sleep(870);
        robot.WL.setPower(0);
        robot.WL.setPower(0);}
    public void forwardbit1() throws InterruptedException {
        robot.WL.setPower(-0.2);
        robot.WR.setPower(-0.2);
        Thread.sleep(1420);
        robot.WL.setPower(0);
        robot.WR.setPower(0);}

    public void turnleft2 () throws InterruptedException{
        robot.WL.setPower(0.2);
        robot.WR.setPower(-0.2);
        Thread.sleep(1110);
        robot.WL.setPower(0);
        robot.WL.setPower(0);}
    public void light1() throws InterruptedException {
        robot.WL.setPower(-0.2);
        robot.WR.setPower(-0.2);
        Thread.sleep(1830);
        robot.WL.setPower(0);
        robot.WR.setPower(0);}
    public void back1() throws InterruptedException {
        robot.WL.setPower(0.2);
        robot.WR.setPower(0.2);
        Thread.sleep(630);
        robot.WL.setPower(0);
        robot.WR.setPower(0);}
    public void light2() throws InterruptedException {
        robot.WL.setPower(-0.2);
        robot.WR.setPower(-0.2);
        Thread.sleep(900);
        robot.WL.setPower(0);
        robot.WR.setPower(0);}
    public void back2() throws InterruptedException {
        robot.WL.setPower(0.2);
        robot.WR.setPower(0.2);
        Thread.sleep(630);
        robot.WL.setPower(0);
        robot.WR.setPower(0);}
    public void light3() throws InterruptedException {
        robot.WL.setPower(-0.2);
        robot.WR.setPower(-0.2);
        Thread.sleep(900);
        robot.WL.setPower(0);
        robot.WR.setPower(0);}
    public void back3() throws InterruptedException {
        robot.WL.setPower(0.2);
        robot.WR.setPower(0.2);
        Thread.sleep(1230);
        robot.WL.setPower(0);
        robot.WR.setPower(0);}
    public void turnleft3 () throws InterruptedException{
        robot.WL.setPower(0.2);
        robot.WR.setPower(-0.2);
        Thread.sleep(980);
        robot.WL.setPower(0);
        robot.WL.setPower(0);}

    public void forwardbit2() throws InterruptedException {
        robot.WL.setPower(-0.2);
        robot.WR.setPower(-0.2);
        Thread.sleep(890);
        robot.WL.setPower(0);
        robot.WR.setPower(0);}
    public void right45 () throws InterruptedException{
        robot.WL.setPower(-0.2);
        robot.WR.setPower(0.2);
        Thread.sleep(560);
        robot.WL.setPower(0);
        robot.WL.setPower(0);}
    public void corner() throws InterruptedException {
        robot.WL.setPower(-0.2);
        robot.WR.setPower(-0.2);
        Thread.sleep(1240);
        robot.WL.setPower(0);
        robot.WR.setPower(0);}
    public void moca1() throws InterruptedException {
        robot.WL.setPower(-0.2);
        Thread.sleep(400);
        robot.WL.setPower(0);


    }

    public void moca2() throws InterruptedException {
        robot.WR.setPower(-0.2);
        Thread.sleep(400);
        robot.WR.setPower(0);
    }
    public void moca3() throws InterruptedException {
        robot.WL.setPower(-0.2);
        Thread.sleep(400);
        robot.WL.setPower(0);


    }
    public void moca4() throws InterruptedException {
        robot.WR.setPower(-0.2);
        Thread.sleep(400);
        robot.WR.setPower(0);
    }

    public void rengqiu() throws  InterruptedException {
        robot.col.setPower(-0.4);
        Thread.sleep(2500);
        robot.col.setPower(0);
    }
}


