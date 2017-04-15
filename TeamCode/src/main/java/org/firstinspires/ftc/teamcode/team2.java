package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name=" team2", group="TuringEcho")
public class team2 extends  LinearOpMode {

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
        stopslong();
        ball1();
        ballback1();
        ball2();
        ballback2();

    }


    public void driveforward() throws InterruptedException {
        robot.WL.setPower(-0.1);
        robot.WR.setPower(-0.1);
        Thread.sleep(3980);
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

    public void stopslong() throws InterruptedException {
        robot.sht.setPower(0);
        Thread.sleep(14000);
        robot.sht.setPower(0);}
    public void ball1() throws InterruptedException {
        robot.WL.setPower(-0.2);
        robot.WR.setPower(-0.2);
        Thread.sleep(2400);
        robot.WL.setPower(0);
        robot.WR.setPower(0);}
    public void ballback1() throws InterruptedException {
        robot.WL.setPower(0.2);
        robot.WR.setPower(0.2);
        Thread.sleep(1100);
        robot.WL.setPower(0);
        robot.WR.setPower(0);}
    public void ball2() throws InterruptedException {
        robot.WL.setPower(-0.2);
        robot.WR.setPower(-0.2);
        Thread.sleep(2200);
        robot.WL.setPower(0);
        robot.WR.setPower(0);}
    public void ballback2() throws InterruptedException {
        robot.WL.setPower(0.2);
        robot.WR.setPower(0.2);
        Thread.sleep(510);
        robot.WL.setPower(0);
        robot.WR.setPower(0);}



}


