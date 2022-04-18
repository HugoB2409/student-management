package secretariat.io.writer;

import secretariat.Cours;

import java.io.File;
import java.io.FileWriter;

public class CoursWriter implements Writer<Iterable<Cours>> {
    @Override
    public void write(Iterable<Cours> obj, File file) {
        try {
            FileWriter fw = new FileWriter(file);
            for (Cours cours : obj) {
                var sb = new StringBuilder();
                if (cours.getPrerequis() != null && cours.getPrerequis().size() > 0) {
                    cours.getPrerequis().forEach(preq -> sb.append(preq.getSigle()).append(" ; "));
                    sb.delete(sb.lastIndexOf(";") - 1, sb.length());
                }

                var f = String.format("%s\t%s\t%d\t%s\n", cours.getSigle(), cours.getNom(), cours.getMaxEtudiants(), sb);
                fw.write(f);
                fw.flush();
            }
            fw.close();
        } catch (Exception ignored) {
        }
    }
}
