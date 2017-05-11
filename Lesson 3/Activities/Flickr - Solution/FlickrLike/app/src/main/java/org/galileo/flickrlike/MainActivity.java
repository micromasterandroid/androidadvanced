package org.galileo.flickrlike;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final String TAGS_EXTRA = "TAGS_EXTRA";

    @BindView(R.id.search_editText) EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.search_button)
    public void goToSearch() {
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra(TAGS_EXTRA, searchEditText.getText().toString());
        startActivity(intent);
    }

    @OnClick(R.id.viewPhotos_button)
    public void goToViewPhotos() {
        Intent intent = new Intent(this, MyPhotosActivity.class);
        startActivity(intent);
    }
}
