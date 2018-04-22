package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.external.samples.BasicOpMode_Linear;

/**
 * Created by DeanNoreen on 2018/4/20.
 */

public class TurningEchoHardwareConfig extends BasicOpMode_Linear{

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

    public void TurningEchoHardwareMethod(){
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

        motorCatchBaby_1  = hardwareMap.get(DcMotor.class, "motorCatchBaby_1");
        motorCatchBaby_2 = hardwareMap.get(DcMotor.class, "motorCatchBaby_2");

        servoCatchBaby_1 = hardwareMap.get(Servo.class,"servoCatchBaby_1");
        servoCatchBaby_2 = hardwareMap.get(Servo.class,"servoCatchBaby_2");

        servoKickBall_1 = hardwareMap.get(Servo.class,"ServoKickBall_1");
        servoKickBall_2 = hardwareMap.get(Servo.class,"ServoKickBall_2");
    }

    double ServoBabyPosition_1 = 0;
    double ServoBabyPosition_2 = 0.8;//定义机械臂舵机position

    double ServoBallPosition_2 = 0.59;

    boolean armIns = true;
    boolean babyIns = false;//机械臂安全锁
    //boolean robot_case_1 = false;
    //boolean robot_case_2 = false;

    double powerMode;//切换快/慢速模式
    final double POWER_MODE_SLOW = 2.5;
    final double POWER_MODE_FAST = 1;

    enum moveStatus{
        xF,xB,yR,yL,rL,rR,S
//      xF = forward 前进
//         xB = back 后退
//            yR = right 右平移
//               yL = left 左平移
//                   rL = 左转
//                      rR = 右转
//                          S = stop 停止
    }

    public void moveFix (double power,moveStatus moveStatus){//0 <= power <= 1
        switch (moveStatus){
            case xF:
                motorFL.setPower(power/powerMode);
                motorFR.setPower(power/powerMode);
                motorBL.setPower(power/powerMode);
                motorBR.setPower(power/powerMode);
                break;

            case xB:
                motorFL.setPower(-power/powerMode);
                motorFR.setPower(-power/powerMode);
                motorBL.setPower(-power/powerMode);
                motorBR.setPower(-power/powerMode);
                break;

            case yL:
                motorFL.setPower(-power/powerMode);
                motorFR.setPower(power/powerMode);
                motorBL.setPower(power/powerMode);
                motorBR.setPower(-power/powerMode);
                break;

            case yR:
                motorFL.setPower(power/powerMode);
                motorFR.setPower(-power/powerMode);
                motorBL.setPower(-power/powerMode);
                motorBR.setPower(power/powerMode);
                break;

            case rL:
                motorFL.setPower(-power/powerMode);
                motorFR.setPower(power/powerMode);
                motorBL.setPower(-power/powerMode);
                motorBR.setPower(power/powerMode);
                break;

            case rR:
                motorFL.setPower(power/powerMode);
                motorFR.setPower(-power/powerMode);
                motorBL.setPower(power/powerMode);
                motorBR.setPower(-power/powerMode);
                break;

            case S:
                motorFL.setPower(0);
                motorFR.setPower(0);
                motorBL.setPower(0);
                motorBR.setPower(0);
                break;
        }
    }

    public void moveVar (double yPower,double xPower,double rPower,double powerMode){
        //                       yPower = y轴功率（前后平移方向上的功率）
        //                                      xPower = x轴功率（左右平移方向上的功率）
        //                                                     rPower = 自转功率（左右转向的功率）
        double FinalPower1 = Range.clip((yPower + xPower + rPower)/powerMode,-1,1);
        double FinalPower2 = Range.clip((yPower - xPower - rPower)/powerMode,-1,1);
        double FinalPower3 = Range.clip((yPower - xPower + rPower)/powerMode,-1,1);
        double FinalPower4 = Range.clip((yPower + xPower - rPower)/powerMode,-1,1);

        motorFL.setPower(FinalPower1);
        motorFR.setPower(FinalPower2);
        motorBL.setPower(FinalPower3);
        motorBR.setPower(FinalPower4);
    }

    public void servoCatchBlock(double ServoBlockPosition_1,double ServoBlockPosition_2){//夹持方块函数
        servoCatchBlock_1.setPosition(ServoBlockPosition_1);
        servoCatchBlock_2.setPosition(ServoBlockPosition_2);
    }

    public void motorCatchBaby(double PowerBaby_1,double PowerBaby_2){//机械臂电机函数
        motorCatchBaby_1.setPower(PowerBaby_1);
        motorCatchBaby_2.setPower(PowerBaby_2);
    }

    public void servoCatchBaby_1(double ServoBabyPosition_1){//夹小人第三节舵机函数
        servoCatchBaby_1.setPosition(ServoBabyPosition_1);
    }

    public void servoCatchBaby_2(double ServoBabyPosition_2){//夹小人末节舵机函数
        servoCatchBaby_2.setPosition(ServoBabyPosition_2);
    }

    public void lift(double PowerLift){//滑轨抬升函数
        motorLift.setPower(PowerLift);
    }

    public double switchPowerMode(){//切换低/高速模式函数
        if(gamepad1.right_stick_y  > 0.4){
            return (POWER_MODE_SLOW);
        }

        else if(gamepad1.right_stick_y < -0.4){
            return (POWER_MODE_FAST);
        }

        else {
            return (powerMode);
        }

    }

}
