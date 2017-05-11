# ftc_app
FTC Android Studio project to create FTC Robot Controller app.　FTC Android Studio项目创建FTC 机器人控制应用程序。

This is the FTC SDK that can be used to create an FTC Robot Controller app, with custom op modes.
The FTC Robot Controller app is designed to work in conjunction with the FTC Driver Station app.
The FTC Driver Station app is available through Google Play.
这是FTC SDK，可用于创建具有自定义操作模式的FTC机器人控制器应用程序。
FTC机器人控制器应用程序旨在与FTC Driver Station应用程序配合使用。
FTC Driver Station应用程序可通过Google Play使用。

To use this SDK, download/clone the entire project to your local computer.

*** Important note regarding the repository size ***:  this repository is large and it can take a long time and a lot of space to clone the entire repository. If you would like to save time and space, there are some options that you can choose to download only the most current version of the Android project folder.

If you are a git user, you can use the --depth command line argument to only clone the most current version of the repository:

  git clone --depth=1 https://github.com/ftctechnh/ftc_app.git

Or, if you prefer, you can use the "Download Zip" button available through the main repository page.  You can also download the project folder (as a .zip or .tar.gz archive file) from the Downloads subsection of the Releases page for this repository.

Use Android Studio to import the folder  ("Import project (Eclipse ADT, Gradle, etc.)").
要使用此SDK，请将整个项目下载/克隆到本地计算机。
使用Android Studio导入文件夹（“导入项目（Eclipse ADT，Gradle等）”）。

The Javadoc reference documentation for the FTC SDK is now available online.  Visit the following URL to view the FTC SDK documentation as a live website:
FTC SDK的Javadoc参考文档现已在线提供。 请访问以下URL以查看作为实时网站的FTC SDK文档：

http://ftctechnh.github.io/ftc_app/doc/javadoc/index.html

Documentation for the FTC SDK is also included with this repository.  There is a subfolder called "doc" which contains several subfolders:
该存储库还包含FTC SDK的文档。 有一个名为“doc”的子文件夹，其中包含几个子文件夹：

 * The folder "apk" contains the .apk files for the FTC Driver Station and FTC Robot Controller apps.
 * The folder "javadoc" contains the JavaDoc user documentation for the FTC SDK.
 * The folder "tutorial" contains PDF files that help teach the basics of using the FTC SDK.
 *“apk”文件夹包含FTC Driver Station和FTC Robot Controller应用程序的.apk文件。
  *文件夹“javadoc”包含FTC SDK的JavaDoc用户文档。
  *文件夹“教程”包含有助于教授使用FTC SDK的基础知识的PDF文件。

For technical questions regarding the SDK, please visit the FTC Technology forum:
有关SDK的技术问题，请访问FTC技术论坛：

  http://ftcforum.usfirst.org/forumdisplay.php?156-FTC-Technology

**************************************************************************************

Version 3.10 (built on 17.05.09)

This version of the software provides support for the REV Robotics Expansion Hub.  This version also includes improvements in the USB communication layer in an effort to enhance system resiliency.  If you were using a 2.x version of the software previously, updating to version 3.1 requires that you also update your Driver Station software in addition to updating the Robot Controller software.

Also note that in version 3.10 software, the setMaxSpeed and getMaxSpeed methods are no longer available (not deprecated, they have been removed from the SDK). Also note that the the new 3.x software incorporates motor profiles that a user can select as he/she configures the robot.

Changes include:
 * Blocks changes
    - Added VuforiaTrackableDefaultListener.getPose and Vuforia.trackPose blocks.
    - Added optimized blocks support for Vuforia extended tracking.
    - Added atan2 block to the math category.
    - Added useCompetitionFieldTargetLocations parameter to Vuforia.initialize block.  If set to false, the target locations are placed at (0,0,0) with target orientation as specified in https://github.com/gearsincorg/FTCVuforiaDemo/blob/master/Robot_Navigation.java tutorial op mode.
 * Incorporates additional improvements to USB comm layer to improve system resiliency (to recover from a greater number of communication disruptions).

**************************************************************************************

Additional Notes Regarding Version 3.00 (built on 17.04.13)

In addition to the release changes listed below (see section labeled "Version 3.00 (built on 17.04.013)"), version 3.00 has the following important changes:

1. Version 3.00 software uses a new version of the FTC Robocol (robot protocol).  If you upgrade to v3.0 on the Robot Controller and/or Android Studio side, you must also upgrade the Driver Station software to match the new Robocol.
2. Version 3.00 software removes the setMaxSpeed and getMaxSpeed methods from the DcMotor class.  If you have an op mode that formerly used these methods, you will need to remove the references/calls to these methods.  Instead, v3.0 provides the max speed information through the use of motor profiles that are selected by the user during robot configuration.
3. Version 3.00 software currently does not have a mechanism to disable extra i2c sensors.  We hope to re-introduce this function with a release in the near future.

**************************************************************************************

Version 3.00 (built on 17.04.13)

*** Use this version of the software at YOUR OWN RISK!!! ***

This software is being released as an "alpha" version.  Use this version at your own risk!

This pre-release software contains SIGNIFICANT changes, including changes to the Wi-Fi Direct pairing mechanism, rewrites of the I2C sensor classes, changes to the USB/FTDI layer, and the introduction of support for the REV Robotics Expansion Hub and the REV Robotics color-range-light sensor.  These changes were implemented to improve the reliability and resiliency of the FTC control system.

Please note, however, that version 3.00 is considered "alpha" code.  This code is being released so that the FIRST community will have an opportunity to test the new REV Expansion Hub electronics module when it becomes available in May.  The developers do not recommend using this code for critical applications (i.e., competition use).

*** Use this version of the software at YOUR OWN RISK!!! ***

