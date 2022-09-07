package tech.mekb.mekbhax.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static tech.mekb.mekbhax.Main.*;

@Mixin(InGameHud.class)
public class HudRenderMixin {
    @Inject(method = "render", at = @At("RETURN"), cancellable = true)
    public void onRender(MatrixStack matrices, float tickDelta, CallbackInfo info) {
        MinecraftClient.getInstance().textRenderer.drawWithShadow(matrices,
                (flyEnabled ? "§aFly\n" : "") +
                        (speedEnabled ? "§aSpeed\n" : "") +
                        (noFallEnabled ? "§aNo Fall\n" : "") +
                        (xrayEnabled ? "§aX-Ray\n" : "") +
                        (fullBrightEnabled ? "§aFull-Bright\n" : ""),
                5, 5, -1);
    }
}
