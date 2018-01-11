package com.tyhone.arcanacraft.common.items.items;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.util.ItemStackUtil;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.items.base.ModItemBase;
import com.tyhone.arcanacraft.common.reference.Names;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemItem  extends ModItemBase{

	//private final static String[] VARIANTS = {"bone_ash", "quartz_dust", "clean_flesh"};
	
	public ItemItem() {
		super("item", Names.MetaItems.ITEM); //bone_ash
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
        if(!worldIn.isRemote){
        	if(player.getHeldItem(hand).getItem() == this && player.getHeldItem(hand).getMetadata() == ItemMetaUtil.item("bone_ash")){
	        	boolean flag = worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos);
			    BlockPos blockpos = flag ? pos : pos.offset(facing);
			
			    if (player.canPlayerEdit(blockpos, facing, new ItemStack(ModBlocks.CHALK_BLOCK))){
	
			        Block block = worldIn.getBlockState(blockpos).getBlock();
			        
			        Block chalkType = ModBlocks.CHALK_BLOCK;
			
			        if (chalkType.canPlaceBlockAt(worldIn, blockpos))
			        {
			        	worldIn.setBlockState(blockpos, chalkType.getStateFromMeta(ItemMetaUtil.chalk("bone")));
			        	player.getHeldItem(hand).shrink(1);
			        	
			            return EnumActionResult.SUCCESS;
			        }
			    }
	        }
        }
        return EnumActionResult.FAIL;
    }
}
