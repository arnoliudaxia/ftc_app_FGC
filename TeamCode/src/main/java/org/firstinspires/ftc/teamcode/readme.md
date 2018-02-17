Welcome! 2018A

This module, TeamCode, is the place where you will write/paste the code for your team's robot controller App. This module is currently empty (a clean slate) but the process for adding OpModes is straight forward.

//这个文件夹——团队程序是一个你用来编写复制代码给机器人控制app的。这个模块一开始是空的。但是编写程序是我们需要做的。

Creating your own OpModes

The easiest way to create your own OpMode is to copy a Sample OpMode and make it your own.

//最简洁的编写你的机器人程序的方法是复制例程到这个文件夹，让他成为你的机器人程序。

Sample opmodes exist in the FtcRobotController module. To locate these samples, find the FtcRobotController module in the "Project/Android" tab.

//例程在 FTCRobotController 文件夹里。 来定位这些例程，找到 FtcRobotController文件夹在"Project/Android"表格里。

Expand the following tree elements: FtcRobotController / java / org.firstinspires.ftc.robotcontroller / external / samples

//点开以下分支 FtcRobotController / java / org.firstinspires.ftc.robotcontroller / external / samples

A range of different samples classes can be seen in this folder. The class names follow a naming convention which indicates the purpose of each class. The full description of this convention is found in the samples/sample_convention.md file.

//一系列不同的例程在这个文件夹可以找到。 这个程序按照一定的命名方式代表了来程序的目的。 这些程序命名方式的介绍在samples/sample_convention.md 文件里可以找到。

A brief synopsis of the naming convention is given here: The prefix of the name will be one of the following:

// 主要的命名方式在这里可以看到。 名字的前缀的解释下面可以看到。

Basic: This is a minimally functional OpMode used to illustrate the skeleton/structure of a particular style of OpMode. These are bare bones examples.
//basic（基础）：这个前缀代表最低限度的功能的机器人程序用来阐明特别方式的机器人程序。这些是示例程序的梗概。

Sensor: This is a Sample OpMode that shows how to use a specific sensor. It is not intended as a functioning robot, it is simply showing the minimal code required to read and display the sensor values.
//sensor（传感器）： 这是一个示例机器人程序，它会告诉我们怎么使用特殊的传感器。这些程序不是用来驱动运行机器人， 它展示了使用传感器需要的知道的东西和使用传感器的功能。

Hardware: This is not an actual OpMode, but a helper class that is used to describe one particular robot's hardware devices: eg: for a Pushbot. Look at any Pushbot sample to see how this can be used in an OpMode. Teams can copy one of these to create their own robot definition.
//harware（硬件）: 这不是机器人程序，而是一个帮助类，用来描述特殊的机器人硬件配置： 比如：给K9或pushbot。 看任何和Pushbot有关的例程来看他怎么在机器人程序中应用。 队伍可以复制它们中的一部分到自己的队伍文件夹中来创造自己的机器人。

Pushbot: This is a Sample OpMode that uses the Pushbot robot structure as a base.
//Pushbot： 这是机器人程序的例程用于Pushbot机器人硬件作为基础。 它能提供一些标准基线Pushbot机器人和程序，或者展示特殊的传感器或概念可以直接用在Pushbot的底盘上。

Concept:	This is a sample OpMode that illustrates performing a specific function or concept. These may be complex, but their operation should be explained clearly in the comments, or the header should reference an external doc, guide or tutorial.
//concept(概念) ：这是一个机器人程序阐明了运行一个特殊的功能或概念。 这些可能很复杂，但他的操作可以用指令很简单的解释。 否则机器人程序应该借鉴外部的文档、指导、教程。 每一个机器人程序应该尝试展示一个单独的概念，这样它们就会更加容易基于他们的名字。这些机器人程序可能不能运行一个能行动的机器人。

Library: This is a class, or set of classes used to implement some strategy. These will typically NOT implement a full OpMode. Instead they will be included by an OpMode to provide some stand-alone capability.
//library(库) ：这是一种类，或者一系列类用来使一些策略生效。 这些类不会使某一个机器人程序生效。 而是它会包含在一个机器人程序中提供独立地能力。

Once you are familiar with the range of samples available, you can choose one to be the basis for your own robot. In all cases, the desired sample(s) needs to be copied into your TeamCode module to be used.

//一旦你对例程很熟悉，你能选择一个例程来作为你机器人程序的基础。 在所以的项目中，需要的例程可以被复制到TeamCode文件夹里被使用。

This is done inside Android Studio directly, using the following steps:

Locate the desired sample class in the Project/Android tree.

Right click on the sample class and select "Copy"

Expand the TeamCode / java folder

Right click on the org.firstinspires.ftc.teamcode folder and select "Paste"

You will be prompted for a class name for the copy. Choose something meaningful based on the purpose of this class. Start with a capital letter, and remember that there may be more similar classes later.

//这些步骤完成后，这些可以在android找到： 1） 找到需要的例程在project/Android分支。

