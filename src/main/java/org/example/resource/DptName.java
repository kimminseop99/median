package org.example.resource;

public class DptName {

    public static String numToDept(int dpt_id){

        switch (dpt_id){
            case 1 :
                return "간담췌외과";
            case 2 :
                return  "신경외과";
            case 3 :
                return  "산부인과";
            case 4 :
                return  "흉부외과";
            case 5 :
                return  "소아외과";
            default:
                return "없음";
        }
    }
}
