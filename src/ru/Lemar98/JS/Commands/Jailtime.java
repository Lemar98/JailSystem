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
				sender.sendMessage(utils.getColor("&cКоманду можно выполнить только в игре!"));
				return true;
			}
			Player p = (Player)sender;
			if(args.length > 1)
			{
				utils.sendTitle(p, "&cНеверные аргументы", "&e/jailtime");
				return true;
			}
			JailManager manager = new JailManager();
			switch(args.length)
			{
				case 0:	
					if(!manager.playerIsJailed(p.getName()))
					{
						utils.sendTitle(p, "&cВы не в тюрьме", "");
						return true;
					}
					utils.sendTitle(p, "&aДо амнистии", "&e"+manager.minutesAgainAmnesty(p.getName())+" &aмин.");
					return true;
				case 1:
					if(!p.hasPermission("js.checkOther"))
					{
						p.sendMessage(utils.getPrefix() + utils.getColor("&cУ Вас недостаточно прав!"));
						return true;
					}
					Player target = Bukkit.getPlayer(args[0]);
					if(target == null)
					{
						utils.sendTitle(p, "&7"+args[0], "&cне в сети");
						return true;
					}
					if(!manager.playerIsJailed(target.getName()))
					{
						utils.sendTitle(p, "&7"+target.getName(), "&cне в тюрьме");
						return true;
					}
					utils.sendTitle(p, "&aДо его амнистии", "&e"+manager.minutesAgainAmnesty(target.getName())+" &aмин.");
					return true;
			}
			return true;
		}
		return true;
	}
	
}
