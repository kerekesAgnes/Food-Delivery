package presentation;

import business.*;
import model.User;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class AdministratorController {
    private AdministratorView administratorView;
    private User user;
    private IDeliveryServiceProcessing deliveryService;

    public AdministratorController(AdministratorView administratorView, User user, IDeliveryServiceProcessing deliveryService) {
        this.administratorView = administratorView;
        this.user = user;
        this.deliveryService = deliveryService;

        administratorView.addImportButtonListener(e -> {
            try {
                deliveryService.importProduct("C:\\Users\\agnes\\OneDrive - Technical University of Cluj-Napoca\\II\\TP\\Tema 4\\products.csv");
                administratorView.updateTable();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        administratorView.addAddButtonListener(e -> {
            try{
                String title = administratorView.getTitle();
                float rating = Float.parseFloat(administratorView.getRating());
                int calories = Integer.parseInt(administratorView.getCalories());
                int protein = Integer.parseInt(administratorView.getProtein());
                int fat = Integer.parseInt(administratorView.getFat());
                int sodium = Integer.parseInt(administratorView.getSodium());
                int price = Integer.parseInt(administratorView.getPrice());

                MenuItem baseProduct = new BaseProduct(title, rating, calories, protein, fat, sodium, price);
                deliveryService.add(baseProduct);
                administratorView.updateTable();
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Invalid inputs");
            }
        });

        administratorView.addDeleteButtonListener(e -> {
            MenuItem menuItem = administratorView.getSelectedMenuItem();
            if(menuItem == null){
                JOptionPane.showMessageDialog(null, "Nothing selected");
                return;
            }
            deliveryService.delete(menuItem);
            administratorView.updateTable();
        });

        administratorView.addEditButtonListener(e -> {
            BaseProduct productToModify = (BaseProduct) administratorView.getSelectedMenuItem();
            if(productToModify == null){
                JOptionPane.showMessageDialog(null, "Nothing selected");
                return;
            }
            try{
                String title = administratorView.getTitle();
                productToModify.setTitle(title);
                float rating = Float.parseFloat(administratorView.getRating());
                productToModify.setRating(rating);
                int calories = Integer.parseInt(administratorView.getCalories());
                productToModify.setCalories(calories);
                int protein = Integer.parseInt(administratorView.getProtein());
                productToModify.setProtein(protein);
                int fat = Integer.parseInt(administratorView.getFat());
                productToModify.setFat(fat);
                int sodium = Integer.parseInt(administratorView.getSodium());
                productToModify.setSodium(sodium);
                int price = Integer.parseInt(administratorView.getPrice());
                productToModify.setPrice(price);

                administratorView.updateTable();
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Invalid input");
            }

        });

        administratorView.addCreateButtonListener(e -> {
            List<MenuItem> selectedMenuItems = administratorView.getSelectedMenuItems();
            if(selectedMenuItems.isEmpty()){
                JOptionPane.showMessageDialog(null, "Nothing selected");
                return;
            }
            String title = administratorView.getTitle();
            CompositeProduct compositeProduct = new CompositeProduct(title);
            for(MenuItem menuItem : selectedMenuItems){
                compositeProduct.add(menuItem);
            }
            deliveryService.add(compositeProduct);
            administratorView.updateTable();
        });

        administratorView.addReport1ButtonListener(e -> {
            try {
                FileWriter reportFile = new FileWriter("Time Report.txt");
                reportFile.write("Orders performed between the given start hour and given end hour: \n");
                int startHour = Integer.parseInt(administratorView.getStartHour());
                int endHour = Integer.parseInt(administratorView.getEndHour());
                for(Map.Entry<Order, Collection<MenuItem>> order : deliveryService.generateTimeReport(startHour, endHour)){
                    reportFile.write(order.getKey().toString());
                    reportFile.write("\n");
                    reportFile.write(order.getValue().toString());
                    reportFile.write("\n");
                }
                reportFile.close();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid start or end hour");
            } catch (IOException ioException){
                ioException.printStackTrace();
            }
        });

        administratorView.addReport2ButtonListener(e -> {
            try{
                FileWriter reportFile = new FileWriter("Product Report.txt");
                reportFile.write("The products ordered more than the specified number of times so far:\n");
                int nrOrders = Integer.parseInt(administratorView.getnrOrders());
                reportFile.write(deliveryService.generateProductReport(nrOrders).toString() + "\n");
                reportFile.close();
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Invalid input");
            }catch(IOException ex){
                ex.printStackTrace();
            }
        });

        administratorView.addReport3ButtonListener(e -> {
            try{
                FileWriter reportFile = new FileWriter("Client Report.txt");
                reportFile.write("The clients that have ordered more than the specified number of times and the value of the order was higher than the specified amount.\n");
                int nrOrders = Integer.parseInt(administratorView.getnrOrders());
                int value = Integer.parseInt(administratorView.getValue());
                reportFile.write(deliveryService.generateClientReport(nrOrders, value).toString() + "\n");
                reportFile.close();
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Invalid input");
            }catch(IOException ioException){
                ioException.printStackTrace();
            }
        });

        administratorView.addReport4ButtonListener(e -> {
            try {
                FileWriter reportFile = new FileWriter("Date Report.txt");
                reportFile.write("The products ordered within the specified day with the number of times they have been ordered:\n");
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(administratorView.getDate());
                reportFile.write(deliveryService.generateDayReport(date).toString() + "\n");
                reportFile.close();
            } catch (ParseException parseException) {
                JOptionPane.showMessageDialog(null, "Wrong date format. Correct date format: yyyy-MM-dd");
                parseException.printStackTrace();
            }catch(IOException ioException){
                ioException.printStackTrace();
            }
        });
    }

}
