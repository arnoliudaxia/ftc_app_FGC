package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This OpMode illustrates using the Device Interface Module as a signalling device.该OpMode说明了使用设备接口模块作为信号设备。
 * The code is structured as a LinearOpMode　该代码被构造为一个LinearOpMode
 *
 * This code assumes a DIM name "dim".
 *
 * There are many examples where the robot might like to signal the driver, without requiring them
 * to look at the driver station.  This might be something like a "ball in hopper" condition or a
 * "ready to shoot" condition.
 *
 * The DIM has two user settable indicator LEDs (one red one blue).  These can be controlled
 * directly from your program.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */
 //此代码假定DIM名称为“dim”。
  //*
  //*在很多例子中，机器人可能想要向driver发出信号，不需要它们再征求driver station的命令。 这可能是像“球在漏斗”条件或一个“准备拍摄”条件。
  //*
  //* DIM有两个用户可设置的指示灯（一个红色一个蓝色）。 这些可以直接从你的程序控制。
  //*
  //*使用Android Studios复制此类，并使用新名称将其粘贴到团队的代码文件夹中。
  //*删除或注释掉@Disabled行将此opmode添加到Driver Station OpMode列表
@Autonomous(name = "Concept: DIM As Indicator", group = "Concept")
@Disabled
public class ConceptDIMAsIndicator extends LinearOpMode {

    static final int    BLUE_LED    = 0;     // Blue LED channel on DIM　DIM上的蓝色LED通道
    static final int    RED_LED     = 1;     // Red LED Channel on DIM　DIM上的红色LED通道

    // Create timer to toggle LEDs　创建定时器以切换LED
    private ElapsedTime runtime = new ElapsedTime();

    // Define class members　定义分级成员
    DeviceInterfaceModule   dim;

    @Override
    public void runOpMode() {

        // Connect to motor (Assume standard left wheel)　连接电机（假设标准左轮）
        // Change the text in quotes to match any motor name on your robot.　更改引号中的文本以匹配机器人上的任何电机名称。
        dim = this.hardwareMap.deviceInterfaceModule.get("dim");

        // Toggle LEDs while Waiting for the start button　等待开始按钮时切换LED
        telemetry.addData(">", "Press Play to test LEDs." );
        telemetry.update();

        while (!isStarted()) {
            // Determine if we are on an odd or even second　确定我们是在奇数还是偶数秒上
            boolean even = (((int)(runtime.time()) & 0x01) == 0);
            dim.setLED(RED_LED,   even); // Red for even　红灯为偶
            dim.setLED(BLUE_LED, !even); // Blue for odd　蓝灯为奇
            idle();
        }

        // Running now　开始运行
        telemetry.addData(">", "Press X for Blue, B for Red." );
        telemetry.update();

        // Now just use red and blue buttons to set red and blue LEDs　现在只需使用红色和蓝色按钮来设置红色和蓝色LED
        while(opModeIsActive()){
            dim.setLED(BLUE_LED, gamepad1.x);
            dim.setLED(RED_LED,  gamepad1.b);
            idle();
        }

       // Turn off LEDs;　关闭ＬＥＤ灯
        dim.setLED(BLUE_LED, false);
        dim.setLED(RED_LED,  false);
        telemetry.addData(">", "Done");
        telemetry.update();
    }
}
