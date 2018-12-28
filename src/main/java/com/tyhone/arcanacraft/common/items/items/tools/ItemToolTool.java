package com.tyhone.arcanacraft.common.items.items.tools;

import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.tileentity.ITEDimensionsBindable;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.items.base.ModItemBase;
import com.tyhone.arcanacraft.common.util.PlayerUtils;
import com.tyhone.arcanacraft.common.util.PosUtil;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemToolTool extends ModItemBase{

	private final String NBT_MODE = "mode";
	private final String NBT_DIM = "dim";
	private final int[] BLANK_ARRAY = {0, -1, 0};
	private final BlockPos BLANK_POS = new BlockPos(0, -1, 0);
	
	//Modes:
	//-1 None
	//0 Binding dimensions
	//private int mode = -1;
    //private BlockPos[] dim = {null, null};
	
	public ItemToolTool() {
		super("tool");
	}
	
	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
		if(!world.isRemote) {
			IBlockState blockState = world.getBlockState(pos);
			
			NBTTagCompound tag = new NBTTagCompound();
			ItemStack stack = player.getHeldItem(hand);
			if(stack.hasTagCompound()) {
				tag = stack.getTagCompound();
			}
			else {
				tag.setInteger(NBT_MODE, -1);
			}
			
			int mode = tag.getInteger(NBT_MODE);
			
			if(mode == -1) {
				if(world.getTileEntity(pos) != null && world.getTileEntity(pos) instanceof ITEDimensionsBindable) {
					tag.setInteger(NBT_MODE, 0);
					stack.setTagCompound(tag);
					PlayerUtils.sendPlayerMessage(player, world, "Binding Mode");
					return EnumActionResult.SUCCESS;
				}
			}
			
			else if(mode == 0) {
				
				BlockPos[] dim = getDimsFromNBT(tag);
				
				if(!PosUtil.comparePos(dim[0], BLANK_POS) && !PosUtil.comparePos(dim[1], BLANK_POS)) {
					if(world.getTileEntity(pos) != null && world.getTileEntity(pos) instanceof ITEDimensionsBindable) {
						((ITEDimensionsBindable)world.getTileEntity(pos)).setDimensions(dim[0], dim[1]);
						tag.setIntArray(NBT_DIM + 1, BLANK_ARRAY); //TODO: cannot set nbt to null, set y to -1 or above max build height?
						
						tag.setIntArray(NBT_DIM + 2, BLANK_ARRAY); //TODO: cannot set nbt to null
						tag.setInteger(NBT_MODE, -1);
						stack.setTagCompound(tag);
						return EnumActionResult.SUCCESS;
					}
				}
				else{
					if(PosUtil.comparePos(dim[0], BLANK_POS)) {
						int[] a = {pos.getX(), pos.getY(), pos.getZ()};
						tag.setIntArray(NBT_DIM + 1, a);
						stack.setTagCompound(tag);
					}
					else {
						int[] a = {pos.getX(), pos.getY(), pos.getZ()};
						tag.setIntArray(NBT_DIM + 2, a);
						stack.setTagCompound(tag);
					}
					//Arcanacraft.log("Wand dims: " + dim[0] == null ? "null" : dim[0].toString() + "/" + dim[1] == null ? "null" : dim[1].toString());
					return EnumActionResult.SUCCESS;
				}
			}
			return EnumActionResult.FAIL;
		}

		return EnumActionResult.SUCCESS;
		
	}
	
	private BlockPos[] getDimsFromNBT(NBTTagCompound tag) {
		BlockPos[] dim = {BLANK_POS, BLANK_POS};
		int[] a = BLANK_ARRAY, b = BLANK_ARRAY;
		if(tag.hasKey(NBT_DIM + 1)) a = tag.getIntArray(NBT_DIM + 1);
		if(tag.hasKey(NBT_DIM + 2)) b = tag.getIntArray(NBT_DIM + 2);

		if(a != BLANK_ARRAY) {
			dim[0] = new BlockPos(a[0], a[1], a[2]);
		}
		if(b != BLANK_ARRAY) {
			dim[1] = new BlockPos(b[0], b[1], b[2]);
		}
		return dim;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		NBTTagCompound tag = new NBTTagCompound();
		if(stack.hasTagCompound()) {
			tag = stack.getTagCompound();
			
			if(tag.hasKey(NBT_MODE)) {
				int mode = tag.getInteger(NBT_MODE);
				
				switch(mode) {
				case -1: 
					tooltip.add("Mode: None");
					break;
				
				case 0: 
					tooltip.add("Mode: Binding");
					BlockPos[] dim = getDimsFromNBT(tag);
					String dim1 = "null";
					String dim2 = "null";
					if(!PosUtil.comparePos(dim[0], BLANK_POS)) {
						dim1 = dim[0].toString();
					}
					if(!PosUtil.comparePos(dim[1], BLANK_POS)) {
						dim2 = dim[1].toString();
					}

					tooltip.add("Dimensions:");
					tooltip.add(dim1);
					tooltip.add(dim2);
					
					break;
				
				default: break;
				}
			}
		}
	}
}
