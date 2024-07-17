package backend.isac.model.enums;

public enum EAsset {
    RPA("RPA"),
    OCR("OCR"),
    AI("Artificial Intelligence"),
    PROCESS_MINING("Process Mining"),
    API("API");

    private String value;

    EAsset(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
