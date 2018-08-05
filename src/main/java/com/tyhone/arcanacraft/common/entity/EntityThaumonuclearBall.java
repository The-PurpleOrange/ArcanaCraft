package com.tyhone.arcanacraft.common.entity;

import java.util.ArrayList;
import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.client.ParticleOrb;
import com.tyhone.arcanacraft.common.init.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkHandshakeEstablished;

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
			
			List<EntityItem> eItems = getBirchEntityList(result.getBlockPos());
			List<BlockPos> posList = getBirchPosList();
			
			this.world.createExplosion(this, this.posX, this.posY, this.posZ, 1F, true);
			
			if(!posList.isEmpty()) {
				for(BlockPos spawnPos : posList) {
					EntityItem eItem = new EntityItem(world, spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), new ItemStack(ModBlocks.SAPLING));
					this.world.spawnEntity(eItem);
				}
			}

			Arcanacraft.log(eItems.toString());
			if(!eItems.isEmpty()) {
				for(EntityItem eitem : eItems) {
					this.world.spawnEntity(eitem);
				}
			}
			
			this.world.setEntityState(this, (byte)3);
			this.setDead();
		}
	}

	private List<EntityItem> getBirchEntityList(BlockPos pos) {
		List<EntityItem> eItems = world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(pos).expand(1, 1, 1).expand(-1, -1, -1));
		List<EntityItem> neweItems = new ArrayList<>();
		
		if(eItems.size()>0) {
			for(EntityItem entityItem : eItems) {
				Item item = entityItem.getItem().getItem();
				
				if(item instanceof ItemBlock){
					if(item == Item.getItemFromBlock(Blocks.SAPLING) && entityItem.getItem().getMetadata() == (BlockPlanks.EnumType.BIRCH.getMetadata())) {
						ItemStack stack = new ItemStack(ModBlocks.SAPLING, entityItem.getItem().getCount());
						neweItems.add(new EntityItem(world, entityItem.posX, entityItem.posY, entityItem.posZ, stack));
						entityItem.setDead();
					}
				}
			}
		}
		return neweItems;
	}

	private List<BlockPos> getBirchPosList() {
		List<BlockPos> posList = new ArrayList<>();
		
		for(int newX = -1; newX <= 1; newX++) {
			for(int newY = -1; newY <= 1; newY++) {
				for(int newZ = -1; newZ <=1; newZ++) {
					
					BlockPos blockpos = new BlockPos(this.posX + newX, this.posY + newY, this.posZ + newZ);
					IBlockState iblockstate = world.getBlockState(blockpos);
					
					if(iblockstate.getBlock() == Blocks.SAPLING && iblockstate.getValue(BlockSapling.TYPE).equals(BlockPlanks.EnumType.BIRCH)) {
						world.setBlockToAir(blockpos);
						posList.add(blockpos);
					}
				}
			}
		}
		return posList;
	}

	@Override
	public String getName(){
    	return I18n.translateToLocal("entity." + Arcanacraft.MODNAME + ":" + NAME + ".name");
    }
	
	public ResourceLocation getRegistryName(){
    	return new ResourceLocation("entity." + Arcanacraft.MODNAME + ":" + NAME + ".name");
    }

}
