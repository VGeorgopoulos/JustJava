package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayQuantity(quantity);
    }

    public void incrementOrder(View view) {
        if (quantity < 100) {
            quantity += 1;
        } else {
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
        }

        displayQuantity(quantity);
    }

    public void decrementOrder(View view) {
        if (quantity > 2) {
            quantity = quantity - 1;
        } else {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
        }
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + enterYourName());
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Calculates the price of the order.
     *
     * @return Total Price
     */
    private int calculatePrice() {
        int basePrice = 5;
        if (hasWippedCream()) {
            basePrice += 1;
        }
        if (hasChocolate()) {
            basePrice += 2;
        }
        return quantity * basePrice;
    }

    /**
     * @return The name inside the EditText
     */
    private String enterYourName() {
        EditText yourName = (EditText) findViewById(R.id.name_field);
        return yourName.getText().toString();
    }

    /**
     * @return Returns True or False if checked for Wipped Cream
     */
    private boolean hasWippedCream() {
        CheckBox hasCream = (CheckBox) findViewById(R.id.whipped_cream_check);
        return hasCream.isChecked();
    }

    /**
     * @return True or False if checked for Chocolate
     */
    private boolean hasChocolate() {
        CheckBox hasChocolate = (CheckBox) findViewById(R.id.chocolate_check);
        return hasChocolate.isChecked();
    }

    /**
     * @return Returns the name,quantity, total and a thank you message
     */
    private String createOrderSummary() {
        String orderSummary = String.format("Name: %s\n" +
                "Add whipped cream? %b\n" +
                "Add chocolate? %b\n" +
                "Quantity: %d\n" +
                "Total: $%d\n" +
                "Thank you!", enterYourName(), hasWippedCream(), hasChocolate(), quantity, calculatePrice());
        return orderSummary;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
}
