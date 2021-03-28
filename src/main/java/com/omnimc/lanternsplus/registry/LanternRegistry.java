package com.omnimc.lanternsplus.registry;

import com.omnimc.lanternsplus.LanternsPlus;
import com.omnimc.lanternsplus.block.BlockLantern;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class LanternRegistry {

    public static final DeferredRegister<Item> ITEMS                        = DeferredRegister.create(ForgeRegistries.ITEMS, LanternsPlus.MODID);
    //public static final RegistryObject<Item> ITEM_DEF                     = ITEMS.register("item_def", () -> new Item(new Item.Properties().group(ItemGroup.DECORATIONS).maxStackSize(64)));


    public static final DeferredRegister<Block> BLOCKS                      = DeferredRegister.create(ForgeRegistries.BLOCKS, LanternsPlus.MODID);
    public static final RegistryObject<Block> PRISMARINE_LANTERN            = BLOCKS.register("prismarine_lantern", () -> new BlockLantern(Block.Properties.of(Material.METAL, MaterialColor.QUARTZ).strength(3.5F).sound(SoundType.LANTERN).noOcclusion().lightLevel(state ->(14))));
    public static final RegistryObject<Block> PURPUR_LANTERN                = BLOCKS.register("purpur_lantern", () -> new BlockLantern(Block.Properties.of(Material.METAL, MaterialColor.COLOR_MAGENTA).strength(3.5F).sound(SoundType.LANTERN).noOcclusion().lightLevel(state ->(12))));
    public static final RegistryObject<Block> BLAZING_LANTERN               = BLOCKS.register("blazing_lantern", () -> new BlockLantern(Block.Properties.of(Material.METAL, MaterialColor.NETHER).strength(3.5F).sound(SoundType.LANTERN).noOcclusion().lightLevel(state ->(12))));
    public static final RegistryObject<Block> BROWN_MUSHROOM_LANTERN        = BLOCKS.register("brown_mushroom_lantern", () -> new BlockLantern(Block.Properties.of(Material.METAL, MaterialColor.COLOR_BROWN).strength(3.5F).sound(SoundType.LANTERN).noOcclusion().lightLevel(state ->(10))));
    public static final RegistryObject<Block> RED_MUSHROOM_LANTERN          = BLOCKS.register("red_mushroom_lantern", () -> new BlockLantern(Block.Properties.of(Material.METAL, MaterialColor.COLOR_RED).strength(3.5F).sound(SoundType.LANTERN).noOcclusion().lightLevel(state ->(10))));
    public static final RegistryObject<Block> FANCY_LANTERN                 = BLOCKS.register("fancy_lantern", () -> new BlockLantern(Block.Properties.copy(Blocks.LANTERN)));
    public static final RegistryObject<Block> FANCY_SOUL_LANTERN            = BLOCKS.register("fancy_soul_lantern", () -> new BlockLantern(Block.Properties.copy(Blocks.LANTERN)));
    public static final RegistryObject<Block> WARPED_FUNGUS_LANTERN         = BLOCKS.register("warped_fungus_lantern", () -> new BlockLantern(Block.Properties.of(Material.METAL, MaterialColor.COLOR_CYAN).strength(3.5F).sound(SoundType.LANTERN).noOcclusion().lightLevel(state ->(12))));
    public static final RegistryObject<Block> CRIMSON_FUNGUS_LANTERN        = BLOCKS.register("crimson_fungus_lantern", () -> new BlockLantern(Block.Properties.of(Material.METAL, MaterialColor.COLOR_RED).strength(3.5F).sound(SoundType.LANTERN).noOcclusion().lightLevel(state ->(12))));
    public static final RegistryObject<Block> GILDED_BLACKSTONE_LANTERN     = BLOCKS.register("gilded_blackstone_lantern", () -> new BlockLantern(Block.Properties.of(Material.METAL, MaterialColor.COLOR_BLACK).strength(3.5F).sound(SoundType.LANTERN).noOcclusion().lightLevel(state ->(12))));
    public static final RegistryObject<Block> QUARTZ_LANTERN                = BLOCKS.register("quartz_lantern", () -> new BlockLantern(Block.Properties.of(Material.METAL, MaterialColor.QUARTZ).strength(3.5F).sound(SoundType.LANTERN).noOcclusion().lightLevel(state ->(14))));



}