Changes include:
 * Major rework of sensor-related infrastructure.  Includes rewriting sensor classes to implement synchronous I2C communication.
 * Fix to reset Autonomous timer back to 30 seconds.
 * Implementation of specific motor profiles for approved 12V motors (includes Tetrix, AndyMark, Matrix and REV models).
 * Modest improvements to enhance Wi-Fi P2P pairing.
 * Fixes telemetry log addition race.
 * Publishes all the sources (not just a select few).
 * Includes Block programming improvements
    - Addition of optimized Vuforia blocks.
    - Auto scrollbar to projects and sounds pages.
    - Fixed blocks paste bug.
    - Blocks execute after while-opModeIsActive loop (to allow for cleanup before exiting op mode).
    - Added gyro integratedZValue block.
    - Fixes bug with projects page for Firefox browser.
    - Added IsSpeaking block to AndroidTextToSpeech.  
 * Implements support for the REV Robotics Expansion Hub
    - Implements support for integral REV IMU (physically installed on I2C bus 0, uses same Bosch BNO055 9 axis absolute orientation sensor as Adafruit 9DOF abs orientation sensor).
    - Implements support for REV color/range/light sensor.
    - Provides support to update Expansion Hub firmware through FTC SDK.
    - Detects REV firmware version and records in log file.
    - Includes support for REV Control Hub (note that the REV Control Hub is not yet approved for FTC use).
    - Implements FTC Blocks programming support for REV Expansion Hub and sensor hardware.
    - Detects and alerts when I2C device disconnect.
    
**************************************************************************************

Version 2.62 (built on 17.01.07)
  * Added null pointer check before calling modeToByte() in finishModeSwitchIfNecessary method for ModernRoboticsUsbDcMotorController class.
  * Changes to enhance Modern Robotics USB protocol robustness.

**************************************************************************************

Version 2.61 (released on 16.12.19)
  * Blocks Programming mode changes:
     - Fix to correct issue when an exception was thrown because an OpticalDistanceSensor object appears twice in the hardware map (the second time as a LightSensor).

**************************************************************************************

Version 2.6 (released on 16.12.16)
  * Fixes for Gyro class:
     - Improve (decrease) sensor refresh latency.
     - fix isCalibrating issues.
  * Blocks Programming mode changes:
     - Blocks now ignores a device in the configuration xml if the name is empty. Other devices work in configuration work fine.
     
**************************************************************************************

Version 2.5 (internal release on released on 16.12.13)
  * Blocks Programming mode changes:
     - Added blocks support for AdafruitBNO055IMU.
     - Added Download Op Mode button to FtcBocks.html.
     - Added support for copying blocks in one OpMode and pasting them in an other OpMode. The clipboard content is stored on the phone, so the programming mode server must be running.
     - Modified Utilities section of the toolbox.
     - In Programming Mode, display information about the active connections.
     - Fixed paste location when workspace has been scrolled.
     - Added blocks support for the android Accelerometer.
     - Fixed issue where Blocks Upload Op Mode truncated name at first dot.
     - Added blocks support for Android SoundPool.
     - Added type safety to blocks for Acceleration.
     - Added type safety to blocks for AdafruitBNO055IMU.Parameters.
     - Added type safety to blocks for AnalogInput.
     - Added type safety to blocks for AngularVelocity.
     - Added type safety to blocks for Color.
     - Added type safety to blocks for ColorSensor.
     - Added type safety to blocks for CompassSensor.
     - Added type safety to blocks for CRServo.
     - Added type safety to blocks for DigitalChannel.
     - Added type safety to blocks for ElapsedTime.
     - Added type safety to blocks for Gamepad.
     - Added type safety to blocks for GyroSensor.
     - Added type safety to blocks for IrSeekerSensor.
     - Added type safety to blocks for LED.
     - Added type safety to blocks for LightSensor.
     - Added type safety to blocks for LinearOpMode.
     - Added type safety to blocks for MagneticFlux.
     - Added type safety to blocks for MatrixF.     
     - Added type safety to blocks for MrI2cCompassSensor.
     - Added type safety to blocks for MrI2cRangeSensor.
     - Added type safety to blocks for OpticalDistanceSensor.
     - Added type safety to blocks for Orientation.
     - Added type safety to blocks for Position.
     - Added type safety to blocks for Quaternion.
     - Added type safety to blocks for Servo.
     - Added type safety to blocks for ServoController.
     - Added type safety to blocks for Telemetry.
     - Added type safety to blocks for Temperature.
     - Added type safety to blocks for TouchSensor.
     - Added type safety to blocks for UltrasonicSensor.
     - Added type safety to blocks for VectorF.
     - Added type safety to blocks for Velocity.
     - Added type safety to blocks for VoltageSensor.
     - Added type safety to blocks for VuforiaLocalizer.Parameters.
     - Added type safety to blocks for VuforiaTrackable.
     - Added type safety to blocks for VuforiaTrackables.
     - Added type safety to blocks for enums in AdafruitBNO055IMU.Parameters.
     - Added type safety to blocks for AndroidAccelerometer, AndroidGyroscope, AndroidOrientation, and AndroidTextToSpeech.

**************************************************************************************

