package com.tyhone.arcanacraft.common.items.items;

import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.items.base.ModItemBase;
import com.tyhone.arcanacraft.common.reference.Names;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;
import com.tyhone.arcanacraft.common.util.ItemStackUtil;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
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
					return jabPlayer(player, new ItemStack(ModItems.NEEDLE, 1, 2), hand, false);
				}
				case 1:{
					return jabPlayer(player, new ItemStack(ModItems.NEEDLE, 1, 3), hand, false);
				}
				case 2:{
					return jabPlayer(player, new ItemStack(ModItems.NEEDLE, 1, 2), hand, true);
				}
				case 3:{
					return jabPlayer(player, new ItemStack(ModItems.NEEDLE, 1, 3), hand, true);
				}
				case 4:{
					if(!player.addItemStackToInventory(new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("blood_drop")))){
						EntityItem eOutput = new EntityItem(world, player.posX, player.posY+1, player.posZ, new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("blood_drop")));
						world.spawnEntity(eOutput);
					}
				}
				default: return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
			}
		}

        return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
    }
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker){
		switch(stack.getMetadata()){
			case 0:{
				return jabOther(new ItemStack(ModItems.NEEDLE, 1, 2), stack, attacker, target, false);
			}
			case 1:{
				return jabOther(new ItemStack(ModItems.NEEDLE, 1, 3), stack, attacker, target, false);
			}
			case 2:{
				return jabOther(new ItemStack(ModItems.NEEDLE, 1, 2), stack, attacker, target, true);
			}
			case 3:{
				return jabOther(new ItemStack(ModItems.NEEDLE, 1, 3), stack, attacker, target, true);
			}
			case 4:{
				if(attacker instanceof EntityPlayer){
					EntityPlayer player = (EntityPlayer) attacker;
					if(!player.addItemStackToInventory(new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("blood_drop")))){
						World world = player.getEntityWorld();
						EntityItem eOutput = new EntityItem(world, player.posX, player.posY+1, player.posZ, new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("blood_drop")));
						world.spawnEntity(eOutput);
					}
				}
			}
			default: return false;
		}
    }
	
	public boolean jabOther(ItemStack addStack, ItemStack usedStack, EntityLivingBase attacker, EntityLivingBase target, boolean poison){
		if(attacker instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) attacker;
			boolean poked = false;
			if(!ItemStackUtil.simpleAreStacksEqual(addStack, usedStack)){
				if(player.addItemStackToInventory(addStack)){
					usedStack.shrink(1);
					poked = true;
				}
			}else{
				poked = true;
			}
			if(poked){
				if(!player.addItemStackToInventory(new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("blood_drop")))){
					World world = player.getEntityWorld();
					EntityItem eOutput = new EntityItem(world, player.posX, player.posY+1, player.posZ, new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("blood_drop")));
					world.spawnEntity(eOutput);
				}
				if(poison){
					target.addPotionEffect(new PotionEffect(MobEffects.POISON, 80, 0, false, true));
				}
				return true;
			}
		}
		return false;
	}
	
	public ActionResult<ItemStack> jabPlayer(EntityPlayer player, ItemStack stack, EnumHand hand, boolean poison){
		
		boolean poked = false;
		if(!ItemStackUtil.simpleAreStacksEqual(stack, player.getHeldItem(hand))){
			if(player.addItemStackToInventory(stack)){
				player.getHeldItem(hand).shrink(1);
				poked = true;
			}
		}else{
			poked = true;
		}
		if(poked){
			if(!player.addItemStackToInventory(new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("blood_drop")))){
				World world = player.getEntityWorld();
				EntityItem eOutput = new EntityItem(world, player.posX, player.posY+1, player.posZ, new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("blood_drop")));
				world.spawnEntity(eOutput);
			}
			if(poison){
				player.addPotionEffect(new PotionEffect(MobEffects.POISON, 240, 1, false, true));
			}
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
	}
}
