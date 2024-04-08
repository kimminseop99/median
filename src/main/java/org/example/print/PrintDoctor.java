package org.example.print;

import org.example.dto.Doctor;
import org.example.dto.Dpt;
import org.example.service.DptService;

import java.util.List;

import static org.example.container.Container.doctorService;


public class PrintDoctor {



    public void doctorPage(int dpt){

        List<Doctor> forPrintDoctors = doctorService.getForPrintDoctors(dpt);

        if (forPrintDoctors.isEmpty()) {
            System.out.println("의사가 공백입니다.");
            return;
        }

        System.out.println("의사 | 진료과");
        for ( int i = forPrintDoctors.size() - 1; i >= 0 ; i-- ) {
            Doctor doctor = forPrintDoctors.get(i);
            Dpt dpt_id = DptService.getDpt(doctor.dpt_id);

            System.out.printf("%4s | %5s \n", doctor.name, dpt_id.name);
        }
        if(dpt == 1){

        } else if (dpt == 2) {

        } else if (dpt == 3) {

        } else if (dpt == 4) {

        } else{

        }
    }
}
