package org.example.resource;

import java.util.HashMap;
import java.util.Map;

public class DoctorName {

    private static final Map<String, String> doctorMap = new HashMap<>();

    static {
        doctorMap.put("1-1", "이익준");
        doctorMap.put("1-2", "장겨울");
        doctorMap.put("2-1", "채송화");
        doctorMap.put("2-2", "윤석민");
        doctorMap.put("3-1", "양석형");
        doctorMap.put("3-2", "추민하");
        doctorMap.put("4-1", "김준완");
        doctorMap.put("4-2", "도재학");
        doctorMap.put("5-1", "안정원");
        doctorMap.put("5-2", "한현희");
    }

    public static String numToDoctor(int dpt_id, int doctor_id) {
        String key = dpt_id + "-" + doctor_id;
        return doctorMap.getOrDefault(key, "없음");
    }
}
