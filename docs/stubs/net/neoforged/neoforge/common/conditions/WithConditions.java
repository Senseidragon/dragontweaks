Compiled from "WithConditions.java"
public final class net.neoforged.neoforge.common.conditions.WithConditions<A> extends java.lang.Record {
  public net.neoforged.neoforge.common.conditions.WithConditions(A, net.neoforged.neoforge.common.conditions.ICondition...);
  public net.neoforged.neoforge.common.conditions.WithConditions(A);
  public net.neoforged.neoforge.common.conditions.WithConditions(java.util.List<net.neoforged.neoforge.common.conditions.ICondition>, A);
  public static <A> net.neoforged.neoforge.common.conditions.WithConditions$Builder<A> builder(A);
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public java.util.List<net.neoforged.neoforge.common.conditions.ICondition> conditions();
  public A carrier();
}
