package business;

import java.util.Objects;

public class BaseProduct extends MenuItem {
    private float rating;
    private int calories;
    private int protein;
    private int  fat;
    private int sodium;
    private int price;

    public BaseProduct(String title, float rating, int calories, int protein, int fat, int sodium, int price) {
        super(title);
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getSodium() {
        return sodium;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static BaseProduct stringToBaseProduct(String str){
        String[] list = str.split(",");
        String title = list[0];
        float rating = Float.parseFloat(list[1]);
        int calories = Integer.parseInt(list[2]);
        int protein = Integer.parseInt(list[3]);
        int fat = Integer.parseInt(list[4]);
        int sodium = Integer.parseInt(list[5]);
        int price = Integer.parseInt(list[6]);
        BaseProduct baseProduct = new BaseProduct(title, rating, calories, protein, fat, sodium, price);
        return baseProduct;
    }

    @Override
    public String toString() {
        return "BP{" +
                "title='" + getTitle() + '\'' +
                ", rating=" + rating +
                ", calories=" + calories +
                ", protein=" + protein +
                ", fat=" + fat +
                ", sodium=" + sodium +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BaseProduct that = (BaseProduct) o;
        return Float.compare(that.rating, rating) == 0 && calories == that.calories && protein == that.protein && fat == that.fat && sodium == that.sodium && price == that.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rating, calories, protein, fat, sodium, price);
    }

    @Override
    public int computePrice(){
        return this.price;
    }
}
