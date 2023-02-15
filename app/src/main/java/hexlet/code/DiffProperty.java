package hexlet.code;

public final class DiffProperty {

    public enum Property {
        ADD,
        DELETE,
        CHANGED,
        UNCHANGED

    }

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
