package com.tyhone.arcanacraft.common.tileentity;

import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.tileentity.base.ModTileEntityBase;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntityElevator extends ModTileEntityBase implements ITickable{

	@Override
	public void update() {
		int i = 0;
		for(i = 0; i < 8; i++){
			if(i != 0 && !world.getBlockState(pos.add(0, i+1, 0)).getBlock().isPassable(world, pos)){
				break;
			}
		}
		AxisAlignedBB bounding = new AxisAlignedBB(pos.up()).expand(0, i, 0);
		List<Entity> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, bounding);

		//Arcanacraft.log(bounding.toString());
		//Arcanacraft.log(entities.toString());

		for(Entity entity : entities){
			boolean doNothing = false;
			if(!(entity instanceof EntityPlayer && ((EntityPlayer) entity).capabilities.isFlying)){
				if(world.isBlockPowered(pos)){

					entity.motionY = -0.3D;
					entity.fallDistance = 0;
				}
				else{
					if(entity.motionY < 0.3D){
						entity.motionY+=0.1D;
					}
					else{
						entity.motionY = 0.3D;
					}
				}
			}
		}
	}

}
