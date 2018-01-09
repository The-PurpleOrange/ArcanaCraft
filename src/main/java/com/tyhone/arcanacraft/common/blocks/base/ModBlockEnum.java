package com.tyhone.arcanacraft.common.blocks.base;

import org.apache.logging.log4j.Level;

import com.tyhone.arcanacraft.Arcanacraft;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlockEnum extends ModBlockBase{

	private final IEnumMeta[] VARIANTS;
	private final String BASE_NAME;
	
	public ModBlockEnum(String name, IEnumMeta[] variants){
		this(name, Material.ROCK, variants);
	}
	
	public ModBlockEnum(String name, Material material, IEnumMeta[] variants){
		super(name, material);
		this.BASE_NAME = name;
		if (variants.length > 0){
			this.VARIANTS = variants;	
		}
		else{
			this.VARIANTS = new IEnumMeta[0];
		}
	}
	
	public String getUnlocalizedName(ItemStack itemStack){
		//Arcanacraft.logger.log(Level.INFO, Arcanacraft.MODID + "." + VARIANTS[itemStack.getMetadata()]);
		if((itemStack != null && itemStack.getItem() != null) && Block.getBlockFromItem(itemStack.getItem()) instanceof ModBlockBase){
			if (VARIANTS.length > 0){
				return Arcanacraft.MODID + "." + BASE_NAME + "." + VARIANTS[itemStack.getMetadata()];
			}
		}
		return super.getUnlocalizedName();
	}
	

	@Override
	@SideOnly(Side.CLIENT)
	public void initItemModel() {
		if(Item.getItemFromBlock(this) != null){
            if (VARIANTS.length > 0){
                for (IEnumMeta variant : VARIANTS){
            		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), variant.getMeta(), new ModelResourceLocation(getRegistryName(), "variant=" + variant.getName()));
                }
            }
            else{
        		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
            }
		}
	}
}
