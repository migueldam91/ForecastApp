package com.example.miguel.forecastapp;


//Temperatura(Cº). humedad y precipitaciones formateado
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by 21443251 on 04/12/2015.
 */
public class Current {
    private String icon;
    private long time;
    private double temperature;
    private double humidity;
    private double precipChance;
    private String summary;
    private String timeZone;

    public Current() {
    }

    public int getIconId() {
        int iconId = R.drawable.clear_day;
        if (icon.equals("clear-day")) {
            iconId = R.drawable.clear_day;
        } else if (icon.equals("clear-night")) {
            iconId = R.drawable.clear_night;
        } else if (icon.equals("rain")) {
            iconId = R.drawable.rain;
        } else if (icon.equals("snow")) {
            iconId = R.drawable.snow;
        } else if (icon.equals("sleet")) {
            iconId = R.drawable.sleet;
        } else if (icon.equals("wind")) {
            iconId = R.drawable.wind;
        } else if (icon.equals("fog")) {
            iconId = R.drawable.fog;
        } else if (icon.equals("cloudy")) {
            iconId = R.drawable.cloudy;
        } else if (icon.equals("partly-cloudy-day")) {
            iconId = R.drawable.partly_cloudy;
        } else if (icon.equals("partly-cloudy-night")) {
            iconId = R.drawable.cloudy_night;
        }
        return iconId;

    }


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getPrecipChance() {
        return precipChance;
    }

    public void setPrecipChance(double precipChance) {
        this.precipChance = precipChance;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    /**
     * Más info
     * http://developer.android.com/intl/es/reference/java/text/SimpleDateFormat.html
     *
     * @return cadena del tiempo formateada
     */
    public String getFormattedTime() {
        // Seleccionaremos el format que queremos
        SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");
        //Determinamos la hora que será según la zona horario
        formatter.setTimeZone(TimeZone.getTimeZone(getTimeZone()));
        //Obtenemos la hora mediate la clase Date, el objeto está en milisegundos por eso lo multiplicamos por 1000
        Date dateTime = new Date(getTime() * 1000);
        //Almacenamos en un String la hora generada con el format creado
        String timeString = formatter.format(dateTime);
        return timeString;
    }
}
