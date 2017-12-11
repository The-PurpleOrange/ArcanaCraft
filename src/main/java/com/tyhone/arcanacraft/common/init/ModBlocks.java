package com.tyhone.arcanacraft.common.init;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.blocks.base.ModBlockBase;
import com.tyhone.arcanacraft.common.blocks.blocks.BlockAlchemicStone;
import com.tyhone.arcanacraft.common.blocks.blocks.BlockAlchemicalCoal;
import com.tyhone.arcanacraft.common.blocks.blocks.BlockRedCoal;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockDeconstructionTable;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockInfusionAltar;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockLensReceptacle;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockPedestal;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockPedestalSlab;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockSoulAltar;

import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Arcanacraft.MODID)
public class ModBlocks {

	private static final List<ModBlockBase> BLOCKS = new ArrayList<>();

	public static final ModBlockBase RED_COAL_BLOCK = new BlockRedCoal();
	public static final ModBlockBase ALCHEMICAL_COAL_BLOCK = new BlockAlchemicalCoal();
	public static final ModBlockBase ALCHEMIC_STONE = new BlockAlchemicStone();
	

	public static final ModBlockBase LENS_RECEPTACLE = new BlockLensReceptacle();
	public static final ModBlockBase DECONSTRUCTION_TABLE = new BlockDeconstructionTable();

	public static final ModBlockBase INFUSION_ALTAR = new BlockInfusionAltar();
	public static final ModBlockBase SOUL_ALTAR = new BlockSoulAltar();
	
	public static final ModBlockBase PEDESTAL = new BlockPedestal();
	public static final ModBlockBase PEDESTAL_SLAB = new BlockPedestalSlab();
	
	private ModBlocks() {}
	
	public static Collection<ModBlockBase> getBlocks(){
		return BLOCKS;
	}
	
	public static void register(ModBlockBase item){
		BLOCKS.add(item);
	}
}
