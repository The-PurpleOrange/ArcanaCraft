package com.tyhone.arcanacraft.common.items.items;

import com.tyhone.arcanacraft.common.entity.EntityThaumonuclearBall;
import com.tyhone.arcanacraft.common.items.base.ModItemBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemThaumonuclearBall extends ModItemBase{

	public ItemThaumonuclearBall() {
		super("thaumonuclear_ball");
		this.maxStackSize = 16;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack itemStack = player.getHeldItem(hand);
		
		if(player.capabilities.isCreativeMode) {
			itemStack.shrink(1);
		}
		
		world.playSound(player, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_EGG_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		
		if(!world.isRemote) {
			EntityThaumonuclearBall entityBall = new EntityThaumonuclearBall(world, player);
			entityBall.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
			world.spawnEntity(entityBall);
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStack);
	}
	
}
