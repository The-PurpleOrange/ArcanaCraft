package com.tyhone.arcanacraft.common.tileentity;

import com.tyhone.arcanacraft.common.tileentity.base.ModTileEntityBase;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityDisapearingBlock extends ModTileEntityBase implements ITickable{

	int timer = 1;
	
	@Override
	public void update() {
		this.timer--;
		if(this.timer<=0){
			world.setBlockState(pos, Blocks.AIR.getDefaultState());
			world.setTileEntity(pos, null);
		}
	}
	
	public void setTimer(int i){
		this.timer = i;
	}
}
