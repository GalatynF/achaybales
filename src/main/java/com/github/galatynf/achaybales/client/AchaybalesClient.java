package com.github.galatynf.achaybales.client;

import com.github.galatynf.achaybales.Achaybales;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class AchaybalesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(Achaybales.untiedhay, RenderLayer.getCutout());
    }
}
