package com.example.jamalian.myandroidclass.Models;

/**
 * Created by Jamalian on 8/18/2017.
 */

public class DownloadModel {
    int percent;
    String name;

    public DownloadModel() {

    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPercent() {

        return percent;
    }

    public String getName() {
        return name;
    }

    public DownloadModel(int percent, String name) {

        this.percent = percent;
        this.name = name;
    }
}
