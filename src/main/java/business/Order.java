package business;

import model.Client;

import java.util.Date;
import java.util.Objects;

public class Order {
    private int orderID;
    private Client client;
    private Date orderDate;
    private static int currentOrderId = 0;

    public Order(Client client, Date orderDate) {
        this.orderID = currentOrderId;
        this.client = client;
        this.orderDate = orderDate;
        currentOrderId++;
    }

    public int getOrderID() {
        return orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderID == order.orderID && client == order.client && orderDate.equals(order.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, client, orderDate);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", client=" + client +
                ", orderDate=" + orderDate +
                '}';
    }


}
