package presentation;

import business.IDeliveryServiceProcessing;
import model.Client;
import model.Role;
import model.User;

import javax.swing.*;

public class RegistrationController {
    private RegistrationView registrationView;
    private IDeliveryServiceProcessing deliveryService;

    public RegistrationController(RegistrationView registrationView, IDeliveryServiceProcessing deliveryService) {
        this.registrationView = registrationView;
        this.deliveryService = deliveryService;

        registrationView.addRegisterButtonListener(e -> {
            if(registrationView.getUsername().equals("") || registrationView.getPassword().equals("")){
                JOptionPane.showMessageDialog(null, "No user");
                return;
            }
            User user = new Client(registrationView.getUsername(), registrationView.getPassword(), Role.CLIENT);
            deliveryService.registerUser(user);
        });
    }
}
