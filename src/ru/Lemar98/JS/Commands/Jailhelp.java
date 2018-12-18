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
			sender.sendMessage(utils.getPrefix() + utils.getColor("&e/jailtime &f- посмотреть, сколько времени осталось до амнистии"));
			sender.sendMessage(utils.getPrefix() + utils.getColor("&e/jsaccept &f- принять предложение адвоката"));
			sender.sendMessage(utils.getPrefix() + utils.getColor("&e/jsdeny &f- отклонить предложение адвоката"));
			if(sender.hasPermission("js.checkOther"))
			{
				sender.sendMessage(utils.getPrefix() + utils.getColor("&e/jailtime &f<&eимя&f> - посмотреть, сколько времени осталось до амнистии у игрока &7(special)"));
			}
			if(sender.hasPermission("js.arrest"))
			{
				sender.sendMessage(utils.getPrefix() + utils.getColor("&e/arrest &f<&eимя&f> - арестовать игрока &7(special)"));
			}
			if(sender.hasPermission("js.advokat"))
			{
				sender.sendMessage(utils.getPrefix() + utils.getColor("&e/freedom &f<&eимя&f> - предложить игроку свои услуги &7(special)"));
			}
			if(sender.hasPermission("js.*"))
			{
				sender.sendMessage(utils.getPrefix() + utils.getColor("&e/amnesty &f<&eимя&f> - амнистировать игрока &c(admin cmd)"));
				sender.sendMessage(utils.getPrefix() + utils.getColor("&e/removeprison &f<&eназвание&f> - удалить тюрьму &c(admin cmd)"));
				sender.sendMessage(utils.getPrefix() + utils.getColor("&e/setprison &f<&eназвание&f> - создать тюрьму &c(admin cmd)"));
				sender.sendMessage(utils.getPrefix() + utils.getColor("&e/setprisonrespawn &f- установить место выхода после тюрьмы &c(admin cmd)"));
				sender.sendMessage(utils.getPrefix() + utils.getColor("&e/jsreload &f<&eplugin/config&f> - перезагрузка плагина/конфига &c(admin cmd)"));
			}
			return true;
		}
		return true;
	}

}
