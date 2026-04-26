package utils;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;
import model.Enfermaria;
import model.EnfermariaCuidadosIntensivos;
import model.EnfermariaGeral;
import model.EnfermariaPsiquiatrica;
import model.Episodio;
import model.Hospital;

/**
 * Classe utilitária para leitura de ficheiros CSV e geração de logs.
 */
public class CSVReader {
    /**
     * Carrega as enfermarias a partir de um ficheiro CSV.
     * O formato esperado: TIPO,ID,CAMAS,EXTRA1,EXTRA2,EXTRA3
     *
     * @param caminho caminho para o ficheiro CSV
     * @param h hospital onde as enfermarias serão adicionadas
     * @throws Exception se ocorrer erro na leitura do ficheiro
     */
    public static void carregarEnfermarias(String caminho, Hospital h) throws Exception {
        File ficheiro = new File(caminho);
        if (ficheiro.exists()) {
            Scanner sc = new Scanner(ficheiro);
            if (sc.hasNextLine()) {
                sc.nextLine();
            }

            while(sc.hasNextLine()) {
                String linha = sc.nextLine();
                if (!linha.trim().isEmpty()) {
                    String[] p = linha.split(",");
                    String tipo = p[0].trim();
                    String id = p[1].trim();
                    int camas = Integer.parseInt(p[2].trim());
                    if (tipo.equalsIgnoreCase("GERAL")) {
                        h.addEnfermaria(new EnfermariaGeral(id, camas, Integer.parseInt(p[3].trim()), p[4].trim()));
                    } else if (tipo.equalsIgnoreCase("PSIQ")) {
                        h.addEnfermaria(new EnfermariaPsiquiatrica(id, camas, p[3].trim(), Integer.parseInt(p[4].trim())));
                    } else if (tipo.equalsIgnoreCase("UCI")) {
                        h.addEnfermaria(new EnfermariaCuidadosIntensivos(id, camas, p[3].trim(), Double.parseDouble(p[4].trim()), Double.parseDouble(p[5].trim())));
                    }
                }
            }

            sc.close();
        }
    }

    /**
     * Carrega os episódios e associa-os à enfermaria correta.
     * Regista erros em 'erros.log' se a enfermaria não existir ou os dados forem inválidos.
     *
     * @param caminho caminho para o ficheiro CSV
     * @param h hospital onde os episódios serão adicionados
     * @throws Exception se ocorrer erro na leitura do ficheiro
     */
    public static void carregarEpisodios(String caminho, Hospital h) throws Exception {
        File ficheiro = new File(caminho);
        if (ficheiro.exists()) {
            Scanner sc = new Scanner(ficheiro);
            PrintWriter log = new PrintWriter(new File("erros.log"));
            if (sc.hasNextLine()) {
                sc.nextLine();
            }

            while(sc.hasNextLine()) {
                String linha = sc.nextLine();
                if (!linha.trim().isEmpty()) {
                    String[] p = linha.split(",");
                    if (p.length < 3) {
                        log.println("ERRO: Colunas insuficientes -> " + linha);
                    } else {
                        String idEnf = p[0].trim();
                        String camaStr = p[1].trim();
                        String dataStr = p[2].trim();
                        boolean camaOk = true;
                        if (camaStr.isEmpty()) {
                            camaOk = false;
                        }

                        for(char c : camaStr.toCharArray()) {
                            if (c < '0' || c > '9') {
                                camaOk = false;
                            }
                        }

                        boolean dataOk = dataStr.length() == 10;
                        if (camaOk && dataOk) {
                            Enfermaria enf = h.getEnfermariaPorId(idEnf);
                            if (enf != null) {
                                int camaId = Integer.parseInt(camaStr);
                                LocalDate adm = LocalDate.parse(dataStr);
                                LocalDate alta = null;
                                if (p.length > 3 && p[3].trim().length() == 10) {
                                    alta = LocalDate.parse(p[3].trim());
                                }

                                enf.addEpisodio(new Episodio(camaId, adm, alta));
                            } else {
                                log.println("ERRO: Enfermaria não encontrada -> " + idEnf);
                            }
                        } else {
                            log.println("ERRO: Dados inválidos (Cama ou Data) -> " + linha);
                        }
                    }
                }
            }

            sc.close();
            log.close();
        }
    }
}
