package ru.Lemar98.JS;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class SpawnConfig 
{
	private File file;
	private FileConfiguration cfg;
	public SpawnConfig()
	{
		this.file = new File(Main.getInstance().getDataFolder(), "spawn.yml");
		this.cfg = YamlConfiguration.loadConfiguration(file);
	}
	
	public FileConfiguration getConfig()
	{
		return this.cfg;
	}
	
	public Location getRespawnPoint()
	{
		return new Location(Bukkit.getWorld(getConfig().getString("world")), getConfig().getDouble("x"), getConfig().getDouble("y"), getConfig().getDouble("z"), Float.valueOf(getConfig().getString("yaw")), Float.valueOf(getConfig().getString("pitch")));
	}
	
	public void saveConfig()
	{
		try 
		{
			getConfig().save(file);
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
