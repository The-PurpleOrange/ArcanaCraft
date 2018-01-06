package com.tyhone.arcanacraft.common.jei.soul_altar;

import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.util.GuiUtils;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.item.ItemStack;

public class SoulAltarRecipeCategory implements IRecipeCategory<SoulAltarRecipeWrapper>{


    public static final String NAME = "arcanacraft.soul_altar";

    private final IDrawable background;
    
    public SoulAltarRecipeCategory(IGuiHelper helper){
		this.background = helper.createDrawable(GuiUtils.getGuiLocation("gui_soul_altar"), 0, 0, 135, 80);
	}
	
	@Override
	public String getUid() {
		return NAME;
	}

	@Override
	public String getTitle() {
		return ("container.nei."+NAME+".name");
	}

	@Override
	public String getModName() {
		return Arcanacraft.MODNAME;
	}

	@Override
	public IDrawable getBackground() {
		return this.background;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, SoulAltarRecipeWrapper wrapper, IIngredients ingredients) {
		List<ItemStack> inputs = wrapper.recipe.getInputs();
		
		recipeLayout.getItemStacks().init(0, true, 24, 29);
		recipeLayout.getItemStacks().set(0, wrapper.recipe.getInfusionItem());

		recipeLayout.getItemStacks().init(2, true, 24, 7);
		if(inputs.size()>=1){
			recipeLayout.getItemStacks().set(2, inputs.get(0));
		}
        
		recipeLayout.getItemStacks().init(3, true, 38, 51);
		if(inputs.size()>=2){
			recipeLayout.getItemStacks().set(3, inputs.get(1));
		}
        
		recipeLayout.getItemStacks().init(4, true, 3, 26);
		if(inputs.size()>=3){
			recipeLayout.getItemStacks().set(4, inputs.get(2));
		}
        
		recipeLayout.getItemStacks().init(5, true, 45, 26);
		if(inputs.size()>=4){
			recipeLayout.getItemStacks().set(5, inputs.get(3));
		}
        
		recipeLayout.getItemStacks().init(6, true, 10, 51);
		if(inputs.size()==5){
			recipeLayout.getItemStacks().set(6, inputs.get(4));
		}
        
        recipeLayout.getItemStacks().init(1, false, 107, 31);
        recipeLayout.getItemStacks().set(1, wrapper.recipe.getOutput());

	}

}