Version 2.4 (released on 16.11.13)版本2.4（16.11.13发布）
  * Fix to avoid crashing for nonexistent resources.修复以避免不存在的资源崩溃。
  * Blocks Programming mode changes:块编程模式更改：
     - Added blocks to support OpenGLMatrix, MatrixF, and VectorF.
     - Added blocks to support AngleUnit, AxesOrder, AxesReference, CameraDirection, CameraMonitorFeedback, DistanceUnit, and TempUnit.
     - Added blocks to support Acceleration.
     - Added blocks to support LinearOpMode.getRuntime.
     - Added blocks to support MagneticFlux and Position.
     - Fixed typos.
     - Made blocks for ElapsedTime more consistent with other objects.
     - Added blocks to support Quaternion, Velocity, Orientation, AngularVelocity.
     - Added blocks to support VuforiaTrackables, VuforiaTrackable, VuforiaLocalizer, VuforiaTrackableDefaultListener.
     - Fixed a few blocks.
     - Added type checking to new blocks.
     - Updated to latest blockly.
     - Added default variable blocks to navigation and matrix blocks.
     - Fixed toolbox entry for openGLMatrix_rotation_withAxesArgs.
     - When user downloads Blocks-generated op mode, only the .blk file is downloaded.
     - When user uploads Blocks-generated op mode (.blk file), Javascript code is auto generated.
     - Added DbgLog support.
     - Added logging when a blocks file is read/written.
     - Fixed bug to properly render blocks even if missing devices from configuration file.
     - Added support for additional characters (not just alphanumeric) for the block file names (for download and upload).
     - Added support for OpMode flavor (“Autonomous” or “TeleOp”) and group.
     - 增加了支持OpenGLMatrix，MatrixF和VectorF的块。
      - 添加块以支持AngleUnit，AxesOrder，AxesReference，CameraDirection，CameraMonitorFeedback，DistanceUnit和TempUnit。
      - 添加块来支持加速。
      - 添加块来支持LinearOpMode.getRuntime。
      - 添加块以支持MagneticFlux和Position。
      - 修正错字
      - 为ElapsedTime制作的块更符合其他对象。
      - 添加块，以支持四元数，速度，方向，AngularVelocity。
      - 添加块以支持VuforiaTrackables，VuforiaTrackable，VuforiaLocalizer，VuforiaTrackableDefaultListener。
      - 修正了几个街区。
      - 添加类型检查到新的块。
      - 更新到最新的块。
      - 向导航和矩阵块添加了默认变量块。
      - 固定openGLMatrix_rotation_withAxesArgs的工具箱条目。
      - 当用户下载块生成的运行模式时，只下载了.blk文件。
      - 当用户上传块生成的运行模式（.blk文件）时，Javascript代码是自动生成的。
      - 添加了DbgLog支持。
      - 当块文件被读取/写入时添加日志记录。
      - 修正了正确渲染块的错误，即使从配置文件中丢失设备。
      - 增加对块文件名的其他字符（不仅仅是字母数字）的支持（用于下载和上传）。
      - 增加了对OpMode风格（“Autonomous”或“TeleOp”）和组的支持。
  * Changes to Samples to prevent tutorial issues.
  * Incorporated suggested changes from public pull 216 (“Replace .. paths”).
  * Remove Servo Glitches when robot stopped.
  * if user hits “Cancels” when editing a configuration file, clears the unsaved changes and reverts to original unmodified configuration.
  * Added log info to help diagnose why the Robot Controller app was terminated (for example, by watch dog function).
  * Added ability to transfer log from the controller.
  * Fixed inconsistency for AngularVelocity
  * Limit unbounded growth of data for telemetry.  If user does not call telemetry.update() for LinearOpMode in a timely manner, data added for telemetry might get lost if size limit is exceeded.
  *更改样品以防止教程问题。
   *合并来自public pull 216（更改……路径）的更改建议
   *当机器人停止时，移除伺服电机。
   *如果用户在编辑配置文件时点击“取消”，则清除未保存的更改并恢复为原始未修改的配置。
   *添加了日志信息，以帮助诊断为什么Robot Controller应用程序被终止（例如，通过看门狗功能）。
   *增加了从控制器传输日志的能力。
   *修改AngularVelocity不稳定性
   *限制遥测数据的无限增长。 如果用户不及时为LinearOpMode调用telemetry.update（），则如果超出了大小限制，则为遥测添加的数据可能会丢失。

**************************************************************************************

Version 2.35 (released on 16.10.06)
  * Blockly programming mode - Removed unnecesary idle() call from blocks for new project.

**************************************************************************************

Version 2.30 (released on 16.10.05)
  * Blockly programming mode:块状编程模式：
     - Mechanism added to save Blockly op modes from Programming Mode Server onto local device
     - To avoid clutter, blocks are displayed in categorized folders
     - Added support for DigitalChannel
     - Added support for ModernRoboticsI2cCompassSensor
     - Added support for ModernRoboticsI2cRangeSensor
     - Added support for VoltageSensor
     - Added support for AnalogInput
     - Added support for AnalogOutput
     - Fix for CompassSensor setMode block
     - 添加了将编程模式服务器的Blockly操作模式保存到本地设备的机制
      - 为了避免混乱，块被显示在分类的文件夹中
      - 增加了对DigitalChannel的支持
      - 增加了对ModernRoboticsI2cCompassSensor的支持
      - 增加了对ModernRoboticsI2cRangeSensor的支持
      - 增加了对VoltageSensor的支持
      - 增加了对AnalogInput的支持
      - 增加了对AnalogOutput的支持
      - 修复CompassSensor setMode模块
  * Vuforia
     - Fix deadlock / make camera data available while Vuforia is running.
     - Update to Vuforia 6.0.117 (recommended by Vuforia and Google to close security loophole). 
  * Fix for autonomous 30 second timer bug (where timer was in effect, even though it appeared to have timed out).
  * opModeIsActive changes to allow cleanup after op mode is stopped (with enforced 2 second safety timeout).
  * Fix to avoid reading i2c twice.
  * Updated sample Op Modes.
  * Improved logging and fixed intermittent freezing.
  * Added digital I/O sample.
  * Cleaned up device names in sample op modes to be consistent with Pushbot guide.
  * Fix to allow use of IrSeekerSensorV3.
  * Vuforia
      - 在Vuforia运行时修复死锁/使相机数据可用。
      - 更新到Vuforia 6.0.117（由Vuforia和Google推荐关闭安全漏洞）。
   *修复自动的30秒定时器错误（定时器有效，即使它似乎已超时）。
   * opModeIs活动更改以允许在运行模式停止后清除（强制执行2秒安全超时）。
   *修复以避免读取i2c两次。
   *更新了操作模式。
   *改进了测井和固定间歇式冷冻。
   *增加了数字I / O样本。
   *清除样品操作模式中的设备名称，使其与Pushbot指南一致。
   *修正为允许使用IrSeekerSensorV3。

