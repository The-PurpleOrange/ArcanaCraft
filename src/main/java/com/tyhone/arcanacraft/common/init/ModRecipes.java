package com.tyhone.arcanacraft.common.init;

import com.tyhone.arcanacraft.common.init.recipes.InitRecipesAlchemicArray;
import com.tyhone.arcanacraft.common.init.recipes.InitRecipesDeconstructionTable;
import com.tyhone.arcanacraft.common.init.recipes.InitRecipesHammer;
import com.tyhone.arcanacraft.common.init.recipes.InitRecipesInfusionAltar;
import com.tyhone.arcanacraft.common.init.recipes.InitRecipesSoulAltar;
import com.tyhone.arcanacraft.common.init.recipes.InitRecipesTransmutationAltar;
import com.tyhone.arcanacraft.common.init.recipes.InitVanillaRecipesSmelting;

public class ModRecipes {
	public static void init(){
		InitRecipesDeconstructionTable.init();
		InitRecipesHammer.init();
		//InitRecipesSoulAltar.init();
		//InitRecipesInfusionAltar.init();
		//InitRecipesTransmutationAltar.init();
		InitRecipesAlchemicArray.init();
		InitVanillaRecipesSmelting.init();
	}
}
