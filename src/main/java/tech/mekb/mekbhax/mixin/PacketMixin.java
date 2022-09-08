package tech.mekb.mekbhax.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import tech.mekb.mekbhax.Main;

@Mixin(ClientConnection.class)
public class PacketMixin {
    @ModifyVariable(at = @At("HEAD"), method = "sendInternal", ordinal = 0, argsOnly = true)
    public Packet<?> sendInternal(Packet<?> packet) {
        // no fall
        if (Main.noFallEnabled) {
            if (packet instanceof PlayerMoveC2SPacket.Full || packet instanceof PlayerMoveC2SPacket.OnGroundOnly || packet instanceof PlayerMoveC2SPacket.PositionAndOnGround) {
                ClientPlayerEntity player = MinecraftClient.getInstance().player;
                if (player != null && !player.isFallFlying() && player.getVelocity().y < -0.5) {
                    ((NoFallPacketMixin) packet).setOnGround(true);
                }
            }
        }
        return packet;
    }
}
