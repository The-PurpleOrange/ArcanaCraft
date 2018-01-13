package com.tyhone.arcanacraft.common.init;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.blocks.base.ModBlockBase;
import com.tyhone.arcanacraft.common.blocks.base.ModBlockTileEntityBase;
import com.tyhone.arcanacraft.common.blocks.blocks.BlockAlchemicGlass;
import com.tyhone.arcanacraft.common.blocks.blocks.BlockAlchemicStone;
import com.tyhone.arcanacraft.common.blocks.blocks.BlockAlchemicalCoal;
import com.tyhone.arcanacraft.common.blocks.blocks.BlockChalk;
import com.tyhone.arcanacraft.common.blocks.blocks.BlockRedCoal;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockAlchemicArray;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockDeconstructionTable;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockGrandRitualCircle;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockInfusionAltar;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockJar;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockLensReceptacle;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockPedestal;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockPedestalSlab;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockRitualCircle;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockSoulAltar;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockSpiritSkull;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockTransmutationAltar;

import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Arcanacraft.MODID)
public class ModBlocks {

	private static final List<ModBlockBase> BLOCKS = new ArrayList<>();

	public static final ModBlockBase RED_COAL_BLOCK = new BlockRedCoal();
	public static final ModBlockBase ALCHEMICAL_COAL_BLOCK = new BlockAlchemicalCoal();
	
	public static final ModBlockBase ALCHEMIC_STONE = new BlockAlchemicStone();
	public static final ModBlockBase ALCHEMIC_GLASS = new BlockAlchemicGlass();

	public static final ModBlockBase CHALK_BLOCK = new BlockChalk();

	public static final ModBlockBase ALCHEMIC_ARRAY = new BlockAlchemicArray();
	public static final ModBlockBase RITUAL_CIRCLE = new BlockRitualCircle();
	public static final ModBlockBase GRAND_RITUAL_CIRCLE = new BlockGrandRitualCircle();
	
	public static final ModBlockBase LENS_RECEPTACLE = new BlockLensReceptacle();
	public static final ModBlockBase DECONSTRUCTION_TABLE = new BlockDeconstructionTable();

	public static final ModBlockBase TRANSMUTATION_ALTAR = new BlockTransmutationAltar();
	public static final ModBlockBase INFUSION_ALTAR = new BlockInfusionAltar();
	public static final ModBlockBase SOUL_ALTAR = new BlockSoulAltar();

	public static final ModBlockBase JAR = new BlockJar();
	public static final ModBlockBase PEDESTAL = new BlockPedestal();
	public static final ModBlockBase PEDESTAL_SLAB = new BlockPedestalSlab();
	

	public static final ModBlockBase SPIRIT_SKULL_BLOCK = new BlockSpiritSkull();
	
	private ModBlocks() {}
	
	public static Collection<ModBlockBase> getBlocks(){
		return BLOCKS;
	}
	
	public static void register(ModBlockBase item){
		BLOCKS.add(item);
	}
}
