package com.tyhone.arcanacraft.client.proxy;

import com.tyhone.arcanacraft.client.TextureStitcherFX;
import com.tyhone.arcanacraft.client.render.ModTileEntitySpecialRender;
import com.tyhone.arcanacraft.client.render.TinktureColour;
import com.tyhone.arcanacraft.common.blocks.base.ModBlockBase;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.init.ModEntities;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.items.base.ModItemBase;
import com.tyhone.arcanacraft.common.proxy.CommonProxy;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        
        MinecraftForge.EVENT_BUS.register(new TextureStitcherFX());
        
        ModTileEntitySpecialRender.preInit();
        ModEntities.initModels();
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new TinktureColour(), ModItems.TINKTURE);
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        ModBlocks.getBlocks().forEach(ModBlockBase::initItemModel);
        ModItems.getItems().forEach(ModItemBase::initModelsAndVariants);
    }

}
