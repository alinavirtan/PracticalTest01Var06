package ro.pub.cs.systems.eim.practicaltest01var06.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ro.pub.cs.systems.eim.practicaltest01var06.R;
import ro.pub.cs.systems.eim.practicaltest01var06.general.Constants;

import androidx.appcompat.app.AppCompatActivity;

public class PracticalTest01Var06SecondaryActivity extends AppCompatActivity {
    private Button okBtn;
    private EditText gainedEditText;
    private int score = 0;
    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ok_btn:
                    Intent parentIntent = new Intent();
                    parentIntent.putExtra(Constants.SCORE, score);
                    setResult(RESULT_OK, parentIntent);
            }
            finish();
        }
    }

    boolean checkEquality(String a, String b, String c) {
        if (a.equals("*")) {
            if (b.equals("*") || c.equals("*")) {
                return true;
            }

            return b.equals(c);
        }

        if (b.equals("*")) {
            if (a.equals("*") || c.equals("*")) {
                return true;
            }

            return a.equals(c);
        }

        if (c.equals("*")) {
            if (a.equals("*") || b.equals("*")) {
                return true;
            }

            return a.equals(b);
        }

        if (a.equals(b) && a.equals(c)) {
            return true;
        }

        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test_01_var06_second);

        gainedEditText = (EditText) findViewById(R.id.gained);
        okBtn = (Button) findViewById(R.id.ok_btn);
        okBtn.setOnClickListener(buttonClickListener);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey(Constants.NUMBER_OF_CHECKS)
                && intent.getExtras().containsKey(Constants.DATA1)
                && intent.getExtras().containsKey(Constants.DATA2)
                && intent.getExtras().containsKey(Constants.DATA3)) {
            int count_checks = intent.getIntExtra(Constants.NUMBER_OF_CHECKS, 0);
            String nr1 = intent.getStringExtra(Constants.DATA1);
            String nr2 = intent.getStringExtra(Constants.DATA2);
            String nr3 = intent.getStringExtra(Constants.DATA3);

            Toast.makeText(getApplicationContext(), nr1 + " " + nr2 + " " + nr3 + " "+ count_checks, Toast.LENGTH_LONG).show();

            if (checkEquality(nr1, nr2, nr3) == true) {

                if (count_checks == 0) {
                    score = 100;
                } else if (count_checks == 1) {
                    score = 50;
                } else if (count_checks == 2) {
                    score = 10;
                }

                gainedEditText.setText("Gained " + score);
            } else {
                gainedEditText.setText("Not Gained ");
            }
        }
    }
}