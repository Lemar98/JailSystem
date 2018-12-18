package ru.Lemar98.JS.Events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import ru.Lemar98.JS.Main;
import ru.Lemar98.JS.Utils.JailManager;
import ru.Lemar98.JS.Utils.Utils;


public class InteractWithDoor implements Listener
{
	private JailManager manager = new JailManager();
	private Utils utils = Main.getInstance().getUtils();
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e)
	{
		if(manager.playerIsJailed(e.getPlayer().getName()))
		{
			if(e.getAction() == Action.RIGHT_CLICK_BLOCK)
			{
				if(e.getClickedBlock().getType() == Material.ACACIA_DOOR || e.getClickedBlock().getType() == Material.BIRCH_DOOR || e.getClickedBlock().getType() == Material.DARK_OAK_DOOR || e.getClickedBlock().getType() == Material.IRON_DOOR || e.getClickedBlock().getType() == Material.JUNGLE_DOOR || e.getClickedBlock().getType() == Material.SPRUCE_DOOR || e.getClickedBlock().getType() == Material.TRAP_DOOR || e.getClickedBlock().getType() == Material.WOODEN_DOOR)
				{
					e.getPlayer().sendMessage(utils.getPrefix() + utils.getColor("&cВы находитесь в тюрьме!"));
					e.setCancelled(true);
				}
			}
		}
	}
}
