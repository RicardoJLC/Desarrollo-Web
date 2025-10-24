public interface iStack<T> {
    void push(T item);
    T pop();
    T peek();
    void clear();
    int size();
    boolean isEmpty();
    void print();
}
