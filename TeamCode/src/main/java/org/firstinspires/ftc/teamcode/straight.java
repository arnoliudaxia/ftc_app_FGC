package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name=" TuringEchoStraight", group="TuringEcho")
public class straight extends  LinearOpMode {

    /* Declare OpMode members. */
    TuringEchoRobotHardware   robot           = new TuringEchoRobotHardware();

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();
        driveforward();
        //stay ();
        //shoot ();
        //stay2 ();
        //driveforward2 ();
    }




    public void  driveforward() throws InterruptedException{
    robot.WL.setPower(-0.2);
    robot.WR.setPower(-0.2);
    Thread.sleep(2080);
    robot.WL.setPower(0);
    robot.WR.setPower(0);
}}
    //public void stay() throws InterruptedException{
        //robot.WL.setPower(0);
        //robot.WR.setPower(0);
        //Thread.sleep(1500);

    //public void shoot () throws InterruptedException{
        //robot.sht.setPower(-1);
        //Thread.sleep(1500);
        //robot.sht.setPower(0);
    //}
      //public void stay2() throws InterruptedException{
      //robot.WL.setPower(0);
      //robot.WR.setPower(0);
      //Thread.sleep(1500);
    //public void  driveforward2() throws InterruptedException{
        //robot.WL.setPower(-1);
        //robot.WR.setPower(-1);
        //Thread.sleep(500);
        //robot.WL.setPower(0);
        //robot.WR.setPower(0);}}



