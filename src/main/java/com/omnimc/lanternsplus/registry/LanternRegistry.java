package com.omnimc.lanternsplus.registry;

import com.omnimc.lanternsplus.LanternsPlus;
import com.omnimc.lanternsplus.block.BlockLantern;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class LanternRegistry {

    public static final DeferredRegister<Item> ITEMS                        = new DeferredRegister<>(ForgeRegistries.ITEMS, LanternsPlus.MODID);
    //public static final RegistryObject<Item> ITEM_DEF                       = ITEMS.register("item_def", () -> new Item(new Item.Properties().group(ItemGroup.DECORATIONS).maxStackSize(64)));


    public static final DeferredRegister<Block> BLOCKS                      = new DeferredRegister<>(ForgeRegistries.BLOCKS, LanternsPlus.MODID);
    public static final RegistryObject<Block> PRISMARINE_LANTERN            = BLOCKS.register("prismarine_lantern", () -> new BlockLantern(Block.Properties.create(Material.IRON, MaterialColor.QUARTZ).hardnessAndResistance(3.5F).sound(SoundType.LANTERN).notSolid().lightValue(14)));
    public static final RegistryObject<Block> PURPUR_LANTERN                = BLOCKS.register("purpur_lantern", () -> new BlockLantern(Block.Properties.create(Material.IRON, MaterialColor.QUARTZ).hardnessAndResistance(3.5F).sound(SoundType.LANTERN).notSolid().lightValue(12)));
    public static final RegistryObject<Block> BLAZING_LANTERN               = BLOCKS.register("blazing_lantern", () -> new BlockLantern(Block.Properties.create(Material.IRON, MaterialColor.QUARTZ).hardnessAndResistance(3.5F).sound(SoundType.LANTERN).notSolid().lightValue(12)));
    public static final RegistryObject<Block> RED_MUSHROOM_LANTERN               = BLOCKS.register("red_mushroom_lantern", () -> new BlockLantern(Block.Properties.create(Material.IRON, MaterialColor.QUARTZ).hardnessAndResistance(3.5F).sound(SoundType.LANTERN).notSolid().lightValue(10)));

}