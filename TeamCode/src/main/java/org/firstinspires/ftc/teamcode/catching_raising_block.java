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
import com.qualcomm.robotcore.hardware.Servo;
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

@TeleOp(name="catching & raising block", group="Linear Opmode")
//@Disabled
public class catching_raising_block extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    //private DcMotor leftDrive = null;
    //private DcMotor rightDrive = null;

    Servo servo_catching_block_1;
    Servo servo_catching_block_2;

    DcMotor motor_raising;

    double servo_position_1 = 0.70;
    double servo_position_2 = 0.10;
    double power = 0.50;


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        servo_catching_block_1 = hardwareMap.servo.get("servo_catching_block_1");
        servo_catching_block_2 = hardwareMap.servo.get("servo_catching_block_2");

        motor_raising = hardwareMap.dcMotor.get("motor_raising");

        servo_catching_block_1.setPosition(servo_position_1);
        servo_catching_block_2.setPosition(servo_position_2);//init

        int case_servo = 0;
        int case_motor = 0;

        waitForStart();
        runtime.reset();

        /*servo_position_1 = 0.40;
        servo_position_2 = 0.40;
        servo_catching_block_1.setPosition(servo_position_1);
        servo_catching_block_2.setPosition(servo_position_2);

        sleep(3000);

        power = 0.90;
        motor_raising.setPower(power);
        sleep(1650);
        power = 0.00;
        motor_raising.setPower(power);//ceshi
        */

        while (opModeIsActive()) {
            if(gamepad1.a){
                switch(case_servo){
                    case 0:
                        servo_position_1 = 0.40;
                        servo_position_2 = 0.40;
                        servo_catching_block_1.setPosition(servo_position_1);
                        servo_catching_block_2.setPosition(servo_position_2);
                        case_servo = 1;
                        break;
                    case 1:
                        servo_position_1 = 0.70;
                        servo_position_2 = 0.10;
                        servo_catching_block_1.setPosition(servo_position_1);
                        servo_catching_block_2.setPosition(servo_position_2);
                        case_servo = 0;
                        break;
                }
            }
            if(gamepad1.x){
                switch(case_motor){
                    case 0:
                        power = 0.90;
                        motor_raising.setPower(power);
                        sleep(1700);
                        power = 0.00;
                        motor_raising.setPower(power);
                        case_motor = 1;
                        break;
                    case 1:
                        power = -0.25;
                        motor_raising.setPower(power);
                        sleep(2000);
                        power = 0.00;
                        motor_raising.setPower(power);
                        case_motor = 0;
                        break;
                }
            }
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }
}
