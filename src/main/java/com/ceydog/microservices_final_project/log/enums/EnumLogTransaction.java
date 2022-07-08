package com.ceydog.microservices_final_project.log.enums;

public enum EnumLogTransaction {
    NOT_FOUND_IN_DATABASE("Not found in database. Obtained via externall API call, and added to database."),
    OBTAINED_FROM_DATABASE("Obtained from database."),
    DELETED_FROM_DATABASE("Record cleared."),
    DELETION_FAILED("Deletion failed.")
    ;

    private String message;

    EnumLogTransaction(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
