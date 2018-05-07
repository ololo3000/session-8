package metricProblem;

import java.util.Calendar;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentMap;

public class MetricProcessor implements Runnable {
    private static final long TIME_TO_PROCESS = 1000;
    private Map<String, Metric> metricsStore;
    private Queue<Event> eventQueue;

    public MetricProcessor(Queue<Event> eventQueue, Map<String, Metric> metricsStore) {
        this.metricsStore = metricsStore;
        this.eventQueue = eventQueue;
    }

    @Override
    public void run() {
        while (true) {
            long started = System.currentTimeMillis();

            while (true) {
                Event event = eventQueue.poll();

                if (event == null) {
                    break;
                }

                if (event.getTimeStamp() > started) {
                    break;
                }

                if (!metricsStore.containsKey(event.getType())) {
                    metricsStore.putIfAbsent(event.getType(),
                            new Metric(event.getType()));
                }

                metricsStore.get(event.getType()).addEvent(event);
            }

            long elapsed = System.currentTimeMillis() - started;

            if (elapsed < TIME_TO_PROCESS) {
                try {
                    Thread.sleep(TIME_TO_PROCESS - elapsed);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

