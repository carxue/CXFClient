package bing.client;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import bing.server.IHelloService;
import bing.server.bean.User;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class HelloServiceClient {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:spring-conf/applicationContext-client.xml");
		IHelloService helloService = (IHelloService) context.getBean("client");
		String response = helloService.sayHello("Peter");
		System.out.println(response);
		User toUser = new User();toUser.setName("little snow");
		User user = helloService.getUser(toUser);
		System.out.println(user.getName()+" : "+user.getAge());
		List<User> list = helloService.getUserList();
		for(User user1:list)
			System.out.println(user1.getName()+" : "+user1.getAge());
		Map<Integer,User> map = helloService.getUserMap();
		Set set = map.keySet();
		for(Iterator it=set.iterator();it.hasNext();){
			Integer key = Integer.parseInt(it.next().toString());
			User user2 = map.get(key);
			System.out.println(user2.getName()+" : "+user2.getAge());
		}
		
		Gson gson = new Gson();
		Map<Integer,User> gmap = gson.fromJson(helloService.getGsonUserMap(), new TypeToken<Map<Integer,User>>(){}.getType());
		Set gset = gmap.keySet();
		for(Iterator it=gset.iterator();it.hasNext();){
			Integer key = Integer.parseInt(it.next().toString());
			User user2 = gmap.get(key);
			System.out.println(user2.getName()+" : "+user2.getAge());
		}
	}

}
