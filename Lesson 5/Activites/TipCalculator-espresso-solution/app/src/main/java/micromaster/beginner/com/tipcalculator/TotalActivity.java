package micromaster.beginner.com.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TotalActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total);

        String totalExtra = getIntent().getExtras().getString("totalExtra");

        textView = (TextView) findViewById(R.id.totalTextView);
        button = (Button) findViewById(R.id.buttonOk);

        textView.setText("The total is: " + totalExtra);

        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
