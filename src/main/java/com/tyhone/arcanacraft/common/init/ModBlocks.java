package com.tyhone.arcanacraft.common.init;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.blocks.base.ModBlockBase;
import com.tyhone.arcanacraft.common.blocks.blocks.BlockRedCoal;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@GameRegistry.ObjectHolder(Arcanacraft.MODID)
public class ModBlocks {

	

	private static final List<ModBlockBase> ITEMS = new ArrayList<>();
	
	public static final ModBlockBase RED_COAL_BLOCK = new BlockRedCoal();
	
	private ModBlocks() {}
	
	public static Collection<ModBlockBase> getBlocks(){
		return ITEMS;
	}
	
	public static void register(ModBlockBase item){
		ITEMS.add(item);
	}
}
