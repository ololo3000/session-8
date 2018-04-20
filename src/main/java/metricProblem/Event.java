package metricProblem;

public class Event {
    private final String type;
    private final long value;

    public Event(String type, long value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return this.type;
    }

    public long getValue() {
        return this.value;
    }
}

