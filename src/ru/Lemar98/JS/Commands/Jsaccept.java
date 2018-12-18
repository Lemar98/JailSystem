package ru.Lemar98.JS.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.Lemar98.JS.Main;
import ru.Lemar98.JS.Utils.JailManager;
import ru.Lemar98.JS.Utils.Utils;

public class Jsaccept implements CommandExecutor
{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if(cmd.getName().equalsIgnoreCase("jsaccept"))
		{
			Utils utils = Main.getInstance().getUtils();
			if(!(sender instanceof Player))
			{
				sender.sendMessage(utils.getColor("&cЭту команду можно выполнить только из игры!"));
				return true;
			}
			Player p = (Player)sender;
			if(args.length > 0)
			{
				utils.sendTitle(p, "&cНеверные аргументы", "&e/jsaccept");
				return true;
			}
			if(!utils.getOffer().containsKey(p))
			{
				utils.sendTitle(p, "&cУ Вас нет", "&cактивных предложений");
				return true;
			}
			Player offerOwner = utils.getOffer().get(p);
			if(offerOwner == null)
			{
				utils.sendTitle(p, "&cАдвокат вышел из игры", "&cоперация невозможна");
				utils.getOffer().remove(p);
				return true;
			}
			double price = utils.getPrice();
			int percent = utils.getPercent();
			double zpAdvokata = price*percent/100;
			utils.minusBabki(p, price);
			utils.sendTitle(p, "&7"+offerOwner.getName(), "&eАмнистировал Вас");
			utils.sendTitle(offerOwner, "&aВы добились амнистии", "&aдля &7"+p.getName());
			utils.plusBabki(offerOwner, zpAdvokata);
			offerOwner.sendMessage(utils.getPrefix() + utils.getColor("&eВы получили &f"+zpAdvokata+"&a$ &eза услуги адвоката"));
			utils.getOffer().remove(p);
			JailManager manager = new JailManager();
			manager.amnesty(p.getName());
			return true;
		}
		return true;
	}

}
