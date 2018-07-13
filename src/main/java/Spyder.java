import java.util.*;

public class Spyder
{

    Set<String> set = new HashSet<>();
    int MaxNum = 0;

    /**
     * BFS爬取网页
     *
     * @param startUrl
     * @param MaxNum
     */
    public void run(String startUrl, Integer MaxNum)
    {
        Queue<String> queue = new LinkedList<>();
        queue.add(startUrl);
        int num=0;
        GetHTML getHTML = new GetHTML();

        while (true)
        {

            if (num>MaxNum)
            {
                return;
            }else
            {
                num++;
            }

            try
            {
                String url = queue.poll();
                Parsing parsing = new Parsing(getHTML.getPage(url));
                SaveUtil.save(parsing.getTitle(), url, parsing.getContent());
                ArrayList<String> links = parsing.getAllurl();
                for (String link:links)
                {
                    if (!isContain(link))
                    {
                        queue.add(link);
                    }
                }
            }catch (Exception e)
            {

            }

        }
    }

    public static void main(String[] args)
    {
        Spyder spyder=new Spyder();
        spyder.run("https://wapbaike.baidu.com/item/%E5%85%8B%E9%87%8C%E6%96%AF%E8%92%82%E4%BA%9A%E8%AF%BA%C2%B7%E7%BD%97%E7%BA%B3%E5%B0%94%E5%A4%9A/4510104",10000);

    }


    /**
     * url去重....暂时用set
     *
     * @param url
     * @return
     */
    boolean isContain(String url)
    {
        if (set.contains(url))
        {
            return true;
        } else
        {
            set.add(url);
            return false;
        }
    }
}


