package com.example.e_dive;

public class MyTicketTrip {

    String nama_trip, lokasi;
    String jumlah_ticket;

    public MyTicketTrip() {
    }

    public MyTicketTrip(String nama_trip, String location, String jumlah_ticket) {
        this.nama_trip = nama_trip;
        this.lokasi = lokasi;
        this.jumlah_ticket = jumlah_ticket;
    }

    public String getNama_trip() {
        return nama_trip;
    }

    public void setNama_trip(String nama_trip) {
        this.nama_trip = nama_trip;
    }

    public String getLocation() {
        return lokasi;
    }

    public void setLocation(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getJumlah_ticket() {
        return jumlah_ticket;
    }

    public void setJumlah_ticket(String jumlah_ticket) {
        this.jumlah_ticket = jumlah_ticket;
    }
}
