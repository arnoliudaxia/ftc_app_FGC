package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


public class sht1 extends  LinearOpMode {

    /* Declare OpMode members. */
    TuringEchoRobotHardware   robot           = new TuringEchoRobotHardware();

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();
        shoot();
        stops();
        collection();
        weng2();
        shoot2();
        stops3();

    }


    public void shoot() throws InterruptedException {
        robot.sht.setPower(1);
        Thread.sleep(900);
        robot.sht.setPower(0);}
    public void stops() throws InterruptedException {
        robot.sht.setPower(0);
        Thread.sleep(400);
        robot.sht.setPower(0);}
    public void collection() throws  InterruptedException{
        robot.col.setPower(0.4);
        Thread.sleep(1000);
        robot.col.setPower(0);

    }
    public void weng2() throws InterruptedException {
        robot.col.setPower(0);
        Thread.sleep(1500);
        robot.col.setPower(0);}
    public void shoot2() throws InterruptedException {
        robot.sht.setPower(1);
        Thread.sleep(900);
        robot.sht.setPower(0);}
    public void stops3() throws InterruptedException {
        robot.sht.setPower(0);
        Thread.sleep(200);
        robot.sht.setPower(0);}

}


