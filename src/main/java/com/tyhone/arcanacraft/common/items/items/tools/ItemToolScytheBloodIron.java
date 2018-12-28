package com.tyhone.arcanacraft.common.items.items.tools;

import com.tyhone.arcanacraft.common.items.ModToolMaterial;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ItemToolScytheBloodIron extends ItemToolScythe{

	public ItemToolScytheBloodIron() {
		super("blood_iron", ModToolMaterial.BLOOD_IRON, 3);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if(stack.isItemDamaged()) {
			if(world.getTotalWorldTime() % 120 == 0 && entity instanceof EntityPlayer){
				EntityPlayer player = (EntityPlayer) entity;
				if(player.getHeldItemMainhand() == stack || player.getHeldItemOffhand() == stack) {
					stack.setItemDamage(stack.getItemDamage()-3);
					player.attackEntityFrom(DamageSource.GENERIC, 1F);
				}
			}
		}
		super.onUpdate(stack, world, entity, itemSlot, isSelected);
	}
	
}
