package org.cactus.microprofile.health;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.health.Readiness;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Liveness
@Readiness
@ApplicationScoped
public class DatabaseHealthCheck implements HealthCheck {

    @Inject
    DataSource dataSource;

    @Override
    public HealthCheckResponse call() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SHOW STATUS LIKE 'Innodb_buffer_pool_bytes_data'")) {

            if (resultSet.next()) {
                long memoryUsage = resultSet.getLong("Value");
                return HealthCheckResponse.named("Database Memory Usage")
                        .withData("memoryUsage", memoryUsage)
                        .up()
                        .build();
            } else {
                return HealthCheckResponse.named("Database Memory Usage")
                        .withData("memoryUsage", "N/A")
                        .down()
                        .build();
            }
        } catch (Exception e) {
            return HealthCheckResponse.named("Database Memory Usage")
                    .withData("error", e.getMessage())
                    .down()
                    .build();
        }
    }
}