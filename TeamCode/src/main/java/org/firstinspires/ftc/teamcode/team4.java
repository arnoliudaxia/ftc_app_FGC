package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name=" team4", group="TuringEcho")
public class team4 extends  LinearOpMode {

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
        collection();
        weng2();
        shoot2();
        stops3();
        adjust2();
        stops4();

    }


    public void driveforward() throws InterruptedException {
        robot.WL.setPower(-0.1);
        robot.WR.setPower(-0.1);
        Thread.sleep(3920);
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
}





