import com.beust.jcommander.DynamicParameter;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JCmdrOptions {
    @Parameter(names = {"-t", "-text"}, description = "use given information(String)")
    private String text;
    @Parameter(names = {"-b"}, description = "display current time(boolean)")
    private boolean bol = false;
    @Parameter(names = {"-s", "-size"}, description = "use given size(Integer)")
    private int size = 0;
    @Parameter(names = {"-f", "-file"}, description = "use given file(File)")
    private File file;
    @DynamicParameter(names = "-D", description = "use value for given property(property=value)")
    public Map<String, String> dynamicParams = new HashMap<String, String>();

    public String getText() {
        return text;
    }

    public boolean isBol() {
        return bol;
    }

    public int getSize() {
        return size;
    }

    public File getFile() {
        return file;
    }

    public Map<String, String> getDynamicParams() {
        return dynamicParams;
    }

    public static void main(String[] args) {
        args = new String[]{"-t", "rensanning", "-f", "c:/aa.txt", "-b", "-s", "10", "-D", "key1=value1", "-D", "key2=value2"};

        try {
            JCmdrOptions options = new JCmdrOptions();
            JCommander jcmdr = new JCommander(options, args);

            // print usage
            jcmdr.setProgramName("JCmdrOptions");
            jcmdr.usage();

            // check the options have been set correctly
            System.out.println(options.getText());
            System.out.println(options.getFile().getName());
            if (options.isBol()) {
                System.out.println(new Date());
            }
            System.out.println(options.getSize());
            System.out.println(options.getDynamicParams().get("key1"));
            System.out.println(options.getDynamicParams().get("key2"));

        } catch (Exception ex) {
            System.out.println("Unexpected exception:" + ex.getMessage());
        }
    }
}
