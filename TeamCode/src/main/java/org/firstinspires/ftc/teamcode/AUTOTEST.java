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

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.Locale;


@Autonomous(name="TeamRightRed", group ="Concept")
//@Disabled
public class AUTOTEST extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    Servo servo_catching_block_1;
    Servo servo_catching_block_2;

    Servo servo_kicking_ball;

    DcMotor motor_raising;

    double servo_position_1 = 0.40;
    double servo_position_2 = 0.00;
    double power_raising = 0.50;
    double servo_position_ball = 1;

    //* private DcMotor rightDrive = null;
    DcMotor Leftfront;
    DcMotor Rightfront;
    DcMotor Leftrear;
    DcMotor Rightrear;

    int counter = 0;
    double power1 = 0.8;

    public void Ball1() {
        if (servo_position_ball <= 0.9) {
            servo_position_ball = servo_position_ball + 0.02;
        }
        sleep(50);
    }

    public void Ball2() {
        if (servo_position_ball >= 0) {
            servo_position_ball = servo_position_ball - 0.02;
        }
        sleep(50);
    }

    public void Raisng() {
        power_raising = 1.0;
        motor_raising.setPower(power_raising);
    }

    public void Down() {
        power_raising = -1.0;
        motor_raising.setPower(power_raising);
    }

    public void Stable() {
        power_raising = 0.08;
        motor_raising.setPower(power_raising);
    }

    public void Forward(Double power) {
        Leftfront.setPower(power);
        Rightfront.setPower(power);
        Leftrear.setPower(power);
        Rightrear.setPower(power);
    }

    public void Rear(Double power) {
        Leftfront.setPower(power);
        Leftrear.setPower(-power);
        Rightfront.setPower(-power);
        Rightrear.setPower(power);
    }

    public void Left(Double power) {
        Leftfront.setPower(-power);
        Leftrear.setPower(power);
        Rightfront.setPower(power);
        Rightrear.setPower(-power);
    }

    public void Right(Double power) {
        Leftfront.setPower(power);
        Leftrear.setPower(-power);
        Rightfront.setPower(-power);
        Rightrear.setPower(power);
    }

    public void Shunshi(Double power) {
        Leftfront.setPower(-power);
        Leftrear.setPower(-power);
        Rightfront.setPower(power);
        Rightrear.setPower(power);
    }

    public void Nishi(Double power) {
        Leftfront.setPower(power);
        Leftrear.setPower(power);
        Rightfront.setPower(-power);
        Rightrear.setPower(-power);
    }

    public void Catch(Double powera, Double powerb) {
        servo_position_1 = powera;
        servo_position_2 = powerb;
        servo_catching_block_1.setPosition(servo_position_1);
        servo_catching_block_2.setPosition(servo_position_2);
    }

    public void Release(Double powera, Double powerb) {
        servo_position_1 = powera;
        servo_position_2 = powerb;
        servo_catching_block_1.setPosition(servo_position_1);
        servo_catching_block_2.setPosition(servo_position_2);
    }

    public void Stop() {
        Leftfront.setPower(0.0);
        Leftrear.setPower(0.0);
        Rightfront.setPower(0.0);
        Rightrear.setPower(0.0);
    }

    @Override
    public void runOpMode() {

        servo_catching_block_1 = hardwareMap.servo.get("servo_catching_block_1");
        servo_catching_block_2 = hardwareMap.servo.get("servo_catching_block_2");

        motor_raising = hardwareMap.dcMotor.get("motor_raising");

        servo_kicking_ball = hardwareMap.get(Servo.class, "servo_kicking_ball");



        servo_kicking_ball.setPosition(servo_position_ball);

        // Wait for the game to start (driver presses PLAY)

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
        telemetry.addData(">", "按 Play 就开始");
        telemetry.update();


        waitForStart();

        //////////////////////////////////////////////////
            if (runtime.equals(30)) {
                Ball1();
                Ball2();


                Raisng();
                sleep(50);
                Stop();
                sleep(5000);


                Down();
                sleep(50);
                Stop();

                Stable();
                sleep(50);
                Stop();
                sleep(5000);


                Forward(0.8);
                sleep(50);
                Stop();
                sleep(5000);

                Rear(0.8);
                sleep(50);
                Stop();
                sleep(5000);

                Left(0.8);
                sleep(50);
                Stop();
                sleep(5000);


                Right(0.8);
                sleep(50);
                Stop();
                sleep(5000);

                Shunshi(0.8);
                sleep(50);
                Stop();
                sleep(5000);

                Nishi(0.8);
                sleep(50);
                Stop();
                sleep(5000);



                Release(0.00,0.40);

                Catch(0.30, 0.10);//init
            telemetry.update();
        }

    }

    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
}