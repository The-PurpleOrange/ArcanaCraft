package com.tyhone.arcanacraft.common.reference;

import java.util.ArrayList;
import java.util.Arrays;

public class Names {

	public static final class MetaItems{
		
		public static final ArrayList<String> CHALK = al("charcoal", "bone", "blood", "lapis", "gold", "magicite");
        public static final ArrayList<String> ALCHEMIC_DUST = al("mundane", "salient", "lavarice", "avaricle");
        public static final ArrayList<String> SOUL = al("fragment", "complete");
        public static final ArrayList<String> LENS = al("essence", "soul");
        public static final ArrayList<String> ESSENCE = al("iron", "gold", "magicite", "blood_iron");
        public static final ArrayList<String> INGOT = al("magicite", "blood_iron", "alcharium");
        public static final ArrayList<String> DUST = al("blood", "bone", "charcoal", "iron", "gold", "magicite");
        public static final ArrayList<String> CRYSTAL = al("blood", "gold", "iron", "magicite");
        public static final ArrayList<String> ITEM = al("bone_ash", "clean_flesh");
        
	}
	
	private static ArrayList<String> al(String ... strings){
		return new ArrayList<String>(Arrays.asList(strings));
	}
	
}
