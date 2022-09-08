package tech.mekb.mekbhax.mixin;

import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

// allow setting onGround on PlayerMoveC2SPacket
@Mixin(PlayerMoveC2SPacket.class)
public interface NoFallPacketMixin {
    @Mutable
    @Accessor("onGround")
    void setOnGround(boolean onGround);
}
