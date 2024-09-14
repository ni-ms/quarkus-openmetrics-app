package org.cactus.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.cactus.entity.InformationSchemaTable;

import java.util.List;

@ApplicationScoped
public class InformationSchemaTableRepository implements PanacheRepository<InformationSchemaTable> {

    public List<InformationSchemaTable> findByTableSchema(String tableSchema) {
        return list("tableSchema", tableSchema);
    }
}