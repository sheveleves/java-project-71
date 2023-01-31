package hexlet.code;

public final class Property {
    public static final String ADD = "ADD";
    public static final String DELETE = "DELETE";
    public static final String CHANGED = "CHANGED";
    public static final String UNCHANGED = "UNCHANGED";


    private String pState;
    private Object pOldValue;
    private Object pCurrentValue;

    public Property(String state, Object oldValue, Object currentValue) {
        this.pState = state;
        this.pOldValue = oldValue;
        this.pCurrentValue = currentValue;
    }
    public Property(String state, Object currentValue) {
        this.pState = state;
        this.pCurrentValue = currentValue;
    }

    public String getState() {
        return pState;
    }

    public Object getOldValue() {
        return pOldValue;
    }

    public Object getCurrentValue() {
        return pCurrentValue;
    }
}
