/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guilasttime;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

/**
 * FXML Controller class
 *
 * @author crvnt
 */
public class OwnerCustScreenController implements Initializable {
    
    @FXML private TableView<Customer> otableView;
    @FXML private TableColumn<Customer, String> oUNColumn;
    @FXML private TableColumn<Customer, String> oPassColumn;
    @FXML private TableColumn<Customer, Double> oPointsColumn;
    
    @FXML private TextField oUN;
    @FXML private TextField oPass;
    @FXML private TextField oPoints;
    
    public void ownerCustScreenBackHit(ActionEvent event) throws IOException{
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("oMenu.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }  
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        oUNColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("username"));
        oPassColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("password"));
        oPointsColumn.setCellValueFactory(new PropertyValueFactory<Customer, Double>("points"));
        
        otableView.setItems(getCustomers());
        
        oUNColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        oPassColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        oPointsColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        
        otableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }    
    
    public void deleteHit() throws IOException {
        ObservableList<Customer> selectedRows, allcust;
        allcust = otableView.getItems();
        
        selectedRows = otableView.getSelectionModel().getSelectedItems();
        
        for (Customer cust: selectedRows)
        {
            Guilasttime.customers.deleteCustomer("Customer.txt",cust.getUsername(),cust.getPassword(),cust.getPoints(),cust.getStatus());
            allcust.remove(cust);
        }
    }
    public ObservableList<Customer> getCustomers() {
        ObservableList<Customer> actualList = FXCollections.observableArrayList();
        for (int i = 0; i < Guilasttime.customers.returnList().size(); i++)
          actualList.add(Guilasttime.customers.returnList().get(i));  
            
        return actualList;
    }
    public void addHit() {
        String status = "Silver";
        
        Customer newCust = new Customer(oUN.getText(),oPass.getText(),0,status);
        Guilasttime.customers.addCustomer(newCust.getUsername(), newCust.getPassword(), 0, status);
        otableView.getItems().add(newCust);
    } 
    
}
