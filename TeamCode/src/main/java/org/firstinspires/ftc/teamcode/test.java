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

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import static org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark.CENTER;
import static org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark.LEFT;
import static org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark.RIGHT;


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

@Autonomous(name="test", group="Linear Opmode")
//@Disabled
public class test extends TurningEchoHardware {

    private ElapsedTime runtime = new ElapsedTime();//计时

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");//直到waitForStart之前是初始化
        telemetry.update();

        TurningEchoHardwareConfigure();

        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        gravity = imu.getGravity();

        waitForStart();//当开始键按下后
        runtime.reset();

        catchBlock34();

        lift(1);

        sleep(500);

        // run until the end of the match (driver presses STOP)
        moveFix(0.5,moveStatus.yB);

        sleep(600);

        frameStop();


        autoTurnLocation(-90);

        //frameStop();

//                sleep(200);
//
//                moveFix(0.6,moveStatus.rR);
//
//                sleep(700);

        frameStop();

        sleep(300);

        //if (vuMark == LEFT){
            moveFix(0.6,moveStatus.xL);//左平移

            sleep(400);
        //}

//        else if (vuMark == CENTER){//done
//            moveFix(0.6,moveStatus.xR);
//
//            sleep(150);
//        }
//
//        else if (vuMark == RIGHT){
//            moveFix(0.6,moveStatus.xR);
//
//            sleep(420);
//        }

        frameStop();

        sleep(400);

        autoTurnLocation(-90);

        frameStop();

        motorShift.setPower(-0.15);

        lift(-1);//下降滑轨

        sleep(500);

        lift(0);//停止滑轨

        releaseBlock34();

        sleep(300);

        moveFix(0.4,moveStatus.yF);//往前怼

        sleep(900);

        frameStop();

        sleep(400);

//        if (vuMark == RIGHT){
//            moveFix(0.3,moveStatus.yB);//后退一点点
//
//            sleep(120);
//
//            moveFix(0.4,moveStatus.rR);//右转
//
//            sleep(600);
//
//            moveFix(0.4,moveStatus.rL);//左转
//
//            sleep(600);
//
//            moveFix(0.3,moveStatus.yF);//往前推一点点
//
//            sleep(380);
//        }

        //if (vuMark == LEFT){
            moveFix(0.3,moveStatus.yB);//后退一点点

            sleep(120);

            moveFix(0.4,moveStatus.rL);//左转

            sleep(600);

            moveFix(0.4,moveStatus.rR);//右转

            sleep(600);

            moveFix(0.3,moveStatus.yF);//往前推一点点

            sleep(380);
        //}

//        if (vuMark == CENTER) {
//            moveFix(0.3,moveStatus.yB);//后退一点点
//
//            sleep(120);
//
//            moveFix(0.4,moveStatus.rL);
//
//            sleep(400);
//
//            moveFix(0.4,moveStatus.rR);
//
//            sleep(400);
//
//            moveFix(0.3,moveStatus.yF);
//
//            sleep(380);
//        }

        frameStop();

        sleep(100);

        moveFix(0.4,moveStatus.yB);

        sleep(220);

        frameStop();

        motorShift.setPower(0);
    }
}
