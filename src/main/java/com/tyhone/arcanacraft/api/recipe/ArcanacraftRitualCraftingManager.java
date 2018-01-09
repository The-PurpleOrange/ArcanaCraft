package com.tyhone.arcanacraft.api.recipe;

import java.util.ArrayList;
import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.ritual.RitualBase;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class ArcanacraftRitualCraftingManager {

	private static final int[] placeOrder = {12,7,13,17,11,6,8,18,16,1,2,3,9,14,19,23,22,21,15,10,5,0,4,24,20};
	
	private static final List<BlockPos> posOrderList = buildPosList();
	
	private static List<BlockPos> buildPosList(){
		int s=2;
		List<BlockPos> posList = new ArrayList<>();
    	for(int x = -s; x < s+1; x++){
    		for(int z = -s; z < s+1; z++){
    			posList.add(new BlockPos(x, 0, z));
    		}
    	}
    	return posList;
	}
	
	private static List<RecipeRitualCircle> ritualCircleRecipe = new ArrayList<RecipeRitualCircle>();
	//private static Map<String, RecipeRitualCircle> activationCircleRecipes = new HashMap<String, RecipeRitualCircle>();
	

	//ACTIVATION CIRCLE RECIPES
	public static RecipeRitualCircle registerRitualCircleRecipe(RitualBase ritual, ItemStack[] itemInputs, Object[] blockInputs) {
		RecipeRitualCircle recipe = new RecipeRitualCircle(ritual, itemInputs, blockInputs);
		ritualCircleRecipe.add(recipe);
		//activationCircleRecipes.put(unlocalizedName, recipe);
		return recipe;
	}
	/*public static RecipeRitualCircle registerRitualCircle(String unlocalizedName, Object[] blockInputs) {
		RecipeRitualCircle recipe = registerRitualCircle(unlocalizedName, null, blockInputs);
		return recipe;
	}*/
	public static List<RecipeRitualCircle> getRitualCircleRecipes(){
		return ritualCircleRecipe;
	}
	
	public static int[] getPlaceOrder(){
		return placeOrder;
	}
	
	public static BlockPos getBlockPlaceFromList(int i){
		return posOrderList.get(i);
	}

	private static BlockPos p(int x, int z) {
		return new BlockPos(x, 0, z);
	}
}
