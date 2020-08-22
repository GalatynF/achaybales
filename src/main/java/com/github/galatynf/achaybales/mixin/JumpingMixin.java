package com.github.galatynf.achaybales.mixin;

import com.github.galatynf.achaybales.Achaybales;
import net.minecraft.client.sound.Sound;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class JumpingMixin extends LivingEntity {
    protected JumpingMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow public abstract void playSound(SoundEvent sound, float volume, float pitch);

    @Inject(at = @At("INVOKE"), method = "tick")
    private void playEagleSound(CallbackInfo info) {
        if(getVelocity().y < 2.0F && !onGround) {
            System.out.println("playing sound");
            playSound(Achaybales.acjumpevent, 100.0F, 1.0F);
        }
    }
}
