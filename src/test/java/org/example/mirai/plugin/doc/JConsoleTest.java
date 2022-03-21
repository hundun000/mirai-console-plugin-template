package org.example.mirai.plugin.doc;

import kotlinx.coroutines.CoroutineScopeKt;
import net.mamoe.mirai.console.plugin.PluginManager;
import net.mamoe.mirai.console.terminal.MiraiConsoleImplementationTerminal;
import net.mamoe.mirai.console.terminal.MiraiConsoleTerminalLoader;
import org.example.mirai.plugin.doc.JExample;
import org.junit.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JConsoleTest {
    private static final String ASSERT_EXPECTED_TEXT = "example text";
    static MiraiConsoleImplementationTerminal consoleInstance;

    @BeforeClass
    public static void initializeConsoleAndPlugin() {
        consoleInstance = new MiraiConsoleImplementationTerminal();
        MiraiConsoleTerminalLoader.INSTANCE.startAsDaemon(consoleInstance);

        PluginManager.INSTANCE.loadPlugin(JExample.INSTANCE);
        PluginManager.INSTANCE.enablePlugin(JExample.INSTANCE);
    }

    @AfterClass
    public static void cancelConsole() {
        CoroutineScopeKt.cancel(consoleInstance, null);
    }

    /**
     * Plugins.md#访问数据目录和配置目录
     */
    @Test
    public void testResolveFile() throws IOException {
        File dataFile = JExample.INSTANCE.resolveDataFile("myDataFile.txt");
        // do something
        List<String> dataLines = Files.readAllLines(dataFile.toPath(), StandardCharsets.UTF_8);
        assertEquals(ASSERT_EXPECTED_TEXT, dataLines.get(0));

        File configFile = JExample.INSTANCE.resolveConfigFile("myConfigFile.txt");
        // do something
        List<String> configLines = Files.readAllLines(configFile.toPath(), StandardCharsets.UTF_8);
        assertEquals(ASSERT_EXPECTED_TEXT, configLines.get(0));
    }

    /**
     * Plugins.md#附录java-插件的多线程调度器---javapluginscheduler
     */
    @Test
    public void testScheduler() {
        JExample.INSTANCE.getScheduler().repeating(1 * 1000, new Runnable() {
            @Override
            public void run() {
                // do something
                JExample.INSTANCE.getLogger().info("clock task arrival");
            }
        });
        // delay for the task works
        try {
            Thread.sleep(1500);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Plugins.md#访问-jar-包内资源文件
     */
    @Test
    public void testResource() {
        String rawText = JExample.INSTANCE.getResource("myResource.yml");
        // do something
        assertEquals(ASSERT_EXPECTED_TEXT, rawText);
    }



}
