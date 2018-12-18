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
				sender.sendMessage(utils.getPrefix() + utils.getColor("&c��� ������� �������� ������ �� ����"));
				return true;
			}
			Player p = (Player)sender;
			if(!p.hasPermission("js.advokat"))
			{
				p.sendMessage(utils.getPrefix() + utils.getColor("&c� ��� ������������ ����!"));
				return true;
			}
			if(args.length != 1)
			{
				utils.sendTitle(p, "&c�������� ���������", "&e/freedom &f<&e���&f>");
				return true;
			}
			if( Bukkit.getPlayer(args[0]) == null)
			{
				utils.sendTitle(p, "&7"+args[0], "&c�� � ����");
				return true;
			}
			Player target = Bukkit.getPlayer(args[0]);
			if(target.getName().equalsIgnoreCase(p.getName()))
			{
				utils.sendTitle(p, "&c������ �������� ����", "&c�� ������");
				return true;
			}
			if(utils.getOffer().containsKey(p))
			{
				utils.sendTitle(p, "&7"+target.getName(), "&c������ ��� ������ ������������");
				return true;
			}
			JailManager manager = new JailManager();
			if(!manager.playerIsJailed(target.getName()))
			{
				utils.sendTitle(p, "&7"+target.getName(), "&c�� � ������");
				return true;
			}
			target.sendMessage(utils.getPrefix() + utils.getColor("&7"+p.getName() + "&a ���������� ��� ���� ������ ��������. ��������� &f"+utils.getPrice()+"&a$"));
			utils.sendClickedMessage(target, utils.getPrefix() + "&a&l�����������", "/jsaccept", "&a&l����������� ��������");
			utils.sendClickedMessage(target, utils.getPrefix() + "&c&l����������", "/jsdeny", "&c&l����������� ��������");
			utils.sendTitle(p, "&7"+target.getName(), "&a������� ���� �����������");
			utils.getOffer().put(p, target);
			return true;
		}
		return true;
	}
	
}
