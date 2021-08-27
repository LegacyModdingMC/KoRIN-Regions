package de.rinonline.korinrpg.Network;

import cpw.mods.fml.relauncher.Side;
import de.rinonline.korinrpg.ConfigurationMoD;
import de.rinonline.korinrpg.ItemRegistry;
import de.rinonline.korinrpg.nbt.RINPlayer;
import java.io.IOException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ChunkCoordinates;

public class PacketGuiTeleport extends AbstractMessage.AbstractServerMessage<PacketGuiTeleport> {
  public int x;
  
  public PacketGuiTeleport() {}
  
  public PacketGuiTeleport(int ID) {
    this.x = ID;
  }
  
  protected void read(PacketBuffer buffer) throws IOException {
    this.x = buffer.readInt();
  }
  
  protected void write(PacketBuffer buffer) throws IOException {
    buffer.writeInt(this.x);
  }
  
  public void process(EntityPlayer player, Side side) {
    if (ConfigurationMoD.canTeleportToVillages) {
      RINPlayer rins = RINPlayer.get(player);
      if (rins.getDiscoverdNameList().size() >= this.x && 
        player.getHeldItem().getItem() == ItemRegistry.Runestone) {
        if (ConfigurationMoD.MapDisapearAfterTeleport)
          player.setCurrentItemOrArmor(0, null); 
        ChunkCoordinates cords = rins.DiscoverdCordsList.get(this.x);
        player.setPositionAndUpdate(cords.posX, cords.posY, cords.posZ);
      } 
    } 
  }
}
