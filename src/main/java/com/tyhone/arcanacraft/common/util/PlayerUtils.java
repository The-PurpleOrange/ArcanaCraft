package com.tyhone.arcanacraft.common.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class PlayerUtils {
	
	public static void sendPlayerMessage(EntityPlayer player, World worldIn, String message){
		if(worldIn.isRemote){
			if(message!=null){
				player.sendMessage(new TextComponentTranslation(message));
			}else{
				player.sendMessage(new TextComponentTranslation("Null Message Error"));
			}
		}
	}
	
	public static boolean consumeItemFromInventory(IItemHandler inventory, ItemStack itemStack){
		for(int i = 0; i < inventory.getSlots(); i++){
			ItemStack inventoryStack = inventory.getStackInSlot(i);
			if(ItemStackUtil.simpleAreStacksEqual(itemStack, inventoryStack)){
				inventoryStack.shrink(1);
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean consumePlayerItem(EntityPlayer player, ItemStack itemStack){
		IItemHandler inventory = player.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
		return consumeItemFromInventory(inventory, itemStack);
	}
}
