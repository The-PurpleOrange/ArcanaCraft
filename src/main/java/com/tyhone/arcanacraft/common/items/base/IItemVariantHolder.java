package com.tyhone.arcanacraft.common.items.base;

import net.minecraft.client.renderer.ItemMeshDefinition;

public interface IItemVariantHolder<T extends ModItemBase> {
	T getItem();
	
	String[] getVariants();
	
	default ItemMeshDefinition getCustomMeshDefinition(){
		return null;
	}

}
