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
public class Customer {
   private String username;
    private String password;
    private double points;
    private String status;


    public Customer(String username,String password, double points, String status)
    {
        this.username = username;
        this.password = password;
        this.points = points;
        this.status = status;
    }

    public String getUsername()
    {
        return username;
    }

    public double getPoints()
    {
        return points;
    }

     public String getPassword()
    {
        return password;
    }

    public String getStatus() {
        return status;
    }
    
    public void updateStatus () {
        if (points >= 1000) 
            status ="Gold";
        else if (points < 1000) 
            status = "Silver";
    }
   
    public double redeemPoints(double cost) {
        double redeemed =0;
        if (cost*100 > points) {
            System.out.println("notenough");
            redeemed = points;
            points -= redeemed;
        } else if (cost*100<= points) {
            System.out.println("enough");
            redeemed = cost*100;
            points -= redeemed;
        }
        return redeemed/100;
    }
    
    public void givePoints(double cost) {
        cost *=10;
        points+=cost;
    }
    
    @Override
    public String toString()
    {
        return ("Customer: " + this.getUsername() + ", Points: " + this.getPoints() + " Status: "+ this.getStatus() +"\n" + "Password: " + this.getPassword() + "\n");
    }
 
}
