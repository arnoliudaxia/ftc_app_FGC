package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.external.samples.BasicOpMode_Linear;
import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.util.Locale;

/**
 * Created by DeanNoreen on 2018/4/20.
 */

/**
 * {@link TurningEchoIMU} gives a short demo on how to use the BNO055 Inertial Motion Unit (IMU) from AdaFruit.
 * <p>
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 *
 * @see <a href="http://www.adafruit.com/products/2472">Adafruit IMU</a>
 */

public class TurningEchoHardware extends BasicOpMode_Linear {

    BNO055IMU imu;

    Orientation angles;
    Acceleration gravity;

    public DcMotor motorFL = null;
    public DcMotor motorFR = null;
    public DcMotor motorBL = null;
    public DcMotor motorBR = null;//定义底盘电机
    //motorFL 左前电机
    //motorFR 右前电机
    //motorBL 左后电机
    //motorBR 右后电机

    public DcMotor motorCatchBaby_1 = null;
    public DcMotor motorCatchBaby_2 = null;//定义机械臂电机

    public Servo servoKickBall_1 = null;
    public Servo servoKickBall_2 = null;

    public Servo servoCatchBaby_1 = null;
    public Servo servoCatchBaby_2 = null;//定义机械臂舵机

    public Servo servoCatchBlock_1 = null;
    public Servo servoCatchBlock_2 = null;//定义夹持方块的舵机

    public DcMotor motorLift = null;//定义抬升滑轨的电机

    public void TurningEchoHardwareConfigure() {
        motorFL = hardwareMap.get(DcMotor.class, "motorFL");
        motorFR = hardwareMap.get(DcMotor.class, "motorFR");
        motorBL = hardwareMap.get(DcMotor.class, "motorBL");
        motorBR = hardwareMap.get(DcMotor.class, "motorBR");
        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        motorFL.setDirection(DcMotor.Direction.REVERSE);
        motorBL.setDirection(DcMotor.Direction.FORWARD);
        motorFR.setDirection(DcMotor.Direction.REVERSE);
        motorBR.setDirection(DcMotor.Direction.REVERSE);

        servoCatchBlock_1 = hardwareMap.servo.get("servoCatchBlock_1");
        servoCatchBlock_2 = hardwareMap.servo.get("servoCatchBlock_2");

        motorLift = hardwareMap.dcMotor.get("motorLift");

        motorCatchBaby_1 = hardwareMap.get(DcMotor.class, "motorCatchBaby_1");
        motorCatchBaby_2 = hardwareMap.get(DcMotor.class, "motorCatchBaby_2");

        servoCatchBaby_1 = hardwareMap.get(Servo.class, "servoCatchBaby_1");
        servoCatchBaby_2 = hardwareMap.get(Servo.class, "servoCatchBaby_2");

        servoKickBall_1 = hardwareMap.get(Servo.class, "servoKickBall_1");
        servoKickBall_2 = hardwareMap.get(Servo.class, "servoKickBall_2");
    }

    double servoBabyPosition_1 = 0;
    double servoBabyPosition_2 = 0.8;//定义机械臂舵机position

    double servoBallPosition_2 = 0.59;

    boolean armIns = true;
    boolean babyIns = false;//机械臂安全锁
    //boolean robot_case_1 = false;
    //boolean robot_case_2 = false;

    double powerMode;//切换快/慢速模式
    final double POWER_MODE_SLOW = 2.5;
    final double POWER_MODE_FAST = 1;

    enum moveStatus {
        xF, xB, yR, yL, rL, rR, S
//      xF = forward 前进
//         xB = back 后退
//            yR = right 右平移
//               yL = left 左平移
//                   rL = 左转
//                      rR = 右转
//                          S = stop 停止
    }

    public void moveFix(double power, moveStatus moveStatus) {//0 <= power <= 1
        switch (moveStatus) {
            case xF:
                motorFL.setPower(power / powerMode);
                motorFR.setPower(power / powerMode);
                motorBL.setPower(power / powerMode);
                motorBR.setPower(power / powerMode);
                break;

            case xB:
                motorFL.setPower(-power / powerMode);
                motorFR.setPower(-power / powerMode);
                motorBL.setPower(-power / powerMode);
                motorBR.setPower(-power / powerMode);
                break;

            case yL:
                motorFL.setPower(-power / powerMode);
                motorFR.setPower(power / powerMode);
                motorBL.setPower(power / powerMode);
                motorBR.setPower(-power / powerMode);
                break;

            case yR:
                motorFL.setPower(power / powerMode);
                motorFR.setPower(-power / powerMode);
                motorBL.setPower(-power / powerMode);
                motorBR.setPower(power / powerMode);
                break;

            case rL:
                motorFL.setPower(-power / powerMode);
                motorFR.setPower(power / powerMode);
                motorBL.setPower(-power / powerMode);
                motorBR.setPower(power / powerMode);
                break;

            case rR:
                motorFL.setPower(power / powerMode);
                motorFR.setPower(-power / powerMode);
                motorBL.setPower(power / powerMode);
                motorBR.setPower(-power / powerMode);
                break;

            case S:
                motorFL.setPower(0);
                motorFR.setPower(0);
                motorBL.setPower(0);
                motorBR.setPower(0);
                break;
        }
    }

