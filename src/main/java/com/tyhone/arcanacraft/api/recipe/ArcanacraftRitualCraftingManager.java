package com.tyhone.arcanacraft.api.recipe;

import java.util.ArrayList;
import java.util.List;

import com.tyhone.arcanacraft.api.ritual.Ritual;
import com.tyhone.arcanacraft.common.util.RecipeUtil;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class ArcanacraftRitualCraftingManager {

	private static final int[] standardPlaceOrder = {
		12, 7, 13, 17, 11, 
		6, 8, 18, 16, 1, 
		2, 3, 9, 14, 19, 
		23, 22, 21, 15, 10, 
		5, 0, 4, 24, 20
	};
	
	private static final int[] grandPlaceOrder = {
		84, 71, 85, 97, 83, 70, 72, 98, 96, 95, 82, 69, 57, 
		58, 59, 73, 86, 99, 111, 110, 109, 108, 94, 81, 68, 56, 
		44, 45, 46, 60, 74, 87, 100, 112, 124, 123, 122, 121, 107, 
		93, 80, 67, 55, 43, 31, 32, 33, 47, 61, 75, 88, 101, 
		113, 125, 137, 136, 135, 134, 133, 119, 106, 92, 79, 66, 54, 
		41, 29, 30, 18, 19, 20, 34, 35, 49, 62, 76, 89, 102, 
		114, 127, 139, 138, 150, 149, 148, 146, 145, 131, 118, 104, 91, 
		78, 65, 52, 40, 27, 15, 16, 4, 5, 6, 7, 8, 22, 
		23, 37, 50, 64, 77, 90, 103, 116, 128, 141, 153, 152, 164, 
		163, 162, 161, 160, 159, 158, 157, 144, 143, 130, 117, 156, 39, 
		26, 13, 14, 1, 2, 3, 0, 9, 10, 11, 24, 25, 38, 
		51, 12, 129, 142, 155, 154, 167, 166, 165, 168, 42, 48, 126, 
		120, 53, 28, 17, 21, 36, 63, 115, 140, 151, 147, 132, 105
	};

	private static final List<BlockPos> posStandardOrderList = buildPosList();
	private static final List<BlockPos> posGrandOrderList = buildGrandPosList();
	
	private static List<BlockPos> buildPosList(){
		int s=2;
		List<BlockPos> posList = new ArrayList<>();
    	for(int x = -s; x < s+1; x++){
    		for(int z = -s; z < s+1; z++){
    			posList.add(p(x, z));
    		}
    	}
    	return posList;
	}
	
	private static List<BlockPos> buildGrandPosList(){
		int s=6;
		List<BlockPos> posList = new ArrayList<>();
    	for(int x = -s; x < s+1; x++){
    		for(int z = -s; z < s+1; z++){
    			posList.add(p(x, z));
    		}
    	}
    	return posList;
	}

	
	private static List<RecipeRitualCircle> standardRitualCircleRecipe = new ArrayList<RecipeRitualCircle>();
	private static List<RecipeRitualCircle> grandRitualCircleRecipe = new ArrayList<RecipeRitualCircle>();
	


	private static BlockPos p(int x, int z) {
		return new BlockPos(x, 0, z);
	}
	
	//RITUAL CIRCLE RECIPES
	public static RecipeRitualCircle registerRitualCircleRecipe(Ritual ritual, ItemStack[] itemInputs, Object[] blockInputs) {
		RecipeRitualCircle recipe = new RecipeRitualCircle(ritual, itemInputs, blockInputs);
		standardRitualCircleRecipe.add(recipe);
		return recipe;
	}
	public static List<RecipeRitualCircle> getRitualCircleRecipes(){
		return standardRitualCircleRecipe;
	}
	
	public static int[] getStandardPlaceOrder(){
		return standardPlaceOrder;
	}
	
	public static List<BlockPos> getStandardBlockPosList(){
		return posStandardOrderList;
	}


	//GRAND RITUAL CIRCLE RECIPES
	public static RecipeRitualCircle registerGrandRitualCircleRecipe(Ritual ritual, ItemStack[] itemInputs, Object[] blockInputs) {
		RecipeRitualCircle recipe = new RecipeRitualCircle(ritual, itemInputs, blockInputs);
		grandRitualCircleRecipe.add(recipe);
		return recipe;
	}
	public static List<RecipeRitualCircle> getGrandRitualCircleRecipes(){
		return grandRitualCircleRecipe;
	}
	
	public static int[] getGrandPlaceOrder(){
		return grandPlaceOrder;
	}
	
	public static List<BlockPos> getGrandBlockPosList(){
		return posGrandOrderList;
	}
	
	//OTHER

	//GET RITUAL RECIPE FROM LIST
	public static RecipeRitualCircle getRecipe(List<ItemStack> itemStacks, List<ItemStack> blockStacks, List<RecipeRitualCircle> recipeList){
		for(RecipeRitualCircle recipe : recipeList){
			if(recipe.matchesBlocks(blockStacks)){
				if(itemStacks!=null && RecipeUtil.doInputsMatch((ArrayList<Object>) recipe.getItemRequirements(), (ArrayList<ItemStack>) itemStacks)){
					return recipe;
				}
			}
		}
		return null;
	}
		
}
