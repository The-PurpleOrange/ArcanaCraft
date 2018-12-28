package com.tyhone.arcanacraft.common.proxy;

import java.io.File;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.Config;
import com.tyhone.arcanacraft.common.handler.GuiHandler;
import com.tyhone.arcanacraft.common.handler.OreDictionaryHandler;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.init.ModEntities;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.init.ModRecipes;
import com.tyhone.arcanacraft.common.init.ModRitualRecipes;
import com.tyhone.arcanacraft.common.init.ModRituals;
import com.tyhone.arcanacraft.common.init.ModTileEntities;
import com.tyhone.arcanacraft.common.init.ModTinktureTypes;
import com.tyhone.arcanacraft.common.items.ModToolMaterial;
import com.tyhone.arcanacraft.common.reference.PickaxeOreValues;
import com.tyhone.arcanacraft.common.util.BlockUtils;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod.EventBusSubscriber
public class CommonProxy {

    // Config instance
    public static Configuration config;

    public void preInit(FMLPreInitializationEvent e) {
        File directory = e.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "modtut.cfg"));
        Config.readConfig();

    	ModTinktureTypes.registerAll();
    	ModEntities.init();
    }

    public void init(FMLInitializationEvent e) {
    	OreDictionaryHandler.registerOreDictionary();
    	ModRecipes.init();
    	ModRituals.init();
    	ModToolMaterial.initRepairMaterial();
    	ModRitualRecipes.init();
    	NetworkRegistry.INSTANCE.registerGuiHandler(Arcanacraft.instance, new GuiHandler());
    }

    public void postInit(FMLPostInitializationEvent e) {
    	PickaxeOreValues.init();
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
