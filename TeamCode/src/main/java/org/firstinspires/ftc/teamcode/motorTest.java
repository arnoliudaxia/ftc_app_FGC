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

import android.graphics.YuvImage;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.external.samples.ConceptTelemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.opencv.core.Mat;

import com.qualcomm.robotcore.hardware.VoltageSensor;


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
/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 * <p>
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 * <p>
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="motorTest", group="Linear Opmode")
//@Disabled
public class motorTest extends TurningEchoHardware {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    double testPosition = 0;

    boolean ccc = false;

    DcMotor motorTest = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        motorTest = hardwareMap.get(DcMotor.class,"motorTest");

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery

        // Wait for the game to start (driver presses PLAY)

        //motorTest.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motorTest.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorTest.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motorTest.setTargetPosition(0);

        waitForStart();
        runtime.reset();
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            double a = motorTest.getCurrentPosition();
            if (gamepad1.start){
                motorTest.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }
//
//            if (gamepad2.right_stick_button){
//                motorTest.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//                shiftPosition = motorTest.getCurrentPosition();
//                if (!shiftReversed){
//                    motorTest.setPower(0.35);
//                }
//                else if (shiftReversed){
//                    motorTest.setPower(-0.35);
//                }
//                while (gamepad2.right_stick_button){
//                    idle();
//                }
//            }
//
//            if (Math.abs(shiftPosition - motorTest.getCurrentPosition()) > 450){
//                if (shiftReversed){
//                    motorTest.setPower(0.2);
//                }
//                else if (!shiftReversed){
//                    motorTest.setPower(-0.2);
//                }
//            }
//
//            if (Math.abs(shiftPosition - motorTest.getCurrentPosition()) > 600){
//                if (shiftReversed){
//                    motorTest.setPower(0.1);
//                }
//                else if (!shiftReversed){
//                    motorTest.setPower(-0.1);
//                }
//            }
            if (Math.abs(a-motorTest.getTargetPosition()) < 250){
                if (!shiftReversed){
                    motorTest.setPower(0.1);
                }
                else if (shiftReversed){
                    motorTest.setPower(-0.1);
                }
            }

            if (gamepad1.left_stick_button){
                if (!shiftReversed){
                    motorTest.setPower(0.3);
                }
                else if (shiftReversed){
                    motorTest.setPower(-0.3);
                }
            }

            if (a == motorTest.getCurrentPosition()) {
                motorTest.setPower(0);
                shiftReversed = !shiftReversed;
            }



            // Tank Mode uses one stick to control each wheel.
            // - This requires no math, but it is hard to drive forward slowly and keep straight.
            // leftPower  = -gamepad1.left_stick_y ;
            // rightPower = -gamepad1.right_stick_y ;

            // Send calculated power to wheels

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("currentPosition", motorTest.getCurrentPosition());
            //telemetry.addData("targetPosition", motorTest.getTargetPosition());
            telemetry.addData("power", motorTest.getPower());
            //telemetry.addData("Motors", "zuoqian (%.2f), youqian (%.2f),zuohou (%.2f),youhou (%.2f)",power_raising);
            telemetry.update();
        }
    }
}
