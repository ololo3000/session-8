package metricProblem;

public class MetricProcessor implements Runnable {

    @Override
    public void run() {
        while (true) {
            int size = EventQueue.getInstance().size();

            for (int i = 0; i < size; i++) {
                Event event = EventQueue.getInstance().poll();

                if (event == null) {
                    break;
                }

                if (MetricsStore.getInstance().containsKey(event.getType())) {
                    Metric metric = MetricsStore.getInstance().get(event.getType());
                    MetricsStore.getInstance().put(metric.getType(),
                            new Metric(metric.getSum() + event.getValue(),
                                    metric.getCount() + 1,
                                    metric.getType()));
                } else {
                    MetricsStore.getInstance().put(event.getType(),
                            new Metric(event.getValue(),
                                    1,
                                    event.getType()));
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
    }
}
