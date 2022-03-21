package org.example.mirai.plugin.doc;

import net.mamoe.mirai.console.command.CommandManager;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;

import java.io.File;

public final class JExample extends JavaPlugin {
    public static final JExample INSTANCE = new JExample(); // 可以像 Kotlin 一样静态初始化单例
    private JExample() {
        super(new JvmPluginDescriptionBuilder(
                        "org.example.doc-plugin", // name
                        "1.0.0" // version
                )
                        // .author("...")
                        // .info("...")
                        .build()
        );
    }

    @Override
    public void onEnable() {
        super.onEnable();

        CommandManager.INSTANCE.registerCommand(new MySimpleCommand(this), false);
    }
}
