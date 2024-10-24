package com.example.firstapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;

@SpringBootApplication
public class ImgSearchEngineApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ImgSearchEngineApp.class, args);
    }

    // This RestController handles all requests for search
    @RestController
    @RequestMapping("/api/search")
    public static class SearchController {

        // API to accept POST requests with search query
        @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<String> saveSearchQuery(@RequestBody SearchRequest searchRequest) {
            String query = searchRequest.getQuery();
            try {
                saveSearchQueryToDatabase(query);
                return ResponseEntity.ok("Search query saved successfully!");
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(500).body("Failed to save search query!");
            }
        }

        // Save the search query into MySQL database
        public static void saveSearchQueryToDatabase(String query) throws SQLException {
            // JDBC database connection details
            String url = "jdbc:mysql://localhost:3306/hello";
            String username = "root";
            String password = "Satish@123";

            // Establish connection
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                // SQL query to insert search data
                String insertQuery = "INSERT INTO search_history (query, search_time, result_count) VALUES (?, NOW(), ?)";
                int resultCount = 10; // Example result count (can be dynamic based on Unsplash response)

                // Prepare and execute the SQL statement
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setString(1, query);
                preparedStatement.setInt(2, resultCount);
                int rowsInserted = preparedStatement.executeUpdate();

                if (rowsInserted > 0) {
                    System.out.println("Search query inserted successfully!");
                }

                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new SQLException("Error while saving search query.");
            }
        }
    }

    // Request body format class to hold search query data
    public static class SearchRequest {
        private String query;

        // Getters and Setters
        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }
    }
}
