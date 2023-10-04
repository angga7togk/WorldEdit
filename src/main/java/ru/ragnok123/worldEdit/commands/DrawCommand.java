package ru.ragnok123.worldEdit.commands;

import java.util.HashMap;
import java.util.Map;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.utils.TextFormat;
import ru.ragnok123.worldEdit.WEPlayer;
import ru.ragnok123.worldEdit.WorldEdit;

public class DrawCommand extends Command{

    public DrawCommand() {
        super("/draw", "Draw untuk gembel kentiaw");
    }
    
    public boolean execute(Player player, WEPlayer dat, String[] args){
        if(args.length < 2){
            player.sendMessage(WorldEdit.getPrefix() + TextFormat.RED + "Use //draw <geo: [off, sphere, hsphere] <radius>");
            return false;
        }
        try {
            Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            player.sendMessage(WorldEdit.getPrefix() + TextFormat.RED + "radius must be a number!");
            return false;
        }
        Map<String, String> data = new HashMap<>();
        switch(args[0].toLowerCase()){
            case "sphere":
                data.put("geo", "sphere");
                data.put("radius", args[1]);
                dat.drawing.drawing.put(player, data);
                player.sendMessage(WorldEdit.getPrefix() + TextFormat.GREEN + "Sukses pake sphere sama radius " + args[1]);
                break;
            case "hsphere":
                data.put("geo", "hsphere");
                data.put("radius", args[1]);
                dat.drawing.drawing.put(player, data);
                player.sendMessage(WorldEdit.getPrefix() + TextFormat.GREEN + "Sukses pake hsphere sama radius " + args[1]);
                break;
            case "off":
                dat.drawing.drawing.remove(player);
                player.sendMessage(WorldEdit.getPrefix() + TextFormat.GREEN + "Sukses off in drawing hehe");
                break;
            default:
                player.sendMessage(WorldEdit.getPrefix() + TextFormat.RED + "Use //draw <geo: [off, sphere, hsphere] <radius>");
                break;
        }

        return true;
    }
}
