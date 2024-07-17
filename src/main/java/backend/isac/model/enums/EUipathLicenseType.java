package backend.isac.model.enums;

public enum EUipathLicenseType {
    ATTENDED("Attended"),
    UNATTENDED("Unattended");

    private String value;

    EUipathLicenseType(String value) { this.value = value; }

    public String getValue() { return value; }

}
