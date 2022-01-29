package org.nick.lightbreak.event;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.*;

public class TabHandler implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!(sender instanceof Player)) {
            // 控制台注册个鬼
            return null;
        }
        if (args.length >=2) {
            // 前两个参数已经输入完成，不继续提示
            return null;
        }
        if(args.length==1){
            List<String> tablist=new ArrayList<>();
            tablist.add("on");
            tablist.add("off");
            tablist.add("del");
            tablist.add("del");
            tablist.add("reload");
            tablist.add("list");
           return tablist;
        }
        return null;
    }
}
