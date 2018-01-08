package com.tyhone.arcanacraft.common.items.items;

import java.util.List;

import javax.annotation.Nullable;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.registries.RitualRegistry;
import com.tyhone.arcanacraft.api.ritual.RitualBase;
import com.tyhone.arcanacraft.common.items.base.ModItemBase;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMetamorphicChalk extends ModItemBase{

	private final String NBT_RITUAL = "ritual";
	private final String NBT_RITUAL_DISPLAY_NAME = "ritual_display_name";
	
	public ItemMetamorphicChalk() {
		super("chalk_metamorphic");
		setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		if(player.isSneaking()){
			String msg = null;
			List<RitualBase> rituals = RitualRegistry.getRitualList();
			ItemStack stack = player.getHeldItem(hand);
	
			NBTTagCompound tag = new NBTTagCompound();
			if(stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT_RITUAL)){
				tag = stack.getTagCompound();
				for(int i = 0; i < rituals.size(); i++){
					if((tag.getString(NBT_RITUAL)).equals(rituals.get(i).getUnlocalizedName())){
						if(i == rituals.size()-1){
							setNBT(tag, rituals.get(0));
		            		msg = (rituals.get(0).getUnlocalizedName());
						}else{
							setNBT(tag, rituals.get(i+1));
		            		msg = (rituals.get(i+1).getUnlocalizedName());
						}
						break;
					}
				}
			}else{
				setNBT(tag, rituals.get(0));
        		msg = (rituals.get(0).getUnlocalizedName());
			}
			
			if(world.isRemote && msg != null){
				player.sendMessage(new TextComponentTranslation(msg));
			}
	
			stack.setTagCompound(tag);
		}
        return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
    }
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        /*if(!worldIn.isRemote){
        	boolean flag = worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos);
		    BlockPos blockpos = flag ? pos : pos.offset(facing);
		
		    if (player.canPlayerEdit(blockpos, facing, player.getHeldItem(hand))){

		        Block block = worldIn.getBlockState(blockpos).getBlock();
		        
		        Block chalkType = ModBlocks.CHALK_BLOCK;
		
		        if (chalkType.canPlaceBlockAt(worldIn, blockpos)) //else if (chalkType.canPlaceBlockAt(worldIn, blockpos))
		        {
		        	worldIn.setBlockState(blockpos, chalkType.getStateFromMeta(player.getHeldItem(hand).getMetadata()));
		        	
		            return EnumActionResult.SUCCESS;
		        }
		    }
        }*/
        return EnumActionResult.FAIL;
    }
	
	private void setNBT(NBTTagCompound tag, RitualBase ritual){
		tag.setString(NBT_RITUAL, ritual.getUnlocalizedName());
		tag.setString(NBT_RITUAL_DISPLAY_NAME, ritual.getDisplayName());
	}
	
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
    	
    	if(stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT_RITUAL_DISPLAY_NAME)){
    		tooltip.add(stack.getTagCompound().getString(NBT_RITUAL_DISPLAY_NAME));
    		tooltip.add(stack.getTagCompound().toString());
    	}
    }
}
