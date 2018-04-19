package xbcao.demo.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonUtil {

    public static String getJsonFromStream(InputStream stream) throws Exception{
        InputStreamReader isr = new InputStreamReader(stream,"UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line;
        StringBuilder builder = new StringBuilder();
        while((line = br.readLine()) != null){
            builder.append(line);
        }
        br.close();
        isr.close();
        return builder.toString();
    }
}
