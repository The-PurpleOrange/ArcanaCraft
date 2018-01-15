package com.tyhone.arcanacraft.common.jei.infusion_altar;

import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.tinkture.TinktureStack;
import com.tyhone.arcanacraft.common.util.GuiUtils;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

public class InfusionAltarRecipeCategory implements IRecipeCategory<InfusionAltarRecipeWrapper>{


    public static final String NAME = "arcanacraft:infusion_altar";

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
		return I18n.translateToLocal("container.nei."+NAME+".name");
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
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		
		List<List<ItemStack>> inputs = ingredients.getInputs(ItemStack.class);
		List<TinktureStack> tInputs = wrapper.recipe.getTInputs();
		
		guiItemStacks.init(0, true, 37, 31);
		guiItemStacks.set(0, inputs.get(0));

		if(inputs.size()>=2){
			guiItemStacks.init(2, true, 26, 0);
			guiItemStacks.set(2, inputs.get(1));
		}
        
		if(inputs.size()>=3){
			guiItemStacks.init(3, true, 48, 0);
			guiItemStacks.set(3, inputs.get(2));
		}
        
		if(inputs.size()>=4){
			guiItemStacks.init(4, true, 6, 42);
			guiItemStacks.set(4, inputs.get(3));
		}
        
		if(inputs.size()>=5){
			guiItemStacks.init(5, true, 6, 20);
			guiItemStacks.set(5, inputs.get(4));
		}
        
		if(inputs.size()>=6){
			guiItemStacks.init(6, true, 68, 20);
			guiItemStacks.set(6, inputs.get(5));
		}
        
		if(inputs.size()>=7){
			guiItemStacks.init(7, true, 68, 42);
			guiItemStacks.set(7, inputs.get(6));
		}
        
		if(inputs.size()>=8){
			guiItemStacks.init(8, true, 48, 62);
			guiItemStacks.set(8, inputs.get(7));
		}
        
		if(inputs.size()==9){
			guiItemStacks.init(9, true, 26, 62);
			guiItemStacks.set(9, inputs.get(8));
		}
        
        guiItemStacks.init(1, false, 107, 31);
        guiItemStacks.set(1, wrapper.recipe.getOutput());

        if(tInputs.size()>=1){
        	recipeLayout.getIngredientsGroup(TinktureStack.class).init(10, true, 28, 22);
         	recipeLayout.getIngredientsGroup(TinktureStack.class).set(10, tInputs.get(0));
     	}
     	
        if(tInputs.size()>=2){
        	recipeLayout.getIngredientsGroup(TinktureStack.class).init(11, true, 58, 22);
         	recipeLayout.getIngredientsGroup(TinktureStack.class).set(11, tInputs.get(1));
     	}
     	
        if(tInputs.size()>=3){
        	recipeLayout.getIngredientsGroup(TinktureStack.class).init(12, true, 28, 52);
         	recipeLayout.getIngredientsGroup(TinktureStack.class).set(12, tInputs.get(2));
     	}
     	
        if(tInputs.size()==4){
        	recipeLayout.getIngredientsGroup(TinktureStack.class).init(13, true, 58, 52);
         	recipeLayout.getIngredientsGroup(TinktureStack.class).set(13, tInputs.get(3));
     	}

	}

}
