package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name=" bluedisturb1423", group="TuringEcho")
public class reddisturb2 extends  LinearOpMode {

    /* Declare OpMode members. */
    TuringEchoRobotHardware   robot           = new TuringEchoRobotHardware();

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();
        driveforward();
        weng();
        shoot();
        stops();
        adjust();
        stops2();
        collection();
        weng2();
        shoot2();
        stops3();
        adjust2();
        stops4();
        turnleft();
        forward();
        turnright();
        forward2();
        turnleft2();
        forwardbit();

    }


    public void driveforward() throws InterruptedException {
        robot.WL.setPower(-0.1);
        robot.WR.setPower(-0.1);
        Thread.sleep(2080);
        robot.WL.setPower(0);
        robot.WR.setPower(0);}

    public void weng() throws InterruptedException {
        robot.WL.setPower(0);
        robot.WR.setPower(0);
        Thread.sleep(3500);
        robot.WL.setPower(0);
        robot.WR.setPower(0);}
    public void shoot() throws InterruptedException {
        robot.sht.setPower(1);
        Thread.sleep(900);
        robot.sht.setPower(0);}
    public void stops() throws InterruptedException {
        robot.sht.setPower(0);
        Thread.sleep(400);
        robot.sht.setPower(0);}
    public void adjust() throws InterruptedException {
        robot.sht.setPower(0.1);
        Thread.sleep(1600);
        robot.sht.setPower(0);}
    public void stops2() throws InterruptedException {
        robot.sht.setPower(0);
        Thread.sleep(200);
        robot.sht.setPower(0);}
    public void collection() throws InterruptedException {
        robot.col.setPower(0.5);
        Thread.sleep(1900);
        robot.sht.setPower(0);}

    public void weng2() throws InterruptedException {
        robot.col.setPower(0);
        Thread.sleep(3400);
        robot.col.setPower(0);}
    public void shoot2() throws InterruptedException {
        robot.sht.setPower(1);
        Thread.sleep(900);
        robot.sht.setPower(0);}
    public void stops3() throws InterruptedException {
        robot.sht.setPower(0);
        Thread.sleep(200);
        robot.sht.setPower(0);}
    public void adjust2() throws InterruptedException {
        robot.sht.setPower(0.1);
        Thread.sleep(1600);
        robot.sht.setPower(0);}
    public void stops4() throws InterruptedException {
        robot.sht.setPower(0);
        Thread.sleep(200);
        robot.sht.setPower(0);}

    public void turnleft () throws InterruptedException{
        robot.WL.setPower(-0.2);
        robot.WR.setPower(0.2);
        Thread.sleep(1020);
        robot.WL.setPower(0);
        robot.WL.setPower(0);}




    public void forward() throws InterruptedException {
        robot.WL.setPower(-0.2);
        robot.WR.setPower(-0.2);
        Thread.sleep(1800);
        robot.WL.setPower(0);
        robot.WR.setPower(0);



    }
    public void turnright () throws InterruptedException{
        robot.WL.setPower(-0.2);
        robot.WR.setPower(0.2);
        Thread.sleep(1020);
        robot.WL.setPower(0);
        robot.WL.setPower(0);}
    public void forward2() throws InterruptedException {
        robot.WL.setPower(-0.2);
        robot.WR.setPower(-0.2);
        Thread.sleep(2200);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
}
    public void turnleft2 () throws InterruptedException{
        robot.WL.setPower(-0.2);
        robot.WR.setPower(0.2);
        Thread.sleep(1020);
        robot.WL.setPower(0);
        robot.WL.setPower(0);}
    public void forwardbit() throws InterruptedException {
        robot.WL.setPower(-0.2);
        robot.WR.setPower(-0.2);
        Thread.sleep(1000);
        robot.WL.setPower(0);
        robot.WR.setPower(0);
    }}