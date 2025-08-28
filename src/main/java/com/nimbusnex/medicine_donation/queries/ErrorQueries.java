package com.nimbusnex.medicine_donation.queries;

public class ErrorQueries {

    private ErrorQueries() {
    }

    public static final String INSERT_UNAUTHENTICATE_RECORD = """
                INSERT INTO un_authenticate_records
                (status, code, message, timestamp, error_message, path, user_id, entered_password)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;

    public static final String INSERT_UNAUTHORIZE_RECORD = """
                INSERT INTO unauthorized_access_records
                (status, code, message, timestamp, error_message, path, user_id)
                VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

}
