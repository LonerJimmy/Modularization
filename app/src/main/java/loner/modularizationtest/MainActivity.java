package loner.modularizationtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import loner.modularization.Modularization;


public class MainActivity extends AppCompatActivity {

    private TextView helloTextView;
    private String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helloTextView = (TextView) findViewById(R.id.hello);

        msg = (String) (Modularization.excute("loner", "isBlank", String.class));

        helloTextView.setText(msg + "");
    }
}
