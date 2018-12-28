package com.tyhone.arcanacraft.common.items.items.tools;

import java.util.List;

import javax.annotation.Nullable;

import com.tyhone.arcanacraft.common.items.base.ModItemBase;

import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class itemTrinketLavaCharm extends ModItemBase{

	private final String NBT_LAVA_AMOUNT = "lava_amount";
	private final int MAX_LAVA = 8000;
	private final int ADD_AMOUNT_BURNING = 20;
	private final int ADD_AMOUNT_LAVA = 5;
	
	public itemTrinketLavaCharm() {
		super("trinket_lava_charm");
		setMaxStackSize(1);
	}
	
	/*@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		// TODO Auto-generated method stub
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}*/
	
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		
		if(world.getTotalWorldTime() % 5 == 0 && entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) entity;
			if(player.isBurning()) {
				int lava_amount = getLavaAmountNBT(stack);
				if(lava_amount < MAX_LAVA) {
					if(world.getBlockState(player.getPosition()).getMaterial() == Material.LAVA) {
						setLavaAmountNBT(stack, (lava_amount + ADD_AMOUNT_LAVA) > MAX_LAVA ? MAX_LAVA : (lava_amount + ADD_AMOUNT_LAVA));
						player.extinguish();
					}
					else {
						setLavaAmountNBT(stack, (lava_amount + ADD_AMOUNT_BURNING) > MAX_LAVA ? MAX_LAVA : (lava_amount + ADD_AMOUNT_BURNING));
						player.extinguish();
					}
				}
			}
		}
		super.onUpdate(stack, world, entity, itemSlot, isSelected);
	}
	
	/*
	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase entity, int count){
		if(entity instanceof EntityPlayer && (count % 5 == 0)){
			EntityPlayer player = (EntityPlayer) entity;
			int InvSize = player.inventory.getSizeInventory();
			for(int i = 0; i < InvSize; i++){
				ItemStack item = player.inventory.getStackInSlot(i);
				if(item.getItem() instanceof ItemFood){
					int itemFoodAmount = ((ItemFood) item.getItem()).getHealAmount(player.inventory.getStackInSlot(i));
					int foodAmount = getLavaAmountNBT(stack);
					if(itemFoodAmount > 0){
						setLavaAmountNBT(stack, foodAmount + itemFoodAmount);
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
	}*/

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

	private int getLavaAmountNBT(ItemStack stack){
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT_LAVA_AMOUNT)){
			return stack.getTagCompound().getInteger(NBT_LAVA_AMOUNT);
		}
		return 0;
	}
	
	private void setLavaAmountNBT(ItemStack stack, int lavaAmount){
		NBTTagCompound tag;
		if(stack.hasTagCompound()){
			tag = stack.getTagCompound();
		}else{
			tag = new NBTTagCompound();
		}
		tag.setInteger(NBT_LAVA_AMOUNT, lavaAmount);
		stack.setTagCompound(tag);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn){
    	tooltip.add("Lava stored: " + getLavaAmountNBT(stack));
    }
}
