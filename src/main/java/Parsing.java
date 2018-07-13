import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;


/**
 * 解析类
 */
public class Parsing
{
    private String htmlData;
    private Document document;

    public Parsing(String htmlData)
    {
        this.htmlData = htmlData;

        document = Jsoup.parse(htmlData);
    }

    /**
     * 获取该页面中的所有url
     *
     * @return
     */
    public ArrayList<String> getAllurl()
    {

        Elements paras = document.getElementsByClass("para");
        StringBuffer sb = new StringBuffer();
        for (Element link : paras)
        {
            sb.append(link);
        }

        document = Jsoup.parse(sb.toString());
        paras = document.select("a[href]");

        ArrayList<String> links = new ArrayList<>();

        for (Element link : paras)
        {
            links.add("https://wapbaike.baidu.com" + link.attr("href"));
        }
        return links;
    }

    /**
     * 获取内容
     *
     * @return
     */
    public String getContent()
    {
        Elements body = document.select("body");
        Elements li = body.select("li");
        StringBuffer stringBuffer = new StringBuffer();

        for (Element tmp : li)
        {
            stringBuffer.append(tmp.text());
        }
        return stringBuffer.toString();
    }

    /**
     * 获取页面标题
     * @return
     */
    public String getTitle()
    {
        Elements body = document.select("title");
        StringBuffer stringBuffer = new StringBuffer();

        for (Element tmp : body)
        {
            stringBuffer.append(tmp.text());
        }
        return stringBuffer.toString();
    }
}
