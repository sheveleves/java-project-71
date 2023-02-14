package hexlet.code;

public final class DiffProperty {
    public static final String ADD = "ADD";
    public static final String DELETE = "DELETE";
    public static final String CHANGED = "CHANGED";
    public static final String UNCHANGED = "UNCHANGED";


    private String state;
    private Object oldValue;
    private Object currentValue;

    public DiffProperty(String pState, Object pOldValue, Object pCurrentValue) {
        this.state = pState;
        this.oldValue = pOldValue;
        this.currentValue = pCurrentValue;
    }
    public DiffProperty(String pState, Object pCurrentValue) {
        this.state = pState;
        this.currentValue = pCurrentValue;
    }

    public String getState() {
        return state;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getCurrentValue() {
        return currentValue;
    }
}
