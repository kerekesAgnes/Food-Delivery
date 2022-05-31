package business;

import model.Client;
import model.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeliveryService extends Observable implements IDeliveryServiceProcessing {
    private Map<Order, Collection<MenuItem>> orders;
    private List<MenuItem> products;
    private List<User> users;

    public DeliveryService() {
        this.orders = new HashMap<>();
        this.products = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public void registerUser(User user){
        this.users.add(user);
    }

    public User login(String name, String password){
        Optional<User> user = this.users.stream().filter(
                u -> name.equals(u.getUsername()) && password.equals(u.getPassword())
        ).findFirst();
        if(user.isPresent()){
            return user.get();
        }else {
            return null;
        }
    }



    @Override
    public void importProduct(String filePath) throws IOException {
        File file = new File(filePath);
        Stream<String> productStrings = Files.lines(file.toPath()).skip(1);
        Set<BaseProduct> productSet= productStrings.map(s -> BaseProduct.stringToBaseProduct(s))
                .collect(Collectors.toSet());
        products.addAll(productSet);
    }

    @Override
    public void add(MenuItem menuItem) {
        products.add((menuItem));
    }

    @Override
    public void delete(MenuItem menuItem) {
        products.remove(menuItem);
    }


    @Override
    public List<MenuItem> list() {
        return products;
    }

    @Override
    public Set<Map.Entry<Order, Collection<MenuItem>>> generateTimeReport(int startHour, int endHour) {
       return  orders.entrySet().stream().filter(o -> {
           int hours = o.getKey().getOrderDate().getHours();
           if(startHour < hours && hours < endHour){
               return true;
           }
           return false;
        }).collect(Collectors.toSet());

    }

    @Override
    public List<MenuItem> generateProductReport(int nrOrders) {
    return orders.entrySet().stream().map(e -> e.getValue()).flatMap(l -> l.stream())
            .collect(Collectors.groupingBy(p -> p, Collectors.counting())).entrySet().stream()
            .filter(e -> e.getValue() > nrOrders).map(p -> p.getKey()).collect(Collectors.toList());
    }

    @Override
    public Collection<Client> generateClientReport(int noOrders, int value) {
        return orders.entrySet().stream().filter(u -> {
            int totalPrice = 0;
            for(MenuItem menuItem : u.getValue()){
                totalPrice += menuItem.computePrice();
            }
            return (totalPrice > value);
        }).collect(Collectors.groupingBy(o -> o.getKey().getClient(), Collectors.counting())).entrySet().stream()
        .filter(o -> o.getValue() > noOrders).map(o -> o.getKey()).collect(Collectors.toList());
    }

    @Override
    public Map<MenuItem, Long> generateDayReport(Date date) {
        return orders.entrySet().stream().filter(o -> {
            Date nextDay = DateUtil.addDays(date, 1);
            Date orderDate = o.getKey().getOrderDate();
            return orderDate.after(date) && orderDate.before(nextDay);
        }).flatMap(p -> p.getValue().stream()).collect(Collectors.groupingBy(p -> p, Collectors.counting()));
    }

    @Override
    public int createOrder(Collection<MenuItem> items, Client client) {
        Order order = new Order(client, new Date());
        orders.put(order, items);
        int totalPrice = 0;
        for(MenuItem menuItem : items){
            totalPrice += menuItem.computePrice();
        }
        this.setChanged();
        this.notifyObservers(items);
        return totalPrice;
    }
}
