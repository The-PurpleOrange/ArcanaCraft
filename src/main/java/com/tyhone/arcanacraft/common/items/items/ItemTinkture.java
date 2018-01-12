package com.tyhone.arcanacraft.common.items.items;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.item.IEssenceVessel;
import com.tyhone.arcanacraft.api.registries.TinktureManager;
import com.tyhone.arcanacraft.api.registries.TinktureType;
import com.tyhone.arcanacraft.common.items.base.ModItemBase;
import com.tyhone.arcanacraft.common.util.ResourceLocationHelper;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTinkture extends ModItemBase implements IEssenceVessel{

	private final String NBT_TINKTURE = "tinkture";
	private final String NBT_TINKTURE_DISPLAY_NAME = "tinkture_display_name";
	private final static ArrayList<String> VARIANTS = TinktureManager.getTinktureNames();
	
	public ItemTinkture() {
		super("tinkture", VARIANTS);
	}

	@Override
	public int getFluidAmount(ItemStack stack) {
		return 10;
	}

	@Override
	public TinktureType getFluidType(ItemStack stack) {
		return TinktureManager.getTinktureTypeFromMeta(stack.getMetadata()+1);
	}
	
	public int getColour(ItemStack stack){
		return getFluidType(stack).getColourHex();
	}
	
	public static TinktureType getStaticFluidType(ItemStack stack) {
		return TinktureManager.getTinktureTypeFromMeta(stack.getMetadata()+1);
	}
	
	public static int getStaticColour(ItemStack stack){
		return getStaticFluidType(stack).getColourHex();
	}
	
	/*@Override
	@SideOnly(Side.CLIENT)
	public void initModelsAndVariants(){
		
		ModelLoader.setCustomMeshDefinition(this, new ItemMeshDefinition() {
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return new ModelResourceLocation(getRegistryName().toString());
			}
		});
		
		if(getCustomMeshDefinition() != null){
			ModelLoader.setCustomMeshDefinition(this, getCustomMeshDefinition());
		}
		
		else{
            ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName().toString()));
		}
	}*/
	
	@Override
	@SideOnly(Side.CLIENT)
	public void initModelsAndVariants(){
		
		if(getCustomMeshDefinition() != null){
			for(int i = 0; i < VARIANTS.size(); i++){
				ModelBakery.registerItemVariants(this, ResourceLocationHelper.getModelResourceLocation("tinkture"));
			}
			
			ModelLoader.setCustomMeshDefinition(this, getCustomMeshDefinition());
		}
		
		else{
			if(getHasSubtypes() && VARIANTS.size()>0){
				List<ModelResourceLocation> modelResources = new ArrayList<>();
				
				for(int i = 0; i < VARIANTS.size(); i++){
                    modelResources.add(ResourceLocationHelper.getModelResourceLocation("tinkture"));
				}
				
				ModelBakery.registerItemVariants(this, modelResources.toArray(new ModelResourceLocation[0]));
                ModelLoader.setCustomMeshDefinition(this, itemStack -> modelResources.get(itemStack.getMetadata()));
            }
            else {
                ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName().toString()));
            }
		}
	}
	
	private TinktureType getNBT(ItemStack stack){
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT_TINKTURE)){
			String tinktureName = stack.getTagCompound().getString(NBT_TINKTURE);
			if(tinktureName!=null){
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