**************************************************************************************

Version 2.20 (released on 16.09.08)
  * Support for Modern Robotics Compass Sensor.
  * Support for Modern Robotics Range Sensor.
  * Revise device names for Pushbot templates to match the names used in Pushbot guide.
  * Fixed bug so that IrSeekerSensorV3 device is accessible as IrSeekerSensor in hardwareMap.
  * Modified computer vision code to require an individual Vuforia license (per legal requirement from PTC).
  * Minor fixes.
  * Blockly enhancements:
     - Support for Voltage Sensor.
     - Support for Analog Input.
     - Support for Analog Output.
     - Support for Light Sensor.
     - Support for Servo Controller.
     版本2.20（16.09.08发布）
   *支持现代机器人罗盘传感器。
   *支持现代机器人距离传感器。
   *修改Pushbot模板的设备名称，以匹配Pushbot指南中使用的名称。
   *修正了错误，以便IrSeekerSensorV3设备可以作为IrSeekerSensor在hardwareMap中访问。
   *修改计算机视觉代码，要求个人Vuforia许可证（根据PTC的法律要求）。
   *次要修复
   *阻力增强：
      - 支持电压传感器。
      - 支持模拟输入。
      - 支持模拟输出。
      - 支持光传感器
      - 支持伺服控制器。

**************************************************************************************

Version 2.10 (released on 16.09.03)
 * Support for Adafruit IMU.
 * Improvements to ModernRoboticsI2cGyro class
    - Block on reset of z axis.
    - isCalibrating() returns true while gyro is calibration.
 * Updated sample gyro program.
 * Blockly enhancements
    - support for android.graphics.Color.
    - added support for ElapsedTime.
    - improved look and legibility of blocks.
    - support for compass sensor.
    - support for ultrasonic sensor.
    - support for IrSeeker.
    - support for LED.
    - support for color sensor.
    - support for CRServo
    - prompt user to configure robot before using programming mode.
 * Provides ability to disable audio cues.
 * various bug fixes and improvements.
 版本2.10（16.09.03发布）
  *支持Adafruit IMU。
  *改进ModernRoboticsI2cGyro类
     - 在z轴复位时阻塞。
     - 在校准陀螺仪时，isCalibrating（）返回true。
  *更新样品陀螺仪程序。
  *阻力增强
     - 支持android.graphics.Color。
     - 增加了ElapsedTime的支持。
     - 改进块的外观和易读性。
     - 支持罗盘传感器。
     - 支持超声波传感器。
     - 支持IrSeeker。
     - 支持LED。
     - 支持彩色传感器。
     - 支持CRServo
     - 在使用编程模式之前，提示用户配置机器人。
  *提供禁用音频线索的能力。
  *各种错误修复和改进。

**************************************************************************************

Version 2.00 (released on 16.08.19)
 * This is the new release for the upcoming 2016-2017 FIRST Tech Challenge Season.
 * Channel change is enabled in the FTC Robot Controller app for Moto G 2nd and 3rd Gen phones.
 * Users can now use annotations to register/disable their Op Modes.
 * Changes in the Android SDK, JDK and build tool requirements (minsdk=19, java 1.7, build tools 23.0.3).
 * Standardized units in analog input.
 * Cleaned up code for existing analog sensor classes.
 * setChannelMode and getChannelMode were REMOVED from the DcMotorController class.  This is important - we no longer set the motor modes through the motor controller.
 * setMode and getMode were added to the DcMotor class.  
 * ContinuousRotationServo class has been added to the FTC SDK.
 * Range.clip() method has been overloaded so it can support this operation for int, short and byte integers.
 * Some changes have been made (new methods added) on how a user can access items from the hardware map.
 * Users can now set the zero power behavior for a DC motor so that the motor will brake or float when power is zero.
 * Prototype Blockly Programming Mode has been added to FTC Robot Controller.  Users can place the Robot Controller into this mode, and then use a device (such as a laptop) that has a Javascript enabled browser to write Blockly-based Op Modes directly onto the Robot Controller.
 * Users can now configure the robot remotely through the FTC Driver Station app.
 * Android Studio project supports Android Studio 2.1.x and compile SDK Version 23 (Marshmallow).
 * Vuforia Computer Vision SDK integrated into FTC SDK.  Users can use sample vision targets to get localization information on a standard FTC field.
 * Project structure has been reorganized so that there is now a TeamCode package that users can use to place their local/custom Op Modes into this package.
 * Inspection function has been integrated into the FTC Robot Controller and Driver Station Apps (Thanks Team HazMat… 9277 & 10650!).
 * Audio cues have been incorporated into FTC SDK.
 * Swap mechanism added to FTC Robot Controller configuration activity.  For example, if you have two motor controllers on a robot, and you misidentified them in your configuration file, you can use the Swap button to swap the devices within the configuration file (so you do not have to manually re-enter in the configuration info for the two devices).
 * Fix mechanism added to all user to replace an electronic module easily.  For example, suppose a servo controller dies on your robot. You replace the broken module with a new module, which has a different serial number from the original servo controller.  You can use the Fix button to automatically reconfigure your configuration file to use the serial number of the new module.
 * Improvements made to fix resiliency and responsiveness of the system.
 * For LinearOpMode the user now must for a telemetry.update() to update the telemetry data on the driver station.  This update() mechanism ensures that the driver station gets the updated data properly and at the same time.
 * The Auto Configure function of the Robot Controller is now template based.  If there is a commonly used robot configuration, a template can be created so that the Auto Configure mechanism can be used to quickly configure a robot of this type.
 * The logic to detect a runaway op mode (both in the LinearOpMode and OpMode types) and to abort the run, then auto recover has been improved/implemented.
 * Fix has been incorporated so that Logitech F310 gamepad mappings will be correct for Marshmallow users.
