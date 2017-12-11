package com.tyhone.arcanacraft.common.util;

import org.apache.logging.log4j.Level;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.init.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockUtils {
	
	private BlockUtils() {}
	
	/*public static ItemBlock getItemBlockFor(Block block){
		return block instanceof ModBlockEnum ? new ModItemBlockEnum((ModBlockEnum) block) : new ItemBlock(block);
	}*/
	
	public static boolean checkIfPedestal(World world, BlockPos pos){
		if(world.getBlockState(pos).getBlock() == ModBlocks.PEDESTAL){
			return true;
		}
		if(world.getBlockState(pos).getBlock() == ModBlocks.PEDESTAL_SLAB){
			return true;
		}
		
		return false;
	}
}
