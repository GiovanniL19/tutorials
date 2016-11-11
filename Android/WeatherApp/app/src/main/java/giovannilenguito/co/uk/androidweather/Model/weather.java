package giovannilenguito.co.uk.androidweather.model;

/**
 * Created by giovannilenguito on 03/11/2016.
 */

public class Weather {
    private String city;
    private String windDescription;
    private String windDirection;
    private String clouds;
    private String country;
    private String description;

    private double visibility;
    private double windSpeed;
    private double currentTemp;
    private double minTemp;
    private double maxTemp;

    @Override
    public String toString() {
        return "Weather{" +
                "city='" + city + '\'' +
                ", windDescription='" + windDescription + '\'' +
                ", windDirection='" + windDirection + '\'' +
                ", clouds='" + clouds + '\'' +
                ", country='" + country + '\'' +
                ", visibility=" + visibility +
                ", windSpeed=" + windSpeed +
                ", currentTemp=" + currentTemp +
                ", minTemp=" + minTemp +
                ", maxTemp=" + maxTemp +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWindDescription() {
        return windDescription;
    }

    public void setWindDescription(String windDescription) {
        this.windDescription = windDescription;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    public double getVisibility() {
        return visibility;
    }

    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(double currentTemp) {
        this.currentTemp = currentTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }
}
