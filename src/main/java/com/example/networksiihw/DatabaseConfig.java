package com.example.networksiihw;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import io.github.cdimascio.dotenv.Dotenv;

public class DatabaseConfig {
    private static SessionFactory sessionFactory;

    public static void init() {
        try {
            if (sessionFactory == null) {
                // Load environment variables from .env file (Optional)
                Dotenv dotenv = Dotenv.configure().load();
                String dbUrl = dotenv.get("DB_URL");
                String dbUser = dotenv.get("DB_USER");
                String dbPassword = dotenv.get("DB_PASSWORD");

                // Initialize the Hibernate SessionFactory
                Configuration configuration = new Configuration().configure();
                configuration.setProperty("hibernate.connection.url", dbUrl);
                configuration.setProperty("hibernate.connection.username", dbUser);
                configuration.setProperty("hibernate.connection.password", dbPassword);
                sessionFactory = configuration.buildSessionFactory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


}
