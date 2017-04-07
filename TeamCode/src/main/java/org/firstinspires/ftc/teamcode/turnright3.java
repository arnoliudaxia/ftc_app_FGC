package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name=" 3", group="TuringEcho")
public class turnright3 extends  LinearOpMode {

    /* Declare OpMode members. */
    TuringEchoRobotHardware   robot           = new TuringEchoRobotHardware();

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();
        turnright();
    }






     public void turnright () throws InterruptedException{
     robot.WL.setPower(-0.2);
     robot.WR.setPower(0.2);
     Thread.sleep(1040);
     robot.WL.setPower(0);
     robot.WL.setPower(0);}


    }





