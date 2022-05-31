package start;

import business.DeliveryService;
import business.IDeliveryServiceProcessing;
import model.Role;
import model.User;
import presentation.*;

public class Start {
    public static void main(String[] args) {

        IDeliveryServiceProcessing deliveryService = new DeliveryService();
        User user = new User("Agnes", "agi", Role.ADMIN);
        deliveryService.registerUser(user);
        LoginView loginView = new LoginView();
        loginView.setVisible(true);
        LoginController loginController = new LoginController(loginView, deliveryService);
        RegistrationView registrationView = new RegistrationView();
        RegistrationController registrationController = new RegistrationController(registrationView, deliveryService);
        registrationView.setVisible(true);
        EmployeeController employeeController = new EmployeeController((DeliveryService) deliveryService);
    }


}
