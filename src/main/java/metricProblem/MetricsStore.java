package metricProblem;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MetricsStore {
    private static final Map<String, Metric> instance = new ConcurrentHashMap<>();

    public static Map<String, Metric> getInstance() {
        return MetricsStore.instance;
    }
}
