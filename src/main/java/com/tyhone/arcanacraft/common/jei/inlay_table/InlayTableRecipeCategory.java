package com.tyhone.arcanacraft.common.jei.inlay_table;

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
import net.minecraft.util.text.translation.I18n;

public class InlayTableRecipeCategory implements IRecipeCategory<InlayTableRecipeWrapper>{


    public static final String NAME = "arcanacraft:inlay_table";

    private final IDrawable background;
    
    public InlayTableRecipeCategory(IGuiHelper helper){
		this.background = helper.createDrawable(GuiUtils.getGuiLocation("gui_inlay_table"), 0, 0, 135, 36);
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
	public void setRecipe(IRecipeLayout recipeLayout, InlayTableRecipeWrapper wrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		List<List<ItemStack>> inputs = ingredients.getInputs(ItemStack.class);
		
		guiItemStacks.init(0, true, 5, 0);
		guiItemStacks.set(0, inputs.get(0));

		guiItemStacks.init(2, true, 23, 0);
		if(inputs.size()>=2){
			guiItemStacks.set(2, inputs.get(1));
		}
        
		guiItemStacks.init(3, true, 41, 0);
		if(inputs.size()>=3){
			guiItemStacks.set(3, inputs.get(2));
		}
        
		guiItemStacks.init(4, true, 59, 0);
		if(inputs.size()>=4){
			guiItemStacks.set(4, inputs.get(3));
		}
        
		guiItemStacks.init(5, true, 5, 18);
		if(inputs.size()==5){
			guiItemStacks.set(5, inputs.get(4));
		}
        
		guiItemStacks.init(6, true, 23, 18);
		if(inputs.size()>=6){
			guiItemStacks.set(6, inputs.get(5));
		}
        
		guiItemStacks.init(7, true, 41, 18);
		if(inputs.size()>=7){
			guiItemStacks.set(7, inputs.get(6));
		}
        
		guiItemStacks.init(8, true, 59, 18);
		if(inputs.size()>=8){
			guiItemStacks.set(8, inputs.get(7));
		}
        
        guiItemStacks.init(1, false, 108, 10);
        guiItemStacks.set(1, wrapper.recipe.getOutput());

	}

}
