package edu.galileo.darthenson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HelloActivity extends AppCompatActivity {

    @InjectExtra String name;
    @InjectExtra String message;
    @InjectExtra boolean showToast;

    @BindView(R.id.textView2) TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        ButterKnife.bind(this);
        Dart.inject(this);

        textView.setText("YOUR NAME IS: " + name + "\n YOUR MESSAGE:" + message);

        if (showToast) {
            Toast.makeText(this, "SHOW TOAST WAS SELECTED", Toast.LENGTH_SHORT).show();
        }
    }
}
