package com.tyhone.arcanacraft.common.init;

import com.tyhone.arcanacraft.common.init.recipes.InitRecipesDeconstructionTable;
import com.tyhone.arcanacraft.common.init.recipes.InitRecipesHammer;
import com.tyhone.arcanacraft.common.init.recipes.InitRecipesInfusionAltar;
import com.tyhone.arcanacraft.common.init.recipes.InitRecipesSoulAltar;

public class ModRecipes {
	public static void init(){
		InitRecipesDeconstructionTable.initDeconstructionTableRecipes();
		InitRecipesHammer.initHammerRecipes();
		InitRecipesSoulAltar.initSoulAltarRecipes();
		InitRecipesInfusionAltar.initInfusionAltarRecipes();
	}
}
