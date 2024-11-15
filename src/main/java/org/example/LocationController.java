package org.example;

import java.time.LocalDate;
import java.util.List;

public class LocationController {
    private LocationService locationService = new LocationService();
    private WeatherDataDAO weatherDataDAO = new WeatherDataDAO();

    public void addLocation(String cityName, String country, String region, double latitude, double longitude) {
        locationService.addLocation(cityName, country, region, latitude, longitude);
    }

    public void listLocations() {
        List<Location> locations = locationService.getAllLocations();
        for (Location loc : locations) {
            System.out.printf("ID: %s, City: %s, Country: %s, Region: %s, Latitude: %.2f, Longitude: %.2f%n",
                    loc.getId(), loc.getCityName(), loc.getCountry(), loc.getRegion(),
                    loc.getLatitude(), loc.getLongitude());
        }
    }

    public void saveWeatherData(String city, LocalDate date, double temperature, double pressure,
                                double humidity, double windSpeed, String windDirection) {
        // Fetch location ID by city name from the Location table
        Location location = locationService.getLocationByCityName(city);
        if (location != null) {
            WeatherData weatherData = new WeatherData(location.getId(), date, temperature, pressure,
                    humidity, windSpeed, windDirection);
            weatherDataDAO.saveWeatherData(weatherData);
        } else {
            System.out.println("Location not found.");
        }
    }

    public WeatherData getWeatherData(String city, LocalDate date) {
        // Fetch location ID by city name
        Location location = locationService.getLocationByCityName(city);
        if (location != null) {
            return weatherDataDAO.getWeatherData(location.getId(), date);
        }
        return null;
    }

}