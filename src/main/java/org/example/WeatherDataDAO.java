package org.example;

import java.sql.*;
import java.time.LocalDate;

public class WeatherDataDAO {

    public void saveWeatherData(WeatherData weatherData) {
        String sql = "INSERT INTO WeatherData (location_id, date, temperature, pressure, humidity, wind_speed, wind_direction) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, weatherData.getLocationId());
            pstmt.setDate(2, Date.valueOf(weatherData.getDate()));
            pstmt.setDouble(3, weatherData.getTemperature());
            pstmt.setDouble(4, weatherData.getPressure());
            pstmt.setDouble(5, weatherData.getHumidity());
            pstmt.setDouble(6, weatherData.getWindSpeed());
            pstmt.setString(7, weatherData.getWindDirection());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public WeatherData getWeatherData(String locationId, LocalDate date) {
        String sql = "SELECT * FROM WeatherData WHERE location_id = ? AND date = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, locationId);
            pstmt.setDate(2, Date.valueOf(date));
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new WeatherData(
                            rs.getString("location_id"),
                            rs.getDate("date").toLocalDate(),
                            rs.getDouble("temperature"),
                            rs.getDouble("pressure"),
                            rs.getDouble("humidity"),
                            rs.getDouble("wind_speed"),
                            rs.getString("wind_direction")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
