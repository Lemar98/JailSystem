package ru.Lemar98.JS;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;
import ru.Lemar98.JS.Commands.Amnesty;
import ru.Lemar98.JS.Commands.Arrest;
import ru.Lemar98.JS.Commands.Freedom;
import ru.Lemar98.JS.Commands.Jailhelp;
import ru.Lemar98.JS.Commands.Jailtime;
import ru.Lemar98.JS.Commands.Jsaccept;
import ru.Lemar98.JS.Commands.Jsdeny;
import ru.Lemar98.JS.Commands.Jsreload;
import ru.Lemar98.JS.Commands.RemovePrison;
import ru.Lemar98.JS.Commands.SetPrison;
import ru.Lemar98.JS.Commands.SetPrisonRespawn;
import ru.Lemar98.JS.Events.CommandPreprocess;
import ru.Lemar98.JS.Events.InteractWithDoor;
import ru.Lemar98.JS.Events.VehicleEvents;
import ru.Lemar98.JS.SQL.ConnectSQL;
import ru.Lemar98.JS.Utils.Checker;
import ru.Lemar98.JS.Utils.Utils;
public class Main extends JavaPlugin
{
	private static Main instance;
	private SpawnConfig scfg;
	private static Utils utils;
	public ConnectSQL sql = new ConnectSQL(getConfig().getString("MySQL.host"), getConfig().getString("MySQL.database"), getConfig().getString("MySQL.username"), getConfig().getString("MySQL.password"), getConfig().getInt("MySql.port"));
	public static Economy economy = null;
	
	public void onEnable()
	{
		Main.instance = this;
		Main.utils = new Utils(this);
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Checker(), 20L, 20L*60L);
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		this.scfg = new SpawnConfig();
		this.scfg.getConfig().options().copyDefaults(true);
		this.scfg.saveConfig();
		sql.setupMySQL();
		setupEconomy();
		
		Bukkit.getPluginManager().registerEvents(new CommandPreprocess(), this);
		Bukkit.getPluginManager().registerEvents(new InteractWithDoor(), this);
		Bukkit.getPluginManager().registerEvents(new VehicleEvents(), this);
		
		getCommand("setprison").setExecutor(new SetPrison());
		getCommand("removeprison").setExecutor(new RemovePrison());
		getCommand("arrest").setExecutor(new Arrest());
		getCommand("amnesty").setExecutor(new Amnesty());
		getCommand("setprisonrespawn").setExecutor(new SetPrisonRespawn());
		getCommand("freedom").setExecutor(new Freedom());
		getCommand("jsaccept").setExecutor(new Jsaccept());
		getCommand("jsdeny").setExecutor(new Jsdeny());
		getCommand("jailtime").setExecutor(new Jailtime());
		getCommand("jailhelp").setExecutor(new Jailhelp());
		getCommand("jsreload").setExecutor(new Jsreload());
	}
	
	public void onDisable()
	{
		Bukkit.getServer().getScheduler().cancelTasks(this);
	}
	
	public Utils getUtils()
	{
		return utils;
	}
	
	public static Main getInstance() 
	{
		return instance;
	}

	 public boolean setupEconomy() 
	 {
		  if (getServer().getPluginManager().getPlugin("Vault") == null) 
		  {
	         return false;
	      }
	      RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
	      if (rsp == null) 
	      {
	          return false;
	      }
	      economy = rsp.getProvider();
	      return economy != null;
	 }	
	
}
