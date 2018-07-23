package com.tyhone.arcanacraft.common.world.gen.feature;

import java.util.Random;

import com.tyhone.arcanacraft.common.blocks.blocks.BlockLog;
import com.tyhone.arcanacraft.common.init.ModBlocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenEvoliteTree extends WorldGenAbstractTree{

	private final int minTreeHeight;
	private final IBlockState wood;
	private final IBlockState sap;
	private final IBlockState leaves;
	
	public WorldGenEvoliteTree(boolean notify) {
		super(notify);
		this.minTreeHeight = 6;
		this.wood = ModBlocks.LOG.getDefaultState().withProperty(BlockLog.VARIANT, BlockLog.EnumLogType.EVOLITE_WOOD);
		this.sap = ModBlocks.LOG.getDefaultState().withProperty(BlockLog.VARIANT, BlockLog.EnumLogType.EVOLITE_SAP);
		this.leaves = ModBlocks.LEAVES.getDefaultState();
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		
		int i = rand.nextInt(3) + this.minTreeHeight;
		int x = position.getX();
		int y = position.getY();
		int z = position.getZ();
		boolean flag = true;
		
		if(y >= 1 && y + i - 1 <= worldIn.getHeight()) {
			for (int newY = y; newY <= y + 1 + i; newY++) {
				
				int k = 1;
				
				if(newY == y) {
					k = 0;
				}
				
				if (newY >= y + 1 + i - 2) {
					k = 2;
				}
				
				BlockPos.MutableBlockPos blockPos$mutableBlockPos = new BlockPos.MutableBlockPos();
				
				for(int newX = x - k; newX <= x + k && flag; newX++) {
					for(int newZ = z - k; newZ <=z + k && flag; newZ++) {
						if(newY >= 0 && newY < worldIn.getHeight()) {
							if(!this.isReplaceable(worldIn, blockPos$mutableBlockPos.setPos(newX, newY, newZ))) {
								flag = false;
							}
						}
						
						else {
							flag = false;
						}
					}
				}
			}
			
			if(!flag) {
				return false;
			}
			
			else {
				IBlockState state = worldIn.getBlockState(position.down());
				
				if(y < worldIn.getHeight() - i - 1) { //TODO block underneath is able to sustain tree
					state.getBlock().onPlantGrow(state, worldIn, position.down(), position);
					int k2 = 3;
					int l2 = 0;
					
					for(int i3 = y - 3 + i; i3 <= y + i; i3++) {
						int i4 = i3 - (y + i);
						int j1 = 1 - i4 / 2;
						
						for(int k1 = x - j1; k1 <= x + j1; k1++) {
							int l1 = k1 - x;
							
							for(int i2 = z - j1; i2 <= z + j1; i2++) {
								int j2 = i2 - z;
								
								if(Math.abs(l1) != j1 || Math.abs(j2) != j1 || rand.nextInt(2) != 0 && i4 != 0) {
									BlockPos blockPos = new BlockPos(k1, i3, i2);
									state = worldIn.getBlockState(blockPos);
									
									if(state.getBlock().isAir(state, worldIn, blockPos) || state.getBlock().isLeaves(state, worldIn, blockPos) || state.getMaterial() == Material.VINE) {

										this.setBlockAndNotifyAdequately(worldIn, blockPos, this.leaves);
									}
								}
							}
						}
					}
					
					for(int j3 = 0; j3 < i; j3++) {
						BlockPos upN = position.up(j3);
						state = worldIn.getBlockState(upN);
						
						if(state.getBlock().isAir(state, worldIn, upN) || state.getBlock().isLeaves(state, worldIn, upN) || state.getMaterial() == Material.VINE) {
							this.setBlockAndNotifyAdequately(worldIn, position.up(j3), rand.nextInt(3) != 0 ? this.wood : this.sap);
						}
					}
				}
			}
		}
		
		
		return flag;
	}

}
