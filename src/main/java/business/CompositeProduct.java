package business;

import java.util.ArrayList;
import java.util.List;

public class CompositeProduct extends MenuItem{
    private List<MenuItem> compositeProduct;

    public CompositeProduct(String title) {
        super(title);
        this.compositeProduct = new ArrayList<>();
    }

    public void add(MenuItem menuItem){
        compositeProduct.add(menuItem);
    }

    @Override
    public int computePrice(){
        int price = 0;
        for(MenuItem menuItem : compositeProduct){
            price += menuItem.computePrice();
        }
        return price;
    }

    @Override
    public String toString() {
        return "CP{" +
                 title + "=" + compositeProduct +
                '}';
    }
}
