package ru.Lemar98.JS.Utils;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.milkbowl.vault.economy.EconomyResponse;
import plugin.Lemar98.SM.Commands.Utilits;
import ru.Lemar98.JS.Main;

public class Utils 
{
	private String prefix;
	private final Map<Player, Player> offer;
	
	/* ����������� */
	public Utils(Main main)
	{
		this.offer = new HashMap<Player, Player>();
		this.prefix = getColor(Main.getInstance().getConfig().getString("pluginPrefix"));
	}
	
	/* ��������� ������� ������� ������� */
	public String getColor(String msg)
	{
		return msg.replace("&", "\u00a7");
	}
	
	/* ���������� ��������� �� ������ �������� �� ������� */
	public double getPrice()
	{
		return Main.getInstance().getConfig().getDouble("AdvokatSettings.price");
	}
	
	/* ���������� ������� ��� ��������� �� ������� */
	public int getPercent()
	{
		return Main.getInstance().getConfig().getInt("AdvokatSettings.percent");
	}
	
	/* ���������� ���� � ������ */
	public Map<Player, Player> getOffer()
	{
		return offer;
	}
	
	/* ���������� ������� ������� */
	public String getPrefix()
	{
		return prefix;
	}
	
	/* ���������� ������ textComponent */
	public void sendClickedMessage(Player p, String msg, String cmd, String hover)
	{
		 TextComponent message = new TextComponent(msg);
         message.setText(getColor(msg));
         message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, cmd));
         message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(getColor(hover)).create()));
         p.spigot().sendMessage((BaseComponent)message);
	}
	
	/* ������ ����� � ������ */
	public EconomyResponse minusBabki(Player p, double amount)
	{
		EconomyResponse plus = Main.economy.withdrawPlayer(p, amount);
		return plus;
	}
	
	/* ���������� ����� ������ */
	public EconomyResponse plusBabki(Player p, double amount)
	{
		EconomyResponse plus = Main.economy.depositPlayer(p, amount);
		return plus;
	}
	
	/* ���������� ������ ����� */
	public void sendTitle(Player player, String title, String subtitle)
	{
		player.sendTitle(getColor(title), getColor(subtitle), 20,40,20);
	}
	
	/* ���������, ������� �� ������ ������ �� ���� */
	public boolean checkStringNotNumeric(String arg)
	{
		try
		{
			Double.parseDouble(arg);
		}
		catch(NumberFormatException e)
		{
			return false;
		}
		return true;
	}
	
	/* �������� ���� � ������� ������ �� �������� ��� ������ ������(wantLevel) �� ������� */
	public int getSrokFromConfig(Player p)
	{
		int srok = -1;
		int wantLevel = Utilits.getWantLevel(p);
		switch(wantLevel)
		{
			case 0:
				srok = 0;
				break;
			case 1:
				srok = Main.getInstance().getConfig().getInt("StarSettings.star1");
				break;
			case 2:
				srok = Main.getInstance().getConfig().getInt("StarSettings.star2");
				break;
			case 3:
				srok = Main.getInstance().getConfig().getInt("StarSettings.star3");
				break;
			case 4:
				srok = Main.getInstance().getConfig().getInt("StarSettings.star4");
				break;
			case 5:
				srok = Main.getInstance().getConfig().getInt("StarSettings.star5");
				break;
			default:
				srok = -1;
				break;
		}
		return srok;
	}
}
