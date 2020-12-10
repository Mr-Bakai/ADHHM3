package com.hfad.adhm3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 45;
    private ImageView imageView;
    private TextView textView;
    private Button btn;
    String txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        btn = findViewById(R.id.btn);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 45 && resultCode == RESULT_OK && data != null) {
            String textUri = data.getStringExtra(SecondActivity.KEY_IMAGE);
            Uri uri = Uri.parse(textUri);
            imageView.setImageURI(uri);
            txt = data.getStringExtra(SecondActivity.KEY_TEXT);
            textView.setText(txt);
        }
    }

    public void btnGmail(View view) {
        Intent intentG = new Intent(Intent.ACTION_VIEW);
        intentG.setType("message/rfc822").setData(Uri.parse("mailto:ismailov.time@gmail.com"));
        intentG.putExtra(Intent.EXTRA_EMAIL, "ismailov.time@gmail.com");
        intentG.putExtra(Intent.EXTRA_SUBJECT, "here goes subject");
        intentG.putExtra(Intent.EXTRA_TEXT, txt);
        intentG.setPackage("com.google.android.gm");
        startActivity(intentG);
    }
}
/*  Домашнее задание:
    Создаете два экрана, в первом экране должна быть ImageView,
    TextView и две кнопки , во втором экране создаете ImageView, EditText и одна кнопка

    Флоу задания:

    При нажатии на кнопку в первом экране,идет переход во второй экран,
    во втором экране вы заполняете Editext , далее кликаете по иконке
    и при нажатии на иконку должен быть переход в галерею, выбираете
    из галереи фото, возвращаетесь в экран и отправляете результат в
    первый экран c помощью кнопки, в первом экране отображете результат
    + кликаете по второй кнопке и должен быть переход в gmail с полученными
    вами текстом

    Дедлайн Среда 15:00
 */