package com.tyhone.arcanacraft.common.jei.infusion_altar;

import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.registries.TinktureStack;
import com.tyhone.arcanacraft.common.util.GuiUtils;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.item.ItemStack;

public class InfusionAltarRecipeCategory implements IRecipeCategory<InfusionAltarRecipeWrapper>{


    public static final String NAME = "arcanacraft.infusion_altar";

    private final IDrawable background;
    
    public InfusionAltarRecipeCategory(IGuiHelper helper){
		this.background = helper.createDrawable(GuiUtils.getGuiLocation("gui_infusion_altar"), 0, 0, 135, 80);
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
	public void setRecipe(IRecipeLayout recipeLayout, InfusionAltarRecipeWrapper wrapper, IIngredients ingredients) {
		List<ItemStack> inputs = wrapper.recipe.getInputs();
		List<TinktureStack> tInputs = wrapper.recipe.getTInputs();
		
		recipeLayout.getItemStacks().init(0, true, 37, 37);
		recipeLayout.getItemStacks().set(0, wrapper.recipe.getInfusionItem());

		recipeLayout.getItemStacks().init(2, true, 26, 6);
		if(inputs.size()>=1){
			recipeLayout.getItemStacks().set(2, inputs.get(0));
		}
        
		recipeLayout.getItemStacks().init(3, true, 48, 6);
		if(inputs.size()>=2){
			recipeLayout.getItemStacks().set(3, inputs.get(1));
		}
        
		recipeLayout.getItemStacks().init(4, true, 6, 48);
		if(inputs.size()>=3){
			recipeLayout.getItemStacks().set(4, inputs.get(2));
		}
        
		recipeLayout.getItemStacks().init(5, true, 6, 26);
		if(inputs.size()>=4){
			recipeLayout.getItemStacks().set(5, inputs.get(3));
		}
        
		recipeLayout.getItemStacks().init(6, true, 68, 26);
		if(inputs.size()>=5){
			recipeLayout.getItemStacks().set(6, inputs.get(4));
		}
        
		recipeLayout.getItemStacks().init(7, true, 68, 48);
		if(inputs.size()>=6){
			recipeLayout.getItemStacks().set(7, inputs.get(5));
		}
        
		recipeLayout.getItemStacks().init(8, true, 48, 68);
		if(inputs.size()>=7){
			recipeLayout.getItemStacks().set(8, inputs.get(6));
		}
        
		recipeLayout.getItemStacks().init(9, true, 26, 68);
		if(inputs.size()==8){
			recipeLayout.getItemStacks().set(9, inputs.get(7));
		}
        
        recipeLayout.getItemStacks().init(1, false, 107, 37);
        recipeLayout.getItemStacks().set(1, wrapper.recipe.getOutput());

        recipeLayout.getIngredientsGroup(TinktureStack.class).init(10, true, 28, 28);
     	if(tInputs.size()>=1){
     		recipeLayout.getIngredientsGroup(TinktureStack.class).set(10, tInputs.get(0));
     	}
     	
        recipeLayout.getIngredientsGroup(TinktureStack.class).init(11, true, 58, 28);
     	if(tInputs.size()>=2){
     		recipeLayout.getIngredientsGroup(TinktureStack.class).set(11, tInputs.get(1));
     	}
     	
        recipeLayout.getIngredientsGroup(TinktureStack.class).init(12, true, 28, 58);
     	if(tInputs.size()>=3){
     		recipeLayout.getIngredientsGroup(TinktureStack.class).set(12, tInputs.get(2));
     	}
     	
        recipeLayout.getIngredientsGroup(TinktureStack.class).init(13, true, 58, 58);
     	if(tInputs.size()==4){
     		recipeLayout.getIngredientsGroup(TinktureStack.class).set(13, tInputs.get(3));
     	}

	}

}
