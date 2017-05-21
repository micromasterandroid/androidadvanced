package micromaster.beginner.com.tipcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText input_billAmount;
    private Button buttonTip15;
    private Button buttonTip20;
    private TextView totalAmount;
    private Button viewTotalButton;

    private Double tipDoubleValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String initialTotalValue = "";
        if (savedInstanceState != null) {
            initialTotalValue = savedInstanceState.getString("totalAmount");
        }

        input_billAmount = (EditText) findViewById(R.id.input_billAmount);
        buttonTip15 = (Button) findViewById(R.id.button_tip_15);
        buttonTip20 = (Button) findViewById(R.id.button_tip_20);
        totalAmount = (TextView) findViewById(R.id.totalAmount);
        viewTotalButton = (Button) findViewById(R.id.viewTotalButton);

        buttonTip15.setOnClickListener(this);
        buttonTip20.setOnClickListener(this);

        totalAmount.setText(initialTotalValue);

        viewTotalButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_tip_15:
                calculateTip(0.15);
                break;
            case R.id.button_tip_20:
                calculateTip(0.20);
                break;
            case R.id.viewTotalButton:
                goToViewTotal();
                break;
        }
    }

    private void goToViewTotal() {
        Intent intent = new Intent(this, TotalActivity.class);
        intent.putExtra("totalExtra", String.valueOf(tipDoubleValue));

        startActivity(intent);
    }

    private void calculateTip(double tipValue) {
        tipDoubleValue = parseTip(input_billAmount.getText().toString());
        if (tipDoubleValue != null) {
            tipDoubleValue += tipDoubleValue * tipValue;
            totalAmount.setText(tipDoubleValue.toString());
        }
    }

    public static Double parseTip(String tipValue) {
        try {
            return Double.parseDouble(tipValue);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("totalAmount", totalAmount.getText().toString());
        super.onSaveInstanceState(outState);
    }
}
