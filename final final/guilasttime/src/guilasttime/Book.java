/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guilasttime;

/**
 *
 * @author crvnt
 */
public class Book {
    String bookTitle = "";
    double bookPrice = 0;
   

    public Book(String title, double price)
    {
        this.bookTitle = title;
        this.bookPrice = price;
    }

    public String getTitle()
    {
        return bookTitle;
    }

    public double getPrice()
    {
        return bookPrice;
    }

    @Override
    public String toString()
    {
        return ("Book: " + this.getTitle() + ", Price: $" + this.getPrice() + "\n");
    }

}
