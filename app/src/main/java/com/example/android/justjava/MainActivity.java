package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void incrementOrder(View view) {
        quantity += 1;
        displayQuantity(quantity);
    }

    public void decrementOrder(View view) {
        quantity -= 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        displayMessage(createOrderSummary(price));
        //Log.v("MainActivity", "The price is " + price);
        //Log.v("MainActivity", "Has Whipped cream " + hasWippedCream());
    }

    /**
     * @return Returns True if checked or false if not.
     */
    private boolean hasWippedCream() {
        CheckBox hasCream = (CheckBox) findViewById(R.id.whipped_cream_check);
        return hasCream.isChecked();
    }

    /**
     * Calculates the price of the order.
     *
     * @return Total Price
     */
    private int calculatePrice() {
        return quantity * 5;
    }

    /**
     * @param price Price of the Coffee
     * @return Returns the name,quantity, total and a thank you message
     */
    private String createOrderSummary(int price) {
        String orderSummary = String.format("Name: Vasilis Georgopoulos\n" +
                "Add whipped cream? %b\n" +
                "Quantity: %d\n" +
                "Total: $%d\n" +
                "Thank you!", hasWippedCream(), quantity, price);
        return orderSummary;
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }/**/

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

}