版本2.00（16.08.19发布）
 *这是即将到来的2016 - 2017年第一季技术挑战季的新版本。
 *在Moto G 2nd和3rd Gen手机的FTC Robot Controller应用程序中启用频道更改。
 *用户现在可以使用注释来注册/禁用其操作模式。
 * Android SDK，JDK和构建工具要求的更改（minsdk = 19，java 1.7，构建工具23.0.3）。
 *模拟输入的标准化单位。
 *清理现有模拟传感器类的代码。
 * setChannelMode和getChannelMode从DcMotorController类中删除。这很重要 - 我们不再通过电机控制器设置电机模式。
 * setMode和getMode被添加到DcMotor类。
 * ContinuousRotationServo类已添加到FTC SDK。
 * Range.clip（）方法已被重载，因此它可以支持int，short和byte整数的这个操作。
 *已经对用户如何从硬件地图中访问项目进行了一些更改（添加了新方法）。
 *用户现在可以设置直流电机的零功率特性，使电机在电源为零时制动或浮动。
 *原型块编程模式已添加到FTC机器人控制器。用户可以将机器人控制器置于此模式，然后使用具有启用Javascript功能的浏览器的设备（如笔记本电脑）将基于块的操作模式直接写入机器人控制器。
 *用户现在可以通过FTC Driver Station应用程序远程配置机器人。
 * Android Studio项目支持Android Studio 2.1.x并编译SDK版本23（Marshmallow）。
 * Vuforia计算机视觉SDK集成到FTC SDK中。用户可以使用样本视觉目标来获取标准FTC字段的本地化信息。
 *项目结构已经重组，现在有一个TeamCode包，用户可以使用它们将本地/自定义操作模式放在此包中。
 *检查功能已经集成到FTC机器人控制器和驱动程序站应用程序（感谢团队HazMat ... 9277和10650！）。
 *音频线索已被并入FTC SDK。
 *交换机制添加到FTC Robot Controller配置活动。例如，如果机器人上有两个电机控制器，并且您在配置文件中错误识别了它们，则可以使用“交换”按钮交换配置文件中的设备（因此您不必在配置中手动重新输入两个设备的信息）。
 *修复机制添加到所有用户以轻松更换电子模块。例如，假设伺服控制器在机器人上死亡。您使用新的模块替换损坏的模块，该模块与原始伺服控制器具有不同的序列号。您可以使用“修复”按钮自动重新配置您的配置文件以使用新模块的序列号。
 *改进系统的弹性和响应能力。
 *对于LinearOpMode，用户现在必须使用遥测.update（）来更新驱​​动程序站上的遥测数据。这种update（）机制可以确保驱动程序工具在正确和同时获取更新的数据。
 *机器人控制器的自动配置功能现在是基于模板的。如果有常用的机器人配置，则可以创建一个模板，以便可以使用自动配置机制快速配置此类机器人。
 *检测失控操作模式（在LinearOpMode和OpMode类型中）并中止运行，然后自动恢复的逻辑已被改进/实现。
 * Fix已被合并，以便Logitech F310的手柄映射对于Marshmallow用户来说是正确的。
**************************************************************************************

Release 16.07.08

 * For the ftc_app project, the gradle files have been modified to support Android Studio 2.1.x.



**************************************************************************************

Release 16.03.30

 * For the MIT App Inventor, the design blocks have new icons that better represent the function of each design component.
 * Some changes were made to the shutdown logic to ensure the robust shutdown of some of our USB services.
 * A change was made to LinearOpMode so as to allow a given instance to be executed more than once, which is required for the App Inventor.
 * Javadoc improved/updated.

**************************************************************************************

