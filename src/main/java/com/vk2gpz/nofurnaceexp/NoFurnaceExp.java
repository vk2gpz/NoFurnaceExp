package com.vk2gpz.nofurnaceexp;

import org.bukkit.command.*;
import org.bukkit.event.*;
import org.bukkit.event.inventory.FurnaceExtractEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.*;

import java.util.*;

public class NoFurnaceExp extends JavaPlugin implements CommandExecutor, Listener {
	private boolean noExp;
	
	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		noExp = getConfig().getBoolean("NoExpMode", true);
		getCommand("nfexp").setExecutor(this);
		getServer().getPluginManager().registerEvents(this, this);
		
		PluginDescriptionFile pdfile = getDescription();
		getLogger().info(pdfile.getName() + " version " + pdfile.getVersion() + " is Enabled");
	}
	
	@Override
	public void onDisable() {
		PluginDescriptionFile pdFile = getDescription();
		getLogger().info(pdFile.getName() + " version " + pdFile.getVersion() + " is Disabled");
	}
	
	
	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] a) {
		if (s.hasPermission("nfexp.toggle")) {
			noExp = !noExp;
			s.sendMessage("§6No exp mode §c" + (noExp ? "enabled" : "disabled") + "§6.");
		}
		return true;
	}
	
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onFurnaceExtract(FurnaceExtractEvent e) {
		if (noExp) {
			e.setExpToDrop(0);
		}
	}
}