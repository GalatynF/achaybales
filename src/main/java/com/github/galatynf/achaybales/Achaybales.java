package com.github.galatynf.achaybales;

import com.github.galatynf.achaybales.config.ModConfig;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class Achaybales implements ModInitializer {

    /** SOUNDS */
    public static final Identifier eagle = new Identifier("achaybales:eagle");
    public static SoundEvent eagleevent = new SoundEvent(eagle);
    public static final Identifier acland = new Identifier("achaybales:landing");
    public static SoundEvent aclandevent = new SoundEvent(acland);

    /** BLOCKS */
    public static final Block untiedhay = new UntiedHay(FabricBlockSettings.of(Material.LEAVES).hardness(0.5F).sounds(BlockSoundGroup.GRASS).breakByTool(FabricToolTags.HOES));
    public static final Item untiedhayitem = new BlockItem(untiedhay, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));

    @Override
    public void onInitialize() {
        Registry.register(Registry.SOUND_EVENT, Achaybales.eagle, eagleevent);
        Registry.register(Registry.SOUND_EVENT, Achaybales.acland, aclandevent);
        Registry.register(Registry.BLOCK, new Identifier("achaybales", "untiedhay"), untiedhay);
        Registry.register(Registry.ITEM, new Identifier("achaybales", "untiedhay"), untiedhayitem);

        AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);
    }
}
