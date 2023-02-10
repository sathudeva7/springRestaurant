package dev.sathu.movies.controller;

import dev.sathu.movies.model.Address;
import dev.sathu.movies.model.Cusine;
import dev.sathu.movies.model.PaymentOptions;

import java.util.List;

public class Payload {
    private String name;
    private Address address;
    private boolean deliveryOptions;
    private Cusine cusine;
    private String phoneNumber;
    private PaymentOptions paymentOptions;
    private List<String> image;
    //getters and setters


    public Payload(String name, Address address, boolean deliveryOptions, Cusine cusine, String phoneNumber, PaymentOptions paymentOptions, List<String> image) {
        this.name = name;
        this.address = address;
        this.deliveryOptions = deliveryOptions;
        this.cusine = cusine;
        this.phoneNumber = phoneNumber;
        this.paymentOptions = paymentOptions;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public boolean isDeliveryOptions() {
        return deliveryOptions;
    }

    public Cusine getCusine() {
        return cusine;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public PaymentOptions getPaymentOptions() {
        return paymentOptions;
    }

    public List<String> getImage() {
        return image;
    }
}
