package metricProblem;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class EventQueue {
    private static final BlockingQueue<Event> instance = new LinkedBlockingQueue();

    public static BlockingQueue<Event> getInstance() {
        return instance;
    }
}
