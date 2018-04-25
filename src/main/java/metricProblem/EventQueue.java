package metricProblem;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class EventQueue {
    private static final Queue<Event> instance = new ConcurrentLinkedQueue<>();

    public static Queue<Event> getInstance() {
        return instance;
    }
}
