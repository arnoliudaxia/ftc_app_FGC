
/*
  这段代码是使用伺服作为挡板后，弹射2个球的自动程序
  在此，特别感谢重庆三十七中的程序员Rarcher为我们在自动程序搭建上的所有帮助
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class shoot1 extends  LinearOpMode {

    /* Declare OpMode members. */
    TuringEchoRobotHardware   robot           = new TuringEchoRobotHardware();

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);

        waitForStart();
        shoot();
        stops();
        jiaozhun();
        collection();
        cstop();
        jiaozhun2();
        weng2();
        shoot2();
        stops3();
        adjust2();
        stops4();


    }


    public void shoot() throws InterruptedException {
        robot.sht.setPower(1);
        Thread.sleep(500);
        robot.sht.setPower(0);}
    public void stops() throws InterruptedException {
        robot.sht.setPower(0);
        Thread.sleep(400);
        robot.sht.setPower(0);}
    public void jiaozhun() throws  InterruptedException{
        robot.sht.setPower(0.1);
        Thread.sleep(1000);
        robot.sht.setPower(0);
        //robot.baffle1.setPosition(0);
        Thread.sleep(900);
    }
    public void collection() throws  InterruptedException{
        robot.col.setPower(0.4);
        Thread.sleep(1800);
        robot.col.setPower(0);
    }
    public void cstop() throws  InterruptedException{
        robot.col.setPower(0);
        Thread.sleep(700);
        robot.col.setPower(0);
    }
    public void jiaozhun2() throws  InterruptedException{

        //robot.baffle1.setPosition(0.6);Thread.sleep(900);
        //robot.baffle1.close();
    }
    public void weng2() throws InterruptedException {
        robot.col.setPower(0);
        Thread.sleep(1500);
        robot.col.setPower(0);}
    public void shoot2() throws InterruptedException {
        robot.sht.setPower(1);
        Thread.sleep(500);
        robot.sht.setPower(0);}
    public void stops3() throws InterruptedException {
        robot.sht.setPower(0);
        Thread.sleep(200);
        robot.sht.setPower(0);}
    public void adjust2() throws InterruptedException {
        robot.sht.setPower(0.1);
        Thread.sleep(1500);
        robot.sht.setPower(0);}
    public void stops4() throws InterruptedException {
        robot.sht.setPower(0);
        Thread.sleep(200);
        robot.sht.setPower(0);}



}


