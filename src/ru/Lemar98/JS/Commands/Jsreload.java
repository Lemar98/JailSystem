package ru.Lemar98.JS.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import ru.Lemar98.JS.Main;
import ru.Lemar98.JS.Utils.Utils;

public class Jsreload implements CommandExecutor
{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if(cmd.getName().equalsIgnoreCase("jsreload"))
		{
			Utils utils = Main.getInstance().getUtils();
			if(!sender.hasPermission("js.reload") || !sender.hasPermission("js.*"))
			{
				sender.sendMessage(utils.getPrefix() + utils.getColor("&cУ Вас недостаточно прав!"));
				return true;
			}
			if(args.length != 1)
			{
				sender.sendMessage(utils.getPrefix() + utils.getColor("&cНеверные аргументы. &e/jsreload &f<&eplugin/config&f>"));
				return true;
			}
			switch(args[0])
			{
				case "plugin":
					Bukkit.getPluginManager().disablePlugin(Main.getInstance());
					Bukkit.getPluginManager().enablePlugin(Main.getInstance());
					sender.sendMessage(utils.getPrefix() + utils.getColor("&aПлагин перезагружен!"));
					return true;
				case "config":
					Main.getInstance().saveConfig();
					Main.getInstance().reloadConfig();
					sender.sendMessage(utils.getPrefix() + utils.getColor("&aКонфиг перезагружен!"));
					return true;
			}
			return true;
		}
		return true;
	}
	
}
