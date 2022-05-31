package presentation;

import business.DeliveryService;
import business.IDeliveryServiceProcessing;
import model.Client;
import model.Role;
import model.User;

import javax.swing.*;

public class LoginController {
    private LoginView loginView;
    private IDeliveryServiceProcessing deliveryService;

    public LoginController(LoginView loginView, IDeliveryServiceProcessing deliveryService){
        this.loginView = loginView;
        this.deliveryService = deliveryService;

        this.loginView.addLoginButtonListener(e -> {
           User user =  deliveryService.login(loginView.getUsername(), loginView.getPassword());
           if(user == null){
               JOptionPane.showMessageDialog(null, "Invalid credentials");
               return;
           }
           if(user.getRole() == Role.ADMIN){
               AdministratorView administratorView = new AdministratorView(deliveryService);
               AdministratorController administratorController = new AdministratorController(administratorView, user, deliveryService);
           }
           if(user.getRole() == Role.CLIENT){
               ClientView clientView = new ClientView(deliveryService);
               ClientController clientController = new ClientController(clientView, (Client) user, deliveryService);
           }
        });
    }
}
