package metricProblem;

public class Event {
    private final String type;
    private final long value;
    private final long timeStamp;

    public Event(String type, long value, long timeStamp) {
        this.type = type;
        this.value = value;
        this.timeStamp = timeStamp;
    }

    public String getType() {
        return this.type;
    }

    public long getValue() {
        return this.value;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }
}

