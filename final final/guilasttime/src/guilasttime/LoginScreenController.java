/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guilasttime;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author crvnt
 */
public class LoginScreenController implements Initializable {
    
    @FXML private TextField username = new TextField();
    @FXML private PasswordField password = new PasswordField();
    
    
    
    
    public void changeScreenButtonHit(ActionEvent event) throws IOException{
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("cMenu.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
    public void ownerMenuButtonHit(ActionEvent event) throws IOException{
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("oMenu.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }

    
    public void loginHit (ActionEvent event) throws IOException{
        String uName = username.getText();
        String pass = password.getText();
        
            if (uName.equals("admin") && pass.equals("admin")) {
                Guilasttime.isOwner = true;
                Guilasttime.owner = new Owner(uName,pass);
                Guilasttime.authenticated = true;
               
            } else {
                Guilasttime.authenticated = Authenticate(uName, pass); 
            }
        if (Guilasttime.authenticated){
            if (Guilasttime.isOwner) {
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("oMenu.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
            }
            else {
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("cMenu.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
            }
        }
            
    }
    public boolean Authenticate(String username, String password) {
        if (Guilasttime.customers.findCustomer(username).getUsername().equalsIgnoreCase("")) {
        //if (!(customera.getUsername().equals(username))) {
            System.out.println("Incorrect username");
            return false;
        } else {
            Guilasttime.customer = Guilasttime.customers.findCustomer(username);
            if (Guilasttime.customer.getPassword().equals(password)) {
                return true;
            } else {
                System.out.println("Incorrect password");
                return false;
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
