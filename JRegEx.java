import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class JRegEx {

    public static String[] readLines(final String filename) throws Exception {
        
        final FileReader fileReader = new FileReader(filename);
        final BufferedReader bufferedReader = new BufferedReader(fileReader);
        final List<String> lines = new ArrayList<String>();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        bufferedReader.close();
        return lines.toArray(new String[lines.size()]);
        
    }

    public static void main(final String[] args) {

        final Integer repeat = 1;

        System.out.println("Started");

        try {
        
            if(args.length != 2) {
                throw new java.lang.Exception("parameters missing need REGEX file and SEARCH file");
            }
            final String[] regexs = readLines(args[0]);
            final String[] testlines = readLines(args[1]);
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
