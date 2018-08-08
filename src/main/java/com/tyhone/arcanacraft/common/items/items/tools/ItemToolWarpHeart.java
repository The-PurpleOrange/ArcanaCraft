package com.tyhone.arcanacraft.common.items.items.tools;

import java.util.Random;

import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.items.base.ModItemBase;
import com.tyhone.arcanacraft.common.util.TeleportUtils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemToolWarpHeart extends ModItemBase{

	private final String NBT_BOUND = "bound";
	private final String NBT_POS_ARRAY = "int_array";
	private final String NBT_DIMENSION = "dimension";
	
	public ItemToolWarpHeart() {
		super("tool_warp_heart");
		this.maxStackSize = 1;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 40;
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		player.stopActiveHand();
		player.setActiveHand(hand);
		return super.onItemRightClick(world, player, hand);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!world.isRemote) {
			if(world.getBlockState(pos).getBlock() == ModBlocks.WARP_CIRCLE) {
				setWarpPos(player.getHeldItem(hand), pos, player.dimension);
			}
			else {
				return EnumActionResult.PASS;
			}
		}

		if(world.getBlockState(pos).getBlock() == ModBlocks.WARP_CIRCLE) {
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.PASS;
	}
	
	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
		if(player.world.isRemote) {
			Random rand = new Random();
			double px = (double)player.posX + (rand.nextDouble()*2 - 1D);;
	        double py = (double)player.posY;
	        double pz = (double)player.posZ + (rand.nextDouble()*2 - 1D);
	        
	        player.getEntityWorld().spawnParticle(EnumParticleTypes.SPELL_WITCH, px, py+0.5, pz, 0, 0.1, 0);
		}
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase player) {
		if(isBound(stack)) {
			world.playSound((EntityPlayer) player, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.PLAYERS, 1F, player.world.rand.nextFloat() * 0.1F + 0.9F);
			BlockPos warpPos = getWarpPos(stack);
			TeleportUtils.warp(player, warpPos, player.dimension, getDimension(stack));
		}
		return super.onItemUseFinish(stack, world, player);
	}
	
/*	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if(isBound(player.getHeldItem(hand))) {
			ItemStack stack = player.getHeldItem(hand);
			BlockPos warpPos = getWarpPos(stack);
			TeleportUtils.warp(player, warpPos, player.dimension, getDimension(stack));
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
	}*/
	
	private boolean isBound(ItemStack stack) {
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT_BOUND)) {
			return stack.getTagCompound().getBoolean(NBT_BOUND);
		}
		return false;
	}
	
	private void setWarpPos(ItemStack stack, BlockPos pos, int dimension) {
		NBTTagCompound tag;
		if(stack.hasTagCompound()) {
			tag = stack.getTagCompound();
		} else {
			tag = new NBTTagCompound();
		}
		
		tag.setBoolean(NBT_BOUND, true);
		tag.setInteger(NBT_DIMENSION, dimension);
		
		int[] posArray = {pos.getX(), pos.getY(), pos.getZ()};
		tag.setIntArray(NBT_POS_ARRAY, posArray);
		
		stack.setTagCompound(tag);
		
	}
	
	private int getDimension(ItemStack stack) {
		if(isBound(stack) && stack.getTagCompound().hasKey(NBT_DIMENSION)) {
			return stack.getTagCompound().getInteger(NBT_DIMENSION);
		}
		return -2;
	}
	
	private BlockPos getWarpPos(ItemStack stack) {
		if(isBound(stack) && stack.getTagCompound().hasKey(NBT_POS_ARRAY)){
			int[] pos = stack.getTagCompound().getIntArray(NBT_POS_ARRAY);
			return new BlockPos(pos[0], pos[1], pos[2]);
		}
		return null;
	}

    @Override
    @SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, java.util.List<String> tooltip, net.minecraft.client.util.ITooltipFlag flagIn) {

    	tooltip.add("Is bound" + isBound(stack));
    	if(isBound(stack)) {
    		tooltip.add("dimension " + getDimension(stack));
    		tooltip.add(getWarpPos(stack).toString());
    	}
    }
}
