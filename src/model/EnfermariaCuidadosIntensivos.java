package model;

public class EnfermariaCuidadosIntensivos extends Enfermaria {
    private String horarioVisitas;
    private double pressao;
    private double referencia;

    public EnfermariaCuidadosIntensivos(String id, int camas, String horarioVisitas, double pressao, double referencia) {
        super(id, camas);
        this.horarioVisitas = horarioVisitas;
        this.pressao = pressao;
        this.referencia = referencia;
    }
}