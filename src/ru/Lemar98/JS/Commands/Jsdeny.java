package ru.Lemar98.JS.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.Lemar98.JS.Main;
import ru.Lemar98.JS.Utils.Utils;

public class Jsdeny implements CommandExecutor
{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if(cmd.getName().equalsIgnoreCase("jsdeny"))
		{
			Utils utils = Main.getInstance().getUtils();
			if(!(sender instanceof Player))
			{
				sender.sendMessage(utils.getColor("&c��� ������� ����� ��������� ������ �� ����!"));
				return true;
			}
			Player p = (Player)sender;
			if(args.length > 0)
			{
				utils.sendTitle(p, "&c�������� ���������", "&e/jsdeny");
				return true;
			}
			if(!utils.getOffer().containsKey(p))
			{
				utils.sendTitle(p, "&c� ��� ���", "&c�������� �����������");
				return true;
			}
			Player offerOwner = utils.getOffer().get(p);
			if(offerOwner == null)
			{
				utils.sendTitle(p, "&c������� ����� �� ����", null);
				utils.getOffer().remove(p);
				return true;
			}
			utils.sendTitle(offerOwner, "&7"+p.getName(), "&c��������� �� ����� �����");
			p.sendMessage(utils.getPrefix() + utils.getColor("&c�� ���������� �� ����� �������� &7"+offerOwner.getName()));
			utils.getOffer().remove(p);
			return true;
		}
		return true;
	}
}
