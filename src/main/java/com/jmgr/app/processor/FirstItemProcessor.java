package com.jmgr.app.processor;

import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class FirstItemProcessor implements ItemProcessor<Integer, String> {

    @Override
    public String process(Integer item) throws Exception {
        System.out.println("Dentro del ItemProcessor: " + item);
        //Retornar el nombre del día de la semana correspondiente al número
        String dayName = getDayName(item);
        return "Día: " + dayName;
    }

    private String getDayName(Integer item) {
        switch (item) {
            case 1:
                return "Lunes";
            case 2:
                return "Martes";
            case 3:
                return "Miércoles";
            case 4:
                return "Jueves";
            case 5:
                return "Viernes";
            case 6:
                return "Sábado";
            case 7:
                return "Domingo";
            default:
                return "Día no válido";
        }
    }

}
