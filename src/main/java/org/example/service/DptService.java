package org.example.service;

import org.example.container.Container;
import org.example.dao.DptDao;
import org.example.dto.Dpt;
import org.example.dto.Member;

import java.util.List;

public class DptService {
    private static DptDao dptDao;

    public DptService() {
        dptDao = Container.dptDao;
    }

    public int doDpt(String name, String phone) {
        Dpt dpt = new Dpt(name, phone);
        return dptDao.doDpt(dpt);
    }

    public static Dpt getDpt(int id)
    {
        return dptDao.getDpt(id);
    }




}