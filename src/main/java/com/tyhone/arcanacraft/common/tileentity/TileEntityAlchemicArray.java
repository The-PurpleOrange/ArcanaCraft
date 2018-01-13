package com.tyhone.arcanacraft.common.tileentity;

import java.util.ArrayList;
import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.recipe.RecipeAlchemicArray;
import com.tyhone.arcanacraft.api.recipe.RecipeRitualCircle;
import com.tyhone.arcanacraft.api.ritual.RitualBase;
import com.tyhone.arcanacraft.api.util.ItemStackUtil;
import com.tyhone.arcanacraft.common.rituals.RitualSummonRain;
import com.tyhone.arcanacraft.common.tileentity.base.ModTileEntityBase;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityAlchemicArray extends ModTileEntityBase {
	public void onActivated(EntityPlayer player, World worldObj){
		List<EntityItem> eItems = worldObj.getEntitiesWithinAABB(EntityItem.class,  this.getRenderBoundingBox().expand(1, 1, 1));
		List<ItemStack> itemStacks = null;
		boolean noItems = false;
		
		if(eItems.size()>0){
			
			List<ItemStack> tempItemStacks = new ArrayList<ItemStack>();
			
			for(EntityItem entityItem : eItems){
				ItemStack itemStack = entityItem.getItem();
				Item item = itemStack.getItem();
				if(item instanceof ItemBlock){
					item = (ItemBlock) item;
				}
				tempItemStacks.add(itemStack.copy());
			}
			itemStacks = ItemStackUtil.compactItems(tempItemStacks);
			
			pos = this.getPos();
			
			RecipeAlchemicArray recipe = RecipeAlchemicArray.getRecipe((ArrayList<ItemStack>) itemStacks);
			if(recipe != null){
				for(EntityItem ei : eItems){
					ei.setInvisible(true);
					ei.setDead();
				}
				EntityItem eOutput = new EntityItem(worldObj, pos.getX()+0.25, pos.getY()+0.25, pos.getZ()+0.25, recipe.getOutput());
				worldObj.spawnEntity(eOutput);
			}
		}
	}
}
