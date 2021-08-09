package com.example.lighting;

public class Led {

    private String Id;
    private String Status;
    private int Intensity;
    private boolean isClicked = false;
    public boolean isClicked() {

        return !isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public int getIntensity() {
        return Intensity;
    }

    public void setIntensity(int intensity) {
        Intensity = intensity;
    }

    public Led(String id, String status ) {
        Id = id;
        Status = status;
    }

    public Led() {
    }
}
