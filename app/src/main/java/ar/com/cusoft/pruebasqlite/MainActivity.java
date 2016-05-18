package ar.com.cusoft.pruebasqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView clientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clientes = (ListView) findViewById(R.id.listViewClientes);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_new_client:
                startActivity(new Intent(MainActivity.this, ClientDetailsActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        ArrayList<String> names = new ArrayList<>();

        //Con Sugar ORM
        List<Client> clients = Client.listAll(Client.class);

        //Con SQLiteOpenHelper
//        DatabaseHelper db = DatabaseHelper.getInstance(this);
//        List<Client> clients = db.getAllClientes();

        for (int i = 0; i < clients.size(); i++) {
            names.add(clients.get(i).getName());
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, names);
        clientes.setAdapter(adapter);

        super.onResume();
    }
}
