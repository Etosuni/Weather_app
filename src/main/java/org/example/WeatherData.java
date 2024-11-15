package org.example;

import java.time.LocalDate;

public class WeatherData {
    private String locationId; // Location ID linked to the city
    private LocalDate date;    // Date of the weather data
    private double temperature; // Temperature
    private double pressure;    // Atmospheric pressure
    private double humidity;    // Humidity level
    private double windSpeed;   // Wind speed
    private String windDirection; // Wind direction

    // Constructor
    public WeatherData(String locationId, LocalDate date, double temperature, double pressure,
                       double humidity, double windSpeed, String windDirection) {
        this.locationId = locationId;
        this.date = date;
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
    }

    // Getters and Setters
    public String getLocationId() { return locationId; }
    public void setLocationId(String locationId) { this.locationId = locationId; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }

    public double getPressure() { return pressure; }
    public void setPressure(double pressure) { this.pressure = pressure; }

    public double getHumidity() { return humidity; }
    public void setHumidity(double humidity) { this.humidity = humidity; }

    public double getWindSpeed() { return windSpeed; }
    public void setWindSpeed(double windSpeed) { this.windSpeed = windSpeed; }

    public String getWindDirection() { return windDirection; }
    public void setWindDirection(String windDirection) { this.windDirection = windDirection; }
}
