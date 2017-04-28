package edu.galileo.darthenson;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.nameEditText) EditText nameEditText;
    @BindView(R.id.messageEditText) EditText messageEditText;
    @BindView(R.id.checkBox) CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void submit() {
        Intent intent = Henson.with(this)
            .gotoHelloActivity()
            .message(messageEditText.getText().toString())
            .name(nameEditText.getText().toString())
            .showToast(checkBox.isChecked())
            .build();

        startActivity(intent);
    }
}
