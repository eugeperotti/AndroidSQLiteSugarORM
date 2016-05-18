package ar.com.cusoft.pruebasqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.orm.SugarContext;
import com.orm.SugarApp;
import com.orm.SugarDb;
import com.orm.SugarRecord;
import com.orm.SugarTransactionHelper;

public class ClientDetailsActivity extends AppCompatActivity {

    EditText editTextNombre;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_details);

        editTextNombre = (EditText) findViewById(R.id.editTextNombre);
        btnGuardar = (Button) findViewById(R.id.buttonGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveClient();
            }
        });
    }

    private void saveClient() {

        Client newClient = new Client();

        //Con Sugar ORM
        newClient.setName(editTextNombre.getText().toString());
        newClient.save();

        //Con SQLiteOpenHelper
//        DatabaseHelper db = DatabaseHelper.getInstance(this);
//        db.insertClient(newClient);

        finish();
    }

    /**
     *
     * Save Entity:
     * Book book = new Book("Title here", "2nd edition")
        book.save();
     Load Entity:
        Book book = Book.findById(Book.class, 1);
     Update Entity:
         Book book = Book.findById(Book.class, 1);
         book.title = "updated title here"; // modify the values
         book.edition = "3rd edition";
         book.save(); // updates the previous entry with new values.
     Delete Entity:
         Book book = Book.findById(Book.class, 1);
         book.delete();
     Bulk Operations:
     List<Book> books = Book.listAll(Book.class);

     Book.deleteAll(Book.class);
     */

    /**
     * package com.sodiq.example1;

     import android.os.Bundle;
     import android.support.v7.app.AppCompatActivity;
     import android.support.v7.widget.Toolbar;

     import com.orm.SugarRecord;
     import com.orm.query.Condition;
     import com.orm.query.Select;
     import com.sodiq.example1.models.Contact;

     import java.util.List;

     public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    //save
    Contact contact = new Contact("Sod", "+6285000000");
    contact.save();

    Contact contact1 = new Contact("Diq", "+6285000001");
    contact1.save();

    Contact contact2 = new Contact("Sodiq", "+6285000003");
    contact2.save();

    //update
    contact2.setPhone("+6285000002");
    contact2.save();

    //load all
    List<Contact> contacts = SugarRecord.listAll(Contact.class);

    //find by id, id = 2
    Contact contact4 = SugarRecord.findById(Contact.class, (long) 2);

    //find with where clause and the arguments
    List<Contact> contacts1 = SugarRecord.find(Contact.class, "name = ?", "Sodiq");

    //find with custom query
    List<Contact> contacts2 = SugarRecord.findWithQuery(Contact.class, "SELECT * FROM Contact WHERE name = ?", "Sodiq");

    //find with query builder
    List<Contact> contacts3 = Select.from(Contact.class).where(Condition.prop("name").eq("Sodiq")).list();

    //delete by id, id = 3
    Contact contact5 = SugarRecord.findById(Contact.class, (long) 3);
    contact5.delete();

    //delete all
    SugarRecord.deleteAll(Contact.class);
    }
    }
     */
}
