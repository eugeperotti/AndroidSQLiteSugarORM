package ar.com.cusoft.pruebasqlite;

import com.orm.SugarContext;
import com.orm.SugarApp;
import com.orm.SugarDb;
import com.orm.SugarRecord;
import com.orm.SugarTransactionHelper;
import com.orm.dsl.Table;

@Table
public class Client {
    private Long id;
    private String name;

    public Client(){

    }

    public Client(String name){
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
