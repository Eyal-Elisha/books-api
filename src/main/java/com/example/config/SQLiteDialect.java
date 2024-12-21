package com.example.config;

import org.hibernate.boot.model.TypeContributions;
import org.hibernate.dialect.DatabaseVersion;
import org.hibernate.dialect.Dialect;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.BasicType;

public class SQLiteDialect extends Dialect {

    public SQLiteDialect() {
        super(DatabaseVersion.make(3)); // Specify SQLite version 3 or higher
    }

    @Override
    public void registerColumnTypes(TypeContributions typeContributions, ServiceRegistry serviceRegistry) {
        var typeConfiguration = typeContributions.getTypeConfiguration();

        typeConfiguration.getBasicTypeRegistry().register(
                (BasicType<?>) typeConfiguration.getBasicTypeForJavaType(Integer.class).getJavaTypeDescriptor(),
                "integer"
        );
        typeConfiguration.getBasicTypeRegistry().register(
                (BasicType<?>) typeConfiguration.getBasicTypeForJavaType(String.class).getJavaTypeDescriptor(),
                "text"
        );
        typeConfiguration.getBasicTypeRegistry().register(
                (BasicType<?>) typeConfiguration.getBasicTypeForJavaType(Byte[].class).getJavaTypeDescriptor(),
                "blob"
        );
        typeConfiguration.getBasicTypeRegistry().register(
                (BasicType<?>) typeConfiguration.getBasicTypeForJavaType(Character[].class).getJavaTypeDescriptor(),
                "text"
        );
        typeConfiguration.getBasicTypeRegistry().register(
                (BasicType<?>) typeConfiguration.getBasicTypeForJavaType(Double.class).getJavaTypeDescriptor(),
                "real"
        );
        typeConfiguration.getBasicTypeRegistry().register(
                (BasicType<?>) typeConfiguration.getBasicTypeForJavaType(Boolean.class).getJavaTypeDescriptor(),
                "boolean"
        );
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