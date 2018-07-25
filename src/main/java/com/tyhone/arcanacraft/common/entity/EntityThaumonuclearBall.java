package com.tyhone.arcanacraft.common.entity;

import java.util.ArrayList;
import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.client.ParticleOrb;
import com.tyhone.arcanacraft.common.init.ModBlocks;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import scala.Int;

public class EntityThaumonuclearBall extends EntityThrowable{

	public final static String NAME = "thaumonuclear_ball";
	public final static String DISPLAY_NAME = (I18n.translateToLocal("entity." + Arcanacraft.MODNAME + ":" + NAME + ".name"));
	
	public EntityThaumonuclearBall(World world) {
		super(world);
	}
	
	public EntityThaumonuclearBall(World worldIn, EntityLivingBase throwerIn) {
		super(worldIn, throwerIn);
	}
	
	public EntityThaumonuclearBall(World world, double x, double y, double z) {
		super(world, x, y, z);
	}
	
	@Override
	protected void entityInit(){
		super.entityInit();
	}
	
	@Override
	public void onUpdate() {

		double x = this.posX;
		double y = this.posY;
		double z = this.posZ;
		
        ParticleOrb trail = new ParticleOrb(world, x, y, z, 0, 0, 0, 2F, 0xbc00bc, 0xbcffff, 5);
        Minecraft.getMinecraft().effectRenderer.addEffect(trail);
        
		super.onUpdate();
	}
	
	@Override
	protected void onImpact(RayTraceResult result) {
		
		if(!this.world.isRemote) {
			
			List<BlockPos> posList = new ArrayList<BlockPos>();
			
			for(int newX = -1; newX <= 1; newX++) {
				for(int newY = -1; newY <= 1; newY++) {
					for(int newZ = -1; newZ <=1; newZ++) {
						
						BlockPos blockpos = new BlockPos(this.posX + newX, this.posY + newY, this.posZ + newZ);
						IBlockState iblockstate = world.getBlockState(blockpos);
						
						Arcanacraft.log(iblockstate.toString());
						
						if(iblockstate.getBlock() == Blocks.SAPLING && iblockstate.getValue(BlockSapling.TYPE).equals(BlockPlanks.EnumType.BIRCH)) {
							world.setBlockToAir(blockpos);
							posList.add(blockpos);
						}
					}
				}
			}
			
			this.world.createExplosion(this, this.posX, this.posY, this.posZ, 1F, true);
			
			Arcanacraft.log(Integer.toString(posList.size()));
			if(!posList.isEmpty()) {
				for(BlockPos spawnPos : posList) {
					EntityItem eItem = new EntityItem(world, spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), new ItemStack(ModBlocks.EVOLITE_SAPLING));
					this.world.spawnEntity(eItem);
				}
			}
			
			this.world.setEntityState(this, (byte)3);
			this.setDead();
		}
	}

	@Override
	public String getName(){
    	return I18n.translateToLocal("entity." + Arcanacraft.MODNAME + ":" + NAME + ".name");
    }
	
	public ResourceLocation getRegistryName(){
    	return new ResourceLocation("entity." + Arcanacraft.MODNAME + ":" + NAME + ".name");
    }

}
