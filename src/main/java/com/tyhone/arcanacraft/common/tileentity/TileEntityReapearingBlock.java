package com.tyhone.arcanacraft.common.tileentity;

import com.tyhone.arcanacraft.common.blocks.tiles.BlockReapearingBlock;
import com.tyhone.arcanacraft.common.tileentity.base.ModTileEntityBase;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

public class TileEntityReapearingBlock extends ModTileEntityBase implements ITickable{

	private final String NBT_DISAPEAR_TIME = "disapear_time";
	private final String NBT_REAPEAR_TIME = "reapear_time";
	private final String NBT_ACTIVATED = "activate";
	
	private final int REAPEAR_TIMER = 40;
	private final int TIMER = 10;
	private final int INIT_TIMER = 40;
	int disapearTime = 0;
	int reapearTime = 0;
	boolean activated = false;
	
	@Override
	public void update() {
		if(!world.isRemote & this.activated) {
			if(this.disapearTime > 0) {
				this.disapearTime--;
			}
			if(this.disapearTime == 0){
				this.disapearTime = -1;
				BlockReapearingBlock.activateAdjacentBlocks(world, pos);
			}
			if(this.reapearTime>0) {
				this.reapearTime--;
			}
			else {
				reset();
			}
			
			this.markForClean();
		}
	}

	public void reset() {
		this.disapearTime = 0;
		this.reapearTime = 0;
		this.activated = false;
		this.markForClean();
	}
	
	public void setTimer(){
		this.disapearTime = this.TIMER;
		setActivated(true);
	}
	public void setInitTimer(){
		this.disapearTime = this.INIT_TIMER;
		setActivated(true);
	}
	
	public void setActivated(boolean active) {
		this.activated = active;
		this.reapearTime = this.REAPEAR_TIMER;
		this.markForClean();
	}
	
	public boolean isActive() {
		return this.activated;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		
		if(compound.hasKey(NBT_ACTIVATED)) {
			this.activated = compound.getBoolean(NBT_ACTIVATED);
			this.disapearTime = compound.getInteger(NBT_DISAPEAR_TIME);
			this.reapearTime = compound.getInteger(NBT_REAPEAR_TIME);
		}
		else {
			this.activated = false;
			this.disapearTime = 0;
			this.reapearTime = 0;
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
		compound.setBoolean(NBT_ACTIVATED, isActive());
		compound.setInteger(NBT_REAPEAR_TIME, reapearTime);
		compound.setInteger(NBT_DISAPEAR_TIME, disapearTime);
		return compound;
	}
}
