package ru.ragnok123.worldEdit.utils;

import cn.nukkit.block.Block;
import cn.nukkit.item.Item;
import cn.nukkit.math.Vector3;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Utils {

	public static Block fromString(String str) {
		String[] b = str.trim().replace(' ', '_').replace("minecraft:", "").split(":");

		int id = 0;
		int meta = 0;

		Pattern integerPattern = Pattern.compile("^[1-9]\\d*$");
		if (integerPattern.matcher(b[0]).matches()) {
			id = Integer.valueOf(b[0]);
		} else {
			try {
				id = Item.class.getField(b[0].toUpperCase()).getInt(null);
			} catch (Exception ignore) {
			}
		}

		id = id & 0xFFFF;
		if (b.length != 1)
			meta = Integer.valueOf(b[1]) & 0xFFFF;

		return Block.get(id, meta);
	}

	public static boolean insideArea(Vector3 vector, Vector3 pos1, Vector3 pos2) {
		if ((vector.getX() > pos1.getX()) && (vector.getX() < pos2.getX())) {
			if ((vector.getY() > pos1.getY()) && (vector.getY() < pos2.getY())) {
				if ((vector.getZ() > pos1.getZ()) && (vector.getZ() < pos2.getZ())) {
					return true;
				}
			}
		}
		return false;
	}

}
