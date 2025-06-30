/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USER
 */
public class Booking {
    private String id, name, startTime, endTime, days;
    int courtNum, duration;
    double price;
    
    public Booking(String id, String name, String startTime, String endTime, String days, int courtNum, int duration, double price) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.days = days;
        this.courtNum = courtNum;
        this.duration = duration;
        this.price = calculate();
    }

    private double calculate(){
        return duration * 15;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getDays() {
        return days;
    }

    public int getCourtNum() {
        return courtNum;
    }

    public int getDuration() {
        return duration;
    }

    public double getPrice() {
        return calculate();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public void setCourtNum(int courtNum) {
        this.courtNum = courtNum;
    }

    public void setDuration(int duration) {
        this.duration = duration;
        this.price = calculate();
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    @Override
    public String toString(){
        return "Booking ID: " + id +
               "\nName: " + name + 
               "\nDays: " + days + 
               "\nCourt: " + courtNum + 
               "\nTime: " + startTime + " - " + endTime +
               "\nDuration: " + duration + " hour(s)" +
               "\nPrice: RM" + price + "\n------------------------\n";
    }
}
