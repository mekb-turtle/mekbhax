package tech.mekb.mekbhax.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static tech.mekb.mekbhax.Main.noSlowEnabled;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
    @Inject(method = "shouldSlowDown", at = @At("HEAD"), cancellable = true)
    private void slowDown(CallbackInfoReturnable<Boolean> cir) {
        if (noSlowEnabled) {
            cir.setReturnValue(true);
        }
    }
}
