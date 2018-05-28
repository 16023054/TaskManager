package sg.edu.rp.c346.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Task> al;
    ListView lv;
    TaskAdapter aa;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button2);

        SQLiteHelper dbh = new SQLiteHelper(MainActivity.this);
        al = new ArrayList<Task>();
        lv = (ListView) findViewById(R.id.lv);
        aa = new TaskAdapter(this, R.layout.row, al);
        lv.setAdapter(aa);
        al.addAll(dbh.getAllSongs(""));
        dbh.close();

        final String[] txt = {""};
        for (int i = 0; i < al.size(); i++) {
            Task tmp = al.get(i);
            txt[0] += tmp + "\n";
        }
        aa.notifyDataSetChanged();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        AddActivity.class);
                i.putExtra("data", "");
                startActivityForResult(i, 9);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9){
            lv.refreshDrawableState();
        }
    }
}
