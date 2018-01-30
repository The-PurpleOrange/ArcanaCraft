package com.tyhone.arcanacraft.common.items.items;

import com.tyhone.arcanacraft.common.items.base.ModItemBase;
import com.tyhone.arcanacraft.common.reference.Names;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemStar  extends ModItemBase{

	//private final static String[] VARIANTS = {"bone_ash", "quartz_dust", "clean_flesh"};
	
	public ItemStar() {
		super("star", Names.MetaItems.STAR);
	}

    @Override
	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
    	return stack.getMetadata() != 0;
    }
}
