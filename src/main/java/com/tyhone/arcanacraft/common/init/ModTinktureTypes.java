package com.tyhone.arcanacraft.common.init;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.tinkture.TinktureRegistry;
import com.tyhone.arcanacraft.api.tinkture.TinktureType;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTinktureTypes {
	
	private static final List<TinktureType> TINKTURE_TYPES = new ArrayList<>();

	public static final TinktureType EMPTY = new TinktureType("empty", 0x000000);
	public static final TinktureType AQUA_REGIA = new TinktureType("aqua_regia", 0xff5500);
	public static final TinktureType ALCHEMIC_BASE = new TinktureType("alchemic_base", 0xffd9df);
	public static final TinktureType QUARTZ_SOLUTION = new TinktureType("quartz_solution", 0xd7dae0);
	public static final TinktureType IMPIRUS = new TinktureType("impirus", 0xed170b);
	public static final TinktureType AMNIS = new TinktureType("amnis", 0x0a78ed);
	public static final TinktureType REBUS = new TinktureType("rebus", 0x239909);
	public static final TinktureType LOCUS = new TinktureType("locus", 0xfcec0a);
	public static final TinktureType ANIMO = new TinktureType("animo", 0xf495e1);
	public static final TinktureType VOIDUS = new TinktureType("voidus", 0x322051);
	public static final TinktureType PURA = new TinktureType("pura", 0xADD6FF);
	public static final TinktureType EVOLITE_SAP = new TinktureType("evolite_sap", 0x96FA42);
	
	private ModTinktureTypes() {}
	
	public static Collection<TinktureType> getTinktureTypes(){
		return TINKTURE_TYPES;
	}
	
	public static void registerTinktureToList(TinktureType type){
		TINKTURE_TYPES.add(type);
	}
	
	public static void registerAll(){
		
		for(TinktureType type : TINKTURE_TYPES){
			TinktureRegistry.registerTinktureType(type);
		}
	}
}
