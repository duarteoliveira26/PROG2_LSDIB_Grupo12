package model;

public class EnfermariaGeral extends Enfermaria {
    private int acompanhantes;
    private String recursos;

    public EnfermariaGeral(String id, int camas, int acompanhantes, String recursos) {
        super(id, camas);
        this.acompanhantes = acompanhantes;
        this.recursos = recursos;
    }
}


