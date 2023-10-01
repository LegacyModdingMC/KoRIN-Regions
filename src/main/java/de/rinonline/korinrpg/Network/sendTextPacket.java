package de.rinonline.korinrpg.Network;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;

import cpw.mods.fml.relauncher.Side;
import de.rinonline.korinrpg.Helper.Gui.InterfaceGUI;

public class sendTextPacket extends AbstractMessage.AbstractClientMessage<sendTextPacket> {

    private String data2;

    public sendTextPacket() {}

    public sendTextPacket(String text) {
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
        InterfaceGUI.setDISPLAYSTRING(this.data2);
    }
}
