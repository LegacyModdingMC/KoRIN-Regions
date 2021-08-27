package de.rinonline.korinrpg;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemRegistry {

	public static Item Runestone;

	public ItemRegistry() {

		  Runestone = new ItemWanderMap().setUnlocalizedName("wandermap");
		  GameRegistry.registerItem(Runestone, "Runestone");
		  GameRegistry.addRecipe(new ItemStack(Runestone,1), new Object[] {"BBB","BAB","BBB",'B', new ItemStack(Items.paper,1),'A', new ItemStack(Items.emerald,1)});
	}
}