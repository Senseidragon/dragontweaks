Compiled from "TablePrinter.java"
public class net.neoforged.neoforge.common.util.TablePrinter<T> {
  public net.neoforged.neoforge.common.util.TablePrinter();
  public net.neoforged.neoforge.common.util.TablePrinter<T> header(java.lang.String, java.util.function.Function<T, java.lang.String>);
  public net.neoforged.neoforge.common.util.TablePrinter<T> header(java.lang.String, java.util.function.Function<T, java.lang.String>, boolean);
  public void clearRows();
  public net.neoforged.neoforge.common.util.TablePrinter<T> add(T);
  public net.neoforged.neoforge.common.util.TablePrinter<T> add(T, T...);
  public net.neoforged.neoforge.common.util.TablePrinter<T> add(java.util.Collection<? extends T>);
  public java.lang.String toString();
  public void build(java.lang.StringBuilder);
}
