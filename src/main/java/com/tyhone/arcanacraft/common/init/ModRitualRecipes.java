package com.tyhone.arcanacraft.common.init;

import com.tyhone.arcanacraft.common.init.rituals.InitGrandRitualCircleRecipe;
import com.tyhone.arcanacraft.common.init.rituals.InitRitualCircleRecipe;

public class ModRitualRecipes {
	public static void init(){
		InitRitualCircleRecipe.initRecipe();
		InitGrandRitualCircleRecipe.initRecipe();
	}
}
