package ru.Lemar98.JS.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.Lemar98.JS.Main;
import ru.Lemar98.JS.Utils.JailManager;
import ru.Lemar98.JS.Utils.Utils;

public class SetPrison implements CommandExecutor
{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("setPrison"))
		{
			Utils utils = Main.getInstance().getUtils();
			JailManager manager = new JailManager();
			if(!(sender instanceof Player))
			{
				sender.sendMessage(utils.getPrefix() + utils.getColor("&cТюрьму можно установить только в игре!"));
				return true;
			}
			Player p = (Player)sender;
			if(args.length != 1)
			{
				p.sendMessage(utils.getPrefix() + utils.getColor("&cНужно указать только название тюрьмы, не более и не менее!"));
				return true;
			}
			if(!p.hasPermission("js.create") || !p.hasPermission("js.*"))
			{
				p.sendMessage(utils.getPrefix() + utils.getColor("&cУ Вас недостаточно прав!"));
				return true;
			}
			if(utils.checkStringNotNumeric(args[0]))
			{
				p.sendMessage(utils.getPrefix() + utils.getColor("&cНазвание должно быть буквенным!"));
				return true;
			}
			String name = args[0];
			if(manager.jailIsExist(name))
			{
				p.sendMessage(utils.getPrefix() + utils.getColor("&cТюрьма с именем &7"+name+" &cуже существует!"));
				return true;
			}
			manager.createJail(p, name, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch());
			p.sendMessage(utils.getPrefix() + utils.getColor("&aТюрьма под названием &7"+name+" &aсоздана!"));
			return true;
		}
		return true;
	}
	
}
