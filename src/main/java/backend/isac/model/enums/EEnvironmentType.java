package backend.isac.model.enums;

public enum EEnvironmentType {
    QUA("Qualification"),
    DEV("Development"),
    PROD("Production");

    private String value;

    EEnvironmentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
