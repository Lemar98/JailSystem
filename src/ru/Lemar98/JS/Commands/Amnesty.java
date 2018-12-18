package ru.Lemar98.JS.Commands;

import java.time.LocalDateTime;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.Lemar98.JS.Main;
import ru.Lemar98.JS.Utils.JailManager;
import ru.Lemar98.JS.Utils.Utils;

public class Amnesty implements CommandExecutor
{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if(cmd.getName().equalsIgnoreCase("amnesty"))
		{
			Utils utils = Main.getInstance().getUtils();
			if(!(sender instanceof Player))
			{
				sender.sendMessage(utils.getPrefix() + utils.getColor("&c������������� ����� ������ � ����!"));
				return true;
			}
			Player p = (Player) sender;
			if(!p.hasPermission("js.amnesty") || !p.hasPermission("js.*"))
			{
				p.sendMessage(utils.getPrefix() + utils.getColor("&c� ��� ������������ ����!"));
				return true;
			}
			if(args.length != 1)
			{
				utils.sendTitle(p, "&c�������� ���������", "&e/amnesty &f<&e�����&f>");
				return true;
			}
			if(Bukkit.getPlayer(args[0]) == null)
			{
				utils.sendTitle(p, "&7"+args[0], "&c�� � ����");
				return true;
			}
			Player target = Bukkit.getPlayer(args[0]);
			if(target.getName().equalsIgnoreCase(p.getName()))
			{
				utils.sendTitle(p, "&c�� �� ������ �������������", "&c������ ����!");
				return true;
			}
			JailManager manager = new JailManager();
			if(!manager.playerIsJailed(target.getName()))
			{
				utils.sendTitle(p, "&7"+target.getName(), "&c�� � ������");
				return true;
			}
			manager.amnesty(target.getName());
			utils.sendTitle(target, "&7"+p.getName(), "&a��� ������������");
			utils.sendTitle(p, "&a�� �������������", "&7"+target.getName());
			return true;
		}
		return true;
	}
	
}
