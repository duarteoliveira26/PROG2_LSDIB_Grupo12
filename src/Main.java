import model.*;
import utils.CSVReader;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) throws Exception {
        Hospital hospital = new Hospital("Hospital XYZ");
        CSVReader.carregarEnfermarias("src/enfermarias.csv", hospital);
        CSVReader.carregarEpisodios("src/episodios.csv", hospital);
        LocalDate hoje = LocalDate.of(2026, 4, 8);
        LocalDate inicioRelatorio = LocalDate.of(2026, 4, 1);
        LocalDate fimRelatorio = LocalDate.of(2026, 4, 10);
        hospital.ordenarPorTaxa(hoje);
        System.out.println("\n>>> RELATÓRIO DIÁRIO DE OCUPAÇÃO (" + hoje + ") <<<");
        System.out.println("===========================================================================");
        System.out.printf("%-5s | %-15s | %-10s | %-12s | %-10s\n", "ID", "TIPO", "OCUPAÇÃO", "ESTADO", "DUR. MÉDIA");
        System.out.println("---------------------------------------------------------------------------");

        for (Enfermaria e : hospital.getEnfermarias()) {
            String estado = e.emPressao(hoje) ? "!! PRESSÃO !!" : "NORMAL";
            System.out.printf("%-5s | %-15s | %6.1f%%    | %-12s | %6.1f dias\n",
                    e.getId(),
                    e.getClass().getSimpleName().replace("Enfermaria", ""),
                    e.taxaOcupacao(hoje),
                    estado,
                    e.mediaLoS());
        }

        System.out.println("\n>>> ANÁLISE DE PERFORMANCE (Período: " + inicioRelatorio + " a " + fimRelatorio + ") <<<");
        System.out.println("===========================================================================");

        for (Enfermaria e : hospital.getEnfermarias()) {
            System.out.println("ENFERMARIA: " + e.getId());
            System.out.printf("  > LoS: [Mín: %d | Máx: %d | Média: %.1f | Desvio Padrão: %.2f]\n",
                    e.minLoS(), e.maxLoS(), e.mediaLoS(), e.desvioPadrao());
            double percPressao = e.percentagemPressao(inicioRelatorio, fimRelatorio);
            System.out.printf("  > Pressão no período: %.1f%% dos dias.\n", percPressao);
            System.out.println("---------------------------------------------------------------------------");
        }

        System.out.println("\n[INFO] Verifique o ficheiro 'erros.log' para dados de entrada inválidos.");
    }
}
