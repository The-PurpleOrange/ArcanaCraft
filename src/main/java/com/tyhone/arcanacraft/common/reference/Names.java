package com.tyhone.arcanacraft.common.reference;

import java.util.ArrayList;
import java.util.Arrays;

public class Names {

	public static final class MetaItems{
		
		public static final ArrayList<String> CHALK = al("charcoal", "bone", "blood", "lapis", "gold", "magicite");
        public static final ArrayList<String> ALCHEMIC_DUST = al("mundane", "salient", "lavarice", "avaricle");
        public static final ArrayList<String> SOUL = al("fragment", "complete", "nether", "eldritch");
        public static final ArrayList<String> LENS = al("essence", "soul");
        public static final ArrayList<String> ESSENCE = al("iron", "gold", "magicite", "nether", "eldritch", "prosper", "greed", "cedius", "soul");
        public static final ArrayList<String> SHARD = al("nether", "eldritch", "prosper", "greed", "cedius");
        public static final ArrayList<String> INGOT = al("magicite", "blood_iron", "alcharium");
        public static final ArrayList<String> DUST = al("blood", "bone", "charcoal", "iron", "gold", "magicite", "ender", "emeradine", "diamond");
        public static final ArrayList<String> CRYSTAL = al("blood", "emeradine");
        public static final ArrayList<String> ITEM = al("bone_ash", "quartz_dust", "clean_flesh", "blood_drop", "star_shard", "inert_star", "heart", "heat_core", "ender_tear", "blood_rod", "heart_ender", "emeradine", "catalyst", "evolite_sap");
        public static final ArrayList<String> STAR = al("mundane", "eldritch", "virtuous");
		public static final ArrayList<String> NEEDLE = al("bone", "cactus", "bone_bloodied", "cactus_bloodied", "blood_iron");
        
	}
	
	private static ArrayList<String> al(String ... strings){
		return new ArrayList<String>(Arrays.asList(strings));
	}
	
}
