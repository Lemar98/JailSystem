package ru.Lemar98.JS.Utils;

import java.time.LocalDateTime;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import ru.Lemar98.JS.Main;

public class Checker implements Runnable
{
	private JailManager manager;
	public Checker()
	{	
		this.manager = new JailManager();
	}
	
	public void run() 
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
			if(!manager.playerIsJailed(p.getName())) continue;
			if(LocalDateTime.now().isAfter(manager.getAmnestyTime(p.getName())))
			{
				manager.amnesty(p.getName());
				Utils utils = Main.getInstance().getUtils();
				utils.sendTitle(p, "&aВаш срок закончился", "&aВы были освобождены");
			}
		}
	}

}
