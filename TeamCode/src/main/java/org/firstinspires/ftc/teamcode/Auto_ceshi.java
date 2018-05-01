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

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
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

@TeleOp(name="Auto_ceshi", group="Linear Opmode")
@Disabled
public class Auto_ceshi extends TurningEchoHardware {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    int P1 = 0;
    int P2 = 0;
    int P3 = 0;
    int P4 = 0;



    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        TurningEchoHardwareConfigure();
//        motorFL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        motorFR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        motorBL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        motorBR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            if (gamepad1.dpad_up){
                runtime.reset();

                motorFL.setTargetPosition(3350);
                motorFR.setTargetPosition(-3350);
                motorBL.setTargetPosition(-3350);
                motorBR.setTargetPosition(3350);
                motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motorFL.setPower(0.5);
                motorFR.setPower(-0.5);
                motorBL.setPower(-0.5);
                motorBR.setPower(0.5);
                while(runtime.seconds()<3 && motorFL.isBusy() && motorFR.isBusy()&&motorBL.isBusy() && motorBR.isBusy()){}
                motorFL.setPower(0);
                motorFR.setPower(0);
                motorBL.setPower(0);
                motorBR.setPower(0);
                sleep(500);
            }
            if (gamepad1.dpad_down){
//                P1 = P1 - 200;
//                P2 = P2 - 200;
//                P3 = P3 - 200;
//                P4 = P4 - 200;
                motorFL.setTargetPosition(200);
                motorFR.setTargetPosition(200);
                motorBL.setTargetPosition(200);
                motorBR.setTargetPosition(200);
                motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motorFL.setPower(-0.5);
                motorFR.setPower(-0.5);
                motorBL.setPower(-0.5);
                motorBR.setPower(-0.5);
                while (motorFL.isBusy()&&motorFR.isBusy()&&motorBL.isBusy()&&motorBR.isBusy()){
                    idle();
                }
                frameStop();
            }
            if (gamepad1.dpad_left){
                P1 = P1 - 200;
                P2 = P2 + 200;
                P3 = P3 + 200;
                P4 = P4 - 200;
                motorFL.setTargetPosition(P1);
                motorFR.setTargetPosition(P2);
                motorBL.setTargetPosition(P3);
                motorBR.setTargetPosition(P4);
                motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motorFL.setPower(-0.5);
                motorFR.setPower(0.5);
                motorBL.setPower(0.5);
                motorBR.setPower(-0.5);
                while (motorFL.isBusy()&&motorFR.isBusy()&&motorBL.isBusy()&&motorBR.isBusy()){
                    idle();
                }
                frameStop();
            }
            if (gamepad1.dpad_up){
                P1 = P1 + 200;
                P2 = P2 - 200;
                P3 = P3 - 200;
                P4 = P4 + 200;
                motorFL.setTargetPosition(P1);
                motorFR.setTargetPosition(P2);
                motorBL.setTargetPosition(P3);
                motorBR.setTargetPosition(P4);
                motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motorFL.setPower(0.5);
                motorFR.setPower(-0.5);
                motorBL.setPower(-0.5);
                motorBR.setPower(0.5);
                while (motorFL.isBusy()&&motorFR.isBusy()&&motorBL.isBusy()&&motorBR.isBusy()){
                    idle();
                }
                frameStop();
            }

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            //telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();
        }
    }
}
