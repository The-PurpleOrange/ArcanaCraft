package com.tyhone.arcanacraft.common.items.base;

import java.util.ArrayList;
import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.init.ModTabs;
import com.tyhone.arcanacraft.common.util.ResourceLocationHelper;

import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


//Uses temporary Pahi-style code from Pahimar for Item Variants, will be replaced in future
public class ModItemBase extends Item implements IItemVariantHolder<ModItemBase>{
	
	private final String BASE_NAME;
	private final String[] VARIANTS;
	
	public ModItemBase(String name){
		super();
		setRegistryName(name);
        this.setCreativeTab(ModTabs.modTab);
		setMaxStackSize(64);
		setNoRepair();
        setMaxDamage(0);
		
		BASE_NAME = name;
		VARIANTS = new String[0];
		setHasSubtypes(false);
		
		ModItems.register(this);
	}
	
	public ModItemBase(String name, ArrayList<String> variants){
		super();
		setRegistryName(name);
        this.setCreativeTab(ModTabs.modTab);
		setMaxStackSize(64);
		setNoRepair();
        setMaxDamage(0);
		
		BASE_NAME = name;
		if(variants.size()>0){
			ArrayList<String> variantList = new ArrayList<>();
			variants.forEach(a -> variantList.add(name + "_" + a));
			String[] variantArray = variantList.toArray(new String[0]); 
			
			VARIANTS = variantArray;
			setHasSubtypes(true);
		}
		else{
			VARIANTS = new String[0];
			setHasSubtypes(false);
		}
		
		
		ModItems.register(this);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack)
    {
		if(getHasSubtypes() && stack.getMetadata() < VARIANTS.length){
			return ("item." + Arcanacraft.MODID + ":" + BASE_NAME + "." + VARIANTS[stack.getMetadata()]);
		}
		else{
			return "item." + Arcanacraft.MODID + ":" + BASE_NAME;
		}
    }

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
		if(this.isInCreativeTab(tab)){
			if(getHasSubtypes() && VARIANTS.length>0){
				for(int i = 0; i<VARIANTS.length;i++){
	                items.add(new ItemStack(this, 1, i));
				}
			}
			else{
				super.getSubItems(tab, items);
	        }
		}
    }
	
	@SideOnly(Side.CLIENT)
	public void initModelsAndVariants(){
		
		if(getCustomMeshDefinition() != null){
			for(int i = 0; i < VARIANTS.length; i++){
				ModelBakery.registerItemVariants(this, ResourceLocationHelper.getModelResourceLocation(VARIANTS[i]));
			}
			
			ModelLoader.setCustomMeshDefinition(this, getCustomMeshDefinition());
		}
		
		else{
			if(getHasSubtypes() && VARIANTS.length>0){
				List<ModelResourceLocation> modelResources = new ArrayList<>();
				
				for(int i = 0; i < VARIANTS.length; i++){
                    modelResources.add(ResourceLocationHelper.getModelResourceLocation(VARIANTS[i]));
				}
				
				ModelBakery.registerItemVariants(this, modelResources.toArray(new ModelResourceLocation[0]));
                ModelLoader.setCustomMeshDefinition(this, itemStack -> modelResources.get(itemStack.getMetadata()));
            }
            else {
                ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName().toString()));
            }
		}
	}

	@Override
	public ModItemBase getItem() {
		return this;
	}

	@Override
	public String[] getVariants() {
		return VARIANTS;
	}
}
