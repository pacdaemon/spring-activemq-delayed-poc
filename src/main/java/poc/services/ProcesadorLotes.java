package poc.services;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import poc.models.Lote;

@Component
public class ProcesadorLotes {
    @JmsListener(destination = "lotes", containerFactory = "myFactory")
    public void receiveMessage(Lote lote) {
        System.out.println(String.format("Proceso lote: %d", lote.getNroLote()));
    }
}
