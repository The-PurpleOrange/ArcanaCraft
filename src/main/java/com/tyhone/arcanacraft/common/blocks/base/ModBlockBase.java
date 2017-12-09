package com.tyhone.arcanacraft.common.blocks.base;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.init.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlockBase extends Block {

	public ModBlockBase(String regName) {
		super(Material.ROCK);
		setRegistryName(regName);
		setUnlocalizedName(Arcanacraft.MODID + "." + getRegistryName());
		
		ModBlocks.register(this);
	}
	
	public ModBlockBase(String regName, Material material) {
		super(Material.ROCK);
		setRegistryName(regName);
		setUnlocalizedName(Arcanacraft.MODID + "." + getRegistryName());

		ModBlocks.register(this);
	}
	
	
	@SideOnly(Side.CLIENT)
	public void initItemModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
}
