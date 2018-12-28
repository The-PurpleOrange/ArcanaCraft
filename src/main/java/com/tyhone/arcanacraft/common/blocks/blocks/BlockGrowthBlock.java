package com.tyhone.arcanacraft.common.blocks.blocks;

import java.util.Random;

import com.tyhone.arcanacraft.common.blocks.base.ModBlockBase;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCactus;
import net.minecraft.block.BlockReed;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

public class BlockGrowthBlock extends ModBlockBase{
    
	public BlockGrowthBlock() {
		super("growth_block");
		setTickRandomly(true);
	}
	
	 @Override
	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable){ 
		return true;
    }
	 
	 @Override
	public void randomTick(World world, BlockPos pos, IBlockState state, Random rand) {
		 IBlockState plant = world.getBlockState(pos.up());
		 if(plant != null) {
			 if(plant.getBlock() instanceof IGrowable && ((IGrowable)plant.getBlock()).canGrow(world, pos.up(), plant, false)){
				 if(rand.nextInt(12) == 0) {
					 ((IGrowable)plant.getBlock()).grow(world, rand, pos.up(), plant);
				 }
			 }
			 else if(plant.getBlock() instanceof BlockReed || plant.getBlock() instanceof BlockCactus){
				 int size = 1;
				 boolean sameBlock = true;
				 
				 while(sameBlock) {
					 if(world.getBlockState(pos.up(size)).getBlock() != null && world.getBlockState(pos.up(size)).getBlock() == plant.getBlock()){
						 for(int i = 0; i < 3; i++) {
							 //if(plant.getBlock().canSustainPlant(state, world, pos, null, (IPlantable) plant.getBlock())) {
								 world.getBlockState(pos.up(size)).getBlock().updateTick(world, pos.up(size), world.getBlockState(pos.up(size)), rand);
							 //}
						 }
						 size++;
						 
					 }else {
						 sameBlock = false;
					 }
				 }
			 }
			 else {
				 for(int i = 0; i < 3; i++) {
					 plant.getBlock().updateTick(world, pos.up(), plant, rand);
				 }
			 }
		 }
		 super.randomTick(world, pos, state, rand);
	}
}
