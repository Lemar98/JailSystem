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
				sender.sendMessage(utils.getPrefix() + utils.getColor("&cТюрьму можно удалить только в игре!"));
				return true;
			}
			Player p = (Player)sender;
			if(!p.hasPermission("js.remove") || !p.hasPermission("js.*"))
			{
				p.sendMessage(utils.getPrefix() + utils.getColor("&cУ Вас недостаточно прав!"));
				return true;
			}
			if(args.length != 1)
			{
				p.sendMessage(utils.getPrefix() + utils.getColor("&cНужно указать только название тюрьмы, не более и не менее!"));
				return true;
			}
			if(utils.checkStringNotNumeric(args[0]))
			{
				p.sendMessage(utils.getPrefix() + utils.getColor("&cНазвание должно быть буквенным!"));
				return true;
			}
			String name = args[0];
			JailManager manager = new JailManager();
			if(!manager.jailIsExist(name))
			{
				p.sendMessage(utils.getPrefix() + utils.getColor("&cТюрьма c именем &7"+name+" &cне существует!"));
				return true;
			}
			manager.removeJail(p, name);
			p.sendMessage(utils.getPrefix() + utils.getColor("&aТюрьма под названием &7"+name+" &aбыла удалена!"));
			return true;
		}
		return true;
	}

}
