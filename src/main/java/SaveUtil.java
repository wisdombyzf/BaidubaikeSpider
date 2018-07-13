import net.sf.json.JSONObject;
import util.FileUtil;

import java.io.*;
import java.util.HashMap;
import java.util.Map;



/**
 * 储存文件类
 */
public class SaveUtil
{
    static class MyHTML
    {
        String title;
        String url;
        String content;

        MyHTML(String title, String url, String content)
        {
            this.title = title;
            this.url = url;
            this.content = content;
        }

    }

    /**
     * 将爬取的内容存为json格式文件
     *
     * @param title
     * @param url
     * @param content
     */
    public static void save(String title, String url, String content)
    {
        Map<String, String> map = new HashMap<>();
        map.put("title", title);
        map.put("url", url);
        map.put("content", content);

        JSONObject jsonObject = JSONObject.fromObject(map);

        //System.out.println(jsonObject.toString());

        String filePath="D:/baidu/";
        FileUtil.mkdir(filePath);
        File file= FileUtil.newFile(filePath+title);
        FileUtil.writeByteArrayToFile(file,jsonObject.toString().getBytes());
        System.out.println("爬取成功一条记录");
    }

}


