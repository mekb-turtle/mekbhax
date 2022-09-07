package tech.mekb.mekbhax.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.GameOptions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tech.mekb.mekbhax.Main;

import static tech.mekb.mekbhax.Main.*;

@Mixin(ClientPlayerEntity.class)
public abstract class TickMixin extends Entity {
	private static final double speedH = 1.0;
	private static final double speedV = 0.5;
	private static final double speedB = 0.1;

	public TickMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Inject(at = @At("HEAD"), method = "tick")
	private void tick(CallbackInfo info) {
		try {
			MinecraftClient mc = MinecraftClient.getInstance();
			if (flyBind.wasPressed())        flyEnabled        = !flyEnabled;
			if (speedBind.wasPressed())      speedEnabled      = !speedEnabled;
			if (noFallBind.wasPressed())     noFallEnabled     = !noFallEnabled;
			if (xrayBind.wasPressed())       xrayEnabled       = !xrayEnabled;
			if (fullBrightBind.wasPressed()) {
				if (fullBrightEnabled) {
					mc.options.getGamma().setValue(originalGamma);
					fullBrightEnabled = false;
				} else {
					originalGamma = mc.options.getGamma().getValue();
					mc.options.getGamma().setValue(fullBrightGamma);
					fullBrightEnabled = true;
				}
			}
			if (stepBind.wasPressed()) {
				if (stepEnabled) {
					this.stepHeight = originalStep;
					stepEnabled = false;
				} else {
					originalStep = this.stepHeight;
					this.stepHeight = 1.0f;
					stepEnabled = true;
				}
			}
			if (Main.speedEnabled || Main.flyEnabled) {
				GameOptions go = mc.options;
				++tick;
				tick %= 40;
				Entity e = this;
				if (e.hasVehicle()) {
					e = e.getVehicle();
				}
				assert e != null;
				boolean kw = go.forwardKey.isPressed();
				boolean ka = go.leftKey.isPressed();
				boolean ks = go.backKey.isPressed();
				boolean kd = go.rightKey.isPressed();
				if (kw && ks) kw = ks = false;
				if (ka && kd) ka = kd = false;
				double x = 0;
				double z = 0;
				if (kw || ka || ks || kd) {
					Vec3d vp = this.getRotationVector();
					double t = Math.atan2(vp.z, vp.x) + Math.atan2(kd?1:ka?-1:0, kw?1:ks?-1:0);
					x = Math.cos(t) * speedH;
					z = Math.sin(t) * speedH;
				}
				double y = e.getVelocity().y;
				if (Main.flyEnabled) {
					y = 0;
					if (go.jumpKey.isPressed()) y += speedV;
					if (go.sprintKey.isPressed()) y -= speedV;
					if (tick >= 0 && tick < 4) y -= speedB;
					if (tick >= 4 && tick < 8) y += speedB;
				}
				e.setVelocity(new Vec3d(x, y, z));
			}
		} catch (Exception e) {
			Logger.error(e.getMessage());
		}
	}
}
