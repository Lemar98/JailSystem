package ru.Lemar98.JS.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.Lemar98.JS.Main;
import ru.Lemar98.JS.Utils.JailManager;
import ru.Lemar98.JS.Utils.Utils;

public class Freedom implements CommandExecutor
{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if(cmd.getName().equalsIgnoreCase("freedom"))
		{
			Utils utils = Main.getInstance().getUtils();
			if(!(sender instanceof Player))
			{
				sender.sendMessage(utils.getPrefix() + utils.getColor("&cЭта команда доступна только из игры"));
				return true;
			}
			Player p = (Player)sender;
			if(!p.hasPermission("js.advokat"))
			{
				p.sendMessage(utils.getPrefix() + utils.getColor("&cУ Вас недостаточно прав!"));
				return true;
			}
			if(args.length != 1)
			{
				utils.sendTitle(p, "&cНеверные аргументы", "&e/freedom &f<&eник&f>");
				return true;
			}
			if( Bukkit.getPlayer(args[0]) == null)
			{
				utils.sendTitle(p, "&7"+args[0], "&cне в сети");
				return true;
			}
			Player target = Bukkit.getPlayer(args[0]);
			if(target.getName().equalsIgnoreCase(p.getName()))
			{
				utils.sendTitle(p, "&cНельзя вытащить себя", "&cиз тюрьмы");
				return true;
			}
			if(utils.getOffer().containsKey(p))
			{
				utils.sendTitle(p, "&7"+target.getName(), "&cДумает над другим предложением");
				return true;
			}
			JailManager manager = new JailManager();
			if(!manager.playerIsJailed(target.getName()))
			{
				utils.sendTitle(p, "&7"+target.getName(), "&cне в тюрьме");
				return true;
			}
			target.sendMessage(utils.getPrefix() + utils.getColor("&7"+p.getName() + "&a Предлагает Вам свои услуги адвоката. Стоимость &f"+utils.getPrice()+"&a$"));
			utils.sendClickedMessage(target, utils.getPrefix() + "&a&lСогласиться", "/jsaccept", "&a&lПодтвердите действие");
			utils.sendClickedMessage(target, utils.getPrefix() + "&c&lОтказаться", "/jsdeny", "&c&lПодтвердите действие");
			utils.sendTitle(p, "&7"+target.getName(), "&aполучил Ваше предложение");
			utils.getOffer().put(p, target);
			return true;
		}
		return true;
	}
	
}
