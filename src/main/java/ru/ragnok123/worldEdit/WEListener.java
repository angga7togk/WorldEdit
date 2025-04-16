package ru.ragnok123.worldEdit;

import java.util.Map;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.item.Item;
import cn.nukkit.level.Location;
import cn.nukkit.level.Position;
import cn.nukkit.utils.TextFormat;
import ru.ragnok123.worldEdit.utils.WorldUtils;

public class WEListener implements Listener {

	String name = TextFormat.BOLD + "" + TextFormat.YELLOW + "MAGIC AXE " + TextFormat.RESET + TextFormat.RED
			+ "(dangerous)";

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		Block b = e.getBlock();
		Item item = p.getInventory().getItemInHand();

		if (p.hasPermission("we.command") && item.getId() == Item.WOODEN_AXE && item.getCustomName().equals(name)) {
			WEPlayer data = WorldEdit.get().getWEPlayer(p);

			data.getSelection().setPosTwo(b.getLocation().clone());
			p.sendMessage(
					WorldEdit.getPrefix() + TextFormat.GREEN + "Selected the second position at " + TextFormat.AQUA + b.x
							+ TextFormat.GREEN + ", " + TextFormat.AQUA + b.y + TextFormat.GREEN + ", " + TextFormat.AQUA + b.z
							+ TextFormat.GREEN);
			e.setCancelled();
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Block b = e.getBlock();
		Item item = p.getInventory().getItemInHand();

		if (p.hasPermission("we.command")) {
			WEPlayer data = WorldEdit.get().getWEPlayer(p);

			switch (e.getAction()) {
				// case LEFT_CLICK_BLOCK:
				// 	if (item.getId() == Item.WOODEN_AXE && item.getCustomName().equals(name)) {
				// 		data.getSelection().setPosTwo(b.getLocation().clone());
				// 		p.sendMessage(
				// 				WorldEdit.getPrefix() + TextFormat.GREEN + "Selected the second position at " + TextFormat.AQUA + b.x
				// 						+ TextFormat.GREEN + ", " + TextFormat.AQUA + b.y + TextFormat.GREEN + ", " + TextFormat.AQUA
				// 						+ b.z + TextFormat.GREEN);

				// 	}
				// 	break;
				case RIGHT_CLICK_BLOCK:
					if (item.getId() == Item.WOODEN_AXE && item.getCustomName().equals(name)) {
						data.getSelection().setPosOne(b.getLocation().clone());
						p.sendMessage(
								WorldEdit.getPrefix() + TextFormat.GREEN + "Selected the first position at " + TextFormat.AQUA + b.x
										+ TextFormat.GREEN + ", " + TextFormat.AQUA + b.y + TextFormat.GREEN + ", " + TextFormat.AQUA
										+ b.z + TextFormat.GREEN);
					}
					break;
				case RIGHT_CLICK_AIR:
					if (data.drawingMode()) {
						Map<String, String> dataDraw = WEPlayer.drawing.get(p.getName());
						String geo = dataDraw.get("geo");
						String radius = dataDraw.get("radius");
						Block block = item.getBlock();

						Position blockPosition = p.getTargetBlock(16);
						if (blockPosition == null) {
							return;
						}

						Location look = blockPosition.getLocation();
						if (look == null) {
							return;
						}

						switch (geo) {
							case "sphere":
								WorldUtils.sphere(data, new Position(look.x, look.y, look.z, p.getLevel()), Integer.valueOf(radius),
										block);
								break;
							case "hsphere":
								WorldUtils.hsphere(data, new Position(look.x, look.y, look.z, p.getLevel()), Integer.valueOf(radius),
										block);
								break;
							case "cyl":
								WorldUtils.cyl(data, new Position(look.x, look.y, look.z, p.getLevel()), Integer.valueOf(radius),
										block);
								break;
							case "hcyl":
								WorldUtils.hcyl(data, new Position(look.x, look.y, look.z, p.getLevel()), Integer.valueOf(radius),				
										block);
								break;

						}
					}
					break;
				default:
					break;
			}
		}
	}

}