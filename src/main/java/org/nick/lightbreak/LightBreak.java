package org.nick.lightbreak;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.nick.lightbreak.command.LightCommand;
import org.nick.lightbreak.event.LightBlockBreak;
import org.nick.lightbreak.event.TabHandler;

import java.io.File;
import java.util.Objects;

public final class LightBreak extends JavaPlugin {
    public static JavaPlugin instance;
    public static FileConfiguration messages_config;
    public static FileConfiguration configuration;
    public static File messages;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        instance=this;
        configuration=getConfig();
        this.saveResource("messages.yml",false);
        messages = new File(LightBreak.instance.getDataFolder(), "messages.yml");
        messages_config = YamlConfiguration.loadConfiguration(messages);
        Bukkit.getPluginManager().registerEvents(new LightBlockBreak(), this);
        Bukkit.getPluginCommand("lightbreak").setExecutor(new LightCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("lightbreak")).setTabCompleter(new TabHandler());
        this.getLogger().info("[LightBreak]已经成功加载Nick防止破坏小插件！");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.getLogger().info("[LightBreak]已经成功卸载Nick防止破坏小插件！");
    }
}
