import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentStack <T> {
    AtomicReference<Node<T>> top = new AtomicReference<Node<T>>();

    public void push(T item) {
        Node<T> newHead = new Node<T>(item);
        Node<T> oldHead;
        do {
            oldHead = top.get();
            newHead.next = oldHead;
        } while (!top.compareAndSet(oldHead, newHead));
    }

    public T pop() {
        Node<T> oldHead;
        Node<T> newHead;
        do {
            oldHead = top.get();
            if (oldHead == null)
                return null;
            newHead = oldHead.next;
        } while (!top.compareAndSet(oldHead, newHead));
        return oldHead.item;
    }

    private static class Node <T> {
        public final T item;
        public Node<T> next;
        public Node(T item) {
            this.item = item;
        }
    }

}
