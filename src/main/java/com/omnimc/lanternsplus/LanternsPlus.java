package com.omnimc.lanternsplus;

import com.omnimc.lanternsplus.registry.LanternRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
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
public class
LanternsPlus {
    public static final String MODID = "lanternsplus";

    public LanternsPlus() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);
        LanternRegistry.ITEMS.register(modEventBus);
        LanternRegistry.BLOCKS.register(modEventBus);
    }

    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        LanternRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
            final Item.Properties properties = new Item.Properties().stacksTo(64).tab(ItemGroup.TAB_DECORATIONS);
            final BlockItem blockItem = new BlockItem(block, properties);
            blockItem.setRegistryName(block.getRegistryName());
            registry.register(blockItem);

        });

    }
    
    private void setup(final FMLCommonSetupEvent event){
        RenderTypeLookup.setRenderLayer(LanternRegistry.PRISMARINE_LANTERN.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(LanternRegistry.PURPUR_LANTERN.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(LanternRegistry.BLAZING_LANTERN.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(LanternRegistry.BROWN_MUSHROOM_LANTERN.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(LanternRegistry.RED_MUSHROOM_LANTERN.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(LanternRegistry.FANCY_LANTERN.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(LanternRegistry.FANCY_SOUL_LANTERN.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(LanternRegistry.WARPED_FUNGUS_LANTERN.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(LanternRegistry.CRIMSON_FUNGUS_LANTERN.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(LanternRegistry.GILDED_BLACKSTONE_LANTERN.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(LanternRegistry.QUARTZ_LANTERN.get(), RenderType.cutout());

    }

    private void doClientStuff(final FMLClientSetupEvent event){
    }
}
