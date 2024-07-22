package backend.isac.model.enums;

public enum EJiraIssueType {
    BUG("Bug"),
    TASK("Task"),
    SUB_TASK("Sub-task"),
    EPIC("Epic"),
    STORY("Story"),
    IMPROVEMENT("Improvement"),
    NEW_FEATURE("New Feature"),
    TECHNICAL_TASK("Technical Task"),
    SUPPORT("Support"),
    INCIDENT("Incident");

    private String value;

    EJiraIssueType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}