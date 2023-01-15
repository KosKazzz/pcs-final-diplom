import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class BooleanSearchEngine implements SearchEngine {
    Map<String, List<PageEntry>> wordStat = new HashMap<>();
    Map<String, PageEntry> wordStatPrep = new HashMap<>();
    List<PageEntry> stat;

    public BooleanSearchEngine(File pdfsDir) throws IOException {
        if (pdfsDir.isDirectory()) {
            String[] fileNamesArr = pdfsDir.list();
            for (String s : fileNamesArr) {
                File oneFile = new File(".\\" + pdfsDir + "\\" + s);
                PdfDocument document = new PdfDocument(new PdfReader(oneFile));
                for (int i = 1; i <= document.getNumberOfPages(); i++) {
                    String text = PdfTextExtractor.getTextFromPage(document.getPage(i));
                    String[] words = text.split("\\P{IsAlphabetic}+");
                    Map<String, Integer> freqs = new HashMap<>();
                    for (var word : words) {
                        if (word.isEmpty()) {
                            continue;
                        }
                        word = word.toLowerCase();
                        freqs.put(word, freqs.getOrDefault(word, 0) + 1);
                    }
                    Set<String> setKey = freqs.keySet();
                    for (String word : setKey) {
                        PageEntry pageEntry = new PageEntry(s, i, freqs.get(word));
                        wordStatPrep.put(word, pageEntry);
                    }
                    Set<String> keyWordStatPrep = wordStatPrep.keySet();
                    for (String word : keyWordStatPrep) {
                        if (wordStat.containsKey(word)) {
                            wordStat.get(word).add(wordStatPrep.get(word));
                        } else {
                            stat = new ArrayList<>();
                            stat.add(wordStatPrep.get(word));
                            wordStat.put(word, stat);
                        }
                    }
                    wordStatPrep.clear();
                }
            }
        }
    }

    @Override
    public List<PageEntry> search(String word) {
        return wordStat.get(word);
    }
}
