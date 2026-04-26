package model;
/**
 * Representa uma unidade de cuidados intensivos.
 */

public class EnfermariaCuidadosIntensivos extends Enfermaria {
    private String horarioVisitas;
    private double pressao;
    private double referencia;
    /**
     * Cria uma unidade de cuidados intensivos.
     *
     * @param id identificador
     * @param camas número de camas
     * @param horarioVisitas horário de visitas
     * @param pressao pressão atmosférica
     * @param referencia pressão atmosférica de referência
     */

    public EnfermariaCuidadosIntensivos(String id, int camas, String horarioVisitas, double pressao, double referencia) {
        super(id, camas);
        this.horarioVisitas = horarioVisitas;
        this.pressao = pressao;
        this.referencia = referencia;
    }
}
