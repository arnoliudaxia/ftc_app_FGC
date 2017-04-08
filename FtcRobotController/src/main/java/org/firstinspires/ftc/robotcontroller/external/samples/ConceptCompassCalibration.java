/*
Copyright (c) 2016 Robert Atkinson　版权所有　（c）罗伯特　阿特金森

All rights reserved.版权所有

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:
满足以下条件的对源和二进制的重新分配和使用，无论是否修改，都是被允许的。（受下文免责声明的限制）

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.
源代码的再分发必须保留上述版权声明，本列表的条件和以下免责声明。


Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.
二进制的重新分发必须复制（遵循）上述版权声明，此份清单所列的条件，以下免责声明或者重新分配中其他的要求。

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.
无论是罗伯特·阿特金森（Robert Atkinson）的名字还是他的合作贡献者的名字，在没有事先书面许可的情况下，都不能被署名支持或推广衍生自该软件的产品。


NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
任何明示或暗示的（直接或间接的），任何方面的授权均由本协议授予许可。 
本软件由版权所有者和贡献者提供“按原样”。任何明示或暗示的授权（保证），包括但不限于，适销性和特定用途的授权（保证），都无关版权所有者和贡献者的责任。
在任何情况下，版权所有者或贡献者均不承担责任。
对于任何直接，间接，偶发，特殊，示例或后果损害（包括但不限于采购替代物品或服务; 使用，数据或利润上的损失; 或业务中断）
无论导致的是什么情况，任何责任要求，无论是否有合同，有严格责任要求或侵权行为（包括疏忽或以其他方式），
以任何方式使用该软件，即使该软件已被告知可能发生此类损坏。版权所有者或贡献者皆不承担任何责任。
（总之，本段意思就是：如果您是想将您开发的专利软件供人使用,又不想承担任何可能产生的种种不良结果的责任,那这个声明已囊括了所有可能发生的情况,您可以放心使用.）
package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CompassSensor;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This file illustrates the concept of calibrating a MR Compass
 * It uses the common Pushbot hardware class to define the drive on the robot.
 * The code is structured as a LinearOpMode
 *
 *   This code assumes there is a compass configured with the name "compass"
 *
 *   This code will put the compass into calibration mode, wait three seconds and then attempt
 *   to rotate two full turns clockwise.  This will allow the compass to do a magnetic calibration.
 *
 *   Once compete, the program will put the compass back into measurement mode and check to see if the
 *   calibration was successful.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="Concept: Compass Calibration", group="Concept")
@Disabled
public class ConceptCompassCalibration extends LinearOpMode {

    /* Declare OpMode members. */
    HardwarePushbot     robot   = new HardwarePushbot();   // Use a Pushbot's hardware
    private ElapsedTime runtime = new ElapsedTime();
    CompassSensor       compass;

    final static double     MOTOR_POWER   = 0.2; // scale from 0 to 1
    static final long       HOLD_TIME_MS  = 3000;
    static final double     CAL_TIME_SEC  = 20;

    @Override
    public void runOpMode() {

        /* Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // get a reference to our Compass Sensor object.
        compass = hardwareMap.compassSensor.get("compass");

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to cal");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Set the compass to calibration mode
        compass.setMode(CompassSensor.CompassMode.CALIBRATION_MODE);
        telemetry.addData("Compass", "Compass in calibration mode");
        telemetry.update();

        sleep(HOLD_TIME_MS);  // Just do a sleep while we switch modes

        // Start the robot rotating clockwise
        telemetry.addData("Compass", "Calibration mode. Turning the robot...");
        telemetry.update();
        robot.leftMotor.setPower(MOTOR_POWER);
        robot.rightMotor.setPower(-MOTOR_POWER);

        // run until time expires OR the driver presses STOP;
        runtime.reset();
        while (opModeIsActive() && (runtime.time() < CAL_TIME_SEC)) {
            idle();
        }

        // Stop all motors and turn off claibration
        robot.leftMotor.setPower(0);
        robot.rightMotor.setPower(0);
        compass.setMode(CompassSensor.CompassMode.MEASUREMENT_MODE);
        telemetry.addData("Compass", "Returning to measurement mode");
        telemetry.update();

        sleep(HOLD_TIME_MS);  // Just do a sleep while we switch modes

        // Report whether the Calibration was successful or not.
        if (compass.calibrationFailed())
            telemetry.addData("Compass", "Calibrate Failed. Try Again!");
        else
            telemetry.addData("Compass", "Calibrate Passed.");
        telemetry.update();
    }
}
