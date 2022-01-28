package org.nick.lightbreak;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.nick.lightbreak.command.LightCommand;
import org.nick.lightbreak.event.LightBlockBreak;

public final class LightBreak extends JavaPlugin {
    public static JavaPlugin instance;
    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        instance=this;
        FileConfiguration configuration=getConfig();
        Bukkit.getPluginManager().registerEvents(new LightBlockBreak(), this);
        Bukkit.getPluginCommand("lightbreak").setExecutor(new LightCommand());
        this.getLogger().info("[LightBreak]已经成功加载Nick防止破坏小插件！");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.getLogger().info("[LightBreak]已经成功卸载Nick防止破坏小插件！");
    }
}
