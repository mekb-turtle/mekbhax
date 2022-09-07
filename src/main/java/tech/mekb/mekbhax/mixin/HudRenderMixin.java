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
        if (flyEnabled)
            MinecraftClient.getInstance().textRenderer.drawWithShadow(matrices, "§aFly",         5, 16*0+5, -1);
        if (speedEnabled)
            MinecraftClient.getInstance().textRenderer.drawWithShadow(matrices, "§aSpeed",       5, 16*1+5, -1);
        if (noFallEnabled)
            MinecraftClient.getInstance().textRenderer.drawWithShadow(matrices, "§aNo Fall",     5, 16*2+5, -1);
        if (stepEnabled)
            MinecraftClient.getInstance().textRenderer.drawWithShadow(matrices, "§aStep",        5, 16*2+5, -1);
        if (xrayEnabled)
            MinecraftClient.getInstance().textRenderer.drawWithShadow(matrices, "§aX-Ray",       5, 16*3+5, -1);
        if (fullBrightEnabled)
            MinecraftClient.getInstance().textRenderer.drawWithShadow(matrices, "§aFull-Bright", 5, 16*4+5, -1);
    }
}
