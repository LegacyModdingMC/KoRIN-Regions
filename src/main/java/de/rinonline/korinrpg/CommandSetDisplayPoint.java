package de.rinonline.korinrpg;

import java.util.ArrayList;
import java.util.List;

import de.rinonline.korinrpg.Network.PacketDispatcher;
import de.rinonline.korinrpg.Network.sendTextPacket;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;

public class CommandSetDisplayPoint implements ICommand {

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCommandName() {
		return "adddisplayPosition";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		return "/adddisplayPosition <PosX> <PosY> <PosZ> <Radius> <Name> Displays a certain Text for the Player at a certain Position";
	}

	@Override
	public List getCommandAliases() {
		List<String> commandAliases = new ArrayList();
		commandAliases.add("adddisplayPosition");
		return commandAliases;
	}
	@Override
	public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {
        if(p_71515_2_[0] != null && p_71515_2_[1] != null && p_71515_2_[2] != null && p_71515_2_[3] != null && p_71515_2_[4] != null) {
            if(isNumeric(p_71515_2_[1]) && isNumeric(p_71515_2_[2])&& isNumeric(p_71515_2_[3])) {
            int x =Integer.parseInt(p_71515_2_[0]);
            int y =Integer.parseInt(p_71515_2_[1]);
            int z =Integer.parseInt(p_71515_2_[2]);
            int radius = Integer.parseInt(p_71515_2_[3]);
            String Name = p_71515_2_[4];

            int[] templist = new int[] {x,y,z,radius};
                ConfigurationMoD.addPoint(templist,Name);
            }
        } else {
            p_71515_1_.addChatMessage(new ChatComponentText("Format Error: /adddisplayPosition <PosX> <PosY> <PosZ> <Radius> <Name>"));
        }
	}

    private static boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return sender.canCommandSenderUseCommand(2, this.getCommandName());
	}

	@Override
	public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
		// TODO Auto-generated method stub
		return false;
	}

}
