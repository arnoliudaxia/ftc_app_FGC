package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.external.samples.BasicOpMode_Linear;
import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import java.util.Locale;

/**
 * Created by DeanNoreen on 2018/4/20.
 */

public class GlobalEchoHardware extends BasicOpMode_Linear {
    private ElapsedTime runtime = new ElapsedTime();//计时

    //下面两个传感器集成在一个REV光探中
    ColorSensor sensorColour;//颜色传感器
    DistanceSensor sensorDistance;//距离传感器

    //关于颜色传感器
//sensorColor.blue()获取蓝色色值
//sensorColor.red()获取红色色值
//sensorColor.green()获取绿色色值

    public DcMotor motorFL = null;
    public DcMotor motorFR = null;
    public DcMotor motorBL = null;
    public DcMotor motorBR = null;//定义底盘电机
    //motorFL 左前电机
    //motorFR 右前电机
    //motorBL 左后电机
    //motorBR 右后电机

    double powerMode = 1;//切换快/慢速模式
    final double POWER_MODE_SLOW = 2.5;
    final double POWER_MODE_FAST = 1;

    double PowerFL = motorFL.getPower();
    double PowerFR = motorFR.getPower();
    double PowerBL = motorBL.getPower();
    double PowerBR = motorBR.getPower();

    public void GlobalEchoHardwareConfigure() {
        motorFL = hardwareMap.get(DcMotor.class, "motorFL");//获取配置文件中的硬件名称
        motorFR = hardwareMap.get(DcMotor.class, "motorFR");
        motorBL = hardwareMap.get(DcMotor.class, "motorBL");
        motorBR = hardwareMap.get(DcMotor.class, "motorBR");
        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        //设置电机转向以便后续数值操作
        motorFL.setDirection(DcMotor.Direction.REVERSE);
        motorBL.setDirection(DcMotor.Direction.FORWARD);
        motorFR.setDirection(DcMotor.Direction.REVERSE);
        motorBR.setDirection(DcMotor.Direction.REVERSE);

        sensorColour = hardwareMap.get(ColorSensor.class, "sensorColourDistance");

        sensorDistance = hardwareMap.get(DistanceSensor.class, "sensorColourDistance");

        telemetry.addData("Hardware", "Initialized");//向信息窗口加入新信息
        telemetry.update();//addData后必须要update才能显示出来一次
    }

    public double getBatteryVoltage() {//获取电池电量
        double result = Double.POSITIVE_INFINITY;
        for (VoltageSensor sensor : hardwareMap.voltageSensor) {
            double voltage = sensor.getVoltage();
            if (voltage > 0) {
                result = Math.min(result, voltage);
            }
        }
        return result;
    }

    enum moveStatus {
        F, B, R, L
//      F = forward 前进
//         B = back 后退
//            R = right 右平移
//               L = left 左平移
    }

    public void moveFix(double power, moveStatus moveStatus) {//由定量输入值的底盘移动方法，0<power<1
        switch (moveStatus) {
            case F:
                motorFL.setPower(power);
                motorFR.setPower(power);
                motorBL.setPower(power);
                motorBR.setPower(power);
                break;
            case B:
                motorFL.setPower(-power);
                motorFR.setPower(-power);
                motorBL.setPower(-power);
                motorBR.setPower(-power);
                break;
            case L:
                motorFL.setPower(-power);
                motorFR.setPower(power);
                motorBL.setPower(-power);
                motorBR.setPower(power);
                break;
            case R:
                motorFL.setPower(power);
                motorFR.setPower(-power);
                motorBL.setPower(power);
                motorBR.setPower(-power);
        }
    }

    public void moveVar(double yPower, double rPower, double powerMode) {//由变量输入值的底盘移动方法
        //                       yPower = y轴功率（前后平移方向上的功率）
        //                                      rPower = 自转功率（左右转向的功率）
        //                                                      powerMode = 快慢模式，越大越慢
        double FinalPower1 = Range.clip((yPower + rPower) / powerMode, -1, 1);
        double FinalPower2 = Range.clip((yPower - rPower) / powerMode, -1, 1);
        double FinalPower3 = Range.clip((yPower + rPower) / powerMode, -1, 1);
        double FinalPower4 = Range.clip((yPower - rPower) / powerMode, -1, 1);

        motorFL.setPower(FinalPower1);
        motorFR.setPower(FinalPower2);
        motorBL.setPower(FinalPower3);
        motorBR.setPower(FinalPower4);
    }

    public void frameStop() {//停止
        motorFL.setPower(0);
        motorFR.setPower(0);
        motorBL.setPower(0);
        motorBR.setPower(0);

        moveVar(0, 0, 1);

        moveFix(0, moveStatus.F);
    }

    public double switchPowerMode() {//切换低/高速模式函数
        if (gamepad1.a) {
            return 1.5;
        } else if (gamepad1.y) {
            return 2.5;
        }
        return powerMode;
        //可以自己改方法，比如这里是第一操作手按下a则稍微降速（返回1.5）；按下y则降速很多（返回值2.5）
    }

    public void frameControl() {//手柄控制底盘，将前进功率写入moveVar方法的第一个输入值，转向功率写入moveVar方法的第二个输入值，powerMode的值由调用switchPowerMode方法获得
        if (gamepad1.left_stick_y != 0 || gamepad1.left_stick_x != 0 || gamepad1.left_trigger != 0 || gamepad1.right_trigger != 0) {
            moveVar(-gamepad1.left_stick_y, -gamepad1.left_stick_x, switchPowerMode());//例如此处手柄控制方法为第一操作手的左摇杆前后左右摇动控制前进后退与转向
            //手柄上摇杆的y轴（前后推动）向前为-1，向后线性增加直至1；x轴（左右推动）向左为-1，向右线性增加至1
            //手柄上的dpad按键、a,b,x,y按键、左右摇杆按钮、Bumper键均为布尔值
            //摇杆和Trigger键为double值，摇杆已解释过，Trigger键不按下为0，按下后，线性增加直至按到底，为1
            //手柄上的这些名词自行百度
        } else {
            frameStop();
        }
    }
}