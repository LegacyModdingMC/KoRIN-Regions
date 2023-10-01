package de.rinonline.korinrpg;

import net.minecraft.entity.player.EntityPlayer;

import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class BasisCommonProxy {

    public EntityPlayer getPlayerEntity(MessageContext ctx) {

        return ctx.getServerHandler().playerEntity;
    }

}
