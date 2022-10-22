package tech.mekb.mekbhax.mixin;

import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

// allow setting onGround, x, y, and z on PlayerMoveC2SPacket
@Mixin(PlayerMoveC2SPacket.class)
public interface PlayerMovePacketMixin {
    @Mutable
    @Accessor("onGround")
    void setOnGround(boolean onGround);
    @Mutable
    @Accessor("onGround")
    boolean getOnGround();
    @Mutable
    @Accessor("x")
    void setX(double x);
    @Mutable
    @Accessor("y")
    void setY(double y);
    @Mutable
    @Accessor("z")
    void setZ(double z);
    @Mutable
    @Accessor("x")
    double getX();
    @Mutable
    @Accessor("y")
    double getY();
    @Mutable
    @Accessor("z")
    double getZ();
}
