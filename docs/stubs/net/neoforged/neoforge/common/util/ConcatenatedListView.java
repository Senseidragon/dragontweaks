Compiled from "ConcatenatedListView.java"
public class net.neoforged.neoforge.common.util.ConcatenatedListView<T> implements java.util.List<T> {
  public static <T> net.neoforged.neoforge.common.util.ConcatenatedListView<T> of(java.util.List<T>...);
  public static <T> java.util.List<T> of(java.util.List<? extends java.util.List<? extends T>>);
  public int size();
  public boolean isEmpty();
  public boolean contains(java.lang.Object);
  public T get(int);
  public int indexOf(java.lang.Object);
  public int lastIndexOf(java.lang.Object);
  public java.util.Iterator<T> iterator();
  public java.util.Spliterator<T> spliterator();
  public java.lang.Object[] toArray();
  public <T1> T1[] toArray(T1[]);
  public boolean containsAll(java.util.Collection<?>);
  public boolean add(T);
  public void add(int, T);
  public T set(int, T);
  public boolean addAll(java.util.Collection<? extends T>);
  public boolean addAll(int, java.util.Collection<? extends T>);
  public boolean remove(java.lang.Object);
  public T remove(int);
  public boolean removeAll(java.util.Collection<?>);
  public boolean retainAll(java.util.Collection<?>);
  public void clear();
  public java.util.ListIterator<T> listIterator();
  public java.util.ListIterator<T> listIterator(int);
  public java.util.List<T> subList(int, int);
}
