package org.nick.lightbreak.command;

import javafx.scene.effect.Light;
import jdk.nashorn.internal.ir.Block;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.nick.lightbreak.LightBreak;

import java.io.Console;
import java.io.File;
import java.util.List;

public class LightCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
       FileConfiguration messages_cofig= LightBreak.messages_config;
       FileConfiguration configuration=LightBreak.configuration;
       File messages=LightBreak.messages;
       Player player = (Player) sender;
        if (args[0].equals("reload")) {
            if (sender.hasPermission("lightbreak.reload")) {
                LightBreak.instance.reloadConfig();
                LightBreak.messages=new File(LightBreak.instance.getDataFolder(), "messages.yml");
                LightBreak.messages_config=YamlConfiguration.loadConfiguration(messages);
                player.sendRawMessage(messages_cofig.getString("pluginname")+messages_cofig.getString("reload"));
            }
        }
        if (args[0].equals("on")) {
            if (sender.hasPermission("lightbreak.enable")) {
                configuration.set("enable", "true");
                player.sendRawMessage(messages_cofig.getString("pluginname")+messages_cofig.getString("enable"));
                LightBreak.instance.saveConfig();
                LightBreak.instance.reloadConfig();
            }

        }
        if (args[0].equals("off")) {
            if (sender.hasPermission("lightbreak.enable")) {
                configuration.set("enable", "false");
                player.sendRawMessage(messages_cofig.getString("pluginname")+messages_cofig.getString("disable"));
                LightBreak.instance.saveConfig();
                LightBreak.instance.reloadConfig();

            }
        }
        if (args[0].equals("add")) {
            if ( sender.hasPermission("lightbreak.add")) {
                ItemStack item = player.getInventory().getItemInMainHand();
                String block_name = item.getType().name().toLowerCase();
                if (!block_name.toLowerCase().equals("air")) {
                    LightBreak.instance.getLogger().info(block_name);
                    List<String> ban_blocks = (List<String>)configuration.getList("blocks");
                    if (ban_blocks.contains(block_name)) {
                        player.sendRawMessage(messages_cofig.getString("pluginname")+messages_cofig.getString("exist"));
                    } else {
                        ban_blocks.add(block_name);
                        configuration.set("blocks", ban_blocks);
                        LightBreak.instance.saveConfig();
                        player.sendRawMessage(messages_cofig.getString("pluginname")+"§2"+block_name+messages_cofig.getString("successadd"));
                    }
                } else {
                    player.sendRawMessage(messages_cofig.getString("pluginname")+messages_cofig.getString("noneblock"));
                }
            }
        }
        if(args[0].equals("del")){
            if ( sender.hasPermission("lightbreak.del")) {
                ItemStack item = player.getInventory().getItemInMainHand();
                String block_name = item.getType().name().toLowerCase();
                if (!block_name.toLowerCase().equals("air")) {
                    LightBreak.instance.getLogger().info(block_name);
                    player.sendMessage(ChatColor.BLUE + block_name);
                    List<String> ban_blocks = (List<String>) configuration.getList("blocks");
                    if (ban_blocks.contains(block_name)) {
                        ban_blocks.remove(block_name);
                        configuration.set("blocks", ban_blocks);
                        LightBreak.instance.saveConfig();
                        player.sendRawMessage(messages_cofig.getString("pluginname")+"§2"+block_name+messages_cofig.getString("successdel"));
                    } else {
                        player.sendRawMessage(messages_cofig.getString("pluginname")+messages_cofig.getString("notexist"));
                    }
                } else {
                    player.sendRawMessage(messages_cofig.getString("pluginname")+messages_cofig.getString("noneblock"));
                }
            }

        }
        if(args[0].equals("list")){
            if ( sender.hasPermission("lightbreak.list")) {
                List<String> ban_blocks = (List<String>) LightBreak.instance.getConfig().getList("blocks");
                player.sendRawMessage(messages_cofig.getString("pluginname")+messages_cofig.getString("list"));
                for(String block :ban_blocks){
                    player.sendMessage(ChatColor.GREEN+block);
                }
            }
        }
        return false;
    }
}