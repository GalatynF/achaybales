package com.github.galatynf.achaybales.mixin;

import com.github.galatynf.achaybales.Achaybales;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HayBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HayBlock.class)
public class HayBlockMixin extends Block {
    public HayBlockMixin(Settings settings) {
        super(settings);
    }

    //Negates fall damage (A redirect instead of inject would be better)
    @Inject(at = @At("INVOKE"), method = "onLandedUpon", cancellable = true)
    private void onLandedUpon(World world, BlockPos pos, Entity entity, float distance, CallbackInfo info) {
        entity.handleFallDamage(distance, 0.0F);
        if(entity instanceof PlayerEntity && distance >= 15) {
            BlockPos posPlayer = entity.getBlockPos();
            world.playSound(posPlayer.getX(), posPlayer.getY(), posPlayer.getZ(),Achaybales.aclandevent, SoundCategory.AMBIENT, 1F,1F,true);
        }
        if(distance >= 30) {
            world.setBlockState(pos, Achaybales.untiedhay.getDefaultState());
        }
        info.cancel();
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        entity.slowMovement(state, new Vec3d(0.5D, 0.9D, 0.5D));
    }
}