2）右键需要的例程选择"copy"。

3）点击Teamcode / java文件夹

4）右键黏贴在org.firstinspires.ftc.teamcode文件夹。

5）你将会为你的复制品起个名字。 选一个概括里你项目目的的名字。 开头字母需要是大写字母，并且记得过会儿会有相似的项目在这个文件夹里。

Once your copy has been created, you should prepare it for use on your robot. This is done by adjusting the OpMode's name, and enabling it to be displayed on the Driver Station's OpMode list.

Each OpMode sample class begins with several lines of code like the ones shown below:

//一旦你复制好了后，你应该准备应用到你的机器人上。 这取决于你的机器人程序的名字，并在Driver Station的机器人程序的列表上呈现。

//每一个机器人程序都在开始有几行这样的语句。(如下)

 @TeleOp(name="Template: Linear OpMode", group="Linear Opmode")
 @Disabled
The name that will appear on the driver station's "opmode list" is defined by the code: name="Template: Linear OpMode"

//机器人程序的名称将会出现在Driver Station的机器人程序列表中，这个名称靠上面这行代码来定义。

You can change what appears between the quotes to better describe your opmode. The "group=" portion of the code can be used to help organize your list of OpModes.

//你能改变描述你的机器人程序的内容。 "group＝"这个代码能被用来管理机器人程序的列表。

As shown, the current OpMode will NOT appear on the driver station's OpMode list because of the @Disabled annotation which has been included. This line can simply be deleted , or commented out, to make the OpMode visible.

//Driver Station无法出现你的机器人程序，因为你的程序中包含来"@Disable"语句。 这行语句删除或者被注释，就能让你的机器人程序能在Driver Station中显示出来。

ADVANCED Multi-Team App management: Cloning the TeamCode Module

进阶：多队伍管理：复制TeamCode

In some situations, you have multiple teams in your club and you want them to all share a common code organization, with each being able to see the others code but each having their own team module with their own code that they maintain themselves.

//在某些时候，你需要整合社团的所有队伍的代码，你需要它们分享的代码。 它们能看到其他队伍的代码，同时拥有自己的队伍代码文件夹。

In this situation, you might wish to clone the TeamCode module, once for each of these teams. Each of the clones would then appear along side each other in the Android Studio module list, together with the FtcRobotController module (and the original TeamCode module).

//在这个时候，你可能希望复制TeamCode文件夹，为每个队伍复制一份。 每一次复制都会在Android Studio中的列表中出现TeamCode和FtcController文件夹（和原始的TeamCode文件夹）。

Selective Team phones can then be programmed by selecting the desired Module from the pull down list prior to clicking to the green Run arrow.

//比赛队伍的手机能被重新编译，通过选择需要的文件夹在下拉菜单中点击绿色的Run。（就是Run TeamCode）

Warning: This is not for the inexperienced Software developer. You will need to be comfortable with File manipulations and managing Android Studio Modules. These changes are performed OUTSIDE of Android Studios, so close Android Studios before you do this.

Also.. Make a full project backup before you start this :)

//警告: 这不适用于一个没有经验的软件开发者。 你需要对文件操作很熟悉，使用Android Studio功能熟练。 这些操作会在Android studio外进行，所以在你进行这些操作前关掉Android Studio。

//还有在你做这些之前准备好足够的空间 ：）

To clone TeamCode, do the following:

// 复制TeamCode的话，完成下列操作：

Note: Some names start with "Team" and others start with "team". This is intentional.

//注意：一些名字是"Team"而有些却是"team"，它们是不同的。这是故意而为之的。

Using your operating system file management tools, copy the whole "TeamCode" folder to a sibling folder with a corresponding new name, eg: "Team0417".
// 用这个操作系统文件的管理工具，复制整个"TeamCode"文件夹到相似的文件夹，用一张的新名字，比如"Team0417"。

In the new Team0417 folder, delete the TeamCode.iml file.
// 在新的Team0417文件夹，删除TeamCode.iml文件

the new Team0417 folder, rename the "src/main/java/org/firstinspires/ftc/teamcode" folder to a matching name with a lowercase 'team' eg: "team0417".
// 在新的Team0417文件夹，重新命名"src/main/java/org/firstinspires/ftc/teamcode"文件夹使名字和小写的team相配 比如："team0417"。

In the new Team0417/src/main folder, edit the "AndroidManifest.xml" file, change the line that contains package="org.firstinspires.ftc.teamcode" to be package="org.firstinspires.ftc.team0417"
// 在新的Team0417/src/main文件夹，编辑"AndroidManifest.xml"文件，改变有一行 package="org.firstinspires.ftc.teamcode" 变成 package="org.firstinspires.ftc.team0417"

Add: include ':Team0417' to the "/settings.gradle" file.
// 加上Team0417在"/settings.gradle" 文件中

Open up Android Studios and clean out any old files by using the menu to "Build/Clean Project""
// 打开Android Studio清除旧文件在Android Studio的"Build/Clean Project"