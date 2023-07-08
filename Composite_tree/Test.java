import java.io.File;

public class Test {
    private static int niveau;

    public static void afficherArborescence(File dossier, boolean estDernier) {
        System.out.print(getIndentation(niveau));

        if (estDernier) {
            System.out.print("└── ");
        } else {
            System.out.print("├── ");
        }

        System.out.println(dossier.getName());

        niveau++;

        File[] liste = dossier.listFiles();
        if (liste != null) {
            for (int i = 0; i < liste.length; i++) {
                File fichier = liste[i];
                boolean dernierFichier = (i == liste.length - 1);

                if (fichier.isDirectory()) {
                    afficherArborescence(fichier, dernierFichier);
                } else if (fichier.isFile()) {
                    afficherFichier(fichier, dernierFichier);
                }
            }
        }

        niveau--;
    }

    public static void afficherFichier(File fichier, boolean estDernier) {
        System.out.print(getIndentation(niveau));

        if (estDernier) {
            System.out.print("└── ");
        } else {
            System.out.print("├── ");
        }

        System.out.println(fichier.getName());
    }

    public static String getIndentation(int niveau) {
        StringBuilder indentation = new StringBuilder();
        for (int i = 0; i < niveau; i++) {
            indentation.append("│   ");
        }
        return indentation.toString();
    }

    public static void main(String[] args) {
        String path =  String.join(" ", args); // Prendre en compte le répertoires où leur nom contion des espaces
        File dossier = new File(path);

        if (dossier.exists() && dossier.isDirectory()) {
            afficherArborescence(dossier, true);
        } else {
            System.out.println("Le répertoire spécifié n'existe pas.");
        }
    }
}
