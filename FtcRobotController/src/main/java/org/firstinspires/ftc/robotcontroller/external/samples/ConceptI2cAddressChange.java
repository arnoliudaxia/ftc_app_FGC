/* Copyright (c) 2015 Qualcomm Technologies Inc

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Qualcomm Technologies Inc nor the names of its contributors
may be used to endorse or promote products derived from this software without
specific prior written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. */
//（以上为版权所有声明及任何情况下的免责声明）

package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsUsbDeviceInterfaceModule;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.util.RobotLog;
import com.qualcomm.robotcore.util.TypeConversion;

import java.util.concurrent.locks.Lock;

/**
 * An example of a linear op mode that shows how to change the I2C address.　线性运算模式的示例，显示如何更改I2C地址。
 */
@TeleOp(name = "Concept: I2c Address Change", group = "Concept")
@Disabled
public class ConceptI2cAddressChange extends LinearOpMode {

  public static final int ADDRESS_SET_NEW_I2C_ADDRESS = 0x70;
  // trigger bytes used to change I2C address on ModernRobotics sensors.　用于在ModernRobotics传感器上更改I2C地址的触发字节。
  public static final byte TRIGGER_BYTE_1 = 0x55;
  public static final byte TRIGGER_BYTE_2 = (byte) 0xaa;

  // Expected bytes from the Modern Robotics IR Seeker V3 memory map　来自Modern Robotics IR Seeker V3内存映射的预期字节
  public static final byte IR_SEEKER_V3_FIRMWARE_REV = 0x12;
  public static final byte IR_SEEKER_V3_SENSOR_ID = 0x49;
  public static final I2cAddr IR_SEEKER_V3_ORIGINAL_ADDRESS = I2cAddr.create8bit(0x38);

  // Expected bytes from the Modern Robotics Color Sensor memory map　来自Modern Robotics颜色传感器内存映射的预期字节
  public static final byte COLOR_SENSOR_FIRMWARE_REV = 0x10;
  public static final byte COLOR_SENSOR_SENSOR_ID = 0x43;
  public static final byte COLOR_SENSOR_ORIGINAL_ADDRESS = 0x3C;

  public static final byte MANUFACTURER_CODE = 0x4d;
  // Currently, this is set to expect the bytes from the IR Seeker.
  // If you change these values so you're setting "FIRMWARE_REV" to
  // COLOR_SENSOR_FIRMWARE_REV, and "SENSOR_ID" to "COLOR_SENSOR_SENSOR_ID",
  // you'll be able to change the I2C address of the ModernRoboticsColorSensor.
  // If the bytes you're expecting are different than what this op mode finds,
  // a comparison will be printed out into the logfile.
  //目前，这是设置是为期望来自IR Seeker的字节。 如果您更改这些值，将“FIRMWARE_REV”设置为COLOR_SENSOR_FIRMWARE_REV，
  ///将“SENSOR_ID”设置为“COLOR_SENSOR_SENSOR_ID”，则可以更改ModernRoboticsColorSensor的I2C地址。
  //如果您期望的字节与此op模式找到的字节不同，则会将比较打印到日志文件中。
  public static final byte FIRMWARE_REV = IR_SEEKER_V3_FIRMWARE_REV;
  public static final byte SENSOR_ID = IR_SEEKER_V3_SENSOR_ID;

  // These byte values are common with most Modern Robotics sensors.　这些字节值在大多数Modern Robotics(现代机器人)传感器中是常见的。
  public static final int READ_MODE = 0x80;
  public static final int ADDRESS_MEMORY_START = 0x0;
  public static final int TOTAL_MEMORY_LENGTH = 0x0c;
  public static final int BUFFER_CHANGE_ADDRESS_LENGTH = 0x03;

  // The port where your sensor is connected.　传感器连接的端口。
  int port = 5;

  byte[] readCache;
  Lock readLock;
  byte[] writeCache;
  Lock writeLock;

  I2cAddr currentAddress = IR_SEEKER_V3_ORIGINAL_ADDRESS;
  // I2c addresses on Modern Robotics devices must be divisible by 2, and between 0x7e and 0x10
  // Different hardware may have different rules.
  // Be sure to read the requirements for the hardware you're using!
  // If you use an invalid address, you may make your device completely unusable.
  //现代机器人设备上的I2c地址必须可以被2除，且在0x7e到0x10之间
   //不同的硬件可能有不同的规则。
   //请务必阅读您正在使用的硬件的要求！
   //如果使用无效的地址，可能会使您的设备完全无法使用。
  I2cAddr newAddress = I2cAddr.create8bit(0x42);

  DeviceInterfaceModule dim;

