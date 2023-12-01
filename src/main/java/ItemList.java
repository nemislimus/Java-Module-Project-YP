public class ItemList {
    String itemName;
    double itemCost;

    public double calculatePersonCash(double summaryPrice, int persCount) {
        return summaryPrice / (double) persCount;
    }

    ItemList(String name, double cost) {
        itemName = name;
        itemCost = cost;
    }
}
