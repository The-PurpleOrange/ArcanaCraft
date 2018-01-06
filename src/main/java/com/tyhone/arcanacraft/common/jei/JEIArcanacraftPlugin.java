package com.tyhone.arcanacraft.common.jei;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.recipe.ArcanacraftCraftingManager;
import com.tyhone.arcanacraft.api.recipe.RecipeAlchemicArray;
import com.tyhone.arcanacraft.api.recipe.RecipeDeconstructionTable;
import com.tyhone.arcanacraft.api.recipe.RecipeHammer;
import com.tyhone.arcanacraft.api.recipe.RecipeInfusionAltar;
import com.tyhone.arcanacraft.api.recipe.RecipeSoulAltar;
import com.tyhone.arcanacraft.api.recipe.RecipeTransmutationAltar;
import com.tyhone.arcanacraft.api.registries.TinktureManager;
import com.tyhone.arcanacraft.api.registries.TinktureStack;
import com.tyhone.arcanacraft.api.registries.TinktureType;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.jei.alchemic_array.AlchemicArrayRecipeCategory;
import com.tyhone.arcanacraft.common.jei.alchemic_array.AlchemicArrayRecipeWrapper;
import com.tyhone.arcanacraft.common.jei.deconstruction_table.DeconstructionTableRecipeCategory;
import com.tyhone.arcanacraft.common.jei.deconstruction_table.DeconstructionTableRecipeWrapper;
import com.tyhone.arcanacraft.common.jei.hammer.HammerRecipeCategory;
import com.tyhone.arcanacraft.common.jei.hammer.HammerRecipeWrapper;
import com.tyhone.arcanacraft.common.jei.infusion_altar.InfusionAltarRecipeCategory;
import com.tyhone.arcanacraft.common.jei.infusion_altar.InfusionAltarRecipeWrapper;
import com.tyhone.arcanacraft.common.jei.soul_altar.SoulAltarRecipeCategory;
import com.tyhone.arcanacraft.common.jei.soul_altar.SoulAltarRecipeWrapper;
import com.tyhone.arcanacraft.common.jei.transmutation_altar.TransmutationAltarRecipeCategory;
import com.tyhone.arcanacraft.common.jei.transmutation_altar.TransmutationAltarRecipeWrapper;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientHelper;
import mezz.jei.api.ingredients.IIngredientRenderer;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class JEIArcanacraftPlugin implements IModPlugin {
	
	@Override
	public void registerIngredients(IModIngredientRegistration registry) {
		
		Collection<TinktureStack> tinktureStackIngredient = new ArrayList<>();
		for(TinktureType type : TinktureManager.getTinktureTypes()){
			tinktureStackIngredient.add(new TinktureStack(type, 0));
		}
		
		registry.register(TinktureStack.class, tinktureStackIngredient, new TinktureIngredientHelper(), new tinktureIngredientRenderer());
		
		//IModPlugin.super.registerIngredients(registry);
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry){
		IJeiHelpers helpers = registry.getJeiHelpers();
		registry.addRecipeCategories(
				new HammerRecipeCategory(helpers.getGuiHelper()),
				new DeconstructionTableRecipeCategory(helpers.getGuiHelper()),
				new TransmutationAltarRecipeCategory(helpers.getGuiHelper()),
				new SoulAltarRecipeCategory(helpers.getGuiHelper()),
				new InfusionAltarRecipeCategory(helpers.getGuiHelper()),
				new AlchemicArrayRecipeCategory(helpers.getGuiHelper())
		);
	}
	
	@Override
	public void register(IModRegistry registry){
		IJeiHelpers helpers = registry.getJeiHelpers();

		registry.handleRecipes(RecipeHammer.class, HammerRecipeWrapper::new, HammerRecipeCategory.NAME);
		registry.handleRecipes(RecipeDeconstructionTable.class, DeconstructionTableRecipeWrapper::new, DeconstructionTableRecipeCategory.NAME);
		registry.handleRecipes(RecipeTransmutationAltar.class, TransmutationAltarRecipeWrapper::new, TransmutationAltarRecipeCategory.NAME);
		registry.handleRecipes(RecipeSoulAltar.class, SoulAltarRecipeWrapper::new, SoulAltarRecipeCategory.NAME);
		registry.handleRecipes(RecipeInfusionAltar.class, InfusionAltarRecipeWrapper::new, InfusionAltarRecipeCategory.NAME);
		registry.handleRecipes(RecipeAlchemicArray.class, AlchemicArrayRecipeWrapper::new, AlchemicArrayRecipeCategory.NAME);

		registry.addRecipes(ArcanacraftCraftingManager.getHammerRecipes(), HammerRecipeCategory.NAME);
		registry.addRecipes(ArcanacraftCraftingManager.getDeconstructionRecipes(), DeconstructionTableRecipeCategory.NAME);
		registry.addRecipes(ArcanacraftCraftingManager.getTransmutationAltarRecipes(), TransmutationAltarRecipeCategory.NAME);
		registry.addRecipes(ArcanacraftCraftingManager.getSoulAltarRecipes(), SoulAltarRecipeCategory.NAME);
		registry.addRecipes(ArcanacraftCraftingManager.getInfusionAltarRecipes(), InfusionAltarRecipeCategory.NAME);
		registry.addRecipes(ArcanacraftCraftingManager.getAlchemicArrayRecipes(), AlchemicArrayRecipeCategory.NAME);

		registry.addRecipeCatalyst(new ItemStack(ModItems.HAMMER), HammerRecipeCategory.NAME);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.DECONSTRUCTION_TABLE), DeconstructionTableRecipeCategory.NAME);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.TRANSMUTATION_ALTAR), TransmutationAltarRecipeCategory.NAME);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.SOUL_ALTAR), SoulAltarRecipeCategory.NAME);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.INFUSION_ALTAR), InfusionAltarRecipeCategory.NAME);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.ALCHEMIC_ARRAY), AlchemicArrayRecipeCategory.NAME);
	}
	


	private static class TinktureIngredientHelper implements IIngredientHelper<TinktureStack>{

		@Override
		public List<TinktureStack> expandSubtypes(List<TinktureStack> ingredients) {
			return ingredients;
		}

		@Override
		public TinktureStack getMatch(Iterable<TinktureStack> ingredients, TinktureStack ingredientToMatch) {
			for(TinktureStack tinktureStack : ingredients){
				if(tinktureStack.getTinktureType() == ingredientToMatch.getTinktureType()){
					return tinktureStack;
				}
			}
			return null;
		}

		@Override
		public String getDisplayName(TinktureStack ingredient) {
			return "Tinkture ingredient Display Name" + ingredient.getTinktureType().getFluidType();
		}

		@Override
		public String getUniqueId(TinktureStack ingredient) {
			return "Tinkture ingredient Unique ID" + ingredient.getTinktureType().getFluidType();
		}

		@Override
		public String getWildcardId(TinktureStack ingredient) {
			return "Tinkture ingredient Wildcard ID" + ingredient.getTinktureType().getFluidType();
		}

		@Override
		public String getModId(TinktureStack ingredient) {
			return Arcanacraft.MODID;
		}

		@Override
		public Iterable<Color> getColors(TinktureStack ingredient) {
			return Collections.singleton(Color.BLACK);
		}

		@Override
		public String getResourceId(TinktureStack ingredient) {
			return "Tinkture Ingredient Resource Id " + ingredient.getTinktureType().getFluidType();
		}

		@Override
		public TinktureStack copyIngredient(TinktureStack ingredient) {
			return ingredient.copy();
		}

		@Override
		public String getErrorInfo(TinktureStack ingredient) {
			return "Tinkture Ingredient Error Info " + ingredient.getTinktureType().getFluidType();
		}
	}
	
	private static class tinktureIngredientRenderer implements IIngredientRenderer<TinktureStack> {

		@Override
		public void render(Minecraft minecraft, int xPosition, int yPosition, TinktureStack ingredient) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public List<String> getTooltip(Minecraft minecraft, TinktureStack ingredient, ITooltipFlag tooltipFlag) {
			return Collections.singletonList("Tinkture Ingredient Tooltip " + ingredient);
		}
	}
}
