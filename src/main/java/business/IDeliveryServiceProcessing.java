package business;

import model.Client;
import model.User;

import java.io.IOException;
import java.util.*;

public interface IDeliveryServiceProcessing {

    void importProduct(String filePath) throws IOException;
    void add(MenuItem menuItem);
    void delete(MenuItem menuItem);
    void registerUser(User user);
    User login(String name, String password);
    List<MenuItem> list();
    Set<Map.Entry<Order, Collection<MenuItem>>> generateTimeReport(int startHour, int endHour);
    Collection<MenuItem> generateProductReport(int nrOrders);
    Collection<Client> generateClientReport(int noOrders, int value);
    Map<MenuItem, Long> generateDayReport(Date date);

    int createOrder(Collection<MenuItem> items, Client client);

}
