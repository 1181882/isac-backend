package backend.isac.model.enums;

public enum EJiraStatus {

    OPEN("Open"),
    IN_PROGRESS("In Progress"),
    REOPENED("Reopened"),
    RESOLVED("Resolved"),
    CLOSED("Closed"),
    CANCELED("Canceled");

    private String value;

    EJiraStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
