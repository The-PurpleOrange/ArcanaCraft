package com.tyhone.arcanacraft.common.blocks.base;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.init.ModTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlockBase extends Block {

	private final String BASE_NAME;
	
	public ModBlockBase(String regName) {
		this(regName, Material.ROCK);
	}
	
	public ModBlockBase(String regName, Material material) {
		super(material);
		this.BASE_NAME = regName;
		setRegistryName(regName);
		setTranslationKey(regName);
        this.setCreativeTab(ModTabs.modTab);
        this.setHardness(2f);
        this.setResistance(10f);
		ModBlocks.register(this);
	}
	
	@Override
	public String getTranslationKey(){
		return "tile." + Arcanacraft.MODID + ":" + BASE_NAME;
	}
	
	@SideOnly(Side.CLIENT)
	public void initItemModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName().toString(), "inventory"));
	}
	
    @Override
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
    {
        return false;
    }
    
    @Override
    public boolean canBeConnectedTo(IBlockAccess world, BlockPos pos, EnumFacing facing) {
    	return false;
    }
}
