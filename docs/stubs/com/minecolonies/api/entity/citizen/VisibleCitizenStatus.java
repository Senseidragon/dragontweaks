Compiled from "VisibleCitizenStatus.java"
public class com.minecolonies.api.entity.citizen.VisibleCitizenStatus {
  public static final com.minecolonies.api.entity.citizen.VisibleCitizenStatus EAT;
  public static final com.minecolonies.api.entity.citizen.VisibleCitizenStatus HOUSE;
  public static final com.minecolonies.api.entity.citizen.VisibleCitizenStatus RAIDED;
  public static final com.minecolonies.api.entity.citizen.VisibleCitizenStatus MOURNING;
  public static final com.minecolonies.api.entity.citizen.VisibleCitizenStatus BAD_WEATHER;
  public static final com.minecolonies.api.entity.citizen.VisibleCitizenStatus SLEEP;
  public static final com.minecolonies.api.entity.citizen.VisibleCitizenStatus SICK;
  public static final com.minecolonies.api.entity.citizen.VisibleCitizenStatus WORKING;
  public com.minecolonies.api.entity.citizen.VisibleCitizenStatus(net.minecraft.resources.ResourceLocation, java.lang.String);
  public com.minecolonies.api.entity.citizen.VisibleCitizenStatus(net.minecraft.resources.ResourceLocation, java.lang.String, boolean);
  public net.minecraft.resources.ResourceLocation getIcon();
  public java.lang.String getTranslationKey();
  public int getId();
  public static com.minecolonies.api.entity.citizen.VisibleCitizenStatus getForId(int);
  public boolean shouldRender();
  public static java.util.Map<java.lang.Integer, com.minecolonies.api.entity.citizen.VisibleCitizenStatus> getVisibleStatus();
}
