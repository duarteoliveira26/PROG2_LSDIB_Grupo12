package model;
/**
 * Representa uma enfermaria geral.
 */
public class EnfermariaGeral extends Enfermaria {
    private int acompanhantes;
    private String recursos;
    /**
     * Cria uma enfermaria geral.
     *
     * @param id identificador
     * @param camas número de camas
     * @param acompanhantes número permitido de acompanhantes
     * @param recursos recursos disponíveis
     */

    public EnfermariaGeral(String id, int camas, int acompanhantes, String recursos) {
        super(id, camas);
        this.acompanhantes = acompanhantes;
        this.recursos = recursos;
    }
}




