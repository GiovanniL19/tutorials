package giovannilenguito.co.uk.androidweather.Model;

/**
 * Created by giovannilenguito on 03/11/2016.
 */

public class Weather {
    String city;
    String windDescription;
    String windDirection;
    String clouds;
    String country;

    double visibility;
    double windSpeed;
    double currentTemp;
    double minTemp;
    double maxTemp;

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
