package ru.Lemar98.JS.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import ru.Lemar98.JS.Main;
import ru.Lemar98.JS.Utils.Utils;

public class Jailhelp implements CommandExecutor
{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if(cmd.getName().equalsIgnoreCase("jailhelp"))
		{
			Utils utils = Main.getInstance().getUtils();
			sender.sendMessage(utils.getPrefix() + utils.getColor("&e/jailtime &f- ����������, ������� ������� �������� �� ��������"));
			sender.sendMessage(utils.getPrefix() + utils.getColor("&e/jsaccept &f- ������� ����������� ��������"));
			sender.sendMessage(utils.getPrefix() + utils.getColor("&e/jsdeny &f- ��������� ����������� ��������"));
			if(sender.hasPermission("js.checkOther"))
			{
				sender.sendMessage(utils.getPrefix() + utils.getColor("&e/jailtime &f<&e���&f> - ����������, ������� ������� �������� �� �������� � ������ &7(special)"));
			}
			if(sender.hasPermission("js.arrest"))
			{
				sender.sendMessage(utils.getPrefix() + utils.getColor("&e/arrest &f<&e���&f> - ���������� ������ &7(special)"));
			}
			if(sender.hasPermission("js.advokat"))
			{
				sender.sendMessage(utils.getPrefix() + utils.getColor("&e/freedom &f<&e���&f> - ���������� ������ ���� ������ &7(special)"));
			}
			if(sender.hasPermission("js.*"))
			{
				sender.sendMessage(utils.getPrefix() + utils.getColor("&e/amnesty &f<&e���&f> - ������������� ������ &c(admin cmd)"));
				sender.sendMessage(utils.getPrefix() + utils.getColor("&e/removeprison &f<&e��������&f> - ������� ������ &c(admin cmd)"));
				sender.sendMessage(utils.getPrefix() + utils.getColor("&e/setprison &f<&e��������&f> - ������� ������ &c(admin cmd)"));
				sender.sendMessage(utils.getPrefix() + utils.getColor("&e/setprisonrespawn &f- ���������� ����� ������ ����� ������ &c(admin cmd)"));
				sender.sendMessage(utils.getPrefix() + utils.getColor("&e/jsreload &f<&eplugin/config&f> - ������������ �������/������� &c(admin cmd)"));
			}
			return true;
		}
		return true;
	}

}
