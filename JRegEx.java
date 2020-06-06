import java.util.regex.Pattern;

public class JRegEx {
    public static void main(final String[] args) {

        final Integer repeat = 1;

        System.out.println("Started");

        try {
            final FileArrayProvider fap = new FileArrayProvider();
            final String[] regexs = fap.readLines("regexs.txt");
            final String[] testlines = fap.readLines("testlines.txt");
            for (final String r : regexs) {
                final long startTime = System.nanoTime();
                for (final String tl : testlines) {
                    for (int i = 1; i <= repeat; i++) {
                        boolean m = Pattern.matches(r, tl);
                        m = false;
                    }
                }
                final long timeElapsed = System.nanoTime() - startTime;
                System.out.printf("%60s x %d = %s\n",r,repeat,"" + timeElapsed);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        System.out.println("End");
    }

}
