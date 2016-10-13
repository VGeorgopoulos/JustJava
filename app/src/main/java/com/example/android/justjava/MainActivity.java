package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
//        String priceMessage = "Total: $" + price;
//        priceMessage += "\nThank you!";
        displayMessage(createOrderSummary(price));

    }


    /**
     * Calculates the price of the order.
     *
     * @return Total Price
     */
    private int calculatePrice() {
        int price = quantity * 5;
        return price;
    }

    /**
     * @param price Price of the Coffee
     * @return Returns the name,quantity, total and a thank you message
     */
    private String createOrderSummary(int price) {
        String orderSummary = String.format("Name: Vasilis Georgopoulos\nQuantity: %d\nTotal: $%d\nThank you!", quantity, price);
        return orderSummary;
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }/**/

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


}
