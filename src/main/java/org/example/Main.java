package org.example;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LocationController locationController = new LocationController();

        while (true) {
            System.out.println("1. Add Location");
            System.out.println("2. List Locations");
            System.out.println("3. Add Weather Data");
            System.out.println("4. Get Weather Data");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter city name: ");
                    String cityName = scanner.nextLine();
                    System.out.print("Enter country: ");
                    String country = scanner.nextLine();
                    System.out.print("Enter region: ");
                    String region = scanner.nextLine();
                    System.out.print("Enter latitude: ");
                    double latitude = scanner.nextDouble();
                    System.out.print("Enter longitude: ");
                    double longitude = scanner.nextDouble();
                    locationController.addLocation(cityName, country, region, latitude, longitude);
                    break;

                case 2:
                    locationController.listLocations();
                    break;

                case 3:
                    System.out.print("Enter city name: ");
                    String cityForWeather = scanner.nextLine();
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    String dateStr = scanner.nextLine();
                    LocalDate date = LocalDate.parse(dateStr);
                    System.out.print("Enter temperature: ");
                    double temperature = scanner.nextDouble();
                    System.out.print("Enter pressure: ");
                    double pressure = scanner.nextDouble();
                    System.out.print("Enter humidity: ");
                    double humidity = scanner.nextDouble();
                    System.out.print("Enter wind speed: ");
                    double windSpeed = scanner.nextDouble();
                    scanner.nextLine(); // Consume the newline
                    System.out.print("Enter wind direction: ");
                    String windDirection = scanner.nextLine();
                    locationController.saveWeatherData(cityForWeather, date, temperature, pressure, humidity, windSpeed, windDirection);
                    break;

                case 4:
                    System.out.print("Enter city name: ");
                    String cityForData = scanner.nextLine();
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    String dateInput = scanner.nextLine();
                    LocalDate weatherDate = LocalDate.parse(dateInput);
                    WeatherData weatherData = locationController.getWeatherData(cityForData, weatherDate);
                    if (weatherData != null) {
                        System.out.println("Weather data for " + cityForData + " on " + weatherDate);
                        System.out.println("Temperature: " + weatherData.getTemperature());
                        System.out.println("Pressure: " + weatherData.getPressure());
                        System.out.println("Humidity: " + weatherData.getHumidity());
                        System.out.println("Wind Speed: " + weatherData.getWindSpeed());
                        System.out.println("Wind Direction: " + weatherData.getWindDirection());
                    } else {
                        System.out.println("No weather data found for the specified date.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    return;
            }
        }
    }
}
