package com.carsonlius.springcloudconsumer.hystrix;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;


/**
 * 自定义异常熔断器类
 * @author carsonlius
 */
public class CustomHystrixCommand extends HystrixCommand {
    private String url;
    private RestTemplate restTemplate;


    /**
     * 父类没有无参构造，所以子类必须使用有参构造
     * */
    public CustomHystrixCommand(Setter setter, RestTemplate restTemplate, String url) {
        super(setter);
        this.restTemplate = restTemplate;
        this.url = url;
    }

    /**
     * 调用这个防范访问服务提供者
     * */
    @Override
    protected Object run() throws Exception {
        return restTemplate.getForObject(url, String.class);
    }

    /**
     * 服务降级操作
     * */
    @Override
    protected Object getFallback() {
        Throwable throwable =  super.getFailedExecutionException();
        System.out.println("获取异常信息" + throwable);
        System.out.println("获取异常信息 message:" + throwable.getMessage());
        return "自定义熔断器，熔断了服务";
    }
}
