package presentation;

import business.DeliveryService;

import java.util.Observable;
import java.util.Observer;

public class EmployeeController implements Observer {
    private EmployeeView employeeView;

    public EmployeeController(DeliveryService deliveryService) {
        deliveryService.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        employeeView = new EmployeeView(arg.toString());
    }
}
