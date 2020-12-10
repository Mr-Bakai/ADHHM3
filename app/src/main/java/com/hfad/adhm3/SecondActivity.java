package com.hfad.adhm3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    private EditText etText;
    private ImageView imageView;
    private Button btn;

    public static final int REQUEST_GALLERY = 45;
    public static final String KEY_IMAGE = "image";
    public static final String KEY_TEXT = "text";
    protected String textUri;

    private static final Intent intent = new Intent(Intent.ACTION_GET_CONTENT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        init();
    }

    private void init() {
        etText = findViewById(R.id.textView2);
        imageView = findViewById(R.id.imageView);
        btn = findViewById(R.id.btn2);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_GALLERY);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String etTextS = etText.getText().toString();
                intent.putExtra(KEY_TEXT, etTextS);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            textUri = String.valueOf(uri);
            imageView.setImageURI(uri);
        } else {
            Toast.makeText(this, "pick an image to proceed", Toast.LENGTH_SHORT).show();
        }
    }
}