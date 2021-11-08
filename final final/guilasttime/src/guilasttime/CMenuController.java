/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guilasttime;

import java.io.IOException;
import java.io.PipedInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
/**
 * FXML Controller class
 *
 * @author crvnt
 */
public class CMenuController implements Initializable {
    
    @FXML private TableView<Book> tableView;
    @FXML private TableColumn<Book, String> titleColumn;
    @FXML private TableColumn<Book, Double> priceColumn;
    
    @FXML private TextField greeting = new TextField();
    
    

    public void changeScreenButtonHit(ActionEvent event) throws IOException{
        Guilasttime.isOwner = false;
        Guilasttime.authenticated = false;
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
    public void viewCartHit(ActionEvent event) throws IOException{
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("viewCart.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        titleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Book, Double>("price"));
        
        greeting.setText("Hello " + Guilasttime.customer.getUsername() + ".  You have " + Guilasttime.customer.getPoints() + " points and are status: " + Guilasttime.customer.getStatus());
        
        tableView.setItems(getBooks());     
        
        titleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        priceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
    }
     
    
    public ObservableList<Book> getBooks() {
        ObservableList<Book> actualList = FXCollections.observableArrayList();
        for (int i = 0; i < Guilasttime.books.returnList().size(); i++)
          actualList.add(Guilasttime.books.returnList().get(i));  
            
        return actualList;
            
    }
    public void addtoCartHit() throws IOException {
        ObservableList<Book> selectedRows, allbooks;
        allbooks = tableView.getItems();
        
        selectedRows = tableView.getSelectionModel().getSelectedItems();
        
        for (Book books: selectedRows)
        {
            
            Guilasttime.cart.addBook(books.getTitle(), books.getPrice());
        }
    }
    
    
    
}
