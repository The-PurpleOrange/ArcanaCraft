package com.tyhone.arcanacraft.common.tileentity;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.Config;
import com.tyhone.arcanacraft.common.handler.OreDictionaryHandler;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.tileentity.base.ModTileEntityBase;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.ITickable;

public class TileEntityQuarryTable extends ModTileEntityBase implements ITickable{

	@Override
	public void update() {
		if(!world.isRemote) {
			if(world.getBlockState(pos.north()).getBlock() == ModBlocks.ALCHEMIC_CONTRAPTION && world.getBlockState(pos.south()).getBlock() == ModBlocks.ALCHEMIC_CONTRAPTION && world.getBlockState(pos.east()).getBlock() == ModBlocks.ALCHEMIC_CONTRAPTION && world.getBlockState(pos.west()).getBlock() == ModBlocks.ALCHEMIC_CONTRAPTION)
			if(world.getBlockState(pos.up()).getBlock() == Blocks.AIR && world.getTotalWorldTime() % 20 == 0) {
				world.setBlockState(pos.up(), getOreNameWithChance());
			}
		}
	}
	
	private IBlockState getOreNameWithChance() {
		Random rand = new Random();
		int valueSelected = rand.nextInt(Config.quarryOreMapTotalValue);

		Arcanacraft.log("Value Selected: " + valueSelected);
		
		int valueTotal = 0;
		for(Entry<String, Integer> entry : Config.quarryOreMap.entrySet()) {
			valueTotal += entry.getValue();
			if(valueSelected <= valueTotal) {
				return OreDictionaryHandler.getBlockFromOreDictionaryEntry(entry.getKey()).getDefaultState();
			}
		}
		return null;
	}

}
