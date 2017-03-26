package loner.modularizationtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.loner.reflect.InitModularization;

public class MainActivity extends AppCompatActivity {

    private TextView helloTextView;
    private boolean msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helloTextView = (TextView) findViewById(R.id.hello);

        msg = (boolean) (InitModularization.excute("demo", "isBlank", String.class));

        helloTextView.setText(msg + "");
    }
}
