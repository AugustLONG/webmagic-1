package hugo;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class LVYEPageProcessor implements PageProcessor{

	private Site site = Site.me().setDomain("bbs.lvye.cn").addStartUrl("http://bbs.lvye.cn/thread-1203781-1-1.html");

    @Override
    public void process(Page page) {
    	//处理得到的页面
        List<String> links = page.getHtml().links().regex("http://bbs.lvye.cn/thread-1203781-1-1.html").all();
        page.addTargetRequests(links);
        
        //去除复制链接的span
        page.putField("title", getContent(page.getHtml().xpath("//div[@id='postlist']"
				+ "/table/tbody/tr/td[@class='plc ptm pbn']/h1").toString()));
        
        //去除本帖发表时间
        page.putField("content", getContent(
        		page.getHtml().xpath("//div[@id='postlist']/div[1]//div[@class='pct']/div[@class='pcb']").toString())
        		     );
        page.putField("time", getContent(page.getHtml().xpath("//div[@id='postlist']/div[1]//div[@class='pi']//div[@class='pti']"
        		+ "//div[@class='authi']/em[1]").toString()));
        
//      page.putField("content", page.getHtml().xpath("//div[@id='postlist']"
//        		+ "/div[1]/table[1]/tbody/tr[1]/td[2]/div[2]/div/div[1]/table/tbody/tr/td//*//text()").toString());
    }

    @Override
    public Site getSite() {
        return site;
    }
    
    public void addSite(String baseUrl){
    	for(int i=1000010;i<1000023;i++){
    		site.addStartUrl(baseUrl.replaceAll("\\*", String.valueOf(i)));
    	}
    }
    
    private String getContent(String html) {
    	Document doc = Jsoup.parse(html);
    	String text = doc.body().text();
    	return text;
	}
}