    public void moveVar(double yPower, double xPower, double rPower, double powerMode) {
        //                       yPower = y轴功率（前后平移方向上的功率）
        //                                      xPower = x轴功率（左右平移方向上的功率）
        //                                                     rPower = 自转功率（左右转向的功率）
        double FinalPower1 = Range.clip((yPower + xPower + rPower) / powerMode, -1, 1);
        double FinalPower2 = Range.clip((yPower - xPower - rPower) / powerMode, -1, 1);
        double FinalPower3 = Range.clip((yPower - xPower + rPower) / powerMode, -1, 1);
        double FinalPower4 = Range.clip((yPower + xPower - rPower) / powerMode, -1, 1);

        motorFL.setPower(FinalPower1);
        motorFR.setPower(FinalPower2);
        motorBL.setPower(FinalPower3);
        motorBR.setPower(FinalPower4);
    }

    public void servoCatchBlock(double servoBlockPosition_1, double servoBlockPosition_2) {//夹持方块函数
        servoCatchBlock_1.setPosition(servoBlockPosition_1);
        servoCatchBlock_2.setPosition(servoBlockPosition_2);

    }

    public void motorCatchBaby(double powerBaby_1, double powerBaby_2) {//机械臂电机函数
        motorCatchBaby_1.setPower(powerBaby_1);
        motorCatchBaby_2.setPower(powerBaby_2);
    }

    public void servoCatchBaby_1(double servoBabyPosition_1) {//夹小人第三节舵机函数
        servoCatchBaby_1.setPosition(servoBabyPosition_1);
    }

    public void servoCatchBaby_2(double servoBabyPosition_2) {//夹小人末节舵机函数
        servoCatchBaby_2.setPosition(servoBabyPosition_2);
    }

    public void lift(double powerLift) {//滑轨抬升函数
        motorLift.setPower(powerLift);
    }

    public double switchPowerMode() {//切换低/高速模式函数
        if (gamepad1.right_stick_y > 0.4) {
            return (POWER_MODE_SLOW);
        } else if (gamepad1.right_stick_y < -0.4) {
            return (POWER_MODE_FAST);
        } else {
            return (powerMode);
        }

    }

    public void frameContorl() {
        if (gamepad1.left_stick_y != 0 || gamepad1.left_stick_x != 0 || gamepad1.left_trigger != 0 || gamepad1.right_trigger != 0) {//底盘平移
            moveVar(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_trigger - gamepad1.left_trigger, powerMode);
        } else {
            moveVar(0, 0, 0, 0);
        }
    }

    void composeTelemetry() {

        // At the beginning of each telemetry update, grab a bunch of data
        // from the IMU that we will then display in separate lines.
        telemetry.addAction(new Runnable() {
            @Override
            public void run() {
                // Acquiring the angles is relatively expensive; we don't want
                // to do that in each of the three items that need that info, as that's
                // three times the necessary expense.
                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                gravity = imu.getGravity();
            }
        });

        telemetry.addLine()
                .addData("status", new Func<String>() {
                    @Override
                    public String value() {
                        return imu.getSystemStatus().toShortString();
                    }
                })
                .addData("calib", new Func<String>() {
                    @Override
                    public String value() {
                        return imu.getCalibrationStatus().toString();
                    }
                });

        telemetry.addLine()
                .addData("heading", new Func<String>() {
                    @Override
                    public String value() {
                        return formatAngle(angles.angleUnit, angles.firstAngle);
                    }
                })
                .addData("roll", new Func<String>() {
                    @Override
                    public String value() {
                        return formatAngle(angles.angleUnit, angles.secondAngle);
                    }
                })
                .addData("pitch", new Func<String>() {
                    @Override
                    public String value() {
                        return formatAngle(angles.angleUnit, angles.thirdAngle);
                    }
                });

        telemetry.addLine()
                .addData("grvty", new Func<String>() {
                    @Override
                    public String value() {
                        return gravity.toString();
                    }
                })
                .addData("mag", new Func<String>() {
                    @Override
                    public String value() {
                        return String.format(Locale.getDefault(), "%.3f",
                                Math.sqrt(gravity.xAccel * gravity.xAccel
                                        + gravity.yAccel * gravity.yAccel
                                        + gravity.zAccel * gravity.zAccel));
                    }
                });
    }

    //----------------------------------------------------------------------------------------------
    // Formatting
    //----------------------------------------------------------------------------------------------

    String formatAngle(AngleUnit angleUnit, double angle) {
        return formatDegrees(AngleUnit.DEGREES.fromUnit(angleUnit, angle));
    }

    String formatDegrees(double degrees) {
        return String.format(Locale.getDefault(), "%.1f", AngleUnit.DEGREES.normalize(degrees));
    }

}
