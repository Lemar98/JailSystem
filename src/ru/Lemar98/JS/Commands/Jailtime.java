package ru.Lemar98.JS.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.Lemar98.JS.Main;
import ru.Lemar98.JS.Utils.JailManager;
import ru.Lemar98.JS.Utils.Utils;

public class Jailtime implements CommandExecutor
{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if(cmd.getName().equalsIgnoreCase("jailtime"))
		{
			Utils utils = Main.getInstance().getUtils();
			if(!(sender instanceof Player))
			{
				sender.sendMessage(utils.getColor("&c������� ����� ��������� ������ � ����!"));
				return true;
			}
			Player p = (Player)sender;
			if(args.length > 1)
			{
				utils.sendTitle(p, "&c�������� ���������", "&e/jailtime");
				return true;
			}
			JailManager manager = new JailManager();
			switch(args.length)
			{
				case 0:	
					if(!manager.playerIsJailed(p.getName()))
					{
						utils.sendTitle(p, "&c�� �� � ������", "");
						return true;
					}
					utils.sendTitle(p, "&a�� ��������", "&e"+manager.minutesAgainAmnesty(p.getName())+" &a���.");
					return true;
				case 1:
					if(!p.hasPermission("js.checkOther"))
					{
						p.sendMessage(utils.getPrefix() + utils.getColor("&c� ��� ������������ ����!"));
						return true;
					}
					Player target = Bukkit.getPlayer(args[0]);
					if(target == null)
					{
						utils.sendTitle(p, "&7"+args[0], "&c�� � ����");
						return true;
					}
					if(!manager.playerIsJailed(target.getName()))
					{
						utils.sendTitle(p, "&7"+target.getName(), "&c�� � ������");
						return true;
					}
					utils.sendTitle(p, "&a�� ��� ��������", "&e"+manager.minutesAgainAmnesty(target.getName())+" &a���.");
					return true;
			}
			return true;
		}
		return true;
	}
	
}
