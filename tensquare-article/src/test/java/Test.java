import org.jasypt.util.text.BasicTextEncryptor;

public class Test {

    public static void main(String[] args) {

        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword("atpingan");

        String userName = textEncryptor.encrypt("root");
        String passWord = textEncryptor.encrypt("root123@eCloud");
        String url = textEncryptor.encrypt("jdbc:p6spy:mysql://10.0.50.55:3306/tensquare_article?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false&serverTimezone=GMT%2B8");
        String passWord2 = textEncryptor.encrypt("atpingan");

        System.out.println("userName==="+userName);
        System.out.println("passWord==="+passWord);
        System.out.println("url     ==="+url);
        System.out.println("passWord2==="+passWord2);

    }

}

