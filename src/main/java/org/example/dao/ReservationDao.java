package org.example.dao;

import org.example.container.Container;
import org.example.db.DBConnection;

public class ReservationDao extends Dao{
    private DBConnection dbConnection;

    public ReservationDao() {
        dbConnection = Container.getDBConnection();
    }



}
