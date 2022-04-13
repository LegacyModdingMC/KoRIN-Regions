package de.rinonline.korinrpg.Network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketDispatcher {
  private static byte packetId = 0;
  
  private static final SimpleNetworkWrapper dispatcher = NetworkRegistry.INSTANCE.newSimpleChannel("korinregions");
  
  public static final void registerPackets() {
    registerMessage(sendTextPacket.class, sendTextPacket.class, Side.CLIENT);
    registerMessage(SyncPlayerPropsRegions.class, SyncPlayerPropsRegions.class, Side.CLIENT);
    registerMessage(SyncPlayerPropsRegions.class, SyncPlayerPropsRegions.class, Side.SERVER);
    registerMessage(sendVillagePacket.class, sendVillagePacket.class, Side.CLIENT);
    registerMessage(PacketGuiOpenerRegions.class, PacketGuiOpenerRegions.class, Side.SERVER);
    registerMessage(PacketGuiTeleport.class, PacketGuiTeleport.class, Side.SERVER);
    registerMessage(sendTextpopRegions.class, sendTextpopRegions.class, Side.CLIENT);
    registerMessage(sendTextpopRegions.class, sendTextpopRegions.class, Side.SERVER);
  }
  
  private static final void registerMessage(Class handlerClass, Class messageClass, Side side) {
    packetId = (byte)(packetId + 1);
    dispatcher.registerMessage(handlerClass, messageClass, packetId, side);
  }
  
  private static final <T extends AbstractMessage<T> & cpw.mods.fml.common.network.simpleimpl.IMessageHandler<T, IMessage>> void registerMessage(Class<T> clazz) {
    if (AbstractMessage.AbstractClientMessage.class.isAssignableFrom(clazz)) {
      packetId = (byte)(packetId + 1);
      dispatcher.registerMessage(clazz, clazz, packetId, Side.CLIENT);
    } else if (AbstractMessage.AbstractServerMessage.class.isAssignableFrom(clazz)) {
      packetId = (byte)(packetId + 1);
      dispatcher.registerMessage(clazz, clazz, packetId, Side.SERVER);
    } else {
      dispatcher.registerMessage(clazz, clazz, packetId, Side.CLIENT);
      packetId = (byte)(packetId + 1);
      dispatcher.registerMessage(clazz, clazz, packetId, Side.SERVER);
    } 
  }
  
  public static final void sendTo(IMessage message, EntityPlayerMP player) {
    dispatcher.sendTo(message, player);
  }
  
  public static final void sendToAllAround(IMessage message, NetworkRegistry.TargetPoint point) {
    dispatcher.sendToAllAround(message, point);
  }
  
  public static final void sendToAllAround(IMessage message, int dimension, double x, double y, double z, double range) {
    sendToAllAround(message, new NetworkRegistry.TargetPoint(dimension, x, y, z, range));
  }
  
  public static final void sendToAllAround(IMessage message, EntityPlayer player, double range) {
    sendToAllAround(message, player.worldObj.provider.dimensionId, player.posX, player.posY, player.posZ, range);
  }
  
  public static final void sendToDimension(IMessage message, int dimensionId) {
    dispatcher.sendToDimension(message, dimensionId);
  }
  
  public static final void sendToServer(IMessage message) {
    dispatcher.sendToServer(message);
  }
}
