package ru.ragnok123.worldEdit.commands;

import java.util.HashMap;
import java.util.Map;

import cn.nukkit.Player;
import cn.nukkit.utils.TextFormat;
import ru.ragnok123.worldEdit.WEPlayer;
import ru.ragnok123.worldEdit.WorldEdit;

public class DrawCommand extends WECommand {

    public DrawCommand() {
        super("/draw", "Draw untuk gembel kentiaw");
    }

    public void execute(Player player, WEPlayer dat, String[] args) {
        if (args.length < 2 && args[0].toLowerCase() != "off") {
            player.sendMessage(
                    WorldEdit.getPrefix() + TextFormat.RED
                            + "Use //draw <geo: [off, sphere, hsphere, cyl, hcyl] <radius>");
            return;
        }
        try {
            Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            player.sendMessage(WorldEdit.getPrefix() + TextFormat.RED + "radius must be a number!");
            return;
        }
        Map<String, String> data = new HashMap<>();
        switch (args[0].toLowerCase()) {
            case "sphere":
                data.put("geo", "sphere");
                data.put("radius", args[1]);
                WEPlayer.drawing.put(player.getName(), data);
                player.sendMessage(
                        WorldEdit.getPrefix() + TextFormat.GREEN + "Sukses pake sphere sama radius " + args[1]);
                break;
            case "hsphere":
                data.put("geo", "hsphere");
                data.put("radius", args[1]);
                WEPlayer.drawing.put(player.getName(), data);
                player.sendMessage(
                        WorldEdit.getPrefix() + TextFormat.GREEN + "Sukses pake hsphere sama radius " + args[1]);
                break;
            case "cyl":
                data.put("geo", "cyl");
                data.put("radius", args[1]);
                WEPlayer.drawing.put(player.getName(), data);
                player.sendMessage(WorldEdit.getPrefix() + TextFormat.GREEN + "Sukses pake cyl sama radius " + args[1]);
                break;
            case "hcyl":
                data.put("geo", "hcyl");
                data.put("radius", args[1]);
                WEPlayer.drawing.put(player.getName(), data);
                player.sendMessage(
                        WorldEdit.getPrefix() + TextFormat.GREEN + "Sukses pake hcyl sama radius " + args[1]);
                break;
            case "off":
                WEPlayer.drawing.remove(player.getName());
                player.sendMessage(WorldEdit.getPrefix() + TextFormat.GREEN + "Sukses off in drawing hehe");
                break;
            default:
                player.sendMessage(
                        WorldEdit.getPrefix() + TextFormat.RED
                                + "Use //draw <geo: [off, sphere, hsphere, cyl, hcyl] <radius>");
                break;
        }
    }
}
