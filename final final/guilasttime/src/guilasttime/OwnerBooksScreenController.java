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
import javafx.scene.control.TableCell;
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
public class OwnerBooksScreenController implements Initializable {
    
    @FXML private TableView<Book> otableView;
    @FXML private TableColumn<Book, String> otitleColumn;
    @FXML private TableColumn<Book, Double> opriceColumn;
    
    @FXML private TextField otitle;
    @FXML private TextField oprice;
    
    
    

    public void oBackButtonHit(ActionEvent event) throws IOException{
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("oMenu.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        otitleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        opriceColumn.setCellValueFactory(new PropertyValueFactory<Book, Double>("price"));
        
        otableView.setItems(getBooks());
        otableView.setEditable(true);
        
        otitleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        opriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        
        otableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    
    }    
    
    public void deleteHit() throws IOException {
        ObservableList<Book> selectedRows, allbooks;
        allbooks = otableView.getItems();
        
        selectedRows = otableView.getSelectionModel().getSelectedItems();
        
        for (Book books: selectedRows)
        {
            Guilasttime.books.deleteBook("books.txt",books.getTitle(),books.getPrice());
            allbooks.remove(books);
        }
    }
    
   public ObservableList<Book> getBooks() {
        ObservableList<Book> actualList = FXCollections.observableArrayList();
        for (int i = 0; i < Guilasttime.books.returnList().size(); i++)
          actualList.add(Guilasttime.books.returnList().get(i));  
            
        return actualList;
            
    }
    
    public void addHit() {
        Book newBook = new Book(otitle.getText(),Double.parseDouble(oprice.getText()));
        Guilasttime.books.addBook(newBook.getTitle(), newBook.getPrice());
        otableView.getItems().add(newBook);
    } 
}
