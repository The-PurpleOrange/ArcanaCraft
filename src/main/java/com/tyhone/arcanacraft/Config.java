package com.tyhone.arcanacraft;

import org.apache.logging.log4j.Level;

import com.tyhone.arcanacraft.common.proxy.CommonProxy;

import net.minecraftforge.common.config.Configuration;

public class Config {
	
	private static final String CATEGORY_GENERAL = "general";
	
	public static boolean testValue = true;
	
	
	public static void readConfig(){
		Configuration cfg = CommonProxy.config;
		try {
			cfg.load();
			initGeneralConfig(cfg);
		} catch (Exception e){
			Arcanacraft.logger.error("Problem loading config file", e);
		} finally {
			if(cfg.hasChanged()){
				cfg.save();
			}
		}
	}

	private static void initGeneralConfig(Configuration cfg) {
		cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General configuration");
		testValue = cfg.getBoolean("testCFG", CATEGORY_GENERAL, testValue, "Boolean test cfg setting");
	}
}
