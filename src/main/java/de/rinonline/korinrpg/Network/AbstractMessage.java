package de.rinonline.korinrpg.Network;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;

import com.google.common.base.Throwables;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import de.rinonline.korinrpg.RINMAIN;
import io.netty.buffer.ByteBuf;

public abstract class AbstractMessage<T extends AbstractMessage<T>> implements IMessage, IMessageHandler<T, IMessage> {

    /**
     * Some PacketBuffer methods throw IOException - default handling propagates the exception.
     * if an IOException is expected but should not be fatal, handle it within this method.
     */
    protected abstract void read(PacketBuffer buffer) throws IOException;

    /**
     * Some PacketBuffer methods throw IOException - default handling propagates the exception.
     * if an IOException is expected but should not be fatal, handle it within this method.
     */
    protected abstract void write(PacketBuffer buffer) throws IOException;

    /**
     * Called on whichever side the message is received;
     * for bidirectional packets, be sure to check side
     * If {@link #requiresMainThread()} returns true, this method is guaranteed
     * to be called on the main Minecraft thread for this side.
     */
    public abstract void process(EntityPlayer player, Side side);

    /**
     * If message is sent to the wrong side, an exception will be thrown during handling
     *
     * @return True if the message is allowed to be handled on the given side
     */
    protected boolean isValidOnSide(Side side) {
        return true; // default allows handling on both sides, i.e. a bidirectional packet
    }

    /**
     * Whether this message requires the main thread to be processed (i.e. it
     * requires that the world, player, and other objects are in a valid state).
     */
    protected boolean requiresMainThread() {
        return true;
    }

    @Override
    public void fromBytes(ByteBuf buffer) {
        try {
            read(new PacketBuffer(buffer));
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    @Override
    public void toBytes(ByteBuf buffer) {
        try {
            write(new PacketBuffer(buffer));
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    // =====================================================================//
    /*
     * Make the implementation final so child classes don't need to bother
     * with it, since the message class shouldn't have anything to do with
     * the handler. This is simply to avoid having to have:
     * public static class Handler extends GenericMessageHandler<OpenGuiMessage> {}
     * in every single message class for the sole purpose of registration.
     */
    @Override
    public final IMessage onMessage(T msg, MessageContext ctx) {
        if (ctx.side.name()
            .equals("CLIENT")) {
            msg.process(RINMAIN.proxy.getPlayerEntity(ctx), ctx.side);
        }
        {
            msg.process(RINMAIN.proxy.getPlayerEntity(ctx), ctx.side);
        }
        return null;
    }

    private void checkThreadAndEnqueue(T msg, MessageContext ctx) {
        // TODO Auto-generated method stub

    }

    /**
     * Messages that can only be sent from the server to the client should use this class
     */
    public static abstract class AbstractClientMessage<T extends AbstractMessage<T>> extends AbstractMessage<T> {

        @Override
        protected final boolean isValidOnSide(Side side) {
            return side.isClient();
        }
    }

    /**
     * Messages that can only be sent from the client to the server should use this class
     */
    public static abstract class AbstractServerMessage<T extends AbstractMessage<T>> extends AbstractMessage<T> {

        @Override
        protected final boolean isValidOnSide(Side side) {
            return side.isServer();
        }
    }
}
