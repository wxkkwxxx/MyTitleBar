package com.wxk.mytitlebar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CommonTitleBar titleBar = new CommonTitleBar.Builder(this)
                .setTitle("首页")
                .setRightIcon(R.mipmap.ic_launcher)
                .setRightClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "这是文章", Toast.LENGTH_SHORT).show();
                    }
                }).build();

    }
}
