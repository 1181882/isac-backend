package backend.isac.model.enums;

public enum ECompletionLevel {
    INCOMPLETE("Incomplete"),
    PARTIALLY("Partially Complete"),
    COMPLETE("Complete ");

    private String value;

    ECompletionLevel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
