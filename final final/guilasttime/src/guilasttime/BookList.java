/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guilasttime;

import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author crvnt
 */
public class BookList {
    private ArrayList<Book> bookList = new ArrayList<>();

    public BookList() {
        try {
            File file = new File("books.txt");
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String bookInfo = reader.nextLine();
                Scanner readerB = new Scanner(bookInfo);
                readerB.useDelimiter(",");
                String title = readerB.next();
                double price = readerB.nextDouble();
                Book newBook = new Book(title, price);
                bookList.add(newBook);
                readerB.close();
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("An error occurred.");
            Logger.getLogger(BookList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addBook(String title, double price) {
        try ( FileWriter writer = new FileWriter("books.txt", true);  
                BufferedWriter buffWriter = new BufferedWriter(writer);  
                PrintWriter out = new PrintWriter(buffWriter)) {
            out.println(title + "," + price);

            Book addedBook = new Book(title, price);
            bookList.add(addedBook);

            out.flush();
            out.close();
            writer.close();
            buffWriter.close();

        } catch (IOException ex) {
            System.out.println("An error occurred.");
            Logger.getLogger(BookList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void reload() {
        bookList.clear();
        try {
            File file = new File("books.txt");
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String bookInfo = reader.nextLine();
                Scanner readerB = new Scanner(bookInfo);
                readerB.useDelimiter(",");
                String title = readerB.next();
                int price = readerB.nextInt();
                Book newBook = new Book(title, price);
                bookList.add(newBook);
                readerB.close();
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("An error occurred.");
            Logger.getLogger(BookList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteBook(String filePath, String title, double price) throws IOException {
        //String tempPrice = String.valueOf(price);
        int position = 0;   //position of the term that we're looking to remove; since title of the book is always first element
        //in data array, position is 0
        String tempFile = "booksTemp.txt";
        File oldFile = new File(filePath);
        File newFile = new File(tempFile);
        String currentLine;
        String data[];

        try ( FileWriter writer = new FileWriter(tempFile, true);  BufferedWriter bWriter = new BufferedWriter(writer);  PrintWriter pWriter = new PrintWriter(bWriter);  FileReader fReader = new FileReader(filePath);  BufferedReader bReader = new BufferedReader(fReader)) {

            while ((currentLine = bReader.readLine()) != null) {    //basically we're splitting each line into a String array and then
                //splitting the line into substrings according to delimiter, ","
                data = currentLine.split(",");
                if (!(data[position].equalsIgnoreCase(title))) {
                    pWriter.println(currentLine);       //as long as the first element in data[] (i.e. title of book) is not the same
                    //as the title of the book we are trying to delete, pWriter writes the currentLine
                    //to the temp txt file
                }
            }
            pWriter.flush();
            pWriter.close();
            fReader.close();
            bReader.close();
            bWriter.close();

            if (oldFile.delete()) {
                System.out.println("Deleted.");
            }

            //File dump = new File(filePath);
            newFile.renameTo(oldFile);

           
            
            reload();
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }
    public ArrayList<Book> returnList() {
        return this.bookList;
    }
}    

    