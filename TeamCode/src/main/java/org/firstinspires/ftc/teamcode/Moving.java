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
    DcMotor Leftfront;
    DcMotor Rightfront ;
    DcMotor Leftrear;
    DcMotor Rightrear;
    double power1 = 0.8;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        Leftfront = hardwareMap.get(DcMotor.class, "Leftfront");
        Rightfront = hardwareMap.get(DcMotor.class, "Rightfront");
        Leftrear = hardwareMap.get(DcMotor.class, "Leftrear");
        Rightrear = hardwareMap.get(DcMotor.class, "Rightrear");
        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        Leftfront.setDirection(DcMotor.Direction.FORWARD);
        Leftrear.setDirection(DcMotor.Direction.FORWARD);
        Rightfront.setDirection(DcMotor.Direction.REVERSE);
        Rightrear.setDirection(DcMotor.Direction.REVERSE);
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        /*double LeftfrontPower;
        double RightfrontPower;
        double LeftrearPower ;
        double RightrearPower ;
        int nothing = 1;

        LeftfrontPower = 0;
        LeftrearPower =0;
        RightfrontPower = 0;
        RightrearPower  = 0;
*/
        // run until the end of the match (driver presses STOP)
        //if you pull forward == - ,|| gamepad1.left_stick_x > 0
/*      LeftfrontPower = gamepad1.left_stick_y - gamepad1.left_stick_x;
        RightfrontPower = gamepad1.left_stick_y + gamepad1.left_stick_x;
        LeftrearPower = gamepad1.left_stick_y + gamepad1.left_stick_x;
        RightrearPower = gamepad1.left_stick_y - gamepad1.left_stick_x;
LeftfrontPower,LeftrearPower,RightfrontPower,RightrearPower
 */
        while (opModeIsActive()) {
            if (!gamepad1.x) {
                if (gamepad1.left_stick_y < 0 && gamepad1.left_stick_x == 0) {
                    power1 = 0.8;

                    Leftfront.setPower(power1);
                    Rightfront.setPower(power1);
                    Leftrear.setPower(power1);
                    Rightrear.setPower(power1);
                }


                if (gamepad1.left_stick_y > 0 && gamepad1.left_stick_x == 0) {
                    power1 = 0.8;
                    Leftfront.setPower(-power1);
                    Leftrear.setPower(-power1);
                    Rightfront.setPower(-power1);
                    Rightrear.setPower(-power1);
                }
                //hou tui
                if (gamepad1.left_stick_x > 0 && gamepad1.left_stick_y == 0) {
                    power1 = 0.8;
                    Leftfront.setPower(power1);
                    Leftrear.setPower(-power1);
                    Rightfront.setPower(-power1);
                    Rightrear.setPower(power1);
                }

                if (gamepad1.left_stick_x < 0 && gamepad1.left_stick_y == 0) {
                    power1 = 0.8;
                    Leftfront.setPower(-power1);
                    Leftrear.setPower(power1);
                    Rightfront.setPower(power1);
                    Rightrear.setPower(-power1);
                }
                /*if (gamepad1.left_stick_x < 0 && gamepad1.left_stick_y < 0) {
                    power1 = 0.5;
                    Leftfront.setPower(power1);
                    Leftrear.setPower(power1);
                    Rightfront.setPower(power1);
                    Rightrear.setPower(-power1);
                }//向左上
                if (gamepad1.left_stick_x < 0 && gamepad1.left_stick_y > 0) {
                    power1 = 0.5;
                    Leftfront.setPower(-power1);
                    Leftrear.setPower(-power1);
                    Rightfront.setPower(power1);
                    Rightrear.setPower(-power1);
                }//向左下
                if (gamepad1.left_stick_x > 0 && gamepad1.left_stick_y > 0) {
                    power1 = 0.5;
                    Leftfront.setPower(power1);
                    Leftrear.setPower(-power1);
                    Rightfront.setPower(-power1);
                    Rightrear.setPower(-power1);
                }//向右下
                if (gamepad1.left_stick_x > 0 && gamepad1.left_stick_y < 0) {
                    power1 = 0.5;
                    Leftfront.setPower(power1);
                    Leftrear.setPower(-power1);
                    Rightfront.setPower(power1);
                    Rightrear.setPower(power1);
                }//向右上*/
                if (gamepad1.left_stick_x == 0 && gamepad1.left_stick_y == 0) {
                    power1 = 0;
                    Leftfront.setPower(power1);
                    Leftrear.setPower(power1);
                    Rightfront.setPower(power1);
                    Rightrear.setPower(power1);
                }//stop}
                if (gamepad1.a) {
                    power1 = 0.5;
                    Leftfront.setPower(power1);
                    Leftrear.setPower(power1);
                    Rightfront.setPower(-power1);
                    Rightrear.setPower(-power1);
                }
                if (gamepad1.b) {
                    power1 = 0.5;
                    Leftfront.setPower(-power1);
                    Leftrear.setPower(-power1);
                    Rightfront.setPower(power1);
                    Rightrear.setPower(power1);
                } }else {
                    if (gamepad1.left_stick_y == -1 && gamepad1.left_stick_x == 0) {
                        power1 = 0.8;

                        Leftfront.setPower(power1);
                        Rightfront.setPower(power1);
                        Leftrear.setPower(power1);
                        Rightrear.setPower(power1);
                    }
                    if (gamepad1.left_stick_y == 1 && gamepad1.left_stick_x == 0) {
                        power1 = 0.8;
                        Leftfront.setPower(power1);
                        Leftrear.setPower(-power1);
                        Rightfront.setPower(-power1);
                        Rightrear.setPower(power1);
                    }
                    //hou tui
                    if (gamepad1.left_stick_x == 1 && gamepad1.left_stick_y == 0) {
                        power1 = 0.8;
                        Leftfront.setPower(power1);
                        Leftrear.setPower(power1);
                        Rightfront.setPower(power1);
                        Rightrear.setPower(power1);
                    }

                    if (gamepad1.left_stick_x == -1 && gamepad1.left_stick_y == 0) {
                        power1 = 0.8;
                        Leftfront.setPower(-power1);
                        Leftrear.setPower(power1);
                        Rightfront.setPower(power1);
                        Rightrear.setPower(-power1);
                    }
                }


            }
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            //telemetry.addData("Motors", "left (%.2f), right (%.2f)", power1);
            telemetry.update();

        }
    }





