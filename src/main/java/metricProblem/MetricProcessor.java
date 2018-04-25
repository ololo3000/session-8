package metricProblem;

import java.util.Map;
import java.util.Queue;

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

                metricsStore.putIfAbsent(event.getType(),
                        new Metric(event.getValue(), 0, event.getType()));

                Metric oldMetric = metricsStore.get(event.getType());
                Metric newMetric = new Metric(oldMetric.getSum() + event.getValue(),
                        oldMetric.getCount() + 1,
                        oldMetric.getType());

                metricsStore.put(oldMetric.getType(), newMetric);

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

