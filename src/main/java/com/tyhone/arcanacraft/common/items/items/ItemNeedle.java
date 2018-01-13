package com.tyhone.arcanacraft.common.items.items;

import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.items.base.ModItemBase;
import com.tyhone.arcanacraft.common.reference.Names;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemNeedle extends ModItemBase{

	public ItemNeedle() {
		super("needle", Names.MetaItems.NEEDLE);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		if(player.isSneaking()){
			ItemStack stack = player.getHeldItem(hand);
			
			player.attackEntityFrom(DamageSource.GENERIC, 1F);
			
			switch(stack.getMetadata()){
				case 0:{
					stack.shrink(1);
					player.addItemStackToInventory(new ItemStack(ModItems.NEEDLE, 1, 2));
					return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
				}
				case 1:{
					stack.shrink(1);
					player.addItemStackToInventory(new ItemStack(ModItems.NEEDLE, 1, 3));
					return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
				}
				case 2:{
					player.addPotionEffect(new PotionEffect(MobEffects.POISON, 240, 1, false, true));
					return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
				}
				case 3:{
					player.addPotionEffect(new PotionEffect(MobEffects.POISON, 240, 1, false, true));
					return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
				}
				default: return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
			}
		}

        return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
    }
}
