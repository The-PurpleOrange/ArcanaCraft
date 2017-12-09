package com.tyhone.arcanacraft;


import org.apache.logging.log4j.Logger;

import com.tyhone.arcanacraft.common.proxy.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Arcanacraft.MODID, name = Arcanacraft.MODNAME, version = Arcanacraft.MODVERSION, dependencies = "required-after:forge@[14.23.1.2562,)", useMetadata = true)
public class Arcanacraft {

    public static final String MODID = "arcanacraft";
    public static final String MODNAME = "Arcanacraft";
    public static final String MODVERSION = "0.0.1";

    @SidedProxy(clientSide = "com.tyhone.arcanacraft.client.proxy.ClientProxy", serverSide = "com.tyhone.arcanacraft.common.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static Arcanacraft instance;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
    }
}
