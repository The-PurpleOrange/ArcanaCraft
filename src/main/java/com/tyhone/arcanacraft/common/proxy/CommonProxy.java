package com.tyhone.arcanacraft.common.proxy;

import java.io.File;

import org.apache.logging.log4j.Level;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.Config;
import com.tyhone.arcanacraft.common.blocks.base.ModBlockEnum;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.init.ModCustomRegistries;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.init.ModRecipes;
import com.tyhone.arcanacraft.common.init.ModRituals;
import com.tyhone.arcanacraft.common.init.ModTileEntities;
import com.tyhone.arcanacraft.common.util.BlockUtils;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class CommonProxy {

    // Config instance
    public static Configuration config;

    public void preInit(FMLPreInitializationEvent e) {
        File directory = e.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "modtut.cfg"));
        Config.readConfig();
        
        //Custom test
        ModCustomRegistries.register();
        
    }

    public void init(FMLInitializationEvent e) {
    	ModRecipes.init();
    	ModRituals.init();
    }

    public void postInit(FMLPostInitializationEvent e) {
        if (config.hasChanged()) {
            config.save();
        }
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
    	for(Block block : ModBlocks.getBlocks()){
			event.getRegistry().register(block);
    	}
    	
    	ModTileEntities.register();
    }
    
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
    	ModItems.getItems().forEach(event.getRegistry()::register);
		for(Block block : ModBlocks.getBlocks()){
			event.getRegistry().register(BlockUtils.getItemBlockFor(block).setRegistryName(block.getRegistryName()));
			//event.getRegistry().register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
			
		}
    }

}
