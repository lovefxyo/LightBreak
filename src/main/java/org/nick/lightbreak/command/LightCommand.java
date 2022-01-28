package org.nick.lightbreak.command;

import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.nick.lightbreak.LightBreak;

public class LightCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args[0].equals("reload")) {
            if (sender.isOp() || sender.hasPermission("lightbreak.reload")) {
                LightBreak.instance.reloadConfig();
                sender.sendMessage(Color.GREEN + "[LightBreak]" + "已经重载");
            }
        }
        if(args[0].equals("enable")){
            if (sender.isOp() || sender.hasPermission("lightbreak.enable")) {
                String condition=LightBreak.instance.getConfig().getString("enable");
                if(condition.equals("true")){
                    LightBreak.instance.getConfig().set("enable","false");
                    sender.sendMessage( "[LightBreak]" + "已经关闭");
                    LightBreak.instance.saveConfig();
                    LightBreak.instance.reloadConfig();
                }else{
                    LightBreak.instance.getConfig().set("enable","true");
                    sender.sendMessage("[LightBreak]" +"已经开启");
                    LightBreak.instance.saveConfig();
                    LightBreak.instance.reloadConfig();
                }

            }
        }
        return false;
    }
}
