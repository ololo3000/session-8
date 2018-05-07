package metricProblem;

import java.util.concurrent.atomic.AtomicLong;

public class Metric {
    private final String type;
    private final AtomicLong eventCnt = new AtomicLong(0);
    private final AtomicLong eventValsSum = new AtomicLong(0);

    public boolean addEvent(Event event) {
        if (!event.getType().equals(type)) {
            return false;
        }

        eventCnt.getAndIncrement();
        eventValsSum.getAndAdd(event.getValue());
        return true;
    }

    public Metric(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public long getSum() {
        return this.eventValsSum.get();
    }

    public long getEventCount() {
        return this.eventCnt.get();
    }

    public float getAverage() {
        return (float) eventValsSum.get() / eventCnt.get();
    }
}
