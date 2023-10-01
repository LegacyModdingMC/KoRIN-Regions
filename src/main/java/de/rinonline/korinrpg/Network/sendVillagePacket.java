package de.rinonline.korinrpg.Network;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ChunkCoordinates;

import cpw.mods.fml.relauncher.Side;
import de.rinonline.korinrpg.Helper.Gui.InterfaceGUI;

public class sendVillagePacket extends AbstractMessage.AbstractClientMessage<sendVillagePacket> {

    private String data2;

    private int x;

    private int y;

    private int z;

    private int rad;

    public sendVillagePacket() {}

    public sendVillagePacket(String text, ChunkCoordinates cords, int i) {
        this.data2 = text;
        this.x = cords.posX;
        this.y = cords.posY;
        this.z = cords.posZ;
        this.rad = i;
    }

    protected void read(PacketBuffer buffer) throws IOException {
        int length = buffer.readInt();
        this.data2 = buffer.readStringFromBuffer(length);
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
        this.rad = buffer.readInt();
    }

    protected void write(PacketBuffer buffer) throws IOException {
        buffer.writeInt(this.data2.length());
        buffer.writeStringToBuffer(this.data2);
        buffer.writeInt(this.x);
        buffer.writeInt(this.y);
        buffer.writeInt(this.z);
        buffer.writeInt(this.rad);
    }

    public void process(EntityPlayer player, Side side) {
        InterfaceGUI.setDISPLAYSTRINGVILLAGE(this.data2, this.x, this.y, this.z, this.rad);
    }
}
