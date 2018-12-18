package ru.Lemar98.JS.Events;

import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import ru.Lemar98.JS.Main;
import ru.Lemar98.JS.Utils.JailManager;
import ru.Lemar98.JS.Utils.Utils;

public class CommandPreprocess implements Listener
{
	private List<String> blocked = Main.getInstance().getConfig().getStringList("BlockedCommand");
	private JailManager manager = new JailManager();
	private Utils utils = Main.getInstance().getUtils();
	
	@EventHandler
	public void onEnterCommand(PlayerCommandPreprocessEvent e)
	{
		if(manager.playerIsJailed(e.getPlayer().getName()))
		{
			for(String cmd : blocked)
			{
				if(e.getMessage().equalsIgnoreCase("/"+cmd))
				{
					e.getPlayer().sendMessage(utils.getPrefix() + utils.getColor("&cВы находитесь в тюрьме!"));
					e.setCancelled(true);
				}
			}
		}
	}
}
