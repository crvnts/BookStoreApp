/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guilasttime;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author crvnt
 */
public class CustomerList {
    private ArrayList<Customer> customerList = new ArrayList<>();
    
    public CustomerList()
    {
        try {
            File file = new File("Customer.txt");
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String userInfo = reader.nextLine();
                Scanner readerB = new Scanner(userInfo);
                readerB.useDelimiter(",");
                String username = readerB.next();
                String password = readerB.next();
                double points = readerB.nextDouble();
                String status = readerB.next();
                Customer newCustomer = new Customer(username, password, points, status);
                customerList.add(newCustomer);
                readerB.close();
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("An error occurred.");
            Logger.getLogger(CustomerList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addCustomer(String username, String password, double points, String status) {
        try ( FileWriter writer = new FileWriter("Customer.txt", true);  BufferedWriter buffWriter = new BufferedWriter(writer);  PrintWriter out = new PrintWriter(buffWriter)) {
            out.println(username + "," + password + "," + points+"," + status);

            Customer addedCustomer = new Customer(username, password, points, status);
            customerList.add(addedCustomer);

            out.flush();
            out.close();
            writer.close();
            buffWriter.close();

        } catch (IOException ex) {
            System.out.println("An error occurred.");
            Logger.getLogger(CustomerList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteCustomer(String filePath, String username, String password, double points, String status) throws IOException {

        int position = 0;
        String tempFile = "CustomerDel.txt";
        File oldFile = new File(filePath);
        File newFile = new File(tempFile);
        String currentLine;
        String data[];

        try ( FileWriter writer = new FileWriter(tempFile, true);  BufferedWriter bWriter = new BufferedWriter(writer);  PrintWriter pWriter = new PrintWriter(bWriter);  FileReader fReader = new FileReader(filePath);  BufferedReader bReader = new BufferedReader(fReader)) {

            while ((currentLine = bReader.readLine()) != null) {
                data = currentLine.split(",");
                if (!(data[position].equalsIgnoreCase(username))) {
                    pWriter.println(currentLine);
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

            newFile.renameTo(oldFile);

            Customer CustomerRemove = new Customer(username, password, points, status);
            customerList.remove(CustomerRemove);
            reload();
            

        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }
    public void reload() {
        customerList.clear();
        try {
            File file = new File("Customer.txt");
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String userInfo = reader.nextLine();
                Scanner readerB = new Scanner(userInfo);
                readerB.useDelimiter(",");
                String username = readerB.next();
                String password = readerB.next();
                double points = readerB.nextDouble();
                String status = readerB.next();
                Customer newCustomer = new Customer(username, password, points, status);
                customerList.add(newCustomer);
                readerB.close();
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("An error occurred.");
            Logger.getLogger(CustomerList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   public void updateList() {
        String tempFile = "customerTemp.txt";
        File oldFile = new File("Customer.txt");
        File newFile = new File(tempFile);
        String currentLine;
        //String data[];

        try ( FileWriter writer = new FileWriter(tempFile, true);
                BufferedWriter bWriter = new BufferedWriter(writer);
                PrintWriter pWriter = new PrintWriter(bWriter);
                FileReader fReader = new FileReader("Customer.txt");  BufferedReader bReader = new BufferedReader(fReader)) {

            for(int i = 0; i < customerList.size(); i++)
            {
                pWriter.println(customerList.get(i).getUsername()+","+customerList.get(i).getPassword()+","+customerList.get(i).getPoints()+","+customerList.get(i).getStatus());
            }
            pWriter.flush();
            pWriter.close();
            fReader.close();
            bReader.close();
            bWriter.close();

            if (oldFile.delete()) {
                System.out.println("Deleted.");
            }

            newFile.renameTo(oldFile);

        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }
    
    public Customer findCustomer(String username) {
        Customer returnCustomer = new Customer("", "", 0, "");
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getUsername().equalsIgnoreCase(username)) {
                returnCustomer = customerList.get(i);
            }
        }
        return returnCustomer;
    }
    
    public void updateCustomer(Customer c) {
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getUsername().equalsIgnoreCase(c.getUsername())) {
                //System.out.println(c);
                System.out.println(customerList.get(i));
                customerList.set(i, c);
            }
        }
    }
    
   public ArrayList<Customer> returnList() {
       return this.customerList;
   }
}
