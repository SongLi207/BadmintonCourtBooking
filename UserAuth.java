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
import javax.swing.JOptionPane;
public class UserAuth {
    private ArrayList <User> users; // List to store all user 
    private static String FILE_NAME = "users.txt"; // File name for saving/loading user data
    private User currentUser; // Track the login user
    
    public UserAuth(){
        users = readUsers();// Load users from the file
        if (users.isEmpty()) {
            users.add(new User("admin", "admin123")); // Add default admin user
            saveUsers();
        }   
    }
    // Check if given username and password match any user
    public boolean login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user; // Store login user
                return true; 
            }
        }
        return false;
    }

    public User getCurrentUser() {
        return currentUser;
    }
    
    // check if the username is not already taken
    public boolean register(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return false; // Username already exists
            }
        }
        users.add(new User(username, password));
        saveUsers(); // Save to file
        return true;
    }
    // Reset password for existing user
    public boolean resetPassword(String username, String newPassword) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                user.setPassword(newPassword);
                saveUsers(); // Save changes
                return true;
            }   
        }
        return false;
    }
    // Get the list of all users
    public ArrayList<User> getUsers(){
        return users;
    }
    
    private void saveUsers(){
        try{
            // Open file to writing
            BufferedWriter writer = new BufferedWriter (new FileWriter(FILE_NAME));
            for(User user : users){
                writer.write(user.getUsername() + "," + user.getPassword());
                writer.newLine();
            }
            writer.close();
        }catch (IOException e){
            JOptionPane.showMessageDialog(null, "Error saving users:\n" + e.getMessage());
        }
    }
    // Load users from the text file
    private ArrayList<User> readUsers(){
        ArrayList<User> userList = new ArrayList<>();
        File file = new File(FILE_NAME);
        // If file does not exist return empty list
        if(!file.exists()){
            return userList;
        }
        try{
            BufferedReader Reader = new BufferedReader (new FileReader(FILE_NAME));
            String line;
            while ((line = Reader.readLine()) != null){
                String[] parts = line.split(","); //Split the line by comma
                if(parts.length == 2){
                    userList.add(new User(parts[0], parts[1]));
                }
            }
            Reader.close();
        }catch (IOException e){
            JOptionPane.showMessageDialog(null, "Error saving users:\n" + e.getMessage());
        }
        return userList;
    }
}
