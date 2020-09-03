package curseforgeparse.utilities;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utility {
    private Utility(){}
    public static void test(){
        System.out.println("Utilities");
    }

    public static String ReadToString(URL path){
        {
            try {
                String s = new String(Files.readAllBytes(Paths.get(String.valueOf(path).substring(6))));
                System.out.println(s);
                return s;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }
}
