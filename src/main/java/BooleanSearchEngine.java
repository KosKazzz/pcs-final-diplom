import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class BooleanSearchEngine implements SearchEngine {
    Map<String, List<PageEntry>> wordStat = new HashMap<>();
    List<PageEntry> sum = new ArrayList<>();

    public BooleanSearchEngine(File pdfsDir) throws IOException {
        if (pdfsDir.isDirectory()) {
            String[] fileNamesArr = pdfsDir.list();
            for (String s : fileNamesArr) {
                File oneFile = new File(".\\" + pdfsDir + "\\" + s);
                PdfDocument document = new PdfDocument(new PdfReader(oneFile));
                for (int i = 1; i < document.getNumberOfPages(); i++) { //перебираем страницы
                    String text = PdfTextExtractor.getTextFromPage(document.getPage(i));// i-тая страничка
                    String[] words = text.split("\\P{IsAlphabetic}+"); // массив слов из i-той странички
                    Map<String, Integer> freqs = new HashMap<>(); // мапа, где ключом будет слово, а значением - частота
                    for (var word : words) { // перебираем слова
                        if (word.isEmpty()) {
                            continue;
                        }
                        word = word.toLowerCase();
                        freqs.put(word, freqs.getOrDefault(word, 0) + 1);
                    }
                    Set<String> setKey = freqs.keySet();
                    for (String word : setKey) { // перебираем всe слова со странички
                        PageEntry pageEntry = new PageEntry(s, i, freqs.get(word));

                        sum.add(pageEntry); // дичь
                        wordStat.put(word, sum); // дичь

                    }
                }
            }
        }
    }

    @Override
    public List<PageEntry> search(String word) {
        // тут реализуйте поиск по слову
        return wordStat.get(word);
    }
}
