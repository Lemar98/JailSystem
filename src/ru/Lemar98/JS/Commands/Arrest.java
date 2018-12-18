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
				sender.sendMessage(utils.getPrefix() + utils.getColor("&c������������ ����� ������ � ����!"));
				return true;
			}
			Player p = (Player)sender;
			if(!p.hasPermission("js.arrest") || !p.hasPermission("js.*"))
			{
				p.sendMessage(utils.getPrefix() + utils.getColor("&c� ��� ������������ ����!"));
				return true;
			}
			if(args.length < 2 || args.length > 2)
			{
				utils.sendTitle(p,"&c�������� ���������", "&e/arrest <&f�������� ������&e> <&f�����&e>");
				return true;
			}
			if(utils.checkStringNotNumeric(args[0]))
			{
				p.sendMessage(utils.getPrefix() + utils.getColor("&c�������� ������ ���� ���������!"));
				return true;
			}
			if(Bukkit.getPlayer(args[1]) == null)
			{
				utils.sendTitle(p,"&7"+args[1], "&c�� � ����");
				return true;
			}
			String jail = args[0];
			Player target = Bukkit.getPlayer(args[1]);
			JailManager manager = new JailManager();
			int srok = utils.getSrokFromConfig(target);
			if(!manager.jailIsExist(jail))
			{
				utils.sendTitle(p, "&c������ &7"+jail, "&c�� ����������");
				return true;
			}
			if(manager.playerIsJailed(target.getName()))
			{
				utils.sendTitle(p, "&7"+target.getName(), "&c��� � ������");
				return true;
			}
			if(Utilits.getWantLevel(target) == 0)
			{
				utils.sendTitle(p,"&c���� ����� �� ������", "&c������ �������!");
				return true;
			}
			if(target.getName().equalsIgnoreCase(p.getName()))
			{
			    utils.sendTitle(p, "&c�� �� ������ ��������", "&c������ ���� � ������!");
				return true;
			}
			manager.arrest(jail, target, p, srok);
			Utilits.setWantLevel(target, 0);
			utils.sendTitle(p,"&c�� �������� � ������", "&c������� &e"+p.getName());
			p.sendMessage(utils.getPrefix() + utils.getColor("&a�� �������� &e")+target.getName()+utils.getColor(" &a� ������ &7"+jail+" &a�� &d"+srok+" &a�����"));
			return true;
		}
		return true;
	}

}
