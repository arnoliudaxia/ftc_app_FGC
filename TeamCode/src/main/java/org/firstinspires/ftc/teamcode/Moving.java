/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Moving", group="Linear Opmode")
//@Disabled
public class Moving extends LinearOpMode {

    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();
    //* private DcMotor leftDrive = null;
    //* private DcMotor rightDrive = null;
     DcMotor Motor_Zuoqian ;
     DcMotor Motor_Youqian ;
     DcMotor Motor_Zuohou ;
     DcMotor Motor_Youhou ;

double power = 0.6;
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        Motor_Zuoqian = hardwareMap.get(DcMotor.class, "ZuoQian");
        Motor_Youqian = hardwareMap.get(DcMotor.class, "YouQian");
        Motor_Zuohou = hardwareMap.get(DcMotor.class, "ZuoHou");
        Motor_Youhou = hardwareMap.get(DcMotor.class, "YouHou");
        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        Motor_Zuoqian.setDirection(DcMotor.Direction.REVERSE);
        Motor_Youqian.setDirection(DcMotor.Direction.FORWARD);
        Motor_Zuohou.setDirection(DcMotor.Direction.REVERSE);
        Motor_Youhou.setDirection(DcMotor.Direction.FORWARD);
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();
        double leftPower;
        double rightPower;
        int nothing = 1;
        double power1 = 0.5;

        double drive = -gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;
        leftPower = Range.clip(drive + turn, -1.0, 1.0);
        rightPower = Range.clip(drive - turn, -1.0, 1.0);
        // run until the end of the match (driver presses STOP)
        //if you pull forward == - ,
        // a == qianjin ; x == houtui  ; y == zhuan  ; b == tingzhi
        while (opModeIsActive()) {
            if (gamepad1.a) {//qianjin
                while (nothing == 1) {

                    Motor_Zuohou.setPower(power1);
                    Motor_Zuoqian.setPower(power1);
                    Motor_Youhou.setPower(power1);
                    Motor_Youqian.setPower(power1);
                }
            }

            if (gamepad1.x) {
                nothing = 2;
                while (nothing == 2) {
                    Motor_Zuohou.setPower(-power1);
                    Motor_Zuoqian.setPower(-power1);
                    Motor_Youhou.setPower(-power1);
                    Motor_Youqian.setPower(-power1);
                }
            }//hou tui
            if (gamepad1.y) {
                Motor_Zuohou.setPower(leftPower);
                Motor_Zuoqian.setPower(leftPower);
                Motor_Youhou.setPower(rightPower);
                Motor_Youqian.setPower(rightPower);
            }

            if (gamepad1.b) {
                power1=0.0;
                Motor_Zuohou.setPower(power1);
                Motor_Zuoqian.setPower(power1);
                Motor_Youhou.setPower(power1);
                Motor_Youqian.setPower(power1);

            }

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();
        }

    }
}
