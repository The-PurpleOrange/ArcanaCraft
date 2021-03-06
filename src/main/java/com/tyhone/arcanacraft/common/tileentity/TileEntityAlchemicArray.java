package com.tyhone.arcanacraft.common.tileentity;

import java.util.ArrayList;
import java.util.List;

import com.tyhone.arcanacraft.api.recipe.RecipeAlchemicArray;
import com.tyhone.arcanacraft.common.tileentity.base.ModTileEntityBase;
import com.tyhone.arcanacraft.common.util.ItemStackUtil;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.UniversalBucket;

public class TileEntityAlchemicArray extends ModTileEntityBase {
	
	public void onActivated(EntityPlayer player, World worldObj){
		List<EntityItem> eItems = worldObj.getEntitiesWithinAABB(EntityItem.class,  this.getRenderBoundingBox().expand(1, 1, 1).expand(-1, -1, -1));
		List<ItemStack> itemStacks = null;
		
		if(eItems.size()>0){
			
			List<ItemStack> tempItemStacks = new ArrayList<ItemStack>();
			
			for(EntityItem entityItem : eItems){
				ItemStack itemStack = entityItem.getItem();
				tempItemStacks.add(itemStack.copy());
			}
			itemStacks = ItemStackUtil.compactItems(tempItemStacks);
			
			pos = this.getPos();
			
			RecipeAlchemicArray recipe = RecipeAlchemicArray.getRecipe((ArrayList<ItemStack>) itemStacks);
			if(recipe != null){
				for(EntityItem ei : eItems){
					if(ei.getItem().getItem() == Items.LAVA_BUCKET || 
							ei.getItem().getItem() == Items.WATER_BUCKET || 
							ei.getItem().getItem() == Items.MILK_BUCKET || 
							ei.getItem().getItem() == UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, FluidRegistry.WATER).getItem()){
						EntityItem bucket = new EntityItem(worldObj, pos.getX()+0.25, pos.getY()+0.25, pos.getZ()+0.25, new ItemStack(Items.BUCKET));
						worldObj.spawnEntity(bucket);
					}
					ei.setInvisible(true);
					ei.setDead();
				}
				EntityItem eOutput = new EntityItem(worldObj, pos.getX()+0.25, pos.getY()+0.25, pos.getZ()+0.25, recipe.getOutput());
				worldObj.spawnEntity(eOutput);
			}
		}
	}
}
