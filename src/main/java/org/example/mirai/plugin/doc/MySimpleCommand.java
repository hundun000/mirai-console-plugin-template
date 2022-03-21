package org.example.mirai.plugin.doc;

import net.mamoe.mirai.console.command.CommandSender;
import net.mamoe.mirai.console.command.SimpleCommand;
import net.mamoe.mirai.console.command.descriptor.CommandArgumentContext;
import net.mamoe.mirai.console.plugin.jvm.JvmPlugin;
import net.mamoe.mirai.contact.User;

public class MySimpleCommand extends SimpleCommand {

    public MySimpleCommand(JvmPlugin plugin) {
        super(plugin, "tell", new String[]{"私聊"}, "Tell somebody privately", plugin.getParentPermission(), CommandArgumentContext.EMPTY);
    }

    @Handler // 标记这是指令处理器  // 函数名随意
    public void handle(CommandSender sender, User target, String message) { // 后两个参数会被作为指令参数要求
        target.sendMessage(message);
    }

}
