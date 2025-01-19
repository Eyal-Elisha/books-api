package com.example.config;

import org.hibernate.dialect.Dialect;
import org.hibernate.boot.model.TypeContributions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.SqlTypes;
import org.hibernate.type.spi.TypeConfiguration;

public class SQLiteDialect extends Dialect {

    public SQLiteDialect() {
        super();
    }

    @Override
    public void contributeTypes(TypeContributions typeContributions, ServiceRegistry serviceRegistry) {
        var typeConfiguration = typeContributions.getTypeConfiguration();


        registerIfNotNull(typeConfiguration, Integer.class, "integer");
        registerIfNotNull(typeConfiguration, String.class, "text");
        registerIfNotNull(typeConfiguration, Byte[].class, "blob");
        registerIfNotNull(typeConfiguration, Double.class, "real");
        registerIfNotNull(typeConfiguration, Boolean.class, "boolean");
        registerIfNotNull(typeConfiguration, Long.class, "integer");
    }

    private <T> void registerIfNotNull(TypeConfiguration typeConfiguration, Class<T> javaType, String sqlType) {
        var basicType = typeConfiguration.getBasicTypeForJavaType(javaType);
        if (basicType != null) {
            typeConfiguration.getBasicTypeRegistry().register(basicType, sqlType);
        } else {
            System.err.println("Warning: No BasicType found for " + javaType.getName());
        }
    }
}
