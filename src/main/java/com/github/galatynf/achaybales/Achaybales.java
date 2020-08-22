package com.github.galatynf.achaybales;

import net.fabricmc.api.ModInitializer;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class Achaybales implements ModInitializer {

    public static final Identifier acjump = new Identifier("tutorial:acjump");
    public static SoundEvent acjumpevent = new SoundEvent(acjump);

    @Override
    public void onInitialize() {
        Registry.register(Registry.SOUND_EVENT, Achaybales.acjump, acjumpevent);
    }
}
