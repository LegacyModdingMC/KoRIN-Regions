package de.rinonline.korinrpg;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.registry.GameRegistry;

public class ItemRegistry {

    public static Item Runestone;

    public ItemRegistry() {

        Runestone = new ItemWanderMap().setUnlocalizedName("wandermap");
        GameRegistry.registerItem(Runestone, "Runestone");
        GameRegistry.addRecipe(
            new ItemStack(Runestone, 1),
            "BBB",
            "BAB",
            "BBB",
            'B',
            new ItemStack(Items.paper, 1),
            'A',
            new ItemStack(Items.emerald, 1));
    }
}
