package backend.isac.model.enums;

public enum ELifecycle {
    ASSESSMENT("Assessment"),
    DELIVERY("Delivery"),
    DESIGN("Design"),
    TEST("Test"),
    PRODUCTION("Production"),
    BABYSITTING("Babysitting"),
    MAINTENANCE("Maintenance");

    private String value;

    ELifecycle(String value) {
        this.value = value;
    }

    public String getValue() { return value; }
}
