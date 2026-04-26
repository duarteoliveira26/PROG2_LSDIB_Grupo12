package model;
/**
 * Representa uma enfermaria psiquiátrica.
 */

public class EnfermariaPsiquiatrica extends Enfermaria {
    private String horarioVisitas;
    private int nivelSeguranca;
    /**
     * Cria uma enfermaria psiquiátrica.
     *
     * @param id identificador
     * @param camas número de camas
     * @param horarioVisitas horário de visitas
     * @param nivelSeguranca nível de segurança
     */

    public EnfermariaPsiquiatrica(String id, int camas, String horarioVisitas, int nivelSeguranca) {
        super(id, camas);
        this.horarioVisitas = horarioVisitas;
        this.nivelSeguranca = nivelSeguranca;
    }
}
