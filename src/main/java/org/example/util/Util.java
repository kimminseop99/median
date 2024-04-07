package org.example.util;

import java.io.*;
import java.time.LocalDateTime;

public class Util {
    // 현재 날짜 저장
    public static String getNowDateStr() {
        LocalDateTime now = LocalDateTime.now();
        return now.toString();
    }

}