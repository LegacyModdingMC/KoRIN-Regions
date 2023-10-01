package de.rinonline.korinrpg;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.rinonline.korinrpg.Network.PacketDispatcher;
import de.rinonline.korinrpg.Network.SyncPlayerPropsRegions;

public class ItemWanderMap extends Item {

    public ItemWanderMap() {
        setMaxStackSize(1);
        setCreativeTab(CreativeTabs.tabTools);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon("korinregions:runestone");
    }

    public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
        if (!p_77659_2_.isRemote) {
            PacketDispatcher.sendTo((IMessage) new SyncPlayerPropsRegions(p_77659_3_), (EntityPlayerMP) p_77659_3_);
            p_77659_3_.openGui(
                RINMAIN.instance,
                55,
                p_77659_3_.worldObj,
                (int) p_77659_3_.posX,
                (int) p_77659_3_.posY,
                (int) p_77659_3_.posZ);
        }
        return p_77659_1_;
    }
}
