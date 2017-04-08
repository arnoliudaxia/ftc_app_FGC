/* Copyright (c) 2014, 2015 Qualcomm Technologies Inc　高通科技有限公司　２０１４，２０１５　版权所有

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
满足以下条件的对源和二进制的重新分配和使用，无论是否修改，都是被允许的。（受下文免责声明的限制）
源代码的再分发必须保留上述版权声明，本列表的条件和以下免责声明。
二进制的重新分发必须复制（遵循）上述版权声明，此份清单所列的条件，以下免责声明或者重新分配中其他的要求。
无论是高通科技有限公司还是他的合作贡献者的名字，在没有事先书面许可的情况下，都不能被署名支持或推广衍生自该软件的产品。
任何明示或暗示的（直接或间接的），任何方面的授权均由本协议授予许可。 
本软件由版权所有者和贡献者提供“按原样”。任何明示或暗示的授权（保证），包括但不限于，适销性和特定用途的授权（保证），都无关版权所有者和贡献者的责任。
在任何情况下，版权所有者或贡献者均不承担责任。
对于任何直接，间接，偶发，特殊，示例或后果损害（包括但不限于采购替代物品或服务; 使用，数据或利润上的损失; 或业务中断）
无论导致的是什么情况，任何责任要求，无论是否有合同，有严格责任要求或侵权行为（包括疏忽或以其他方式），
以任何方式使用该软件，即使该软件已被告知可能发生此类损坏。版权所有者或贡献者皆不承担任何责任。
（总之，本段意思就是：如果您是想将您开发的专利软件供人使用,又不想承担任何可能产生的种种不良结果的责任,那这个声明已囊括了所有可能发生的情况,您可以放心使用.）
package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Demonstrates empty OpMode　演示 清空操作模式
 */
@Autonomous(name = "Concept: NullOp", group = "Concept")
@Disabled
public class ConceptNullOp extends OpMode {

  private ElapsedTime runtime = new ElapsedTime();

  @Override
  public void init() {
    telemetry.addData("Status", "Initialized");
  }

  /*
     * Code to run when the op mode is first enabled goes here 首次启用运行模式时运行的代码在此处
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
     */
  @Override
  public void init_loop() {
  }

  /*
   * This method will be called ONCE when start is pressed　当按下启动时，此方法将被称为ONCE
   * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
   */
  @Override
  public void start() {
    runtime.reset();
  }

  /*
   * This method will be called repeatedly in a loop　这个方法将在循环中重复调用
   * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
   */
  @Override
  public void loop() {
    telemetry.addData("Status", "Run Time: " + runtime.toString());
  }
}
