package com.tyhone.arcanacraft.common.init;

import com.tyhone.arcanacraft.common.init.rituals.InitGrandRitualCircleRecipe;
import com.tyhone.arcanacraft.common.init.rituals.InitRitualChalkRecipe;
import com.tyhone.arcanacraft.common.init.rituals.InitRitualCircleRecipe;

public class ModRitualRecipes {
	public static void init(){
		InitRitualChalkRecipe.initRecipe();
		InitRitualCircleRecipe.initRecipe();
		InitGrandRitualCircleRecipe.initRecipe();
	}
}
