Compiled from "CrashReportExtender.java"
public class net.neoforged.neoforge.logging.CrashReportExtender {
  public net.neoforged.neoforge.logging.CrashReportExtender();
  public static void extendSystemReport(net.minecraft.SystemReport);
  public static void addCrashReportHeader(java.lang.StringBuilder, net.minecraft.CrashReport);
  public static java.lang.String generateEnhancedStackTrace(java.lang.Throwable);
  public static java.lang.String generateEnhancedStackTrace(java.lang.StackTraceElement[]);
  public static java.lang.String generateEnhancedStackTrace(java.lang.Throwable, boolean);
  public static java.io.File dumpModLoadingCrashReport(org.apache.logging.log4j.Logger, java.util.List<net.neoforged.fml.ModLoadingIssue>, java.io.File);
}
