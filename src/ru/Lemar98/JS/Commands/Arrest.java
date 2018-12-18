package ru.Lemar98.JS.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import plugin.Lemar98.SM.Commands.Utilits;
import ru.Lemar98.JS.Main;
import ru.Lemar98.JS.Utils.JailManager;
import ru.Lemar98.JS.Utils.Utils;

public class Arrest implements CommandExecutor
{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if(cmd.getName().equalsIgnoreCase("arrest"))
		{
			Utils utils = Main.getInstance().getUtils();
			if(!(sender instanceof Player))
			{
				sender.sendMessage(utils.getPrefix() + utils.getColor("&cАрестовывать можно только в игре!"));
				return true;
			}
			Player p = (Player)sender;
			if(!p.hasPermission("js.arrest") || !p.hasPermission("js.*"))
			{
				p.sendMessage(utils.getPrefix() + utils.getColor("&cУ Вас недостаточно прав!"));
				return true;
			}
			if(args.length < 2 || args.length > 2)
			{
				utils.sendTitle(p,"&cНеверные аргументы", "&e/arrest <&fназвание тюрьмы&e> <&fигрок&e>");
				return true;
			}
			if(utils.checkStringNotNumeric(args[0]))
			{
				p.sendMessage(utils.getPrefix() + utils.getColor("&cНазвание должно быть буквенным!"));
				return true;
			}
			if(Bukkit.getPlayer(args[1]) == null)
			{
				utils.sendTitle(p,"&7"+args[1], "&cне в сети");
				return true;
			}
			String jail = args[0];
			Player target = Bukkit.getPlayer(args[1]);
			JailManager manager = new JailManager();
			int srok = utils.getSrokFromConfig(target);
			if(!manager.jailIsExist(jail))
			{
				utils.sendTitle(p, "&cТюрьмы &7"+jail, "&cне существует");
				return true;
			}
			if(manager.playerIsJailed(target.getName()))
			{
				utils.sendTitle(p, "&7"+target.getName(), "&cуже в тюрьме");
				return true;
			}
			if(Utilits.getWantLevel(target) == 0)
			{
				utils.sendTitle(p,"&cЭтот игрок не сделал", "&cничего плохого!");
				return true;
			}
			if(target.getName().equalsIgnoreCase(p.getName()))
			{
			    utils.sendTitle(p, "&cВы не можете посадить", "&cсамого себя в тюрьму!");
				return true;
			}
			manager.arrest(jail, target, p, srok);
			Utilits.setWantLevel(target, 0);
			utils.sendTitle(p,"&cВы посажены в тюрьму", "&cигроком &e"+p.getName());
			p.sendMessage(utils.getPrefix() + utils.getColor("&aВы посадили &e")+target.getName()+utils.getColor(" &aв тюрьму &7"+jail+" &aна &d"+srok+" &aминут"));
			return true;
		}
		return true;
	}

}