  @Override
  public void runOpMode() {

    // set up the hardware devices we are going to use　设置我们将要使用的硬件设备
    dim = hardwareMap.deviceInterfaceModule.get("dim");

    readCache = dim.getI2cReadCache(port);
    readLock = dim.getI2cReadCacheLock(port);
    writeCache = dim.getI2cWriteCache(port);
    writeLock = dim.getI2cWriteCacheLock(port);

    // I2c addresses on Modern Robotics devices must be divisible by 2, and between 0x7e and 0x10
    // Different hardware may have different rules.
    // Be sure to read the requirements for the hardware you're using!
    //现代机器人设备上的I2c地址必须可以被2除，且在0x7e到0x10之间
   //不同的硬件可能有不同的规则。
   //请务必阅读您正在使用的硬件的要求！
    ModernRoboticsUsbDeviceInterfaceModule.throwIfModernRoboticsI2cAddressIsInvalid(newAddress);

    // wait for the start button to be pressed　等待开始按钮被按下
    waitForStart();

    performAction("read", port, currentAddress, ADDRESS_MEMORY_START, TOTAL_MEMORY_LENGTH);

    while(!dim.isI2cPortReady(port)) {
      telemetry.addData("I2cAddressChange", "waiting for the port to be ready...");
      telemetry.update();
      sleep(1000);
    }

    // update the local cache　更新本地缓存
    dim.readI2cCacheFromController(port);

    // make sure the first bytes are what we think they should be.　确保第一个字节是我们认为应该是的。
    int count = 0;
    int[] initialArray = {READ_MODE, currentAddress.get8Bit(), ADDRESS_MEMORY_START, TOTAL_MEMORY_LENGTH, FIRMWARE_REV, MANUFACTURER_CODE, SENSOR_ID};
    while (!foundExpectedBytes(initialArray, readLock, readCache)) {
      telemetry.addData("I2cAddressChange", "Confirming that we're reading the correct bytes...");
      telemetry.update();
      dim.readI2cCacheFromController(port);
      sleep(1000);
      count++;
      // if we go too long with failure, we probably are expecting the wrong bytes.　如果我们总是失败，我们可能弄错了（第一个）字节。
      if (count >= 10)  {
        telemetry.addData("I2cAddressChange", String.format("Looping too long with no change, probably have the wrong address. Current address: 8bit=0x%02x", currentAddress.get8Bit()));
        hardwareMap.irSeekerSensor.get(String.format("Looping too long with no change, probably have the wrong address. Current address: 8bit=0x%02x", currentAddress.get8Bit()));
        telemetry.update();
      }
    }

    // Enable writes to the correct segment of the memory map.　启用对存储器映射的正确段的写入。
    performAction("write", port, currentAddress, ADDRESS_SET_NEW_I2C_ADDRESS, BUFFER_CHANGE_ADDRESS_LENGTH);

    // Write out the trigger bytes, and the new desired address.　　写出触发字节和新的所需地址。
    writeNewAddress();
    dim.setI2cPortActionFlag(port);
    dim.writeI2cCacheToController(port);

    telemetry.addData("I2cAddressChange", "Giving the hardware 60 seconds to make the change...");
    telemetry.update();

    // Changing the I2C address takes some time.　更改I2C地址需要一些时间。
    sleep(60000);

    // Query the new address and see if we can get the bytes we expect.　查询新地址，看看是否可以获取我们期待的字节。
    dim.enableI2cReadMode(port, newAddress, ADDRESS_MEMORY_START, TOTAL_MEMORY_LENGTH);
    dim.setI2cPortActionFlag(port);
    dim.writeI2cCacheToController(port);

    int[] confirmArray = {READ_MODE, newAddress.get8Bit(), ADDRESS_MEMORY_START, TOTAL_MEMORY_LENGTH, FIRMWARE_REV, MANUFACTURER_CODE, SENSOR_ID};
    while (!foundExpectedBytes(confirmArray, readLock, readCache)) {
      telemetry.addData("I2cAddressChange", "Have not confirmed the changes yet...");
      telemetry.update();
      dim.readI2cCacheFromController(port);
      sleep(1000);
    }

    telemetry.addData("I2cAddressChange", "Successfully changed the I2C address. New address: 8bit=0x%02x", newAddress.get8Bit());
    telemetry.update();
    RobotLog.i("Successfully changed the I2C address." + String.format("New address: 8bit=0x%02x", newAddress.get8Bit()));

    /**** IMPORTANT NOTE ******/
    //重要提示
    // You need to add a line like this at the top of your op mode
    // to update the I2cAddress in the driver.　//您需要在op模式的顶部添加如下一行，以更新驱动程序中的I2cAddress。
    //irSeeker.setI2cAddress(newAddress);
    /***************************/

  }

  private boolean foundExpectedBytes(int[] byteArray, Lock lock, byte[] cache) {
    try {
      lock.lock();
      boolean allMatch = true;
      StringBuilder s = new StringBuilder(300 * 4);
      String mismatch = "";
      for (int i = 0; i < byteArray.length; i++) {
        s.append(String.format("expected: %02x, got: %02x \n", TypeConversion.unsignedByteToInt( (byte) byteArray[i]), cache[i]));
        if (TypeConversion.unsignedByteToInt(cache[i]) != TypeConversion.unsignedByteToInt( (byte) byteArray[i])) {
          mismatch = String.format("i: %d, byteArray[i]: %02x, cache[i]: %02x", i, byteArray[i], cache[i]);
          allMatch = false;
        }
      }
      RobotLog.e(s.toString() + "\n allMatch: " + allMatch + ", mismatch: " + mismatch);
      return allMatch;
    } finally {
      lock.unlock();
    }
  }

  private void performAction(String actionName, int port, I2cAddr i2cAddress, int memAddress, int memLength) {
    if (actionName.equalsIgnoreCase("read")) dim.enableI2cReadMode(port, i2cAddress, memAddress, memLength);
    if (actionName.equalsIgnoreCase("write")) dim.enableI2cWriteMode(port, i2cAddress, memAddress, memLength);

    dim.setI2cPortActionFlag(port);
    dim.writeI2cCacheToController(port);
    dim.readI2cCacheFromController(port);
  }

  private void writeNewAddress() {
    try {
      writeLock.lock();
      writeCache[4] = (byte) newAddress.get8Bit();
      writeCache[5] = TRIGGER_BYTE_1;
      writeCache[6] = TRIGGER_BYTE_2;
    } finally {
      writeLock.unlock();
    }
  }
}