Release 16.03.09

 * Changes made to make the FTC SDK synchronous (significant change!)
    - waitOneFullHardwareCycle() and waitForNextHardwareCycle() are no longer needed and have been deprecated.
    - runOpMode() (for a LinearOpMode) is now decoupled from the system's hardware read/write thread.
    - loop() (for an OpMode) is now decoupled from the system's hardware read/write thread.
    - Methods are synchronous.
    - For example, if you call setMode(DcMotorController.RunMode.RESET_ENCODERS) for a motor, the encoder is guaranteed to be reset when the method call is complete.
    - For legacy module (NXT compatible), user no longer has to toggle between read and write modes when reading from or writing to a legacy device.
 * Changes made to enhance reliability/robustness during ESD event.
 * Changes made to make code thread safe.
 * Debug keystore added so that user-generated robot controller APKs will all use the same signed key (to avoid conflicts if a team has multiple developer laptops for example).
 * Firmware version information for Modern Robotics modules are now logged.
 * Changes made to improve USB comm reliability and robustness.
 * Added support for voltage indicator for legacy (NXT-compatible) motor controllers.
 * Changes made to provide auto stop capabilities for op modes.
    - A LinearOpMode class will stop when the statements in runOpMode() are complete.  User does not have to push the stop button on the driver station.
    - If an op mode is stopped by the driver station, but there is a run away/uninterruptible thread persisting, the app will log an error message then force itself to crash to stop the runaway thread.
 * Driver Station UI modified to display lowest measured voltage below current voltage (12V battery).
 * Driver Station UI modified to have color background for current voltage (green=good, yellow=caution, red=danger, extremely low voltage).
 * javadoc improved (edits and additional classes).
 * Added app build time to About activity for driver station and robot controller apps.
 * Display local IP addresses on Driver Station About activity.
 * Added I2cDeviceSynchImpl.
 * Added I2cDeviceSync interface.
 * Added seconds() and milliseconds() to ElapsedTime for clarity.
 * Added getCallbackCount() to I2cDevice.
 * Added missing clearI2cPortActionFlag.
 * Added code to create log messages while waiting for LinearOpMode shutdown.
 * Fix so Wifi Direct Config activity will no longer launch multiple times.
 * Added the ability to specify an alternate i2c address in software for the Modern Robotics gyro.
 *改变使FTC SDK同步（显着变化！）
     - waitOneFullHardwareCycle（）和waitForNextHardwareCycle（）不再需要，已被弃用。
     - runOpMode（）（对于LinearOpMode）现在与系统的硬件读/写线程分离。
     - loop（）（对于OpMode）现在与系统的硬件读/写线程分离。
     - 方法是同步的。
     - 例如，如果您为电机调用setMode（DcMotorController.RunMode.RESET_ENCODERS），则在方法调用完成时，编码器将被保证被重置。
     - 对于传统模块（NXT兼容），用户在读取或写入旧设备时不再需要在读取和写入模式之间切换。
 *为改善ESD事件期间的可靠性/鲁棒性所做的更改。
 *使代码线程安全的变更。
 *添加了调试密钥库，以便用户生成的机器人控制器APK将全部使用相同的签名密钥（例如，如果团队有多个开发人员笔记本电脑，则避免冲突）。
 *现在记录了现代机器人模块的固件版本信息。
 *改进以提高USB通信的可靠性和鲁棒性。
 *增加了对传统（NXT兼容）电机控制器的电压指示器的支持。
 *为op模式提供自动停止功能所做的更改。
     - 当runOpMode（）中的语句完成时，LinearOpMode类将停止。用户不必按下驱动台上的停止按钮。
     - 如果操作模式由驱动程序站停止，但是有一个运行/不间断的线程持续存在，应用程序将记录错误消息，然后强制自身崩溃以停止失控线程。
 *驱动站UI修改为显示最低测量电压低于当前电压（12V电池）。
 *驱动工作站UI修改为具有当前电压的颜色背景（绿色=好，黄色=小心，红色=危险，极低电压）。
 *改进javadoc（编辑和附加类）。
 *添加应用程序构建时间到关于驱动程序站和机器人控制器应用程序的活动。
 *在Driver Station上显示本地IP地址关于活动。
 *添加I2cDeviceSynchImpl。
 *添加I2cDeviceSync接口。
 *为了清楚起见，向ElapsedTime添加了秒（）和毫秒（）。
 *添加回调计数（）到I2c设备。
 *添加清晰的I2c端口操作标志。
 *添加了在等待LinearOpMode关闭时创建日志消息的代码。
 *修复，因此Wifi直接配置活动将不再启动多次。
 *增加了在Modern Robotics陀螺仪的软件中指定备用i2c地址的能力。
**************************************************************************************

Release 16.02.09

 * Improved battery checker feature so that voltage values get refreshed regularly (every 250 msec) on Driver Station (DS) user interface.
 * Improved software so that Robot Controller (RC) is much more resilient and “self-healing” to USB disconnects:
    - If user attempts to start/restart RC with one or more module missing, it will display a warning but still start up.
    - When running an op mode, if one or more modules gets disconnected, the RC & DS will display warnings,and robot will keep on working in spite of the missing module(s).
    - If a disconnected module gets physically reconnected the RC will auto detect the module and the user will regain control of the recently connected module.
    - Warning messages are more helpful (identifies the type of module that’s missing plus its USB serial number).   
 * Code changes to fix the null gamepad reference when users try to reference the gamepads in the init() portion of their op mode.
 * NXT light sensor output is now properly scaled.  Note that teams might have to readjust their light threshold values in their op modes.
 * On DS user interface, gamepad icon for a driver will disappear if the matching gamepad is disconnected or if that gamepad gets designated as a different driver.
 * Robot Protocol (ROBOCOL) version number info is displayed in About screen on RC and DS apps.
 * Incorporated a display filter on pairing screen to filter out devices that don’t use the “<TEAM NUMBER>-“ format. This filter can be turned off to show all WiFi Direct devices.
 * Updated text in License file.
 * Fixed formatting error in OpticalDistanceSensor.toString().
 * Fixed issue on with a blank (“”) device name that would disrupt WiFi Direct Pairing.
 * Made a change so that the WiFi info and battery info can be displayed more quickly on the DS upon connecting to RC.
 * Improved javadoc generation.
 * Modified code to make it easier to support language localization in the future.
 版本16.02.09

 *改进了电池检查功能，以便在Driver Station（DS）用户界面上定期刷新电压值（每250毫秒）。
 *改进的软件，使机器人控制器（RC）更具弹性和“自我修复”USB断开连接：
     - 如果用户尝试启动/重新启动具有一个或多个模块的RC，则会显示警告但仍然启动。
     - 运行op模式时，如果一个或多个模块断开连接，则RC＆DS将显示警告，机器人将继续工作，尽管丢失模块。
     - 如果断开的模块物理重新连接，RC将自动检测模块，用户将重新获得对最近连接的模块的控制。
     - 警告消息更有用（识别缺少的模块类型及其USB序列号）。
 *当用户尝试引用它们的运行模式的init（）部分中的游戏手柄时，代码更改以修复null gamepad参考。
 * NXT光传感器输出现在正确缩放。请注意，团队可能必须在其操作模式下重新调整其光阈值。
 *在DS用户界面上，如果匹配的游戏手柄已断开，或者该游戏手柄被指定为不同的驱动程序，则驱动程序的游戏手柄图标将消失。
 *机器人协议（ROBOCOL）版本号信息显示在RC和DS应用程序的关于屏幕上。
 *在配对屏幕上加入显示过滤器，以过滤掉不使用“<TEAM NUMBER> - ”格式的设备。可以关闭此过滤器以显示所有WiFi Direct设备。
 *许可证文件中的更新文本。
 *修正 光学距离传感器固定格式化错误到String（）。
 *修正了一个空白（“”）设备名称会中断WiFi直接配对的问题。
 *进行更改，以便在连接到RC时，可以在DS上更快地显示WiFi信息和电池信息。
 *改进javadoc生成
 *修改代码，以便将来更容易支持语言本地化。

