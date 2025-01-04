package com.example.config;

import org.hibernate.boot.model.TypeContributions;
import org.hibernate.dialect.DatabaseVersion;
import org.hibernate.dialect.Dialect;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.SqlTypes;
import org.hibernate.type.spi.TypeConfiguration;

public class SQLiteDialect extends Dialect {

    public SQLiteDialect() {
        super(DatabaseVersion.make(3)); // SQLite version 3
    }

    @Override
    public void contributeTypes(TypeContributions typeContributions, ServiceRegistry serviceRegistry) {
        var typeConfiguration = typeContributions.getTypeConfiguration();

        // Register common types for SQLite, ensuring non-null BasicType
        registerIfNotNull(typeConfiguration, Integer.class, "integer");
        registerIfNotNull(typeConfiguration, String.class, "text");
        registerIfNotNull(typeConfiguration, Byte[].class, "blob");
        registerIfNotNull(typeConfiguration, Double.class, "real");
        registerIfNotNull(typeConfiguration, Boolean.class, "boolean");

        // Map BIGINT to INTEGER for SQLite
        registerIfNotNull(typeConfiguration, Long.class, "integer");  // LONG is mapped as INTEGER in SQLite
    }

    private <T> void registerIfNotNull(TypeConfiguration typeConfiguration, Class<T> javaType, String sqlType) {
        var basicType = typeConfiguration.getBasicTypeForJavaType(javaType);
        if (basicType != null) {
            typeConfiguration.getBasicTypeRegistry().register(basicType, sqlType);
        } else {
            System.err.println("Warning: No BasicType found for " + javaType.getName());
        }
    }

    @Override
    public boolean dropConstraints() {
        return false; // SQLite does not support dropping constraints
    }

    @Override
    public String getDropForeignKeyString() {
        return ""; // SQLite does not support dropping foreign keys
    }

    @Override
    public String getAddForeignKeyConstraintString(String constraintName, String[] foreignKey, String referencedTable, String[] primaryKey, boolean referencesPrimaryKey) {
        return ""; // SQLite does not support adding foreign key constraints
    }

    @Override
    public String getAddPrimaryKeyConstraintString(String constraintName) {
        return ""; // SQLite does not support adding primary keys directly
    }

    @Override
    public boolean hasAlterTable() {
        return false; // SQLite does not support altering tables directly
    }

    @Override
    public boolean qualifyIndexName() {
        return false;
    }
}
