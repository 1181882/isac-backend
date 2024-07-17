package backend.isac.model.enums;

public enum EEntity {
    NPS("Natixis Payments"),
    DO("DO"),
    ASS("Assurance"),
    OTHERS("Others");

    private String value;

    EEntity(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
