package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Representa um episódio de internamento.
 */
public class Episodio {

    private int camaId;
    private LocalDate admissao;
    private LocalDate alta;

    /**
     * Cria um episódio de internamento.
     *
     * @param camaId identificador da cama
     * @param admissao data de admissão
     * @param alta data de alta
     */
    public Episodio(int camaId, LocalDate admissao, LocalDate alta) {
        this.camaId = camaId;
        this.admissao = admissao;
        this.alta = alta;
    }
    /**
     * Verifica se o episódio tem alta.
     *
     * @return true se tiver alta
     */
    public boolean temAlta() {
        return alta != null;
    }

    /**
     * Calcula o Length of Stay.
     *
     * @return número de dias internado
     */
    public long getLoS() {
        if (alta == null) return 0;
        return ChronoUnit.DAYS.between(admissao, alta);
    }
    /**
     * Verifica se o episódio está ativo numa data.
     *
     * @param data data analisada
     * @return true se estiver ativo
     */
    public boolean ativo(LocalDate data) {
        if (alta == null)
            return !data.isBefore(admissao);

        return !data.isBefore(admissao) && !data.isAfter(alta);
    }

    /**
     * Devolve a data de admissão.
     *
     * @return data de admissão
     */
    public LocalDate getAdmissao() {
        return admissao;
    }
}