package de.rinonline.korinrpg;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import cpw.mods.fml.common.network.IGuiHandler;
import de.rinonline.korinrpg.Helper.Gui.InventoryRINPlayer;
import de.rinonline.korinrpg.Helper.Gui.RINInventoryContainerEmpty;
import de.rinonline.korinrpg.Helper.Gui.RuneStoneGUI;

public class GuiHandler implements IGuiHandler {

    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == 55) return new RINInventoryContainerEmpty(player, player.inventory, new InventoryRINPlayer());
        return null;
    }

    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == 55) return new RuneStoneGUI(player, player.inventory, new InventoryRINPlayer());
        return null;
    }

}
