package de.rinonline.korinrpg.Network;

import cpw.mods.fml.relauncher.Side;
import de.rinonline.korinrpg.RINMAIN;
import java.io.IOException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;

public class PacketGuiOpener extends AbstractMessage.AbstractServerMessage<PacketGuiOpener> {
  public int x;
  
  public PacketGuiOpener() {}
  
  public PacketGuiOpener(int ID) {
    this.x = ID;
  }
  
  protected void read(PacketBuffer buffer) throws IOException {
    this.x = buffer.readInt();
  }
  
  protected void write(PacketBuffer buffer) throws IOException {
    buffer.writeInt(this.x);
  }
  
  public void process(EntityPlayer player, Side side) {
    player.openGui(RINMAIN.instance, this.x, player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);
  }
}
