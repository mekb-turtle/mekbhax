package tech.mekb.mekbhax.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import tech.mekb.mekbhax.Main;

@Mixin(ClientConnection.class)
public class PacketMixin {
    @Mixin(PlayerMoveC2SPacket.class)
    public interface NoFallPacket {
        @Mutable
        @Accessor("onGround")
        void setOnGround(boolean onGround);
    }
    @Inject(at = @At("HEAD"), method = "sendInternal")
    public Packet<?> sendInternal(Packet<?> packet) {
        if (Main.noFallEnabled) {
            if (packet instanceof PlayerMoveC2SPacket.Full || packet instanceof PlayerMoveC2SPacket.OnGroundOnly || packet instanceof PlayerMoveC2SPacket.PositionAndOnGround) {
                ClientPlayerEntity player = MinecraftClient.getInstance().player;
                if (player != null && !player.isFallFlying() && !(player.getVelocity().y > -0.5)) {
                    ((NoFallPacket) packet).setOnGround(true);
                }
            }
        }
        return packet;
    }
}
