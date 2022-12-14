package tech.mekb.mekbhax.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.GameOptions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
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
	public TickMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Inject(at = @At("HEAD"), method = "tick")
	private void tick(CallbackInfo info) {
		try {
			MinecraftClient mc = MinecraftClient.getInstance();
			if (flyBind.wasPressed()) {
				flyEnabled = !flyEnabled;
				if (flyEnabled) noFallEnabled = true;
			}
			if (speedBind     .wasPressed()) speedEnabled      = !speedEnabled;
			if (noFallBind    .wasPressed()) noFallEnabled     = !noFallEnabled;
			if (xrayBind      .wasPressed()) xrayEnabled       = !xrayEnabled;
			if (freeCamBind   .wasPressed()) freeCamEnabled    = !freeCamEnabled;
			if (antiEffectBind.wasPressed()) antiEffectEnabled = !antiEffectEnabled;
			if (noSlowBind    .wasPressed()) noSlowEnabled     = !noSlowEnabled;
			if (antiHumanBind .wasPressed()) antiHumanEnabled  = !antiHumanEnabled;
			if (antiEffectEnabled && mc.player != null) {
				mc.player.removeStatusEffect(StatusEffects.BLINDNESS);
				mc.player.removeStatusEffect(StatusEffects.DARKNESS);
				mc.player.removeStatusEffect(StatusEffects.NAUSEA);
			}
			if (xrayBind.wasPressed()) {
				xrayEnabled = !xrayEnabled;
				if (xrayEnabled) fullBrightEnabled = true;
				mc.chunkCullingEnabled = !xrayEnabled;
				mc.worldRenderer.reload();
			}
			if (fastBind.wasPressed()) {
				if (faster && fast) {
					fast = false;
					faster = false;
				} else if (faster) {
					fast = true;
				} else if (fast) {
					faster = true;
					fast = false;
				} else {
					fast = true;
				}
				slow = false;
				slower = false;
			}
			if (slowBind.wasPressed()) {
				if (slower) {
					slow = false;
					slower = false;
				} else if (slow) {
					slower = true;
					slow = false;
				} else {
					slow = true;
				}
				fast = false;
				faster = false;
			}
			if (fullBrightBind.wasPressed()) {
				if (fullBrightEnabled) {
					// set back to original value
					mc.options.getGamma().setValue(originalGamma);
					fullBrightEnabled = false;
				} else {
					originalGamma = mc.options.getGamma().getValue();
					mc.options.getGamma().setValue(fullBrightGamma);
					fullBrightEnabled = true;
				}
			}
			if (fullBrightEnabled && mc.options.getGamma().getValue() < fullBrightGamma) {
				// just in case user sets it manually back
				fullBrightEnabled = false;
			}
			if (stepBind.wasPressed()) {
				if (stepEnabled) {
					// set back to original value
					this.stepHeight = originalStep;
					stepEnabled = false;
				} else {
					originalStep = this.stepHeight;
					stepEnabled = true;
				}
			}
			if (stepEnabled) {
				this.stepHeight = 1.0f;
			}
			if (speedEnabled || flyEnabled/* || freeCamEnabled*/) {
				GameOptions go = mc.options;
				++tick;
				tick %= 40;
				Entity entityToMove = this;
				Entity faceEntity = this;
				boolean boat = false;
				if (this.hasVehicle()) {
					entityToMove = this.getVehicle();
					if (entityToMove == null) entityToMove = this;
					else if (entityToMove.getType() == EntityType.BOAT || entityToMove.getType() == EntityType.CHEST_BOAT) {
						// don't move sideways in boat since it turns normally
						boat = true;
						faceEntity = entityToMove;
					}
				}
				boolean kw = go.forwardKey.isPressed();
				boolean ka = !boat && go.leftKey.isPressed();
				boolean ks = go.backKey.isPressed();
				boolean kd = !boat && go.rightKey.isPressed();
				if (kw && ks) kw = ks = false;
				if (ka && kd) ka = kd = false;
				double x = 0;
				double z = 0;
				double sprintMul_ = getSprintMul();
				if (kw || ka || ks || kd) {
					double t = (faceEntity.getYaw() / 180.0 * Math.PI) + Math.atan2(kw?1:ks?-1:0, ka?1:kd?-1:0);
					x = Math.cos(t) * speedH * sprintMul_;
					z = Math.sin(t) * speedH * sprintMul_;
				}
				double y = entityToMove.getVelocity().y;
				if (flyEnabled || freeCamEnabled) {
					y = 0;
					// extra keybinds for ascend and descend since sneak will dismount if we're in a boat
					if (go.jumpKey.isPressed() || flyAscendBind.isPressed()) y += speedV * sprintMul_;
					if (go.sneakKey.isPressed() || flyDescendBind.isPressed()) y -= speedV * sprintMul_;
					if (tick >= 0 && tick < 4) y -= speedB + (y > 0 ? y : 0);
					if (tick >= 4 && tick < 8) y += speedB + (y > 0 ? y : 0);
				}
				entityToMove.setVelocity(new Vec3d(x, y, z));
			}
		} catch (Exception e) {
			Logger.error(e.getMessage());
		}
	}
}
