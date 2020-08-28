package com.github.galatynf.achaybales.mixin;

import com.github.galatynf.achaybales.Achaybales;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.sound.Sound;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.rmi.StubNotFoundException;

@Mixin(PlayerEntity.class)
public abstract class JumpingMixin extends LivingEntity {

    protected JumpingMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow public abstract void playSound(SoundEvent sound, float volume, float pitch);

    @Shadow public abstract boolean isCreative();

    @Shadow protected boolean isSubmergedInWater;

    @Inject(at = @At("INVOKE"), method = "tick")
    private void playEagleSound(CallbackInfo info) {
        if(!onGround && !isCreative() && !isSubmergedInWater){
            double playerSpeed = getVelocity().getY();
            BlockPos posIterator = getBlockPos();
            boolean thereIsHayWater = false;
            /*
                Check if there is hay or water below the player
             */
            while (world.getBlockState(posIterator).getBlock() == Blocks.AIR) {
                posIterator = posIterator.down();
            }
            if(world.getBlockState(posIterator).getBlock() == Blocks.HAY_BLOCK ||
                world.getBlockState(posIterator).getBlock() == Blocks.WATER) {
                thereIsHayWater = true;
            }

            /*
                Check if the player is moving downwards with speed
             */
            if(playerSpeed < -0.5F &&
                playerSpeed > -0.9F &&
                world.getTime()%2 == 0 &&
                thereIsHayWater) {
                playSound(Achaybales.acjumpevent, 1.0F, 1.0F);
            }
        }
    }
}
