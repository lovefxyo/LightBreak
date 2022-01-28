package org.nick.lightbreak.event;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.nick.lightbreak.LightBreak;
import java.util.List;
import java.util.Locale;

public class LightBlockBreak implements Listener {
    @EventHandler
    public void DontBreak(BlockBreakEvent b){
        if(LightBreak.instance.getConfig().getString("enable").equals("true")){
        boolean flag=false;
        Player player=b.getPlayer();
        Block block=b.getBlock();
        List<String> validblocks= (List<String>) LightBreak.instance.getConfig().getList("blocks");
        for(String valid: validblocks){
            if(block.getBlockData().getAsString().toLowerCase().contains(valid.toLowerCase())){
                flag=true;
            }
        }
        if((!player.isOp())||(!player.hasPermission("lightbreak.canbreak"))){
        if((!flag)){
            b.setCancelled(true);
            player.sendMessage("你不可以破坏这个方块！");
        }}
     }
    }
}
