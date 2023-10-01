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

public class CommandDisplayText implements ICommand {

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCommandName() {
		return "displaytext";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		return "/displaytext <playername> <text> Displays a certain Text for the Player";
	}

	@Override
	public List getCommandAliases() {
		List<String> commandAliases = new ArrayList();
		commandAliases.add("displaytext");
		return commandAliases;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] p_71515_2_) {
		EntityPlayer p = sender.getEntityWorld().getPlayerEntityByName(p_71515_2_[0]);
		if(p != null) {
			if(p_71515_2_[1] != null) {
				PacketDispatcher.sendTo(new sendTextPacket(p_71515_2_[1]), (EntityPlayerMP) p);
            } else {
                sender.addChatMessage(new ChatComponentText("Format Error: /displaytext getPlayerEntityByName"));
            }
		} else {
			sender.addChatMessage(new ChatComponentText("Format Error: /displaytext getPlayerEntityByName"));
		}
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
