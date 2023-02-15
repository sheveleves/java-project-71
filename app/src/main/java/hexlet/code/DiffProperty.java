package hexlet.code;

public final class DiffProperty {

    public enum Property {
        ADD,
        DELETE,
        CHANGED,
        UNCHANGED

    }
//    public static final String ADD = "ADD";
//    public static final String DELETE = "DELETE";
//    public static final String CHANGED = "CHANGED";
//    public static final String UNCHANGED = "UNCHANGED";


    private Property state;
    private Object oldValue;
    private Object currentValue;

    public DiffProperty(Property pState, Object pOldValue, Object pCurrentValue) {
        this.state = pState;
        this.oldValue = pOldValue;
        this.currentValue = pCurrentValue;
    }
    public DiffProperty(Property pState, Object pCurrentValue) {
        this.state = pState;
        this.currentValue = pCurrentValue;
    }

    public Property getState() {
        return state;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getCurrentValue() {
        return currentValue;
    }
}
