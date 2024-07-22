package backend.isac.model.enums;

public enum EApplicationType {
    LOCAL("Local"),
    WEB("Web");

    private String value;

    EApplicationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
