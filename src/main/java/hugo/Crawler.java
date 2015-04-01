package hugo;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class Crawler{

    public static void main(String[] args) {
    	
    	LVYEPageProcessor lvye = new LVYEPageProcessor();
    	lvye.addSite("http://bbs.lvye.cn/thread-*-1-1.html");
        Spider.create(lvye).pipeline(new ConsolePipeline()).run();
    }
    
    private static void getContent(String html) {
    	Document doc = Jsoup.parse(html);
    	String text = doc.body().text();
	}
}
