package model;

public class EnfermariaPsiquiatrica extends Enfermaria {
    private String horarioVisitas;
    private int nivelSeguranca;

    public EnfermariaPsiquiatrica(String id, int camas, String horarioVisitas, int nivelSeguranca) {
        super(id, camas);
        this.horarioVisitas = horarioVisitas;
        this.nivelSeguranca = nivelSeguranca;
    }
}
