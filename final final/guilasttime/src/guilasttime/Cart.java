/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guilasttime;
import java.util.ArrayList;
/**
 *
 * @author crvnt
 */
public class Cart {
    private ArrayList<Book> cart = new ArrayList<>();
    private ArrayList<Double> costs = new ArrayList<>();

    public Cart() {}

    public void addBook(String title, double price) {
        cart.add(new Book(title, price));
        costs.add(price);
    }

    public void deleteBook(String title, double price) {
        for (int i = 0; i< cart.size(); i++) {
            if (cart.get(i).getTitle().equals(title) && cart.get(i).getPrice() == price) {
                cart.remove(i);
                costs.remove(i);
            }
        }
    }
    
    public void clearAll() {
        cart.clear();
        costs.clear();
    }

    public double sumOfPrice() {
        double sum = 0;
        for (int i = 0; i < costs.size(); i++) {
            sum += costs.get(i);
        }
        return sum;
    }

    public ArrayList<Book> returnList() {
        return this.cart;
    }
}
