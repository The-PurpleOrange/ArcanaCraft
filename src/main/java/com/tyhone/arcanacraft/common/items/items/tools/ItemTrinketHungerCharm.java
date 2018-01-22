package com.tyhone.arcanacraft.common.items.items.tools;

import java.util.List;

import javax.annotation.Nullable;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.items.base.ModItemBase;

import ibxm.Player;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTrinketHungerCharm extends ModItemBase{

	private final String NBT_FOOD_AMOUNT = "food_amount";
	
	public ItemTrinketHungerCharm() {
		super("trinket_hunger_charm");
	}
	
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		
		if(world.getTotalWorldTime() % 30 == 0 && entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) entity;

			int foodAmount = getFoodAmountNBT(stack);
			
			if(player.getFoodStats().getFoodLevel() < 20 && foodAmount > 0){
				if(player.getFoodStats().getFoodLevel()<20){
					player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel()+1);
					setFoodAmountNBT(stack, foodAmount-1);
				}
			}
		}
		super.onUpdate(stack, world, entity, itemSlot, isSelected);
	}
	
	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase entity, int count){
		if(entity instanceof EntityPlayer && (count % 5 == 0)){
			EntityPlayer player = (EntityPlayer) entity;
			int InvSize = player.inventory.getSizeInventory();
			for(int i = 0; i < InvSize; i++){
				ItemStack item = player.inventory.getStackInSlot(i);
				if(item.getItem() instanceof ItemFood){
					int itemFoodAmount = ((ItemFood) item.getItem()).getHealAmount(player.inventory.getStackInSlot(i));
					int foodAmount = getFoodAmountNBT(stack);
					if(itemFoodAmount > 0){
						setFoodAmountNBT(stack, foodAmount + itemFoodAmount);
						item.shrink(1);
					}
				}
			}
		}
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		if(entityLiving instanceof EntityPlayer){
			((EntityPlayer)entityLiving).stopActiveHand();
		}
		return super.onItemUseFinish(stack, worldIn, entityLiving);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		
		if(player.isSneaking()){
			player.getFoodStats().setFoodLevel(1);
			player.getFoodStats().setFoodSaturationLevel(1);
		}
		else{
			player.stopActiveHand();
			player.setActiveHand(hand);
		}
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	}

    @Override
	public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
    }
    
    @Override
	public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BOW;
    }

	private int getFoodAmountNBT(ItemStack stack){
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT_FOOD_AMOUNT)){
			return stack.getTagCompound().getInteger(NBT_FOOD_AMOUNT);
		}
		return 0;
	}
	
	private void setFoodAmountNBT(ItemStack stack, int foodAmount){
		NBTTagCompound tag;
		if(stack.hasTagCompound()){
			tag = stack.getTagCompound();
		}else{
			tag = new NBTTagCompound();
		}
		tag.setInteger(NBT_FOOD_AMOUNT, foodAmount);
		stack.setTagCompound(tag);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn){
    	tooltip.add("Calories stored: " + getFoodAmountNBT(stack));
    }
}
