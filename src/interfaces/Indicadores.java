package interfaces;

import java.time.LocalDate;
/**
 * Interface que define indicadores de ocupação de uma enfermaria.
 */

public interface Indicadores {
    /**
     * Calcula a taxa de ocupação numa data.
     *
     * @param data data de referência
     * @return taxa de ocupação em percentagem
     */
    double taxaOcupacao(LocalDate data);
    /**
     * Verifica se a enfermaria está em pressão.
     *
     * @param data data de referência
     * @return true se ocupação > 85%, false caso contrário
     */
    boolean emPressao(LocalDate data);
}
