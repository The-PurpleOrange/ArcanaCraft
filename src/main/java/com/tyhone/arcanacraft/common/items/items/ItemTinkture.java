package com.tyhone.arcanacraft.common.items.items;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import org.omg.CORBA.LongLongSeqHelper;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.item.IEssenceVessel;
import com.tyhone.arcanacraft.api.ritual.RitualBase;
import com.tyhone.arcanacraft.api.tinkture.RitualRegistry;
import com.tyhone.arcanacraft.api.tinkture.TinktureManager;
import com.tyhone.arcanacraft.api.tinkture.TinktureType;
import com.tyhone.arcanacraft.common.items.base.ModItemBase;
import com.tyhone.arcanacraft.common.util.PlayerUtils;
import com.tyhone.arcanacraft.common.util.ResourceLocationHelper;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTinkture extends ModItemBase implements IEssenceVessel{

	private final static String NBT_TINKTURE = "tinkture";
	private final static String NBT_TINKTURE_DISPLAY_NAME = "tinkture_display_name";
	private final static List<TinktureType> VARIANTS = TinktureManager.getTinktureTypes();
	
	public ItemTinkture() {
		super("tinkture");
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
					if((tag.getString(NBT_TINKTURE)).equals(VARIANTS.get(i).getUnlocalizedName())){
						if(i == VARIANTS.size()-1){
							setNBT(tag, VARIANTS.get(0));
		            		msg = (VARIANTS.get(0).getUnlocalizedName());
						}else{
							setNBT(tag, VARIANTS.get(i+1));
		            		msg = (VARIANTS.get(i+1).getUnlocalizedName());
						}
						break;
					}
				}
			}else{
				setNBT(tag, VARIANTS.get(0));
        		msg = (VARIANTS.get(0).getUnlocalizedName());
			}
			PlayerUtils.sendPlayerMessage(player, world, msg);
	
			stack.setTagCompound(tag);
	        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
		}

        return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public int getFluidAmount(ItemStack stack) {
		return 10;
	}
	
	@Override
	public TinktureType getFluidType(ItemStack stack) {
		
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT_TINKTURE)){
			String tinktureName = stack.getTagCompound().getString(NBT_TINKTURE);
			if(tinktureName!=null){
				TinktureType tinkture = TinktureManager.getTinktureTypeFromName(tinktureName);
				
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
				int colourHex = TinktureManager.getTinktureColourFromName(tinktureName);
				
				return colourHex;
			}
		}
		return 0x000000;
	}
	
	private TinktureType getNBT(ItemStack stack){
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT_TINKTURE)){
			String tinktureName = stack.getTagCompound().getString(NBT_TINKTURE);
			if(tinktureName!=null){
				Arcanacraft.log("getNBT Type: " + TinktureType.getType(NBT_TINKTURE));
				return TinktureType.getType(NBT_TINKTURE);
			}
		}
		return null;
	}
	
	private void setNBT(NBTTagCompound tag, TinktureType type){
		tag.setString(NBT_TINKTURE, type.getUnlocalizedName());
		tag.setString(NBT_TINKTURE_DISPLAY_NAME, type.getDisplayName());
	}
	
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
    	
    	if(stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT_TINKTURE_DISPLAY_NAME)){
    		tooltip.add(stack.getTagCompound().getString(NBT_TINKTURE_DISPLAY_NAME));
    		tooltip.add(stack.getTagCompound().toString());
    	}
    }
}
