package com.tyhone.arcanacraft.common.jei.transmutation_altar;

import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.util.GuiUtils;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.item.ItemStack;

public class TransmutationAltarRecipeCategory implements IRecipeCategory<TransmutationAltarRecipeWrapper>{


    public static final String NAME = "arcanacraft.transmutation_altar";

    private final IDrawable background;
    
    public TransmutationAltarRecipeCategory(IGuiHelper helper){
		this.background = helper.createDrawable(GuiUtils.getGuiLocation("gui_transmutation_altar"), 0, 0, 135, 62);
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
	public void setRecipe(IRecipeLayout recipeLayout, TransmutationAltarRecipeWrapper wrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		List<List<ItemStack>> inputs = ingredients.getInputs(ItemStack.class);
		
		guiItemStacks.init(0, true, 12, 0);
		guiItemStacks.set(0, inputs.get(0));

		guiItemStacks.init(2, true, 37, 0);
		if(inputs.size()>=2){
			guiItemStacks.set(2, inputs.get(1));
		}
        
		guiItemStacks.init(3, true, 12, 25);
		if(inputs.size()>=3){
			guiItemStacks.set(3, inputs.get(2));
		}
        
		guiItemStacks.init(4, true, 37, 25);
		if(inputs.size()==4){
			guiItemStacks.set(4, inputs.get(3));
		}
        
        guiItemStacks.init(1, false, 98, 13);
        guiItemStacks.set(1, wrapper.recipe.getOutput());

	}

}
