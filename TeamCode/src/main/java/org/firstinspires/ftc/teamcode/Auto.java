package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name=" TuringEchoCorner", group="TuringEcho")
public class Auto extends  LinearOpMode {

    /* Declare OpMode members. */
    TuringEchoRobotHardware   robot           = new TuringEchoRobotHardware();

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();
        driveforward();
        turnright();
        forward();
        right45();
        corner();
        moca1();
        moca2();
        moca3();
        moca4();
        rengqiu();
    }




    public void driveforward() throws InterruptedException {
        robot.WL.setPower(-0.2);
        robot.WR.setPower(-0.2);
        Thread.sleep(1200);
        robot.WL.setPower(0);
        robot.WR.setPower(0);}


    public void turnright () throws InterruptedException{
        robot.WL.setPower(-0.2);
        robot.WR.setPower(0.2);
        Thread.sleep(1040);
        robot.WL.setPower(0);
        robot.WL.setPower(0);}




    public void forward() throws InterruptedException {
        robot.WL.setPower(-0.2);
        robot.WR.setPower(-0.2);
        Thread.sleep(1600);
        robot.WL.setPower(0);
        robot.WR.setPower(0);


     }
    public void right45 () throws InterruptedException{
        robot.WL.setPower(-0.2);
        robot.WR.setPower(0.2);
        Thread.sleep(520);
        robot.WL.setPower(0);
        robot.WL.setPower(0);}

    public void corner() throws InterruptedException {
        robot.WL.setPower(-0.2);
        robot.WR.setPower(-0.2);
        Thread.sleep(1540);
        robot.WL.setPower(0);
        robot.WR.setPower(0);



    }
    public void moca1() throws InterruptedException {
        robot.WL.setPower(-0.2);
        Thread.sleep(200);
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
        robot.col.setPower(-1);
        Thread.sleep(2500);
        robot.col.setPower(0);
    }
}


