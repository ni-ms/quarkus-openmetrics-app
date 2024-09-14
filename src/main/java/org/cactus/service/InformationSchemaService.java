package org.cactus.service;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.eclipse.microprofile.config.inject.ConfigProperty;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@ApplicationScoped
public class InformationSchemaService {

    @ConfigProperty(name = "quarkus.datasource.jdbc.url")
    String url;

    @ConfigProperty(name = "quarkus.datasource.username")
    String user;

    @ConfigProperty(name = "quarkus.datasource.password")
    String password;

}