package com.tyhone.arcanacraft.common.init;

import com.tyhone.arcanacraft.common.init.recipes.InitRecipesDeconstructionTable;
import com.tyhone.arcanacraft.common.init.recipes.InitRecipesHammer;
import com.tyhone.arcanacraft.common.init.recipes.InitRecipesInfusionAltar;
import com.tyhone.arcanacraft.common.init.recipes.InitRecipesSoulAltar;
import com.tyhone.arcanacraft.common.init.recipes.InitRecipesTransmutationAltar;
import com.tyhone.arcanacraft.common.init.rituals.InitBasicRitualCircle;

public class ModRituals {
	public static void init(){
		InitBasicRitualCircle.register();
	}
}
