Compiled from "ScreenshotEvent.java"
public class net.neoforged.neoforge.client.event.ScreenshotEvent extends net.neoforged.bus.api.Event implements net.neoforged.bus.api.ICancellableEvent {
  public static final net.minecraft.network.chat.Component DEFAULT_CANCEL_REASON;
  public net.neoforged.neoforge.client.event.ScreenshotEvent(com.mojang.blaze3d.platform.NativeImage, java.io.File);
  public com.mojang.blaze3d.platform.NativeImage getImage();
  public java.io.File getScreenshotFile();
  public void setScreenshotFile(java.io.File);
  public net.minecraft.network.chat.Component getResultMessage();
  public void setResultMessage(net.minecraft.network.chat.Component);
  public net.minecraft.network.chat.Component getCancelMessage();
}
