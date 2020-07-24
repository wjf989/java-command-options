import org.apache.commons.cli.*;

import java.util.Collections;
import java.util.Date;

public class CommonsCliOptions {
    public static void main(String[] args) {
        args = new String[]{"-t", "rensanning", "-f", "c:/aa.txt", "-b", "-s10", "-Dkey1=value1", "-Dkey2=value2", "-h"};
        String prefix = String.join("", Collections.nCopies(10, "======="));
        try {
            // 创建option对象，即参数对象信息
            Options options = new Options();
            options.addOption(new Option("t", "text", true, "使用给定的信息(String)"));
            options.addOption(new Option("b", false, "显示当前时间(boolean)"));
            options.addOption(new Option("s", "size", true, "使用给定的大小(Integer)"));
            options.addOption(new Option("f", "file", true, "使用给定的文件(File)"));
            options.addOption(new Option("h", "help", false, "将此帮助消息输出到输出流"));
            @SuppressWarnings("static-access")
            Option property = OptionBuilder.withArgName("property=value")
              .hasArgs(2)
              .withValueSeparator()
              .withDescription("使用给定的属性值(property=value)")
              .create("D");
            property.setRequired(true);
            options.addOption(property);

            // 创建命令行解析器
            CommandLineParser parser = new PosixParser();
            CommandLine cmd = parser.parse(options, args);
            // 打印使用方法
            if (cmd.hasOption("h")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.setSyntaxPrefix("用法：");
                formatter.printHelp("CommonsCliOptions", prefix + "\n选项:", options, prefix);
                System.out.println();
            }
            // 检查选项是否设置正确
            System.out.println(cmd.getOptionValue("t"));
            System.out.println(cmd.getOptionValue("f"));
            if (cmd.hasOption("b")) {
                System.out.println(new Date());
            }
            System.out.println(cmd.getOptionValue("s"));
            System.out.println(cmd.getOptionProperties("D").getProperty("key1"));
            System.out.println(cmd.getOptionProperties("D").getProperty("key2"));

        } catch (Exception ex) {
            System.out.println("Unexpected exception:" + ex.getMessage());
        }
    }
}
