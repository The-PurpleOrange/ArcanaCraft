package com.tyhone.arcanacraft.common.init;

import com.tyhone.arcanacraft.api.registries.TinktureManager;

public class ModCustomRegistries {

	public static void register(){

		TinktureManager.registerTinktureType("impirus", 0xed170b);
		TinktureManager.registerTinktureType("amnis", 0x0a78ed);
		TinktureManager.registerTinktureType("rebus", 0x239909);
		TinktureManager.registerTinktureType("locus", 0xfcec0a);
		TinktureManager.registerTinktureType("animo", 0xf495e1);
		
	}
}
