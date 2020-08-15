package com.omnimc.lanternsplus;

import com.omnimc.lanternsplus.block.BlockLantern;
import com.omnimc.lanternsplus.registry.LanternRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;

@Mod(LanternsPlus.MODID)
@Mod.EventBusSubscriber(modid = LanternsPlus.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class LanternsPlus {
    public static final String MODID = "lanternsplus";

    public LanternsPlus() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);
        LanternRegistry.ITEMS.register(modEventBus);
        LanternRegistry.BLOCKS.register(modEventBus);
    }

    //@SuppressWarnings("ConstantConditions")
    //@SubscribeEvent(priority = EventPriority.LOWEST)
    //public static void registerVanillaOverrides(RegistryEvent.Register<Block> event) {
    //    event.getRegistry().register(new BlockLantern(Block.Properties.from(Blocks.LANTERN)).setRegistryName(new ResourceLocation("minecraft", "lantern")));
    //}

    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        LanternRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
            final Item.Properties properties = new Item.Properties().maxStackSize(64).group(ItemGroup.DECORATIONS);
            final BlockItem blockItem = new BlockItem(block, properties);
            blockItem.setRegistryName(block.getRegistryName());
            registry.register(blockItem);

        });

    }
    
    private void setup(final FMLCommonSetupEvent event){
        RenderTypeLookup.setRenderLayer(LanternRegistry.PRISMARINE_LANTERN.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(LanternRegistry.PURPUR_LANTERN.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(LanternRegistry.BLAZING_LANTERN.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(LanternRegistry.RED_MUSHROOM_LANTERN.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(LanternRegistry.FANCY_LANTERN.get(), RenderType.getCutout());
    }

    private void doClientStuff(final FMLClientSetupEvent event){
    }
}
