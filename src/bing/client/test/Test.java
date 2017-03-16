package bing.client.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.wmc.api.order.webservice.controller.ITaobaoService;
import com.wmc.api.order.webservice.vo.TaobaoGoodsRequestMsg;
import com.wmc.api.order.webservice.vo.TaobaoGoodsSkuQtUpdMsg;
import com.wmc.api.order.webservice.vo.TaobaoSkuIdListRequestMsg;
import com.wmc.api.order.webservice.vo.TaobaoSkuRequestMsg;
import com.wmc.api.order.webservice.vo.TaobaoSkusQuantityUpdateMsg;



public class Test {
	public static void main(String[] args) throws ParseException {
		//BY   61016222189a4acedcac44e1e450061f
		//KFC_TM  MML_TM
//		Test.updateGoodsSkusQuantity();
//		Test.updateSkusQuantity();
		Test.getTaobaoSkuBySkuId();
//		Test.getTaobaoSkuIdListByNumiid();
//		Test.getTaobaoGoodsList();
	}
private static void updateGoodsSkusQuantity(){
		
		String wsdl = "http://localhost:8088/wmc/webservice/taobaoService?wsdl";
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ITaobaoService.class);
        factory.setAddress(wsdl);
        ITaobaoService client = (ITaobaoService) factory.create();
        TaobaoGoodsSkuQtUpdMsg  req = new TaobaoGoodsSkuQtUpdMsg ();
        req.setCustomerNo("EBS_TM");//店铺号-ef_common_customer 表中的customer_no
        req.setOrderSource("18");//渠道编码
        req.setNum_iid(521811127231l);
//        req.setSku_id(3114391987348l);
//        req.setOuter_id("");
        req.setQuantity(100l);
        req.setType(1L);
        
        System.out.println(client.updateGoodsSkusQuantity(req));
        
	}
	private static void updateSkusQuantity(){
		
		String wsdl = "http://183.238.41.133:8084/escm/webservice/taobaoService?wsdl";
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ITaobaoService.class);
        factory.setAddress(wsdl);
        ITaobaoService client = (ITaobaoService) factory.create();
        TaobaoSkusQuantityUpdateMsg req = new TaobaoSkusQuantityUpdateMsg();
        req.setCustomerNo("XSC_TM");//店铺号-ef_common_customer 表中的customer_no
        req.setOrderSource("18");//渠道编码
        req.setNum_iid(523158018280L);
        req.setType(1L);
        Map<String,String> skusMap = new HashMap<String,String>();
        //3114391987361:152    skuid:库存值;
		skusMap.put("523158018280", "3114391987348:550,");
        req.setSkusMap(skusMap);
        
        System.out.println(client.updateSkusQuantity(req));
        
	}
	private static void getTaobaoSkuBySkuId(){
		String wsdl = "http://183.238.41.133:8084/escm/webservice/taobaoService?wsdl";
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ITaobaoService.class);
        factory.setAddress(wsdl);
        ITaobaoService client = (ITaobaoService) factory.create();
        TaobaoSkuRequestMsg req = new TaobaoSkuRequestMsg();
        req.setCustomerNo("XSC_TM");//店铺号-ef_common_customer 表中的customer_no
        req.setOrderSource("18");
        req.setFields("sku_id,iid,properties,quantity,price,outer_id,created,modified,status,sku_spec_id,change_prop,memo");
        req.setNum_iid(523158018280l);
        req.setNick("小素材旗舰店");
        req.setSku_id(3114391987348L);
        System.out.println(client.getTaobaoSkuBySkuId(req));
        
	}
	private static void getTaobaoSkuIdListByNumiid(){
		String wsdl = "http://183.238.41.133:8084/escm/webservice/taobaoService?wsdl";
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ITaobaoService.class);
        factory.setAddress(wsdl);
        ITaobaoService client = (ITaobaoService) factory.create();
        TaobaoSkuIdListRequestMsg req = new TaobaoSkuIdListRequestMsg();
        req.setCustomerNo("XSC_TM");//店铺号-ef_common_customer 表中的customer_no
        req.setOrderSource("18");
        req.setFields("sku_id,num_iid");
        req.setNum_iids("520917079539");//可以传多个值查询出列表用,隔开
        System.out.println(client.getTaobaoSkuIdListByNumiid(req));
        
	}
	private static  void getTaobaoGoodsList() throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String wsdl = "http://183.238.41.133:8084/escm/webservice/taobaoService?wsdl";
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ITaobaoService.class);
        factory.setAddress(wsdl);
        ITaobaoService client = (ITaobaoService) factory.create();
        TaobaoGoodsRequestMsg req = new TaobaoGoodsRequestMsg();
        //查询时间-可选值
//        req.setStart_modified(df.parse("2015-10-01"));
//        req.setEnd_modified(df.parse("2015-10-23"));
        req.setCustomerNo("XSC_TM");//店铺号-ef_common_customer 表中的customer_no
        req.setOrderSource("18");
        req.setFields("iid,num_iid,title,price");
		req.setPage_no(1l);
		req.setPage_size(100l);
        
        System.out.println(client.getTaobaoGoodsList(req));
		
	}
	
}
