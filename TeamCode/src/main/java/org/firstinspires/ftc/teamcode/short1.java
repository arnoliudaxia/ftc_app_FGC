package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name=" short1", group="TuringEcho")
public class short1 extends  LinearOpMode {

    /* Declare OpMode members. */
    TuringEchoRobotHardware robot = new TuringEchoRobotHardware();

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();
        driveforward();
    }


    public void driveforward() throws InterruptedException {
        robot.WL.setPower(-0.2);
        robot.WR.setPower(-0.2);
        Thread.sleep(700);
        robot.WL.setPower(0);
        robot.WR.setPower(0);


    }
}




