package ru.ragnok123.worldEdit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.nukkit.Player;
import lombok.Getter;
import ru.ragnok123.worldEdit.utils.BlocksArray;
import ru.ragnok123.worldEdit.utils.Selection;

public class WEPlayer {

    @Getter
    private Player player;

    @Getter
    private Selection selection;

    @Getter
    public BlocksArray copiedBlocks = null;
    
    @Getter
    public ArrayList<BlocksArray> undoSteps = new ArrayList<BlocksArray>();

    public static Map<String, Map<String, String>> drawing = new HashMap<>();

    public WEPlayer(Player p) {
        this.player = p;
        this.selection = new Selection();
    }
    
    public void addUndoBlocks(BlocksArray blocks) {
    	this.undoSteps.add(blocks);
    }
    
    public boolean hasSelection() {
    	return getSelection().getPosOne() != null && getSelection().getPosTwo() != null;
    }

    public boolean drawingMode(){
        return drawing.containsKey(player.getName());
    }
}
