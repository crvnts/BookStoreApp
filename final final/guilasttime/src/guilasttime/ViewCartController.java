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
public class ViewCartController implements Initializable {

    @FXML private TableView<Book> ctableView;
    @FXML private TableColumn<Book, String> ctitleColumn;
    @FXML private TableColumn<Book, Double> cpriceColumn;
    
    @FXML private TextField ctotalbox = new TextField();
    @FXML private TextField cprice;
    
    
    
    public void backButtonHit(ActionEvent event) throws IOException{
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("cMenu.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
    public void buyButtonHit(ActionEvent event) throws IOException {
        
        Guilasttime.customer.givePoints(Guilasttime.cart.sumOfPrice());
        Guilasttime.customer.updateStatus();
        Guilasttime.customers.updateList();
        
        Guilasttime.cart.clearAll();
        
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("cMenu.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        
       
    }
    
    public void RedbuyButtonHit(ActionEvent event) throws IOException {
        
        double tc = Guilasttime.cart.sumOfPrice();
        tc -= Guilasttime.customer.redeemPoints(tc);
        Guilasttime.customer.givePoints(tc);
        Guilasttime.customer.updateStatus();
        Guilasttime.customers.updateList();
        
        Guilasttime.cart.clearAll();
        
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("cMenu.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        
    }
    
    public void removeCartHit() throws IOException {
        ObservableList<Book> selectedRows, allbooks;
        allbooks = ctableView.getItems();
        
        selectedRows = ctableView.getSelectionModel().getSelectedItems();
        
        for (Book books: selectedRows)
        {
            Guilasttime.cart.deleteBook(books.getTitle(),books.getPrice());
            allbooks.remove(books);
            ctotalbox.setText("Total: $" + Guilasttime.cart.sumOfPrice());
        }
    }
    
    public ObservableList<Book> getCart() {
        ObservableList<Book> actualList = FXCollections.observableArrayList();
        for (int i = 0; i < Guilasttime.cart.returnList().size(); i++)
          actualList.add(Guilasttime.cart.returnList().get(i));  
            
        return actualList;
            
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ctitleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        cpriceColumn.setCellValueFactory(new PropertyValueFactory<Book, Double>("price"));
        
        ctableView.setItems(getCart());
        
        ctotalbox.setText("Total: $" + Guilasttime.cart.sumOfPrice());
        
        ctitleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        cpriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        
        ctableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }    
    
}
