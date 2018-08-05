package com.tyhone.arcanacraft.common.tileentity;

import java.util.ArrayList;
import java.util.Collections;

import com.tyhone.arcanacraft.common.blocks.blocks.BlockLog;
import com.tyhone.arcanacraft.common.blocks.blocks.BlockLog.EnumAxis;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.init.ModTinktureTypes;
import com.tyhone.arcanacraft.common.tileentity.base.ModTileEntityBase;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import scala.actors.threadpool.Arrays;

public class TileEntityTreeTap extends ModTileEntityBase implements ITickable{

	private final EnumFacing[] FACING = {EnumFacing.NORTH, EnumFacing.SOUTH, EnumFacing.EAST, EnumFacing.WEST};
	
	private static int timeleft = -1;
	//private static final String NBT_TIMELEFT = "timeleft";
	
	@Override
	public void update() {
		if(!world.isRemote) {
			if(findSap()) {
				if(timeleft < 0) {
					timeleft = (200 + (this.getWorld().rand.nextInt(20)*10));
				}
				else {
					if(timeleft == 0) {
						for(int y = 1; y < 5; y++) {
							//Arcanacraft.log("block: " + this.pos.offset(EnumFacing.DOWN, y) + ", " + this.getWorld().getBlockState(this.pos.offset(EnumFacing.DOWN, y)));
							if(this.getWorld().getBlockState(this.pos.offset(EnumFacing.DOWN, y)) == ModBlocks.JAR.getDefaultState()) {
								TileEntityJar te = (TileEntityJar) this.getWorld().getTileEntity(this.pos.offset(EnumFacing.DOWN, y));
								if(te.getTinktureAmount() < te.getMaxFluid()) {
									te.addTinktureAmount(ModTinktureTypes.EVOLITE_SAP, 1);
									if(this.getWorld().rand.nextInt(40) == 0) {
										destroySap();
									}
								}
								break;
							}
							else if (!this.getWorld().isAirBlock(this.pos.offset(EnumFacing.DOWN, y))){
								break;
							}
						}
					}
					timeleft--;
				}
			}
		}
	}
	
	private boolean findSap() {
		for(EnumFacing face : FACING) {
			if(this.getWorld().getBlockState(this.pos.offset(face)).getBlock() == ModBlocks.LOG) {
				if(this.getWorld().getBlockState(this.pos.offset(face)).getProperties().get(BlockLog.VARIANT) == BlockLog.EnumLogType.EVOLITE_SAP) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean destroySap() {
		ArrayList<EnumFacing> rFacing = new ArrayList<EnumFacing>(Arrays.asList(FACING));
		Collections.shuffle(rFacing);
		for(EnumFacing face : rFacing) {
			BlockPos place = this.pos.offset(face);
			if(this.getWorld().getBlockState(place).getBlock() == ModBlocks.LOG) {
				if(this.getWorld().getBlockState(place).getProperties().get(BlockLog.VARIANT) == BlockLog.EnumLogType.EVOLITE_SAP) {
					this.getWorld().setBlockState(place, ModBlocks.LOG.getDefaultState()
							.withProperty(BlockLog.VARIANT, BlockLog.EnumLogType.EVOLITE_WOOD)
							.withProperty(BlockLog.LOG_AXIS, (EnumAxis) this.getWorld().getBlockState(place).getProperties().get(BlockLog.LOG_AXIS)));
					return true;
				}
				
			}
		}
		return false;
	}
}
