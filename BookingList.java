/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USER
 */
import java.util.*;
import java.io.*;
import javax.swing.*;
public class BookingList {
    private ArrayList<Booking> list = new ArrayList<>(); // List to store all booking objects
    File fileName = new File("booking.data"); // File where bookings are stored
    
    // Read booking records from file and store them in the list.
    public void readFile(){
        try {
            list.clear(); // Add this to avoid duplicate records
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null){
                String [] data = line.split(",");
                if(data.length == 8){
                    String id = data[0];
                    String name = data[1];
                    String startTime = data[2];
                    String endTime = data[3];
                    String days = data[4];
                    int courtNum = Integer.parseInt(data[5]);
                    int duration = Integer.parseInt(data[6]);
                    double price = Double.parseDouble(data[7]);
                    // Add new Booking to the list
                    list.add(new Booking(id, name, startTime, endTime, days, courtNum, duration, price));
                }
            }
            reader.close();
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Error Reading File");
        }
    }
    // save bookings to file
    public void saveToFile(){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            for(Booking b : list){
                bw.write(b.getId() + "," + b.getName() + "," + b.getStartTime() + "," +
                b.getEndTime() + "," + b.getDays() + "," + b.getCourtNum() + "," + 
                b.getDuration() + "," + b.getPrice());
                bw.newLine();
            }
            bw.close();
        }catch (IOException e){
            JOptionPane.showMessageDialog(null, "Error Saving File");
        }
    }
    // Add a booking and save
    public void addBooking(Booking b){
        list.add(b);
        saveToFile();
    }
    // Returns the full list of bookings.
    public ArrayList<Booking> getList(){
        return list;
    }
    //Converts time from String to 24 hour int format
    public int timeToInt(String time){
        if (time == null || time.trim().isEmpty()){
            return -1; // If the input is invalid
        }
         // Remove dots and spaces like 06:00 p.m. to 0600pm
        time = time.toLowerCase().replaceAll("\\.", "").replaceAll(" ", "");
        // Extract hour part before the :
        String[] parts = time.split(":");
        if (parts.length < 2){
            return -1;
        }
        
        try {
            int hour = Integer.parseInt(parts[0]);
             // Convert to 24 hour format
            if (time.contains("pm") && hour != 12) {
                hour += 12;
            } else if (time.contains("am") && hour == 12) {
                hour = 0;
            }
            return hour;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    // Check if court is available (excluding a booking ID during update)
    public boolean checkCourt(String days, String startTime, String endTime, int courtNum, String excludeId){
        int newStart = timeToInt(startTime);
        int newEnd = timeToInt(endTime);
        for(Booking b : list){
            if (!b.getId().equals(excludeId) && b.getDays().equalsIgnoreCase(days) &&
                b.getCourtNum() == courtNum) {
                int existStart = timeToInt(b.getStartTime());
                int existEnd = timeToInt(b.getEndTime());

                if (newStart < existEnd && newEnd > existStart) {
                    return false; // Conflict found
                }
            }
        }
        return true;
    }
}
