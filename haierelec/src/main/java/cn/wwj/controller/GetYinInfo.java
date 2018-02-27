package cn.wwj.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.wwj.po.Offer;
import cn.wwj.service.OfferService;
import cn.wwj.vo.IndexVo;

@Component
public class GetYinInfo {

	@Autowired
	private OfferService offerService;
	
    public static String httpGet(String url, String charset)
    		  throws HttpException, IOException {
    		  DefaultHttpClient client = new DefaultHttpClient();
    		  String json = null;
    		  HttpGet httpGet = new HttpGet();
    		  // 设置参数
    		  try {
    		   httpGet.setURI(new URI(url));
    		  } catch (URISyntaxException e) {
    		   throw new HttpException("请求url格式错误。"+e.getMessage());
    		  }
    		  // 发送请求
    		  HttpResponse httpResponse = client.execute(httpGet);
    		  // 获取返回的数据
    		  HttpEntity entity = httpResponse.getEntity();
    		  byte[] body = EntityUtils.toByteArray(entity);
    		  StatusLine sL = httpResponse.getStatusLine();
    		  int statusCode = sL.getStatusCode();
    		  if (statusCode == 200) {
    		   json = new String(body, charset);
    		   entity.consumeContent();
    		  } else {
    		   throw new HttpException("statusCode="+statusCode);
    		  }
    		  return json;
    		}

    
    @Scheduled(cron = "0/10 * * * * ?")//每隔5秒隔行一次 
    public void myClock(){
		Timestamp createDate = new Timestamp(System.currentTimeMillis());//当前时间
	    IndexVo vo = new IndexVo();
    	List<Offer> list = offerService.findAll(vo);
    	for(Offer o:list){
    		String eNo = o.geteNo();
    		String sUrl = o.getsUrlStr();
    		String urlStr = sUrl.replace("ELECNO", eNo);
    	
    		String json;

			try {
				json = httpGet(urlStr,"utf-8");
				String jsonStr = json.substring(10,json.length()-3); 
				
				JSONObject jsonRes = JSONObject.fromObject(jsonStr);
				if(jsonRes.has("p")){
					//插入报价
					vo.setMoney(jsonRes.getDouble("p"));
					vo.setOfferId(o.getId());
					vo.setCreateDate(createDate);
					offerService.addStatistical(vo);
				}
				
			} catch (HttpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    	}
    	
    }

}
