package com.example.sweet_xue.hanzitopinyin;

import java.util.ArrayList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.sweet_xue.hanzitopinyin.HanziToPinyin.Token;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    private TextView textView;

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.edit);
        textView = (TextView) findViewById(R.id.tv);
        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editText.getText())) {
                    Toast.makeText(MainActivity.this, "请输入想要转换的汉子", Toast.LENGTH_SHORT).show();
                } else {
                    textView.setText(getPinYin(editText.getText().toString()));
                }
            }
        });

    }

    public static String getPinYin(String hanzi) {
        ArrayList<Token> tokens = HanziToPinyin.getInstance().get(hanzi);
        StringBuilder sb = new StringBuilder();
        if (tokens != null && tokens.size() > 0) {
            for (HanziToPinyin.Token token : tokens) {
                if (HanziToPinyin.Token.PINYIN == token.type) {
                    sb.append(token.target);
                } else {
                    sb.append(token.source);
                }
            }
        }
        return sb.toString().toUpperCase();
    }
}
