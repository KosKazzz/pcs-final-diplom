public class PageEntry implements Comparable<PageEntry> {
    public final String pdfName; // private
    public final int page; // private
    public final int count; // private

    public PageEntry(String pdfName, int page, int count) {
        this.pdfName = pdfName;
        this.page = page;
        this.count = count;
    }

    @Override
    public int compareTo(PageEntry o) {
        return Integer.compare(this.count, o.count);
    }
}
