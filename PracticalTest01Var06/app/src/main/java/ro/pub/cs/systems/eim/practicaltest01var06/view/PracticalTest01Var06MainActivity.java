package ro.pub.cs.systems.eim.practicaltest01var06.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import ro.pub.cs.systems.eim.practicaltest01var06.R;
import ro.pub.cs.systems.eim.practicaltest01var06.general.Constants;

public class PracticalTest01Var06MainActivity extends AppCompatActivity {
    private Button playBtn, navigateBtn;
    private CheckBox check1, check2, check3;
    private TextView number1, number2, number3;
    private Random random = new Random();
    private int count_checked = 0;
    private int score = 0;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            String nr1 = getRandomChar();
            String nr2 = getRandomChar();
            String nr3 = getRandomChar();

            switch (view.getId()) {
                case R.id.play_btn:
                    if (check1.isChecked() == false) {
                        number1.setText(nr1);
                    }

                    if (check2.isChecked() == false) {
                        number2.setText(nr2);
                    }

                    if (check3.isChecked() == false) {
                        number3.setText(nr3);
                    }

                    String text = "Numerele pregatite: " + nr1 + " " + nr2 + " " + nr3;
                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();

                    break;
                case R.id.navigate_btn:
                    count_checked = 0;

                    if (check1.isChecked()) {
                        count_checked++;
                    }
                    if (check2.isChecked()) {
                        count_checked++;
                    }
                    if (check3.isChecked()) {
                        count_checked++;
                    }
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var06SecondaryActivity.class);
                    intent.putExtra(Constants.DATA1, number1.getText().toString());
                    intent.putExtra(Constants.DATA2, number2.getText().toString());
                    intent.putExtra(Constants.DATA3, number3.getText().toString());
                    intent.putExtra(Constants.NUMBER_OF_CHECKS, count_checked);
                    startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);

                    break;
            }
        }
    }

    String getRandomChar() {
        int i = random.nextInt(4);
        if (i == 0) {
            return "1";
        } else if (i == 1) {
            return "2";
        } else if (i == 2) {
            return "3";
        }

        return "*";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var06_main);

        playBtn = (Button) findViewById(R.id.play_btn);
        navigateBtn = (Button) findViewById(R.id.navigate_btn);

        check1 = (CheckBox) findViewById(R.id.check1);
        check2 = (CheckBox) findViewById(R.id.check2);
        check3 = (CheckBox) findViewById(R.id.check3);
        number1 = (TextView) findViewById(R.id.number1);
        number2 = (TextView) findViewById(R.id.number2);
        number3 = (TextView) findViewById(R.id.number3);

        playBtn.setOnClickListener(buttonClickListener);
        navigateBtn.setOnClickListener(buttonClickListener);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(Constants.SAVED_SCORE)) {
                score = savedInstanceState.getInt(Constants.SAVED_SCORE);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {
            score = score + intent.getExtras().getInt(Constants.SCORE, -1);
            Toast.makeText(this, "Score " + score, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(Constants.SAVED_SCORE, score);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(Constants.SAVED_SCORE)) {
            score =  savedInstanceState.getInt(Constants.SAVED_SCORE);
        }
    }
}