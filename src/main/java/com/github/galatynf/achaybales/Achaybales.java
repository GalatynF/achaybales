package com.github.galatynf.achaybales;

import net.fabricmc.api.ModInitializer;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class Achaybales implements ModInitializer {

    public static final Identifier eagle = new Identifier("achaybales:eagle");
    public static SoundEvent eagleevent = new SoundEvent(eagle);
    public static final Identifier acland = new Identifier("achaybales:landing");
    public static SoundEvent aclandevent = new SoundEvent(acland);

    @Override
    public void onInitialize() {
        Registry.register(Registry.SOUND_EVENT, Achaybales.eagle, eagleevent);
        Registry.register(Registry.SOUND_EVENT, Achaybales.acland, aclandevent);
    }
}
