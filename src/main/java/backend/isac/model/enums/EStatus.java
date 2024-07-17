package backend.isac.model.enums;

public enum EStatus {
    ACTIVE("Active"),
    INACTIVE("Inactive");

    private String value;

    EStatus(String value) { this.value = value; }

    public String getValue() { return value; }
}
