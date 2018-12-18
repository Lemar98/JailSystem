package ru.Lemar98.JS.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import plugin.Lemar98.SM.Commands.Utilits;
import ru.Lemar98.JS.Main;
import ru.Lemar98.JS.SpawnConfig;

public class JailManager 
{
	/* Проверяет, существует ли тюрьма с таким именем */
	public boolean jailIsExist(String name) 
	{	
		Connection con = null;
		PreparedStatement ps = null;
		try 
		{	
			con = Main.getInstance().sql.hikari.getConnection();
			ps = con.prepareStatement("SELECT * FROM JailInfo WHERE name=?");
			ps.setString(1, name);
			ResultSet result = ps.executeQuery();
			if(result.next())
			{
				return true;
			}
			/*
			PreparedStatement statement = Main.getInstance().sql.getConnection().prepareStatement("SELECT * FROM JailInfo WHERE name=?");
			statement.setString(1, name);
			ResultSet result = statement.executeQuery();
			if(result.next()) 
			{
				return true;
			}
			*/	
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			if(con != null)
			{
				try 
				{
					con.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			if(ps != null)
			{
				try 
				{
					ps.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	/* Записывает все данные о новой тюрьме */
	public void createJail(Player sender, String name, double X, double Y, double Z, float YAW, float PITCH)
	{
		Utils utils = Main.getInstance().getUtils();
		if(jailIsExist(name))
		{
			sender.sendMessage(utils.getPrefix() + utils.getColor("&cТюрьма с именем &7"+name+" уже существует!"));
			return;
		}
		Connection con = null;
		PreparedStatement ps = null;
		try
		{
			con = Main.getInstance().sql.hikari.getConnection();
			ps = con.prepareStatement("INSERT INTO JailInfo (name, WorldName, X, Y, Z, YAW, PITCH) VALUES (?,?,?,?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, sender.getLocation().getWorld().getName());
			ps.setDouble(3, X);
			ps.setDouble(4, Y);
			ps.setDouble(5, Z);
			ps.setFloat(6, YAW);
			ps.setFloat(7, PITCH);
			ps.executeUpdate();
			/*
			PreparedStatement insert = Main.getInstance().sql.getConnection().prepareStatement("INSERT INTO JailInfo (name, WorldName, X, Y, Z, YAW, PITCH) VALUES (?,?,?,?,?,?,?)");
			insert.setString(1, name);
			insert.setString(2, sender.getLocation().getWorld().getName());
			insert.setDouble(3, X);
			insert.setDouble(4, Y);
			insert.setDouble(5, Z);
			insert.setFloat(6, YAW);
			insert.setFloat(7, PITCH);
			insert.executeUpdate();
			*/
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(con != null)
			{
				try 
				{
					con.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			if(ps != null)
			{
				try 
				{
					ps.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	/* Удалит все данные о тюрьме */
	public void removeJail(Player sender, String name)
	{
		Utils utils = Main.getInstance().getUtils();
		if(!jailIsExist(name))
		{
			sender.sendMessage(utils.getPrefix() + utils.getColor("&cТюрьма с именем &7"+name+" не существует!"));
			return;
		}
		Connection con = null;
		PreparedStatement ps = null;
		try
		{
			con = Main.getInstance().sql.hikari.getConnection();
			ps = con.prepareStatement("DELETE FROM JailInfo WHERE name=?");
			ps.setString(1, name);
			ps.executeUpdate();
			/*
			PreparedStatement delete = Main.getInstance().sql.getConnection().prepareStatement("DELETE FROM JailInfo WHERE name=?");
			delete.setString(1, name);
			delete.executeUpdate();		
			*/
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(con != null)
			{
				try 
				{
					con.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			if(ps != null)
			{
				try 
				{
					ps.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	/* Проверяет, сидит ли игрок в тюрьме */
	public boolean playerIsJailed(String playername)
	{
		Connection con = null;
		PreparedStatement ps = null;
		try
		{
			con = Main.getInstance().sql.hikari.getConnection();
			ps = con.prepareStatement("SELECT * FROM PlayerData WHERE PlayerName=?");
			ps.setString(1, playername);
			ResultSet result = ps.executeQuery();
			if(result.next())
			{
				return true;
			}
			/*
			PreparedStatement statement = Main.getInstance().sql.getConnection().prepareStatement("SELECT * FROM PlayerData WHERE PlayerName=?");
			statement.setString(1, playername);
			ResultSet result = statement.executeQuery();
			if(result.next())
			{
				return true;
			}
			*/
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(con != null)
			{
				try 
				{
					con.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			if(ps != null)
			{
				try 
				{
					ps.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	/* Посадит игрока в тюрьму */
	public void arrest(String JailName, Player target, Player sender, int time)
	{
		Utils utils = Main.getInstance().getUtils();
		if(!jailIsExist(JailName))
		{
			sender.sendMessage(utils.getPrefix() + utils.getColor("&cТюрьма с именем &7"+JailName+" не существует!"));
			return;
		}
		Connection con = null;
		PreparedStatement ps = null;
		Connection con2 = null;
		PreparedStatement ps2 = null;
		try 
		{	
			con = Main.getInstance().sql.hikari.getConnection();
			ps = con.prepareStatement("INSERT INTO PlayerData (PlayerName, JailName, ArrestedBy, WantLevel, Time, TimeLeft) VALUES (?,?,?,?,?,?)");
			ps.setString(1, target.getName());
			ps.setString(2, JailName);
			ps.setString(3, sender.getName());
			ps.setInt(4, Utilits.getWantLevel(target));
			ps.setInt(5, time);
			ps.setString(6, String.valueOf(LocalDateTime.now().plusMinutes(time)));
			ps.executeUpdate();
			
			addPlayerJailedCount(target.getName());
			
			con2 = Main.getInstance().sql.hikari.getConnection();
			ps2 = con2.prepareStatement("SELECT * FROM JailInfo WHERE name='"+JailName+"'");
			ResultSet result = ps2.executeQuery();
			result.next();
			double x = result.getDouble("X");
			double y = result.getDouble("Y");
			double z = result.getDouble("Z");
			float yaw = result.getFloat("YAW");
			float pitch = result.getFloat("PITCH");
			String world = result.getString("WorldName");
			Location loc = new Location(Bukkit.getWorld(world), x,y,z,yaw,pitch);
			target.teleport(loc);
			
			/*
			PreparedStatement insert = Main.getInstance().sql.getConnection().prepareStatement("INSERT INTO PlayerData (PlayerName, JailName, ArrestedBy, WantLevel, Time, TimeLeft) VALUES (?,?,?,?,?,?)");
			insert.setString(1, target.getName());
			insert.setString(2, JailName);
			insert.setString(3, sender.getName());
			insert.setInt(4, Utilits.getWantLevel(target));
			insert.setInt(5, time);
			insert.setString(6, String.valueOf(LocalDateTime.now().plusMinutes(time)));
			insert.executeUpdate();
			
			addPlayerJailedCount(target.getName());
			
			PreparedStatement select = Main.getInstance().sql.getConnection().prepareStatement("SELECT * FROM JailInfo WHERE name='"+JailName+"'");
			ResultSet result = select.executeQuery();
			result.next();
			double x = result.getDouble("X");
			double y = result.getDouble("Y");
			double z = result.getDouble("Z");
			float yaw = result.getFloat("YAW");
			float pitch = result.getFloat("PITCH");
			String world = result.getString("WorldName");
			Location loc = new Location(Bukkit.getWorld(world), x,y,z,yaw,pitch);
			target.teleport(loc);
			*/
		}catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			if(con != null)
			{
				try 
				{
					con.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			if(con2 != null)
			{
				try 
				{
					con2.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			if(ps != null)
			{
				try 
				{
					ps.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			if(ps2 != null)
			{
				try 
				{
					ps2.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}

	/* Амнистирует игрока */
	public void amnesty(String name)
	{
		Connection con = null;
		PreparedStatement ps = null;
		try
		{
			con = Main.getInstance().sql.hikari.getConnection();
			ps = con.prepareStatement("DELETE FROM PlayerData WHERE PlayerName=?");
			ps.setString(1, name);
			ps.executeUpdate();
			Player p = Bukkit.getPlayer(name);
			SpawnConfig cfg = new SpawnConfig();
			p.teleport(cfg.getRespawnPoint());
			/*
			PreparedStatement delete = Main.getInstance().sql.getConnection().prepareStatement("DELETE FROM PlayerData WHERE PlayerName=?");
			delete.setString(1, name);
			delete.executeUpdate();		
			Player p = Bukkit.getPlayer(name);
			SpawnConfig cfg = new SpawnConfig();
			p.teleport(cfg.getRespawnPoint());
			*/
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(con != null)
			{
				try 
				{
					con.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			if(ps != null)
			{
				try 
				{
					ps.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	/* Вернет дату освобождения игрока (в дату входят: Год, месяц, число, часы,минуты,секнды (yyyy-mm-dd-hh-mm-ss))*/
	public LocalDateTime getAmnestyTime(String playername)
	{
		LocalDateTime time = null;
		Connection con = null;
		PreparedStatement ps = null;
		try
		{
			con = Main.getInstance().sql.hikari.getConnection();
			ps = con.prepareStatement("SELECT * FROM PlayerData WHERE PlayerName=?");
			ps.setString(1, playername);
			ResultSet result = ps.executeQuery();
			result.next();
			String str = String.valueOf(LocalDateTime.parse(result.getString("TimeLeft")));
			time = LocalDateTime.parse(str);
			/*
			PreparedStatement statement = Main.getInstance().sql.getConnection().prepareStatement("SELECT * FROM PlayerData WHERE PlayerName=?");
			statement.setString(1, playername);
			ResultSet result = statement.executeQuery();
			result.next();
			String str = String.valueOf(LocalDateTime.parse(result.getString("TimeLeft")));
			time = LocalDateTime.parse(str);
			*/
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(con != null)
			{
				try 
				{
					con.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			if(ps != null)
			{
				try 
				{
					ps.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
		return time;
	}
	
	/* Возвращает время (в минутах) до амнистии */
	public long minutesAgainAmnesty(String playername)
	{
		return getAmnestyTime(playername).isAfter(LocalDateTime.now()) ? Duration.between(LocalDateTime.now(), getAmnestyTime(playername)).toMinutes() : -1L;
	}
	
	/* Вернет время на которое игрок посажен (в минутах) */
	public int getJailedTime(String playername)
	{
		int time = -1;
		Connection con = null;
		PreparedStatement ps = null;
		try
		{
			con = Main.getInstance().sql.hikari.getConnection();
			ps = con.prepareStatement("SELECT * FROM PlayerData WHERE PlayerName=?");
			ps.setString(1, playername);
			ResultSet result = ps.executeQuery();
			result.next();
			time = result.getInt("time");
			/*
			PreparedStatement statement = Main.getInstance().sql.getConnection().prepareStatement("SELECT * FROM PlayerData WHERE PlayerName=?");
			statement.setString(1, playername);
			ResultSet result = statement.executeQuery();
			result.next();
			time = result.getInt("Time");
			*/
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(con != null)
			{
				try 
				{
					con.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			if(ps != null)
			{
				try 
				{
					ps.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
		return time;
	}
	
	/* Вернет общее кол-во попадания в тюрьму игроком */
	public int getPlayerJailedCount(String playername)
	{
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try
		{
			con = Main.getInstance().sql.hikari.getConnection();
			ps = con.prepareStatement("SELECT * FROM PlayerStatistic WHERE PlayerName=?");
			ps.setString(1, playername);
			ResultSet result = ps.executeQuery();
			if(result.next())
			{
				count = result.getInt("JailedCount");
			}
			/*
			PreparedStatement statement = Main.getInstance().sql.getConnection().prepareStatement("SELECT * FROM PlayerStatistic WHERE PlayerName=?");
			statement.setString(1, playername);
			ResultSet result = statement.executeQuery();
			if(result.next())
			{
				count = result.getInt("JailedCount");
			}
			*/
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(con != null)
			{
				try 
				{
					con.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			if(ps != null)
			{
				try 
				{
					ps.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
		return count;
	}
	
	/* Добавит к кол-ву попаданий в тюрьму переменную count */
	public void addPlayerJailedCount(String playername)
	{
		Connection con = null;
		PreparedStatement ps = null;
		try
		{
			con = Main.getInstance().sql.hikari.getConnection();
			ps = con.prepareStatement("INSERT INTO PlayerStatistic (PlayerName, JailedCount) VALUES (?,?) ON DUPLICATE KEY UPDATE JailedCount=?");
			ps.setString(1, playername);
			ps.setInt(2, 1);
			ps.setInt(3, getPlayerJailedCount(playername)+1);
			ps.executeUpdate();
			/*
			PreparedStatement insert = Main.getInstance().sql.getConnection().prepareStatement("INSERT INTO PlayerStatistic (PlayerName, JailedCount) VALUES (?,?) ON DUPLICATE KEY UPDATE JailedCount=?");
			insert.setString(1, playername);
			insert.setInt(2, 1);
			insert.setInt(3, getPlayerJailedCount(playername)+1);
			insert.executeUpdate();			
			*/
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(con != null)
			{
				try 
				{
					con.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			if(ps != null)
			{
				try 
				{
					ps.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
}
