package org.cactus.service;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.cactus.entity.InformationSchemaTable;
import org.cactus.repository.InformationSchemaTableRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class DatabaseService {

    @Inject
    DataSource dataSource;

    @Inject
    MeterRegistry registry;

    @Inject
    InformationSchemaTableRepository informationSchemaTableRepository;

    public List<InformationSchemaTable> getInformationSchemaTables() {
        return informationSchemaTableRepository.findByTableSchema("information_schema");
    }

    public int getRowCount(String tableName) {
        Timer.Sample sample = Timer.start(registry);
        int rowCount = 0;
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM " + tableName); ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                rowCount = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sample.stop(registry.timer("database.query.time", "table", tableName));
        return rowCount;
    }

    public boolean isInformationSchemaEnabled() {
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT ENABLED FROM performance_schema.setup_consumers WHERE NAME = 'events_statements_history'"); ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return "YES".equals(resultSet.getString("ENABLED"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}