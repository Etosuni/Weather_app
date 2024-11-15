package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationDAO {

    public void addLocation(Location location) {
        String sql = "INSERT INTO Location (id, city_name, country, region, latitude, longitude) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, location.getId());
            pstmt.setString(2, location.getCityName());
            pstmt.setString(3, location.getCountry());
            pstmt.setString(4, location.getRegion());
            pstmt.setDouble(5, location.getLatitude());
            pstmt.setDouble(6, location.getLongitude());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Location> getAllLocations() {
        List<Location> locations = new ArrayList<>();
        String sql = "SELECT * FROM Location";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                locations.add(new Location(
                        rs.getString("id"),
                        rs.getString("city_name"),
                        rs.getString("country"),
                        rs.getString("region"),
                        rs.getDouble("latitude"),
                        rs.getDouble("longitude")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locations;
    }

    public Location getLocationById(String id) {
        String sql = "SELECT * FROM Location WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Location(
                            rs.getString("id"),
                            rs.getString("city_name"),
                            rs.getString("country"),
                            rs.getString("region"),
                            rs.getDouble("latitude"),
                            rs.getDouble("longitude")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static final String SELECT_LOCATION_BY_CITY_SQL = "SELECT * FROM Location WHERE city_name = ?";

    // Other methods (addLocation, getAllLocations) go here...

    public Location getLocationByCityName(String cityName) {
        Location location = null;
        String sql = SELECT_LOCATION_BY_CITY_SQL;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cityName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                location = new Location(
                        rs.getString("id"),
                        rs.getString("city_name"),
                        rs.getString("country"),
                        rs.getString("region"),
                        rs.getDouble("latitude"),
                        rs.getDouble("longitude")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return location;
    }
}
