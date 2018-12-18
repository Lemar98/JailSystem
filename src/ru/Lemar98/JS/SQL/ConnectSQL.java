package ru.Lemar98.JS.SQL;

import java.sql.Connection;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import com.zaxxer.hikari.HikariDataSource;

public class ConnectSQL
{
	public HikariDataSource hikari;
	//private Connection connection;
	private String host, database, username, password;
	private int port;
	public ConnectSQL(String host, String database, String username, String password, int port)
	{
		this.host = host;
		this.database = database;
		this.username = username;
		this.password = password;
		this.port = port;
	}
	
	public void setupMySQL()
	{
		/*
		try
		{
			synchronized(Main.getInstance()) 
			{
				if(getConnection() != null && !getConnection().isClosed())
				{
					return;
				}
				Class.forName("com.mysql.jdbc.Driver");
                setConnection((Connection) DriverManager.getConnection("jdbc:mysql://"+this.host+":"+this.port+"/"+this.database, this.username, this.password));
               Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "MYSQL CONNECTED SUCCESSFUL");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		*/
		hikari = new HikariDataSource();
		hikari.setMaximumPoolSize(20);
		hikari.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
		hikari.addDataSourceProperty("serverName", host);
		hikari.addDataSourceProperty("port", port);
		hikari.addDataSourceProperty("databaseName", database);
		hikari.addDataSourceProperty("user", username);
		hikari.addDataSourceProperty("password", password);
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "MYSQL CONNECTED SUCCESSFUL");
	}
	
	/*
	public void setConnection(Connection connection)
	{
		this.connection = connection;
	}
	*/
}
