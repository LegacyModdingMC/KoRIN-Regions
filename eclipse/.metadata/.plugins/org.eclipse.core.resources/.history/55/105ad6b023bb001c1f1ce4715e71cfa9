package de.rinonline.korinrpg.Network;

import java.io.IOException;

import cpw.mods.fml.relauncher.Side;
import de.rinonline.korinrpg.Helper.Gui.InterfaceGUI;
import de.rinonline.korinrpg.nbt.RINPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;

public class sendTextpopRegions extends AbstractMessage.AbstractClientMessage<sendTextpopRegions> {
  private String data2;
  
  public sendTextpopRegions() {}
  
  public sendTextpopRegions(String text) {
    this.data2 = text;
  }
  
  protected void read(PacketBuffer buffer) throws IOException {
    int length = buffer.readInt();
    this.data2 = buffer.readStringFromBuffer(length);
  }
  
  protected void write(PacketBuffer buffer) throws IOException {
    buffer.writeInt(this.data2.length());
    buffer.writeStringToBuffer(this.data2);
  }
  
  public void process(EntityPlayer player, Side side) {
	  if(side.isServer()) {
	  if(!RINPlayer.get(player).DiscoverdBiomeList.contains(this.data2)) {
	      (RINPlayer.get(player)).DiscoverdBiomeList.add(this.data2); 
	  }
      PacketDispatcher.sendTo(new SyncPlayerPropsRegions(player),(EntityPlayerMP) player); 
	  }else {
			InterfaceGUI.playtime = 200;
	  	}
	  }
}