**************************************************************************************

Release 16.01.04

 * Updated compileSdkVersion for apps
 * Prevent Wifi from entering power saving mode
 * removed unused import from driver station
 * Corrrected "Dead zone" joystick code.
 * LED.getDeviceName and .getConnectionInfo() return null
 * apps check for ROBOCOL_VERSION mismatch
 * Fix for Telemetry also has off-by-one errors in its data string sizing / short size limitations error
 * User telemetry output is sorted.
 * added formatting variants to DbgLog and RobotLog APIs
 * code modified to allow for a long list of op mode names.
 * changes to improve thread safety of RobocolDatagramSocket
 * Fix for "missing hardware leaves robot controller disconnected from driver station" error
 * fix for "fast tapping of Init/Start causes problems" (toast is now only instantiated on UI thread).
 * added some log statements for thread life cycle.
 * moved gamepad reset logic inside of initActiveOpMode() for robustness
 * changes made to mitigate risk of race conditions on public methods.
 * changes to try and flag when WiFi Direct name contains non-printable characters.
 * fix to correct race condition between .run() and .close() in ReadWriteRunnableStandard.
 * updated FTDI driver
 * made ReadWriteRunnableStanard interface public.
 * fixed off-by-one errors in Command constructor
 * moved specific hardware implmentations into their own package.
 * moved specific gamepad implemnatations to the hardware library.
 * changed LICENSE file to new BSD version.
 * fixed race condition when shutting down Modern Robotics USB devices.
 * methods in the ColorSensor classes have been synchronized.
 * corrected isBusy() status to reflect end of motion.
 * corrected "back" button keycode.
 * the notSupported() method of the GyroSensor class was changed to protected (it should not be public).
 版本16.01.04

 *更新了应用程序的compileSdkVersion
 *防止Wifi进入省电模式
 *从驱动器移除未使用的导入
 *修正“死区”操纵杆代码。
 *LED获取设备名称并获取连接信息（）返回null
 *应用程序检查ROBOCOL_VERSION不匹配
 *修复遥测还会在其数据字符串大小/短尺寸限制错误中出现一个错误
 *用户遥测输出排序。
 *添加了DbgLog和RobotLog API的格式化变体
 *代码修改为允许长列表的op模式名称。
 *改变以改善RobocolDatagramSocket的线程安全性
 *修复“缺少硬件使机器人控制器与驱动程序站断开”错误
 *修复“快速点击Init / Start导致问题”（吐司现在只在UI线程上实例化）。
 *为线程生命周期添加了一些日志语句。
 *启动Active OpMode（）中的游戏垫重置逻辑，以实现稳健性
 *为减轻公共方法的竞争条件风险而作出的改变。
 *当WiFi直接名称包含不可打印的字符时，更改为尝试和标记。
 *修正ReadWriteRunnableStandard中的.run（）和.close（）之间的竞争条件。
 *更新的FTDI驱动程序
 *使读写Runnable Stanard接口公开。
 *在Command构造函数中修复了一个一个错误
 *将特定的硬件插件移动到自己的包中。
 *将特定的操作系统实现移动到硬件库。
 *将LICENSE文件更改为新的BSD版本。
 *关闭现代机器人USB设备时的固定竞争状况。
 * ColorSensor类中的方法已经同步。
 *修正为Busy（）状态以反映动作结束。
 *修正“后退”按钮键码。
 * GyroSensor类的notSupported（）方法已更改为protected（不应该是public）


**************************************************************************************

