package org.archive.crawler;

import java.io.File;  
import javax.management.InvalidAttributeValueException;  
import org.archive.crawler.event.CrawlStatusListener;  
import org.archive.crawler.framework.CrawlController;  
import org.archive.crawler.framework.exceptions.InitializationException;  
import org.archive.crawler.settings.XMLSettingsHandler;  
  
public class StartHeritrixByEclipse {  
  
    @SuppressWarnings("unused")
	public static void main(String[] args) throws InterruptedException {  
        String orderFile = "D:/ProgramData/HeritrixOrderFile/order.xml";//order.xml文件路径  
        File file = null;   //order.xml文件  
          
        CrawlStatusListener listener = null;//监听器  
        XMLSettingsHandler handler = null;  //读取order.xml文件的处理器  
        CrawlController controller = null;  //Heritrix的控制器  
        try {  
            file=new File(orderFile);  
            System.out.println("---------------start--------------");
            handler = new XMLSettingsHandler(file);  
            System.out.println("读取order.xml中的配置");
            handler.initialize();//读取order.xml中的各个配置  
  
            controller = new CrawlController();//  
            controller.initialize(handler);//从读取的order.xml中的各个配置来初始化控制器  
  
            if (listener != null) {  
                controller.addCrawlStatusListener(listener);//控制器添加监听器  
            }  
            controller.requestCrawlStart();//开始抓取  
            /* 
             * 如果Heritrix还一直在运行则等待 
             */  
            while (true) {  
                if (controller.isRunning() == false) {  
                    break;  
                }  
                Thread.sleep(3000);  
            }  
              
            //如果Heritrix不再运行则停止  
            controller.requestCrawlStop();  
  
        } catch (InvalidAttributeValueException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (InitializationException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (InterruptedException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
  
    }  
  
}  