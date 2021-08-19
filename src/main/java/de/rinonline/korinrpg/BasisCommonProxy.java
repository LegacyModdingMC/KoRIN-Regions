package de.rinonline.korinrpg;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import de.rinonline.korinrpg.Helper.Gui.InventoryRINPlayer;
import de.rinonline.korinrpg.Helper.Gui.RINInventoryContainerEmpty;
import de.rinonline.korinrpg.Helper.Gui.RuneStoneGUI;
import de.rinonline.korinrpg.nbt.RINPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BasisCommonProxy {
	
	public EntityPlayer getPlayerEntity(MessageContext ctx) {

		 return ctx.getServerHandler().playerEntity;
	}
    	
}
