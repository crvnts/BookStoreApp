/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guilasttime;

import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author crvnt
 */
public class Guilasttime extends Application {
    public static CustomerList customers;
    public static BookList books;
    public static Owner owner;
    public static Customer customer;
    public static boolean isOwner = false;
    public static Scanner input = new Scanner(System.in);
    public static Cart cart;
    public static boolean authenticated;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        cart = new Cart();
        customers = new CustomerList();
        books = new BookList();
        owner = new Owner("","");
        customer = new Customer("","",0,"");
        authenticated = false;
        launch(args);
    }
    
}
