package hexlet.code;

public final class DiffProperty {

    public enum Property {
        ADD,
        DELETE,
        CHANGED,
        UNCHANGED

    }

    private String field;
    private Property state;
    private Object oldValue;
    private Object currentValue;

    public DiffProperty(String pField, Property pState, Object pOldValue, Object pCurrentValue) {
        this.field = pField;
        this.state = pState;
        this.oldValue = pOldValue;
        this.currentValue = pCurrentValue;
    }
    public DiffProperty(String pField, Property pState, Object pCurrentValue) {
        this.field = pField;
        this.state = pState;
        this.currentValue = pCurrentValue;
    }

    public String getField() {
        return field;
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
