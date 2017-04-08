# Contributing to the FTC SDK

The following is a set of guidelines for contributing the FIRST FTC SDK.  The FTC Technology Team welcomes suggestions for improvements to core software, ideas for new features, requests for built-in support of new sensors, and well written bug reports.

## How can I contribute?

### Pull requests

__STOP!__  If you are new to git, do not understand the mechanics of forks, branches, and pulls, if what you just read is confusing, __do not__ push this button.  Most likely it won't do what you think it will.

![Pull Button](../doc/media/PullRequest.PNG)

If you are looking at this button then you've pushed some changes to your team's fork of ftctechnh/ftc_app.  Congratulations!  You are almost certainly finished.

The vast majority of pull requests seen on the ftctechnh/ftc_app repository are not intended to be merged into the official SDK.  Team software is just that, your team's.  It's specific to the tasks you are trying to accomplish, the testing you are doing, and goals your team has.  You don't want that pushed into the official SDK.

If what you've read so far makes little sense, there are some very good git learning resources online.  
[Git Book](https://git-scm.com/book/en/v2)  
[Interactive Git Tutorial](https://try.github.io)

＃对FTC SDK做出贡献

以下是提供FIRST FTC SDK的一套指南。 FTC技术团队欢迎对核心软件的改进，新功能的想法，内置的新传感器支持请求以及编写好的错误报告的建议。

##如何贡献？

###向按钮ＰＵＬＬ请求

__停止！__如果你是git的新手，不明白叉子，分支和拉力的机制，如果你刚才看到的是令人困惑的，__绝对不要__按这个按钮。很可能不会做你认为的事情。

！[ＰＵＬＬ按钮]（../ doc / media / PullRequest.PNG）

如果你正在看这个按钮，那么你已经对你的团队的ftctechnh / ftc_app的分支进行了一些改变。恭喜！你几乎肯定完成了

在ftctechnh / ftc_app存储库中看到的绝大多数拉请求不能合并到官方SDK中。团队软件就是这样，你的团队的。它具体针对您要完成的任务，您正在进行的测试以及您的团队的目标。您不希望将其推入官方SDK。

如果你目前所读的内容没有任何意义，那么网路上有一些非常好的git学习资源。
[Git Book]（https://git-scm.com/book/en/v2）
[Interactive Git Tutorial]（https://try.github.io）

##### Guidlines for experienced GIT users.

If you are absolutely certain that you want to push the big green button above, read on.  Otherwise back _slowly away from keyboard_.

The real intent for advanced users is often to issue a pull request from the [branch](https://www.atlassian.com/git/tutorials/using-branches/git-branch) on a local fork back to master on either the same local fork or a child of the team fork and not on the parent ftctechnh/ftc_app.  See [Creating a Pull Request](https://help.github.com/articles/creating-a-pull-request-from-a-fork/).

If that is indeed the intent, then you can merge your [topic branch](https://git-scm.com/book/en/v2/Git-Branching-Branching-Workflows#Topic-Branches) into master locally by hand before pushing it up to github, or if you want a pull request for pulls between branches on the same repository because, say, you want team members to look at your software before merging into master, you can select the base fork from the dropdown on the "Open a pull request" page and select your team repo instead of ftctechnh's.

Alternatively, if you have a team repository forked from ftctechnh/ftc_app, and then team members individually fork from your team repository, then pull requests from the individual team member's forks will have the main team repository automatically selected as the base fork for the pull. And you won't inadvertently request to pull your team software into ftctechnh's repository.

The latter would be the "best" way to manage software among a large team. But as with all things git there are many options.

Pull requests that do not fall into the category above are evaluated by the FTC Technology Team on a case-by-case basis.  Please note however that the deployment model of the SDK does not support direct pulls into ftctechnh/ftc_app.  
经验丰富的GIT用户指南#####

如果您绝对确定要按上面的大绿色按钮，请继续阅读。否则返回_慢慢远离键盘。

高级用户的真正意图往往是从本地分支（https://www.atlassian.com/git/tutorials/using-branches/git-branch）发出拉取请求。相同的本地分支或小组的子支而不是父支fttechnh / ftc_app。请参阅[创建提取请求]（https://help.github.com/articles/creating-a-pull-request-from-a-fork/）。

如果这是真的意图，那么您可以手动将您的[主题分支]（https://git-scm.com/book/en/v2/Git-Branching-Branching-Workflows#Topic-Branches）合并到本地主机中在将其推送到github之前，或者如果您想要在同一存储库上的分支之间拉取请求，因为您希望团队成员在合并到主服务器之前查看您的软件，您可以从下拉列表中选择基本分支“打开一个拉动请求”页面，选择您的团队repo而不是ftctechnh的。

或者，如果您有一个团队存储库从ftctechnh / ftc_app分支，然后团队成员从您的团队存储库单独分支，则从个别团队成员的叉子中提取请求将自动选择主团队信息库作为抽取的基本分支。而且您不会无意中要求将您的团队软件拉入ftctechnh的资料库。

后者将是在一个大团队中管理软件的“最佳”方式。但是，所有的事情git有很多选择。

FTC技术团队根据具体情况评估不属于上述类别的请求。请注意，SDK的部署模型不支持直接拉入ftctechnh / ftc_app。

### Report bugs

This section guides you through filing a bug report.  The better the report the more likely it is to be root caused and fixed.  Please refrain from feature requests or software enhancements when opening new issues.  See Suggesting Enhancements below.

#### Before submitting a bug report

- Check the [forums](http://ftcforum.usfirst.org/forum.php) to see if someone else has run into the problem and whether there is an official solution that doesn't require a new SDK.

- Perform a search of current [issues](https://github.com/ftctechnh/ftc_app/issues) to see if the problem has already been reported.  If so, add a comment to the existing issue instead of creating a new one.
###报告错误

本节将引导您提交错误报告。 报告越好，根本原因和固定就越有可能。 在开启新问题时，请避免使用功能请求或软件增强功能。 请参阅下面的建议增强。

####提交错误报告之前

 - 检查[论坛]（http://ftcforum.usfirst.org/forum.php），查看其他人是否遇到问题，以及是否存在不需要新SDK的官方解决方案。

 - 搜索当前的[问题]（https://github.com/ftctechnh/ftc_app/issues），查看问题是否已被报告。 如果是这样，请向现有问题添加注释，而不是创建新问题。

#### How Do I Submit A (Good) Bug Report?

Bugs are tracked as GitHub issues. Create an issue on ftctechnh/ftc_app and provide the following information.
Explain the problem and include additional details to help maintainers reproduce the problem:

- Use a clear and descriptive title for the issue to identify the problem.

- Describe the exact steps which reproduce the problem in as many details as possible.

- Provide specific examples to demonstrate the steps.

- Describe the behavior you observed after following the steps and point out what exactly is the problem with that behavior. Explain which behavior you expected to see instead and why. If applicable, include screenshots which show you following the described steps and clearly demonstrate the problem.

- If you're reporting that the RobotController crashed, include the logfile with a stack trace of the crash.  [Example of good bug report with stack trace](https://github.com/ftctechnh/ftc_app/issues/224)

- If the problem wasn't triggered by a specific action, describe what you were doing before the problem happened and share more information using the guidelines below.
####如何提交（好）错误报告？

Bug被跟踪为GitHub问题。在ftctechnh / ftc_app上创建一个问题，并提供以下信息。
解释问题，并附加详细信息，以帮助维护者重现问题：

 - 为问题使用清晰和描述性的标题来识别问题。

 - 描述尽可能多的细节再现问题的确切步骤。

 - 提供具体的例子来说明步骤。

 - 描述遵循步骤后观察到的行为，并指出该行为的问题究竟是什么。说明你期望看到的行为，为什么。如果适用，请包括屏幕截图，显示您遵循所述步骤，并清楚地说明问题。

 - 如果您报告RobotController崩溃，请包含具有崩溃堆栈跟踪的日志文件。 [使用堆栈跟踪的错误报告示例]（https://github.com/ftctechnh/ftc_app/issues/224）

 - 如果问题未被特定操作触发，请描述您在发生问题之前所做的工作，并使用以下准则分享更多信息。

 ### Suggesting Enhancements

FIRST volunteers are awesome.  You all have great ideas and we want to hear them.  

Enhancements should be broadly applicable to a large majority of teams, should not force teams to change their workflow, and should provide real value to the mission of FIRST as it relates to engaging youth in engineering activities.

The best way to get momentum behind new features is to post a description of your idea in the forums.  Build community support for it.  The FTC Technology Team monitors the forums.  We'll hear you and if there's a large enough call for the feature it's very likely to get put on the list for a future release.


###建议增强

最早贡献想法的志愿者们，你们真棒 你们都有很好的想法，我们想听听你们的想法。

对软件的增强应广泛适用于大多数团队，不应强制团队改变其工作流程。FIRST（第一）是一种使命，它将提供真正的价值，它将鼓励更多青年们参与工程活动。

在新功能上获得动力的最佳方法是在论坛中发布您的想法的描述。 建立社区支持。 FTC技术团队会视看论坛。 如果呼声很高，我们会听到你的想法，这个功能很有可能被放在列表中，以供将来发布。