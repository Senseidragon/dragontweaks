Compiled from "DynamicChannelHandler.java"
public interface net.neoforged.neoforge.network.filters.DynamicChannelHandler extends io.netty.channel.ChannelHandler {
  public abstract boolean isNecessary(net.minecraft.network.Connection);
}
