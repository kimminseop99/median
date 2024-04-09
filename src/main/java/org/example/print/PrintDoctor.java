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
            Dpt dptObj = DptService.getDpt(doctor.getDptId());

            System.out.printf("%4s | %5s \n", doctor.getName(), dptObj.getName());
        }

    }
}
