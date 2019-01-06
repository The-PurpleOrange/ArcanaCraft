 package com.tyhone.arcanacraft.common.init;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.blocks.base.ModBlockBase;
import com.tyhone.arcanacraft.common.blocks.blocks.BlockAlchemicContraption;
import com.tyhone.arcanacraft.common.blocks.blocks.BlockAlchemicGlass;
import com.tyhone.arcanacraft.common.blocks.blocks.BlockAlchemicLight;
import com.tyhone.arcanacraft.common.blocks.blocks.BlockAlchemicStone;
import com.tyhone.arcanacraft.common.blocks.blocks.BlockAlchemicalCoal;
import com.tyhone.arcanacraft.common.blocks.blocks.BlockAquelsa;
import com.tyhone.arcanacraft.common.blocks.blocks.BlockBloodStone;
import com.tyhone.arcanacraft.common.blocks.blocks.BlockChalk;
import com.tyhone.arcanacraft.common.blocks.blocks.BlockFeyStone;
import com.tyhone.arcanacraft.common.blocks.blocks.BlockFleshyBlock;
import com.tyhone.arcanacraft.common.blocks.blocks.BlockGrowthBlock;
import com.tyhone.arcanacraft.common.blocks.blocks.BlockLeaves;
import com.tyhone.arcanacraft.common.blocks.blocks.BlockLog;
import com.tyhone.arcanacraft.common.blocks.blocks.BlockRedCoal;
import com.tyhone.arcanacraft.common.blocks.blocks.BlockSapling;
import com.tyhone.arcanacraft.common.blocks.blocks.BlockSoulStone;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockAlchemicArray;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockAlembic;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockDeconstructionTable;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockDisapearingBlock;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockElevator;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockGrandRitualCircle;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockHeavyChest;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockInfusionAltar;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockInlayTable;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockJar;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockLensReceptacle;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockPedestal;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockPedestalSlab;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockQuarryTable;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockReapearingBlock;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockRitualCircle;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockSoulAltar;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockSpiritSkull;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockTransmutationAltar;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockTreeTap;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockVacuumChest;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockVacuumChestEnder;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockWardStone;
import com.tyhone.arcanacraft.common.blocks.tiles.BlockWarpCircle;

import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Arcanacraft.MODID)
public class ModBlocks {

	private static final List<ModBlockBase> BLOCKS = new ArrayList<>();

	public static final ModBlockBase RED_COAL_BLOCK = new BlockRedCoal();
	public static final ModBlockBase ALCHEMICAL_COAL_BLOCK = new BlockAlchemicalCoal();

	public static final ModBlockBase ALCHEMIC_GLASS = new BlockAlchemicGlass();
	public static final ModBlockBase ALCHEMIC_STONE = new BlockAlchemicStone();
	public static final ModBlockBase BLOOD_STONE = new BlockBloodStone();
	public static final ModBlockBase SOUL_STONE = new BlockSoulStone();
	public static final ModBlockBase FEY_STONE = new BlockFeyStone();

	public static final ModBlockBase FLESHY_BLOCK = new BlockFleshyBlock();
	public static final ModBlockBase ALCHEMIC_CONTRAPTION = new BlockAlchemicContraption();
	public static final ModBlockBase AQUELSA = new BlockAquelsa();
	public static final ModBlockBase GROWTH_BLOCK = new BlockGrowthBlock();

	public static final ModBlockBase WARD_STONE = new BlockWardStone();
	public static final ModBlockBase ELEVATOR = new BlockElevator();

	public static final ModBlockBase CHALK_BLOCK = new BlockChalk();
	public static final ModBlockBase ALCHEMIC_LIGHT = new BlockAlchemicLight();

	public static final ModBlockBase ALCHEMIC_ARRAY = new BlockAlchemicArray();
	public static final ModBlockBase RITUAL_CIRCLE = new BlockRitualCircle();
	public static final ModBlockBase GRAND_RITUAL_CIRCLE = new BlockGrandRitualCircle();
	public static final ModBlockBase WARP_CIRCLE = new BlockWarpCircle();
	
	public static final ModBlockBase LENS_RECEPTACLE = new BlockLensReceptacle();
	public static final ModBlockBase DECONSTRUCTION_TABLE = new BlockDeconstructionTable();

	public static final ModBlockBase TRANSMUTATION_ALTAR = new BlockTransmutationAltar();
	public static final ModBlockBase INFUSION_ALTAR = new BlockInfusionAltar();
	public static final ModBlockBase SOUL_ALTAR = new BlockSoulAltar();

	public static final ModBlockBase ALEMBIC = new BlockAlembic();

	public static final ModBlockBase INLAY_TABLE = new BlockInlayTable();

	public static final ModBlockBase QUARRY_TABLE = new BlockQuarryTable();

	public static final ModBlockBase HEAVY_CHEST = new BlockHeavyChest();
	public static final ModBlockBase VACUUM_CHEST = new BlockVacuumChest();
	public static final ModBlockBase VACUUM_CHEST_ENDER = new BlockVacuumChestEnder();
	
	public static final ModBlockBase JAR = new BlockJar();
	public static final ModBlockBase PEDESTAL = new BlockPedestal();
	public static final ModBlockBase PEDESTAL_SLAB = new BlockPedestalSlab();

	public static final ModBlockBase TREE_TAP = new BlockTreeTap();

	public static final ModBlockBase DISAPEARING_BLOCK = new BlockDisapearingBlock();
	public static final ModBlockBase REAPEARING_BLOCK = new BlockReapearingBlock();

	public static final ModBlockBase SPIRIT_SKULL_BLOCK = new BlockSpiritSkull();

	public static final ModBlockBase SAPLING = new BlockSapling();
	public static final ModBlockBase LOG = new BlockLog();
	public static final ModBlockBase LEAVES = new BlockLeaves();
	
	private ModBlocks() {}
	
	public static Collection<ModBlockBase> getBlocks(){
		return BLOCKS;
	}
	
	public static void register(ModBlockBase item){
		BLOCKS.add(item);
	}
}
