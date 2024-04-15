package org.example.dao;

import org.example.container.Container;
import org.example.db.DBConnection;
import org.example.dto.Admin;
import org.example.dto.Member;

import java.util.List;
import java.util.Map;

public class AdminDao extends Dao {
    private DBConnection dbConnection;

    public AdminDao() {
        dbConnection = Container.getDBConnection();
    }


    public Admin getAdminByLoginId(String loginId) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SELECT * "));
        sb.append(String.format("FROM admin "));
        sb.append(String.format("WHERE loginId = '%s' ", loginId));

        Map<String, Object> row = dbConnection.selectRow((sb.toString()));

        if ( row.isEmpty() ) {
            return null;
        }

        return new Admin(row);
    }

    public void StringUpdate(String Info, String changeInfo, int id) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("UPDATE admin "));
        sb.append(String.format("SET `%s` = '%s' ", Info, changeInfo));
        sb.append(String.format("WHERE id = %d ", id));


        dbConnection.update(sb.toString());
    }
}
