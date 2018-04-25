package metricProblem;

public class Metric {
    private final long sum;
    private final long count;
    private final String type;

    public Metric(long sum, long count, String type) {
        this.type = type;
        this.count = count;
        this.sum = sum;
    }

    public String getType() {
        return this.type;
    }

    public long getSum() {
        return this.sum;
    }

    public long getCount() {
        return this.count;
    }

    public float getAverage() {
        return (float) sum / count;
    }
}
