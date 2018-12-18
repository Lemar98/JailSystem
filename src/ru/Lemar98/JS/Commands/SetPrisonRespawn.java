package ru.Lemar98.JS.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.Lemar98.JS.Main;
import ru.Lemar98.JS.SpawnConfig;
import ru.Lemar98.JS.Utils.Utils;

public class SetPrisonRespawn implements CommandExecutor
{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if(cmd.getName().equalsIgnoreCase("setprisonrespawn"))
		{
			Utils utils = Main.getInstance().getUtils();
			if(!(sender instanceof Player))
			{
				sender.sendMessage(utils.getPrefix() + utils.getColor("&cТочку респавна после тюрьмы можно установить только в игре!"));
				return true;
			}
			Player p = (Player)sender;
			if(args.length  != 0)
			{
				p.sendMessage(utils.getPrefix() + utils.getColor("&cНеверные аргументы. &e/setprisonrespawn"));
				return true;
			}
			if(!p.hasPermission("js.setspawn") || !p.hasPermission("js.*"))
			{
				p.sendMessage(utils.getPrefix() + utils.getColor("&cУ Вас недостаточно прав!"));
				return true;
			}
			SpawnConfig cfg = new SpawnConfig();
			cfg.getConfig().set("world", p.getLocation().getWorld().getName());
			cfg.getConfig().set("x", p.getLocation().getX());
			cfg.getConfig().set("y", p.getLocation().getY());
			cfg.getConfig().set("z", p.getLocation().getZ());
			cfg.getConfig().set("yaw", p.getLocation().getYaw());
			cfg.getConfig().set("pitch", p.getLocation().getPitch());
			cfg.saveConfig();
			utils.sendTitle(p, "&aТочка респавна", "&aУстановлена");
			return true;
		}
		return true;
	}

}
