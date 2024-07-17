package backend.isac.model.enums;

public enum ESupportTeam {
    DEVELOPMENT("Development"),
    SUPPORT_TEAM("Support Team");

    private String value;

    ESupportTeam(String value) { this.value = value; }

    public String getValue() { return value; }
}
