package com.tyhone.arcanacraft.common.init;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.entity.EntityHomunculus;
import com.tyhone.arcanacraft.common.entity.RenderHomunculus;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModEntities {

	public static void init(){
		
		int id = 1;
		EntityRegistry.registerModEntity(new ResourceLocation(Arcanacraft.MODID, EntityHomunculus.NAME), EntityHomunculus.class, EntityHomunculus.NAME, id++, Arcanacraft.instance, 32, 3, true, 0x996600, 0x00ff00);
		//EntityRegistry.addSpawn(EntityHomunculus.class, 100, 1, 5, EnumCreatureType.MONSTER, Biomes.PLAINS, Biomes.FOREST, Biomes.FOREST_HILLS);
		LootTableList.register(EntityHomunculus.LOOT);
	}
	
	@SideOnly(Side.CLIENT)
    public static void initModels() {
        RenderingRegistry.registerEntityRenderingHandler(EntityHomunculus.class, RenderHomunculus.FACTORY);
    }
}
