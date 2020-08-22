package com.github.galatynf.achaybales.mixin;

import net.minecraft.block.HayBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HayBlock.class)
public class HayBlockMixin {
    @Inject(at = @At("INVOKE"), method = "onLandedUpon", cancellable = true)
    private void onLandedUpon(World world, BlockPos pos, Entity entity, float distance, CallbackInfo info) {
        System.out.println("Fell on a mixin hay !");
        entity.handleFallDamage(distance, 0.0F);
        entity.setPos(entity.getX(), pos.getY(), entity.getZ());

        info.cancel();
    }
}
