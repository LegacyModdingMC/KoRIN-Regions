package de.rinonline.korinrpg.Helper.Gui;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class RINInventoryContainerEmpty extends Container

{
private Minecraft mc;

public RINInventoryContainerEmpty(EntityPlayer player, InventoryPlayer inventoryPlayer, InventoryRINPlayer inventoryCustom)

{
int i;
}


/**

 * This should always return true, since custom inventory can be accessed from anywhere

 */

@Override
public boolean canInteractWith(EntityPlayer player)

{

return true;

}
}