Release 15.11.04.001

 * Added Support for Modern Robotics Gyro.
  - The GyroSensor class now supports the MR Gyro Sensor.
  - Users can access heading data (about Z axis)
  - Users can also access raw gyro data (X, Y, & Z axes).
  - Example MRGyroTest.java op mode included.
 * Improved error messages
  - More descriptive error messages for exceptions in user code.
 * Updated DcMotor API
 * Enable read mode on new address in setI2cAddress
 * Fix so that driver station app resets the gamepads when switching op modes.
 * USB-related code changes to make USB comm more responsive and to display more explicit error messages.
  - Fix so that USB will recover properly if the USB bus returns garbage data.
  - Fix USB initializtion race condition.
  - Better error reporting during FTDI open.
  - More explicit messages during USB failures.
  - Fixed bug so that USB device is closed if event loop teardown method was not called.
 * Fixed timer UI issue
 * Fixed duplicate name UI bug (Legacy Module configuration).
 * Fixed race condition in EventLoopManager.
 * Fix to keep references stable when updating gamepad.
 * For legacy Matrix motor/servo controllers removed necessity of appending "Motor" and "Servo" to controller names.
 * Updated HT color sensor driver to use constants from ModernRoboticsUsbLegacyModule class.
 * Updated MR color sensor driver to use constants from ModernRoboticsUsbDeviceInterfaceModule class. 
 * Correctly handle I2C Address change in all color sensors
 * Updated/cleaned up op modes.
  - Updated comments in LinearI2cAddressChange.java example op mode.
  - Replaced the calls to "setChannelMode" with "setMode" (to match the new of the DcMotor  method).
  - Removed K9AutoTime.java op mode.
  - Added MRGyroTest.java op mode (demonstrates how to use MR Gyro Sensor).
  - Added MRRGBExample.java op mode (demonstrates how to use MR Color Sensor).
  - Added HTRGBExample.java op mode (demonstrates how to use HT legacy color sensor).
  - Added MatrixControllerDemo.java (demonstrates how to use legacy Matrix controller).
 * Updated javadoc documentation.
 * Updated release .apk files for Robot Controller and Driver Station apps.
 版本15.11.04.001

 *增加了对现代机器人陀螺仪的支持。
   - 陀螺传感器类现在支持MR陀螺传感器。
   - 用户可以访问标题数据（约Z轴）
   - 用户还可以访问原始陀螺仪数据（X，Y和Z轴）。
   - 包含示例MRGyroTest.java op模式。
 *改进的错误消息
   - 用户代码中异常的更多描述性错误消息。
 *更新DcMotor API
 *在setI2cAddress中的新地址上启用读取模式
 *修正，以便驾驶员站应用程序在切换运行模式时复位游戏手柄。
 * USB相关代码更改，使USB通信更灵敏，并显示更明确的错误消息。
   - 修复，如果USB总线返回垃圾数据，USB将正常恢复。
   - 修复USB初始化竞争条件。
   - 在FTDI开放期间更好的错误报告。
   - USB故障期间更明确的消息。
   - 修正错误，以便在未调用事件循环拆卸方法时关闭USB设备。
 *固定定时器UI问题
 *修复重复名称UI bug（旧版模块配置）。
 *在EventLoopManager中修正了比赛条件。
 *修正以在更新游戏手柄时保持参考稳定。
 *对于传统的Matrix电机/伺服控制器，取消了将“电机”和“伺服”附加到控制器名称的必要性。
 *更新HT颜色传感器驱动程序以使用ModernRoboticsUsbLegacyModule类中的常量。
 *更新MR颜色传感器驱动程序，以使用ModernRoboticsUsbDeviceInterfaceModule类中的常量。
 *正确处理所有颜色传感器中的I2C地址更改
 *更新/清理操作模式。
   - 在LinearI2cAddressChange.java示例op模式下更新了注释。
   - 用“setMode”替换到“setChannelMode”的调用（匹配新的DcMotor方法）。
   - 删除K9AutoTime.java操作模式。
   - 添加了MRGyroTest.java操作模式（演示如何使用MR陀螺传感器）。
   - 添加MRRGBExample.java操作模式（演示如何使用MR颜色传感器）。
   - 添加HTRGBExample.java操作模式（演示如何使用HT传统颜色传感器）。
   - 添加了MatrixControllerDemo.java（演示如何使用legacy Matrix控制器）。
 *更新的javadoc文档。
 *更新发布的.apk文件为机器人控制器和驱动程序工作站应用程序。

T. Eng
November 5, 2015
 
**************************************************************************************

Release 15.10.06.002

 * Added support for Legacy Matrix 9.6V motor/servo controller.
 * Cleaned up build.gradle file.
 * Minor UI and bug fixes for driver station and robot controller apps.
 * Throws error if Ultrasonic sensor (NXT) is not configured for legacy module port 4 or 5.

 版本15.10.06.002

  *增加了对Legacy Matrix 9.6V电机/伺服控制器的支持。
  *清理build.gradle文件。
  *驱动站和机器人控制器应用程序的小UI和错误修复。
  *如果传统模块端口4或5未配置超声波传感器（NXT），则会抛出错误

T. Eng
October 6, 2015

**************************************************************************************

In this latest version of the FTC SDK (20150803_001) the following changes should be noted:

 * New user interfaces for FTC Driver Station and FTC Robot Controller apps.
 * An init() method is added to the OpMode class.
   - For this release, init() is triggered right before the start() method.
   - Eventually, the init() method will be triggered when the user presses an "INIT" button on driver station.
   - The init() and loop() methods are now required (i.e., need to be overridden in the user's op mode).
   - The start() and stop() methods are optional.
 * A new LinearOpMode class is introduced.
   - Teams can use the LinearOpMode mode to create a linear (not event driven) program model.
   - Teams can use blocking statements like Thread.sleep() within a linear op mode.
 * The API for the Legacy Module and Core Device Interface Module have been updated.
   - Support for encoders with the Legacy Module is now working.
 * The hardware loop has been updated for better performance.
 在最新版本的FTC SDK（20150803_001）中应注意以下更改：

  * FTC Driver Station和FTC Robot Controller应用程序的新用户界面。
  *将一个init（）方法添加到OpMode类中。
    - 对于此版本，init（）在start（）方法之前被触发。
    - 最终，当用户按下驱动程序站上的“INIT”按钮时，会触发init（）方法。
    - 现在需要init（）和loop（）方法（即需要在用户的op模式下覆盖）。
    - start（）和stop（）方法是可选的。
  *引入了一个新的LinearOpMode类。
    - 团队可以使用LinearOpMode模式创建线性（非事件驱动）程序模型。
    - 团队可以在线性运行模式下使用Thread.sleep（）等阻塞语句。
  *旧版模块和核心设备接口模块的API已更新。
    - 支持使用遗留模块的编码器现在正在工作。
  *硬件循环已更新，以获得更好的性能。


T. Eng
August 3, 2015
T. Eng
2015年8月3日
