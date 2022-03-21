package org.example.mirai.plugin.doc;

import net.mamoe.mirai.console.plugin.PluginManager;
import net.mamoe.mirai.console.terminal.MiraiConsoleImplementationTerminal;
import net.mamoe.mirai.console.terminal.MiraiConsoleTerminalLoader;

public class RunMirai {

    public static void main(String[] args) {
        MiraiConsoleImplementationTerminal consoleInstance = new MiraiConsoleImplementationTerminal();
        MiraiConsoleTerminalLoader.INSTANCE.startAsDaemon(consoleInstance);

        PluginManager.INSTANCE.loadPlugin(JExample.INSTANCE);
        PluginManager.INSTANCE.enablePlugin(JExample.INSTANCE);
    }
}
