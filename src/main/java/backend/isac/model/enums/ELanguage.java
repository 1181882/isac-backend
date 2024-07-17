package backend.isac.model.enums;

public enum ELanguage {
    EN("English"),
    FR("French"),
    PT("Portuguese");

    private String value;

    ELanguage(String value) {
        this.value = value;
    }

    public String getValue() { return value; }
}
