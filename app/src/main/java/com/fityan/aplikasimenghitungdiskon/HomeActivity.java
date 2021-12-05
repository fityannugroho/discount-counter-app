package com.fityan.aplikasimenghitungdiskon;

import android.os.Bundle;
import android.os.Process;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity {
    /* View Elements */
    TextInputEditText inputPrice, inputDiscountPercentage;
    Button btnCount, btnExit;
    TextView tvResultDiscounts, tvResultAfterDiscount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /* Initialize View Elements */
        inputPrice = findViewById(R.id.inputPrice);
        inputDiscountPercentage = findViewById(R.id.inputDiscountPercentage);
        btnCount = findViewById(R.id.btnCount);
        btnExit = findViewById(R.id.btnExit);
        tvResultDiscounts = findViewById(R.id.tvResultDiscounts);
        tvResultAfterDiscount = findViewById(R.id.tvResultAfterDiscount);


        /* Start counting if Count Button is clicked.  */
        btnCount.setOnClickListener(view -> {
            float price = 0, discountPercentage = 0;
            boolean validInput = true;

            /* Get price from input */
            try {
                price = getFloatFromInput(inputPrice);
            } catch (Exception e) {
                validInput = false;
                inputPrice.setError(e.getMessage());
            }

            /* Get discount percentage from input */
            try {
                discountPercentage = getFloatFromInput(inputDiscountPercentage);

                if (discountPercentage > 100) {
                    throw new NumberFormatException("Diskon tidak boleh lebih dari 100%.");
                }
            } catch (Exception e) {
                validInput = false;
                inputDiscountPercentage.setError(e.getMessage());
            }

            /* Count the discount if inputs are valid. */
            if (validInput) {
                float discount = price * (discountPercentage / 100);
                float priceAfterDiscount = price - discount;

                /* Displaying the result */
                displayDiscounts(discount);
                displayPriceAfterDiscounts(priceAfterDiscount);
            }
        });


        /* Close the app if Exit Button is clicked. */
        btnExit.setOnClickListener(view -> {
            moveTaskToBack(true);
            Process.killProcess(Process.myPid());
            System.exit(1);
        });
    }


    /**
     * Get float number from an field.
     * @param input The input.
     * @return The value of input in float.
     */
    private float getFloatFromInput(TextInputEditText input) {
        String value = Objects.requireNonNull(
                input.getText()).toString();

        if (value.isEmpty()) {
            throw new NullPointerException("Masukan angka diperlukan.");
        }

        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Hanya masukan angka yang diperbolehkan.");
        }
    }


    /**
     * Displaying the discounts.
     * @param discounts The discounts in float.
     */
    private void displayDiscounts(float discounts) {
        String result = "Rp" + idnNumberFormat(Math.round(discounts));
        tvResultDiscounts.setText(result);
    }


    /**
     * Displaying the price after discounts.
     * @param price The new price after discounts.
     */
    private void displayPriceAfterDiscounts(float price) {
        /* Displaying the price */
        String result = "Rp" + idnNumberFormat(Math.round(price));
        tvResultAfterDiscount.setText(result);
    }


    /**
     * Convert float to string with with indonesian number formatting.
     * @param number The number to convert.
     * @return The converted numbers.
     */
    private String idnNumberFormat(float number) {
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance();
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();

        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        formatter.setDecimalFormatSymbols(symbols);

        return formatter.format(number);
    }
}
