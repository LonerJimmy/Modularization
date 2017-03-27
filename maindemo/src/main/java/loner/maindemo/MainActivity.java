package loner.maindemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import loner.modularization.Modularization;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DemoUtil demoUtil = new DemoUtil();
    }
}
