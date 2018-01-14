package com.tyhone.arcanacraft.common.items.items;

import java.util.List;

import javax.annotation.Nullable;

import com.tyhone.arcanacraft.api.tinkture.IEssenceVessel;
import com.tyhone.arcanacraft.api.tinkture.TinktureRegistry;
import com.tyhone.arcanacraft.api.tinkture.TinktureType;
import com.tyhone.arcanacraft.common.items.base.ModItemBase;
import com.tyhone.arcanacraft.common.util.PlayerUtils;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTinkture extends ModItemBase implements IEssenceVessel{

	public final static String NBT_TINKTURE = "tinkture";
	public final static String NBT_TINKTURE_DISPLAY_NAME = "tinkture_display_name";
	private final static List<TinktureType> VARIANTS = TinktureRegistry.getTinktureTypes();
	
	public ItemTinkture() {
		super("tinkture");
		setHasSubtypes(false);
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items){
		for(TinktureType type : VARIANTS){
	    	ItemStack stack = new ItemStack(this, 1);
	    	
	    	NBTTagCompound tag = new NBTTagCompound();
	    	
	    	setNBT(tag, type);
	    	
	    	stack.setTagCompound(tag);
	        items.add(stack);
		}
    }
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		if(player.isSneaking()){
			String msg = null;
			ItemStack stack = player.getHeldItem(hand);
	
			NBTTagCompound tag = new NBTTagCompound();
			if(stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT_TINKTURE)){
				tag = stack.getTagCompound();
				for(int i = 0; i < VARIANTS.size(); i++){
					if((tag.getString(NBT_TINKTURE)).equals(VARIANTS.get(i).getRegistryName())){
						if(i == VARIANTS.size()-1){
							setNBT(tag, VARIANTS.get(0));
		            		msg = (VARIANTS.get(0).getRegistryName().toString());
						}else{
							setNBT(tag, VARIANTS.get(i+1));
		            		msg = (VARIANTS.get(i+1).getRegistryName().toString());
						}
						break;
					}
				}
			}else{
				setNBT(tag, VARIANTS.get(0));
        		msg = (VARIANTS.get(0).getRegistryName().toString());
			}
			PlayerUtils.sendPlayerMessage(player, world, msg);
	
			stack.setTagCompound(tag);
	        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
		}

        return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
    }

	@Override
	public int getFluidAmount(ItemStack stack) {
		return 8;
	}
	
	@Override
	public TinktureType getFluidType(ItemStack stack) {
		
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT_TINKTURE)){
			String tinktureName = stack.getTagCompound().getString(NBT_TINKTURE);
			if(tinktureName!=null){
				TinktureType tinkture = TinktureRegistry.getTinktureTypeFromName(tinktureName);
				
				return tinkture;
			}
		}
		return null;
	}
	
	public int getColour(ItemStack stack){
		return getFluidType(stack).getColourHex();
	}
	
	public static int getColourFromNBT(ItemStack stack){
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT_TINKTURE)){
			String tinktureName = stack.getTagCompound().getString(NBT_TINKTURE);
			if(tinktureName!=null){
				int colourHex = TinktureRegistry.getTinktureColourFromName(tinktureName);
				
				return colourHex;
			}
		}
		return 0x000000;
	}
	
	public TinktureType getNBT(ItemStack stack){
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT_TINKTURE)){
			String tinktureName = stack.getTagCompound().getString(NBT_TINKTURE);
			if(tinktureName!=null){
				return TinktureType.getType(NBT_TINKTURE);
			}
		}
		return null;
	}
	
	private void setNBT(NBTTagCompound tag, TinktureType type){
		tag.setString(NBT_TINKTURE, type.getRegistryName().toString());
		tag.setString(NBT_TINKTURE_DISPLAY_NAME, type.getDisplayName());
	}
	
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
    	
    	if(stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT_TINKTURE_DISPLAY_NAME)){
    		tooltip.add(stack.getTagCompound().getString(NBT_TINKTURE_DISPLAY_NAME));
    	}
    }
}
