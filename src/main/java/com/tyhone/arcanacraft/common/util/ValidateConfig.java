package com.tyhone.arcanacraft.common.util;

import java.awt.List;
import java.util.ArrayList;
import java.util.Map.Entry;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.Config;

import net.minecraftforge.oredict.OreDictionary;

public class ValidateConfig {

	public static void init(){
		
		ArrayList<String> remove = new ArrayList<String>();
		int totalValue = 0;
		
		for(Entry<String, Integer> entry : Config.quarryOreMap.entrySet()) {
			if(!OreDictionary.doesOreNameExist(entry.getKey())) {
				Arcanacraft.logger.error("No Oredict entry for " + entry.getKey());
				remove.add(entry.getKey());
			}
			else {
				totalValue += entry.getValue();
			}
		}

		Config.quarryOreMapTotalValue = totalValue;
		
		for(String item : remove) {
			try {
				Config.quarryOreMap.remove(item);
			} catch(Exception e) {
				Arcanacraft.logger.error("Problem editing QuarryOreMap for entry", e);
			}
		}
	}
}
