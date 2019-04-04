package com.project.naveen.chess10x10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.project.naveen.chess10x10.chess10x10camel.chess10x10camel;
import com.project.naveen.chess10x10.chess10x10empty.chess10x10;
import com.project.naveen.chess10x10.chess8x8.chess;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        Intent intent;
        switch (v.getId()) {
            case R.id.btnChess8x8:
                intent = new Intent(this,chess.class);
                /*
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                Bundle extras = new Bundle();
                extras.putString("userId",userId);
                intent.putExtras(extras);
                */
                startActivity(intent);
                break;
            case R.id.btnChess10x10:
                intent = new Intent(this,chess10x10.class);
                startActivity(intent);
                break;

            case R.id.btnChess10x10Camel:
                intent = new Intent(this,chess10x10camel.class);
                startActivity(intent);
                break;
        }
    }
}
