package com.tyhone.arcanacraft.common.util;

import com.tyhone.arcanacraft.common.blocks.base.ModBlockEnum;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.items.base.ModItemBlockEnum;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockUtils {
	
	private BlockUtils() {}
	
	public static ItemBlock getItemBlockFor(Block block){
		return block instanceof ModBlockEnum ? new ModItemBlockEnum((ModBlockEnum) block) : new ItemBlock(block);
	}
	
	public static boolean checkIfPedestal(World world, BlockPos pos){
		if(world.getBlockState(pos).getBlock() == ModBlocks.PEDESTAL){
			return true;
		}
		if(world.getBlockState(pos).getBlock() == ModBlocks.PEDESTAL_SLAB){
			return true;
		}
		
		return false;
	}
	
	public static boolean isRedstoneOre(IBlockState state1, IBlockState state2) {
		if((state1.getBlock() == Blocks.LIT_REDSTONE_ORE || state1.getBlock() == Blocks.REDSTONE_ORE) && (state2.getBlock() == Blocks.LIT_REDSTONE_ORE || state2.getBlock() == Blocks.REDSTONE_ORE)) {
			return true;
		}
		return false;
	}
	
	public static boolean compareBlockState(IBlockState state1, IBlockState state2) {
		if(state1.getBlock() == state2.getBlock()) {
			return true;
		}
		return false;
	}
	
	public static boolean compareBlockStateWithMeta(IBlockState state1, IBlockState state2) {
		if(state1.getBlock() == state2.getBlock()) {
			if(state1.getBlock().getMetaFromState(state1) == state2.getBlock().getMetaFromState(state2)) {
				return true;
			}
		}
		return false;
	}
}
