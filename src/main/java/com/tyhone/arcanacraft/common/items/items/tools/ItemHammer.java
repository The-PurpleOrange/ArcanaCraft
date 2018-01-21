package com.tyhone.arcanacraft.common.items.items.tools;

import java.util.List;

import com.tyhone.arcanacraft.api.recipe.RecipeHammer;
import com.tyhone.arcanacraft.common.items.base.ModItemBase;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemHammer extends ModItemBase{
	
	public ItemHammer(){
		super("hammer");
		setMaxStackSize(1);
		setMaxDamage(63);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(!worldIn.isRemote){
        	List<EntityItem> eItems = player.getEntityWorld().getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(pos.add(0, 1, 0)));
			scan_loop:
			for(EntityItem eItem : eItems){
				ItemStack itemStack = eItem.getItem();
				RecipeHammer recipe = RecipeHammer.getRecipe(itemStack);
				if(recipe!=null){
					ItemStack output = recipe.getOutput().copy();
					Entity newEItem = new EntityItem(worldIn, eItem.posX, eItem.posY, eItem.posZ, output);
					worldIn.spawnEntity(newEItem);
					if(itemStack.getCount()>1){
						itemStack.setCount(itemStack.getCount()-1);
					}
					else{
						eItem.setDead();
					}
					player.getHeldItem(hand).damageItem(1, player);
					break scan_loop;
				}
			}
        }
        return EnumActionResult.PASS;
    }
	
}
