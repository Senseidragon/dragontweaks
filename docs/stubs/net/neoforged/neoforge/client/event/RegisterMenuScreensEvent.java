Compiled from "RegisterMenuScreensEvent.java"
public class net.neoforged.neoforge.client.event.RegisterMenuScreensEvent extends net.neoforged.bus.api.Event implements net.neoforged.fml.event.IModBusEvent {
  public net.neoforged.neoforge.client.event.RegisterMenuScreensEvent(java.util.Map<net.minecraft.world.inventory.MenuType<?>, net.minecraft.client.gui.screens.MenuScreens$ScreenConstructor<?, ?>>);
  public <M extends net.minecraft.world.inventory.AbstractContainerMenu, U extends net.minecraft.client.gui.screens.Screen & net.minecraft.client.gui.screens.inventory.MenuAccess<M>> void register(net.minecraft.world.inventory.MenuType<? extends M>, net.minecraft.client.gui.screens.MenuScreens$ScreenConstructor<M, U>);
}
