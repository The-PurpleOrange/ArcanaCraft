package com.tyhone.arcanacraft.common.blocks.base;

import net.minecraft.util.IStringSerializable;

public interface IEnumMeta extends IStringSerializable{
	
	int getMeta();
	
	@Override
	default String getName() {
		return ((Enum) this).name().toLowerCase();
	}
}
