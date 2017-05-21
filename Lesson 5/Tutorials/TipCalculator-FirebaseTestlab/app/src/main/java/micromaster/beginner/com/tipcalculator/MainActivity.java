package micromaster.beginner.com.tipcalculator;

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
    private Button buttonTip30;
    private Button buttonTip40;
    private TextView totalAmount;
    private TipCalculator tipCalculator = new TipCalculator();

    String tipCalculated;

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
        buttonTip30 = (Button) findViewById(R.id.button_tip_30);
        buttonTip40 = (Button) findViewById(R.id.button_tip_40);
        totalAmount = (TextView) findViewById(R.id.totalAmount);

        buttonTip15.setOnClickListener(this);
        buttonTip20.setOnClickListener(this);
        buttonTip30.setOnClickListener(this);
        buttonTip40.setOnClickListener(this);

        totalAmount.setText(initialTotalValue);
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
            case R.id.button_tip_30:
                calculateTip(0.30);
                break;
            case R.id.button_tip_40:
                calculateTip(0.40);
                break;
        }
    }

    public void calculateTip(double tipValue) {
        Double billAmount = tipCalculator.parseBillValue(input_billAmount.getText().toString());
        tipCalculated = tipCalculator.calculateTip(billAmount, tipValue);
        totalAmount.setText(tipCalculated);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("totalAmount", totalAmount.getText().toString());
        super.onSaveInstanceState(outState);
    }
}
