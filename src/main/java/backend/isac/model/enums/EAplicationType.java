package backend.isac.model.enums;

public enum EAplicationType {
    LOCAL("Local"),
    WEB("Web");

    private String value;

    EAplicationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
