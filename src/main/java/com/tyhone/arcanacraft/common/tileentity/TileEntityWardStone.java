package com.tyhone.arcanacraft.common.tileentity;

import java.util.List;
import java.util.Random;

import com.tyhone.arcanacraft.client.ParticleWind;
import com.tyhone.arcanacraft.common.tileentity.base.ModTileEntityBase;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntityWardStone extends ModTileEntityBase implements ITickable{

	@Override
	public void update() {
		AxisAlignedBB bounding = new AxisAlignedBB(pos.up()).grow(0.1D, 3D, 0.1D);
		List<Entity> entities = world.getEntitiesWithinAABB(Entity.class, bounding);

		for(Entity entity : entities){
			if(entity instanceof EntityLivingBase && !(entity instanceof EntityPlayer)){
				double d0 = entity.posX - (double) pos.getX()-0.5D;
				double d1 = entity.posZ - (double) pos.getZ()-0.5D;
				
				float s = 1F;
				entity.motionX = d0/4;
				entity.motionZ = d1/4;
			}
			else if(!(entity instanceof EntityPlayer)){
				entity.setVelocity(entity.motionX/8, entity.motionY, entity.motionZ/8);
			}
		}
	}

}
