package org.example.mirai.plugin.doc;

import net.mamoe.mirai.console.command.CommandSender;
import net.mamoe.mirai.console.command.CompositeCommand;
import net.mamoe.mirai.console.command.descriptor.CommandArgumentContext;
import net.mamoe.mirai.console.plugin.jvm.JvmPlugin;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.message.data.Image;

public class MyCompositeCommand extends CompositeCommand {
    JvmPlugin plugin;
    public MyCompositeCommand(JvmPlugin plugin) {
        // "manage" 是主指令名, 还有更多参数可填, 此处忽略
        super(plugin, "manage", new String[]{}, "示例指令", plugin.getParentPermission(), CommandArgumentContext.EMPTY);
        this.plugin = plugin;
    }

    // [参数智能解析]
    //
    // 在控制台执行 "/manage <群号>.<群员> <持续时间>",
    // 或在聊天群内发送 "/manage <@一个群员> <持续时间>",
    // 或在聊天群内发送 "/manage <目标群员的群名> <持续时间>",
    // 或在聊天群内发送 "/manage <目标群员的账号> <持续时间>"
    // 时调用这个函数
    @SubCommand // 表示这是一个子指令，使用函数名作为子指令名称
    public void mute(CommandSender sender, Member target, int duration) { // 通过 /manage mute <target> <duration> 调用
        sender.sendMessage("/manage mute 被调用了, 参数为: " + target.toString() + ", " + duration);

        String result;
        try {
            target.mute(duration);
            result = "成功";
        } catch (Exception e) {
            result = "失败，" + e.getMessage();
        }

        sender.sendMessage("结果: " + result);
    }

    @SubCommand
    public void foo(CommandSender sender) {
        // 使用 ConsoleCommandSender 作为接收者，表示指令只能由控制台执行。
        // 当用户尝试在聊天环境执行时将会收到错误提示。
    }

    @SubCommand(value = {"list", "查看列表"}) // 可以设置多个子指令名。此时函数名会被忽略。
    public void ignoredFunctionName(CommandSender sender) { // 执行 "/manage list" 时调用这个函数
        sender.sendMessage("/manage list 被调用了");
    }

    // 支持 Image 类型, 需在聊天中执行此指令.
    @SubCommand
    public void test(CommandSender sender, Image image) { // 执行 "/manage test <一张图片>" 时调用这个函数
        // 由于 Image 类型消息只可能在聊天环境，可以直接使用 UserCommandSender。

        sender.sendMessage("/manage image 被调用了, 图片是 " + image.getImageId());
    }
}
