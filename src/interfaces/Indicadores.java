package interfaces;

import java.time.LocalDate;

public interface Indicadores {
    double taxaOcupacao(LocalDate data);
    boolean emPressao(LocalDate data);
}