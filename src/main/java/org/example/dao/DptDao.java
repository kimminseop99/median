package org.example.dao;

import org.example.container.Container;
import org.example.db.DBConnection;
import org.example.dto.Dpt;
import org.example.dto.Member;
import org.example.dto.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DptDao extends Dao{
    private DBConnection dbConnection;

    public DptDao() {
        dbConnection = Container.getDBConnection();
    }

    public int doDpt(Dpt dpt) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("INSERT INTO dpt "));
        sb.append(String.format("SET `name` = '%s', ", dpt.name));
        sb.append(String.format("phone = '%s' ", dpt.phone));


        return dbConnection.insert(sb.toString());
    }

    public List<Dpt> getDpt() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SELECT * FROM dpt"));

        List<Dpt> dpts = new ArrayList<>();
        List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

        for ( Map<String, Object> row : rows ) {
            dpts.add(new Dpt((row)));
        }

        return dpts;
    }

    public Dpt getDpt(int id) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SELECT * "));
        sb.append(String.format("FROM dpt "));
        sb.append(String.format("WHERE id = %d ", id));

        Map<String, Object> row = dbConnection.selectRow((sb.toString()));

        if ( row.isEmpty() ) {
            return null;
        }

        return new Dpt(row);
    }
}
