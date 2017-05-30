/*
  这段代码是以倾斜45度为起始位置的自动化程序，弹射两个球并推开大球，并停在中心漩涡上
*/

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class lean extends  LinearOpMode {

    /* Declare OpMode members. */
    TuringEchoRobotHardware   robot           = new TuringEchoRobotHardware();

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();
        driveforward();
        weng();
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
        driveback();
        leftC();
        forwardlong();
        leftC2();
        corner();
        moca1();
        moca2();
        moca3();
        moca4();
    }


    public void driveforward() throws InterruptedException {
        robot.WL.setPower(-0.1);
        robot.WR.setPower(-0.1);
        Thread.sleep(4270);
        robot.WL.setPower(0);
        robot.WR.setPower(0);}

    public void weng() throws InterruptedException {
        robot.WL.setPower(0);
        robot.WR.setPower(0);
        Thread.sleep(2300);
        robot.WL.setPower(0);
        robot.WR.setPower(0);}
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
        //robot.baffle1.setPosition(0);Thread.sleep(900);
    }
    public void collection() throws  InterruptedException{
        robot.col.setPower(0.4);
        Thread.sleep(1400);
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
        Thread.sleep(2500);
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
        Thread.sleep(1600);
        robot.sht.setPower(0);}
    public void stops4() throws InterruptedException {
        robot.sht.setPower(0);
        Thread.sleep(200);
        robot.sht.setPower(0);}
    public void driveback() throws InterruptedException {
        robot.WL.setPower(0.1);
        robot.WR.setPower(0.1);
        Thread.sleep(1090);
        robot.WL.setPower(0);
        robot.WR.setPower(0);}
    public void leftC () throws InterruptedException{
        robot.WL.setPower(0.2);
        robot.WR.setPower(-0.2);
        Thread.sleep(550);
        robot.WL.setPower(0);
        robot.WL.setPower(0);}
    public void forwardlong() throws InterruptedException {
        robot.WL.setPower(-0.2);
        robot.WR.setPower(-0.2);
        Thread.sleep(3700);
        robot.WL.setPower(0);
        robot.WR.setPower(0);}
    public void leftC2 () throws InterruptedException{
        robot.WL.setPower(0.2);
        robot.WR.setPower(-0.2);
        Thread.sleep(580);
        robot.WL.setPower(0);
        robot.WL.setPower(0);}
    public void corner() throws InterruptedException {
        robot.WL.setPower(-0.2);
        robot.WR.setPower(-0.2);
        Thread.sleep(1640);
        robot.WL.setPower(0);
        robot.WR.setPower(0);}
    public void moca1() throws InterruptedException {
        robot.WL.setPower(-0.2);
        Thread.sleep(600);
        robot.WL.setPower(0);


    }

    public void moca2() throws InterruptedException {
        robot.WR.setPower(-0.2);
        Thread.sleep(600);
        robot.WR.setPower(0);
    }
    public void moca3() throws InterruptedException {
        robot.WL.setPower(-0.2);
        Thread.sleep(600);
        robot.WL.setPower(0);


    }
    public void moca4() throws InterruptedException {
        robot.WR.setPower(-0.2);
        Thread.sleep(600);
        robot.WR.setPower(0);
    }


}





