/*
  这段代码是TuringEcho手动程序。一号手柄控制移动以及转向。二号手柄控制弹射校准以及弹射，并包含扎带收集以及扎带反转。
  还包含舵机（伺服）技术，采用挡板控制进球数量。
  至此，感谢重庆三十七中的程序员Rarcher
*/



package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name=" TuringEchoTeleOp", group="TuringEcho")
public class TuringEchoTeleOp extends LinearOpMode {

    /* Declare OpMode members. */
    TuringEchoRobotHardware   robot           = new TuringEchoRobotHardware();              // Use a K9'shardware

    public void sleepmanager(DcMotor sht,long time){
        sleep(time);
        sht.setPower(0);
    }

    public void runOpMode() throws InterruptedException {
        double forward1;
        double forward2;
        double left;
        double right;


        robot.init(hardwareMap);


        telemetry.addData("Say", "Hello Echo");    //
        telemetry.update();


        waitForStart();

        while (opModeIsActive()) {

            forward1 = gamepad1.left_stick_y / 2;
            forward2 = gamepad1.right_stick_y / 2;
            right = gamepad1.left_trigger / 4;
            left = gamepad1.right_trigger / 4;
            robot.WL.setPower(forward1 + forward2 - left + right);
            robot.WR.setPower(forward1 + forward2 + left - right);
            if (gamepad2.left_bumper)
                robot.col.setPower(0.4);
            if (gamepad2.right_bumper)
                robot.col.setPower(-0.4);
            if (gamepad2.a){
                robot.sht.setPower(1);
                sleepmanager(robot.sht,500);}
            if (gamepad2.x) {
                robot.sht.setPower(0.1);
                Thread.sleep(1000);
                robot.sht.setPower(0);
                robot.baffle1.setPosition(0.7);Thread.sleep(800);
                robot.baffle1.close();Thread.sleep(600);
                robot.baffle1.setPosition(0);Thread.sleep(500);
                robot.baffle1.close();
                //Thread.sleep(1500);
                //robot.baffle1.close();
                //Thread.sleep(1500);
                //robot.baffle1.setPosition(0);
                //robot.baffle1.close();
            }}
            telemetry.addData("Servo Position", "%5.2f", robot.baffle1.getPosition());
            telemetry.addData(">", "Press Stop to end test." );
            telemetry.update();



        }

}

