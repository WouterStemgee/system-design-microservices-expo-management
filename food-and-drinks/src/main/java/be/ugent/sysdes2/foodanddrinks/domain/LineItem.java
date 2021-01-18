package be.ugent.sysdes2.foodanddrinks.domain;

import org.springframework.data.annotation.Id;

public class LineItem {

    @Id
    private final String productId;
    private final int amount;

    private LineItem() {
        this.productId = "";
        this.amount = 0;
    }

    public LineItem(String productId, int amount) {
        this.productId = productId;
        this.amount = amount;
    }

    public String getProductId() {
        return productId;
    }

    public int getAmount() {
        return amount;
    }
}
