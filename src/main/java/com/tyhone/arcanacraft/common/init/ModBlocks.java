package com.tyhone.arcanacraft.common.init;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.blocks.base.ModBlockBase;
import com.tyhone.arcanacraft.common.blocks.blocks.BlockRedCoal;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockPedestal;

import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Arcanacraft.MODID)
public class ModBlocks {

	private static final List<ModBlockBase> BLOCKS = new ArrayList<>();
	
	public static final ModBlockBase RED_COAL_BLOCK = new BlockRedCoal();
	public static final ModBlockBase PEDESTAL = new BlockPedestal();
	
	private ModBlocks() {}
	
	public static Collection<ModBlockBase> getBlocks(){
		return BLOCKS;
	}
	
	public static void register(ModBlockBase item){
		BLOCKS.add(item);
	}
}
