package presentation;

import business.BaseProduct;
import business.IDeliveryServiceProcessing;
import business.MenuItem;
import business.Order;
import model.Client;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ClientController {
    private ClientView clientView;
    private Client client;
    private IDeliveryServiceProcessing deliveryService;

    public ClientController(ClientView clientView, Client client, IDeliveryServiceProcessing deliveryService) {
        this.clientView = clientView;
        this.client = client;
        this.deliveryService = deliveryService;

        clientView.addSearchButtonListener(e -> {
            try{
                String title = clientView.getTitle();
                List<MenuItem> menu = deliveryService.list().stream().filter(t -> {
                    if(!t.getTitle().contains(title)){
                        return false;
                    }
                    if(t instanceof BaseProduct){
                        BaseProduct baseProduct = (BaseProduct)t;
                        float rating;
                        if(!clientView.getRating().isBlank()){
                            rating = Float.parseFloat(clientView.getRating());
                            if(baseProduct.getRating() != rating)
                                return false;
                        }
                        int calories;
                        if(!clientView.getCalories().isBlank()){
                            calories = Integer.parseInt(clientView.getCalories());
                            if(baseProduct.getCalories() != calories){
                                return false;
                            }
                        }
                        int protein;
                        if(!clientView.getProtein().isBlank()){
                            protein = Integer.parseInt(clientView.getProtein());
                            if(baseProduct.getProtein() != protein){
                                return false;
                            }
                        }
                        int fat;
                        if(!clientView.getFat().isBlank()){
                            fat = Integer.parseInt(clientView.getFat());
                            if(baseProduct.getFat() != fat){
                                return false;
                            }
                        }
                        int sodium;
                        if(!clientView.getSodium().isBlank()){
                            sodium = Integer.parseInt(clientView.getSodium());
                            if(baseProduct.getSodium() != sodium){
                                return false;
                            }
                        }
                        int price;
                        if(!clientView.getPrice().isBlank()){
                            price = Integer.parseInt(clientView.getPrice());
                            if(baseProduct.getPrice() != price){
                                return false;
                            }
                        }
                    }
                    return true;
                }).collect(Collectors.toList());
                clientView.updateTable(menu);
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Invalid");
            }

        });

        clientView.addOrderButtonListener(e -> {
            List<MenuItem> order = clientView.getSelectedMenuItems();
            int totalPrice = deliveryService.createOrder(order, client);
            Order o = new Order(client, new Date());
            try {
                FileWriter reportFile = new FileWriter("Bill"+ o.getOrderID() + ".txt");
                reportFile.write("Ordered products:\n");
                for(MenuItem menuItem : order){
                    reportFile.write(menuItem.toString() + "\n");
                }
                reportFile.write("\nTotal price = ");
                reportFile.write(totalPrice + "");
                reportFile.close();

            }catch(IOException ioException){
                ioException.printStackTrace();
            }
        });
    }

}
