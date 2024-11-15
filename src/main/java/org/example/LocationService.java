package org.example;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class LocationService {
    private LocationDAO locationDAO = new LocationDAO();
    private WeatherDataDAO weatherDataDAO = new WeatherDataDAO();


    // Add a new location
    public void addLocation(String cityName, String country, String region, double latitude, double longitude) {
        if (cityName == null || cityName.isEmpty() || country == null || country.isEmpty()) {
            System.out.println("City and country cannot be empty.");
            return;
        }
        String id = UUID.randomUUID().toString(); // Generate a unique ID for the location
        Location location = new Location(id, cityName, country, region, latitude, longitude);
        locationDAO.addLocation(location);
        System.out.println("Location added successfully.");
    }

    // Get all locations
    public List<Location> getAllLocations() {
        return locationDAO.getAllLocations();
    }

    // Get location by city name
    public Location getLocationByCity(String cityName) {
        for (Location loc : locationDAO.getAllLocations()) {
            if (loc.getCityName().equalsIgnoreCase(cityName)) {
                return loc;
            }
        }
        return null;
    }

    // Save weather data for a location
    public void saveWeatherData(String cityName, LocalDate date, double temperature, double pressure, double humidity, double windSpeed, String windDirection) {
        Location location = getLocationByCity(cityName);
        if (location != null) {
            WeatherData weatherData = new WeatherData(location.getId(), date, temperature, pressure, humidity, windSpeed, windDirection);
            weatherDataDAO.saveWeatherData(weatherData);
        } else {
            System.out.println("Location not found.");
        }
    }

    // Get weather data for a location and date
    public WeatherData getWeatherData(String cityName, LocalDate date) {
        Location location = getLocationByCity(cityName);
        if (location != null) {
            return weatherDataDAO.getWeatherData(location.getId(), date);
        }
        return null;
    }
    public Location getLocationByCityName(String cityName) {
        return locationDAO.getLocationByCityName(cityName);
    }
}
