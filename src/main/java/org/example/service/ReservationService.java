package org.example.service;

import org.example.container.Container;
import org.example.dao.MemberDao;
import org.example.dao.ReservationDao;
import org.example.dto.Member;

public class ReservationService {
    private ReservationDao reservationDao;

    public ReservationService() {
        reservationDao = Container.reservationDao;
    }

}