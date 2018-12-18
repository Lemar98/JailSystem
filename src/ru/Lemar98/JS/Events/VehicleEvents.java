package ru.Lemar98.JS.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import es.pollitoyeye.vehicles.events.VehicleEnterEvent;

import es.pollitoyeye.vehicles.events.VehiclePlaceEvent;
import ru.Lemar98.JS.Main;
import ru.Lemar98.JS.Utils.JailManager;
import ru.Lemar98.JS.Utils.Utils;

public class VehicleEvents implements Listener
{
	private JailManager manager = new JailManager();
	private Utils utils = Main.getInstance().getUtils();
	
	@EventHandler
	public void placeCar(VehiclePlaceEvent e)
	{
		if(manager.playerIsJailed(e.getOwner().getName()))
		{
			e.getOwner().sendMessage(utils.getPrefix() + utils.getColor("&cВы находитесь в тюрьме!"));
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void enterCar(VehicleEnterEvent e)
	{
		Player p = (Player) e.getPlayer();
		if(manager.playerIsJailed(p.getName()))
		{
			p.sendMessage(utils.getPrefix() + utils.getColor("&cВы находитесь в тюрьме!"));
			e.setCancelled(true);
		}
	}
	
}
