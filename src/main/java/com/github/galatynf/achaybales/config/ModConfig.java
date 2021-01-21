package com.github.galatynf.achaybales.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;

@Config(name = "achaybales")
public class ModConfig implements ConfigData {
    public boolean playEagleSound = true;
    public boolean hayBecomesMessy = true;
    public boolean entitiesBecomeInvisible = true;
}