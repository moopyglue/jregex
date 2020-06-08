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

        System.out.println("Started");

        final Long maxNanoSec = 30000000000l; /* 30 secs */

        try {
        
            if(args.length != 2) {
                throw new java.lang.Exception("parameters missing need REGEX file and SEARCH file");
            }
            final String[] regexs = readLines(args[0]);
            final String[] testlines = readLines(args[1]);
            outerloop:
            for (final String r : regexs) {
                final long startTime = System.nanoTime();
                long timeElapsed = 0;
                for (final String tl : testlines) {
                    boolean m = Pattern.matches(r, tl);
                    m = false;
                    timeElapsed = System.nanoTime() - startTime;
                    if( timeElapsed > maxNanoSec ) {
                        System.out.printf("%24s / %s\n","BREATCHED_MAX_LENGTH",r);
                        continue outerloop;
                    }
                }
                System.out.printf("%24.6f / %s\n",timeElapsed/1000000000f,r);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        System.out.println("End");
    }

}
