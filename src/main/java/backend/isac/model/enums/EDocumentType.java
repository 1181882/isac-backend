package backend.isac.model.enums;

public enum EDocumentType {

    // Requirements Documentation
    BRD("Business Requirements Document"),
    FRD("Functional Requirements Document"),

    // Process Documentation
    PM("Process Mapping"),
    FC("Flowcharts"),
    BPMN("Business Process Model and Notation"),

    // Technical Documentation
    SDS("Solution Design Specification"),
    TDD("Technical Design Document"),
    IM("Installation and Configuration Manual"),
    ID("Integration Documentation"),

    // Development Documentation
    SC("Source Code with Comments"),
    DG("Development Guide"),

    // Testing Documentation
    TP("Test Plan"),
    TC("Test Cases"),
    TR("Test Reports"),

    // Operations Documentation
    UM("User Manual"),
    OM("Operations Manual"),
    TG("Troubleshooting Guide"),
    SLA("Service Level Agreement"),

    // Maintenance Documentation
    MP("Maintenance Plan"),
    MR("Maintenance Record"),

    // Governance Documentation
    RPP("RPA Policies and Procedures"),
    RCM("Risk and Control Matrix"),
    AR("Audit Reports");

    private final String fullName;

    EDocumentType(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
}
