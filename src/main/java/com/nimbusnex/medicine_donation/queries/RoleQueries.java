package com.nimbusnex.medicine_donation.queries;

public class RoleQueries {

    public static final String GET_ALL_ROLLS = """
            SELECT * FROM ROLES
            """;

    public static final String GET_ROLE_BY_NAME = """
            SELECT * FROM ROLES WHERE NAME = ?
            """;

    public static final String GET_ROLE_BY_ID = """
            SELECT * FROM ROLES WHERE ID = ?
            """;

    public static final String ADD_ROLE = """
           INSERT INTO roles (name, description, created_by, updated_by) VALUES (?, ?, ?, ?)
           """;

    public static final String UPDATE_ROLE_DETAILS = """
           UPDATE roles SET name = ?, description = ?, updated_at = CURRENT_TIMESTAMP, updated_by = ? WHERE id = ?
           """;
}
