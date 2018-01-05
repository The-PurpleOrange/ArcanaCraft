package com.tyhone.arcanacraft.common.init;

import com.tyhone.arcanacraft.common.init.recipes.InitRecipesAlchemicArray;
import com.tyhone.arcanacraft.common.init.recipes.InitRecipesDeconstructionTable;
import com.tyhone.arcanacraft.common.init.recipes.InitRecipesHammer;
import com.tyhone.arcanacraft.common.init.recipes.InitRecipesInfusionAltar;
import com.tyhone.arcanacraft.common.init.recipes.InitRecipesSoulAltar;
import com.tyhone.arcanacraft.common.init.recipes.InitRecipesTransmutationAltar;

public class ModRecipes {
	public static void init(){
		InitRecipesDeconstructionTable.initDeconstructionTableRecipes();
		InitRecipesHammer.initHammerRecipes();
		InitRecipesSoulAltar.initSoulAltarRecipes();
		InitRecipesInfusionAltar.initInfusionAltarRecipes();
		InitRecipesTransmutationAltar.initTransmutationAltarRecipes();
		InitRecipesAlchemicArray.initAlchemicArrayRecipes();
	}
}
