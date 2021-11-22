package com.example.api;

public class DogsList {
    private String[] message;
    private String status;

    public DogsList(String[] message, String status) {
        this.message = message;
        this.status = status;
    }

    public String[] getMessage() {
        return message;
    }
    public  String getImages(int i){
        return message[i];
    }

    public void setMessage(String[] message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
