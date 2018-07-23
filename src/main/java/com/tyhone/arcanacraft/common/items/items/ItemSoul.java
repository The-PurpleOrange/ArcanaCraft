package com.tyhone.arcanacraft.common.items.items;

import com.tyhone.arcanacraft.common.items.base.ModItemBase;
import com.tyhone.arcanacraft.common.reference.Names;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSoul extends ModItemBase{
	
	public ItemSoul() {
		super("soul", Names.MetaItems.SOUL);
	}

    @Override
	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
    	switch(stack.getMetadata()){
    		default: return false;
    		case 2: return true;
    		case 3: return true;
    	}
    }
}
