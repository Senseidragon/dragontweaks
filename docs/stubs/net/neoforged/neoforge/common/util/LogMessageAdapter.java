Compiled from "LogMessageAdapter.java"
public final class net.neoforged.neoforge.common.util.LogMessageAdapter extends java.lang.Record implements org.apache.logging.log4j.message.Message,org.apache.logging.log4j.util.StringBuilderFormattable {
  public net.neoforged.neoforge.common.util.LogMessageAdapter(java.util.function.Consumer<java.lang.StringBuilder>);
  public java.lang.String getFormattedMessage();
  public java.lang.String getFormat();
  public java.lang.Object[] getParameters();
  public java.lang.Throwable getThrowable();
  public void formatTo(java.lang.StringBuilder);
  public static org.apache.logging.log4j.message.Message adapt(java.util.function.Consumer<java.lang.StringBuilder>);
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public java.util.function.Consumer<java.lang.StringBuilder> builder();
}
