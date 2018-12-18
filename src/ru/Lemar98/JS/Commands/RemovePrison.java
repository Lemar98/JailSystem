package ru.Lemar98.JS.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.Lemar98.JS.Main;
import ru.Lemar98.JS.Utils.JailManager;
import ru.Lemar98.JS.Utils.Utils;

public class RemovePrison implements CommandExecutor
{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if(cmd.getName().equalsIgnoreCase("removeprison"))
		{
			Utils utils = Main.getInstance().getUtils();
			if(!(sender instanceof Player))
			{
				sender.sendMessage(utils.getPrefix() + utils.getColor("&c������ ����� ������� ������ � ����!"));
				return true;
			}
			Player p = (Player)sender;
			if(!p.hasPermission("js.remove") || !p.hasPermission("js.*"))
			{
				p.sendMessage(utils.getPrefix() + utils.getColor("&c� ��� ������������ ����!"));
				return true;
			}
			if(args.length != 1)
			{
				p.sendMessage(utils.getPrefix() + utils.getColor("&c����� ������� ������ �������� ������, �� ����� � �� �����!"));
				return true;
			}
			if(utils.checkStringNotNumeric(args[0]))
			{
				p.sendMessage(utils.getPrefix() + utils.getColor("&c�������� ������ ���� ���������!"));
				return true;
			}
			String name = args[0];
			JailManager manager = new JailManager();
			if(!manager.jailIsExist(name))
			{
				p.sendMessage(utils.getPrefix() + utils.getColor("&c������ c ������ &7"+name+" &c�� ����������!"));
				return true;
			}
			manager.removeJail(p, name);
			p.sendMessage(utils.getPrefix() + utils.getColor("&a������ ��� ��������� &7"+name+" &a���� �������!"));
			return true;
		}
		return true;
	}

}
