package com.sangeng;


import com.alibaba.fastjson.JSON;
import com.sangeng.config.RequestMappingScanner;
import com.sangeng.domain.Ignore;
import com.sangeng.domain.Result;
import com.sangeng.domain.User;
import com.sangeng.domain.vo.ProductCheckHandlerConfig;
import com.sangeng.domain.vo.ProductVO;
import com.sangeng.mapper.UserMapper;

import com.sangeng.service.AbstractCheckHandler;
import com.sangeng.service.impl.责任链.HandlerClient;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import javax.swing.plaf.PanelUI;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@SpringBootTest
public class MapperTest {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WebApplicationContext applicationContext;

    @Autowired
    private RequestMappingScanner mappingScanner;

    @Test
    public void testUserMapper(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users.size());
    }

    @Test
    public void TestBCryptPasswordEncoder(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("1234");
        System.out.println(encode);
    }

    @Test
    public void test01(){
        String s = testRout();
        String s1 = "\"/test/hello\",\"/user/testAuth\",\"/user/testAuth2\"";
        String replace = s1.trim().replace("\"", "");
        System.out.println(replace);

    }
    public String testRout(){

        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        List<String> router = new ArrayList<>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            RequestMappingInfo info = m.getKey();
            HandlerMethod method = m.getValue();
            Ignore methodAnnotation = method.getMethodAnnotation(Ignore.class);
            if (methodAnnotation != null) {
                PatternsRequestCondition p = info.getPatternsCondition();
                for (String url : p.getPatterns()) {
                    router.add(url);
                }
            }
        }
        String collect = router.stream().map(url->"\""+url+"\"").collect(Collectors.joining(","));
        return collect;
    }

    @Test
    public void testScanner(){
        mappingScanner.scanControllerMappings();
    }

    @Test
    public void testfanshe() throws ClassNotFoundException {
        mappingScanner.fanshe();
    }

    @Test
    public void test02(){
        mappingScanner.scanControllerMappings2();
    }
    /**
     * =========================================================================================
     */

    /**
     * 创建商品
     * @return
     */
    @Test
    public void createProduct() {
        ProductVO param = new ProductVO();
        param.setPrice(new BigDecimal(100));
        param.setSkuName("测试商品");
        param.setSkuId(123456L);
        param.setStock(100);
        param.setImgPath("http://www.baidu.com");
        //参数校验，使用责任链模式
        Result paramCheckResult = this.paramCheck(param);
        if (!paramCheckResult.isSuccess()) {
//            return paramCheckResult;
            System.out.println("创建商品 失败...");
        }

        System.out.println("创建商品 成功...");
    }

    private Result saveProduct(ProductVO param) {
        return null;
    }

    /**
     * 参数校验：责任链模式
     * @param param
     * @return
     */
    private Result paramCheck(ProductVO param) {

        //获取处理器配置：通常配置使用统一配置中心存储，支持动态变更
        ProductCheckHandlerConfig handlerConfig = this.getHandlerConfigFile();

        //获取处理器
        AbstractCheckHandler handler = this.getHandler(handlerConfig);

        //责任链：执行处理器链路
        Result executeChainResult = HandlerClient.executeChain(handler, param);
        if (!executeChainResult.isSuccess()) {
            System.out.println("创建商品 失败...");
            return executeChainResult;
        }

        //处理器链路全部成功
        return Result.success();
    }

    /**
     * 获取处理器配置：通常配置使用统一配置中心存储，支持动态变更
     * @return
     */
    private ProductCheckHandlerConfig getHandlerConfigFile() {
        //配置中心存储的配置
        String configJson = "{\"handler\":\"nullValueCheckHandler\",\"down\":false,\"next\":{\"handler\":\"priceCheckHandler\",\"next\":{\"handler\":\"stockCheckHandler\",\"next\":null}}}";
        //转成Config对象
        ProductCheckHandlerConfig handlerConfig = JSON.parseObject(configJson, ProductCheckHandlerConfig.class);
        return handlerConfig;
    }

    /**
     * 使用Spring注入:所有继承了AbstractCheckHandler抽象类的Spring Bean都会注入进来。Map的Key对应Bean的name,Value是name对应相应的Bean
     */
    @Resource
    private Map<String, AbstractCheckHandler> handlerMap;

    /**
     * 获取处理器
     * @param config
     * @return
     */
    private AbstractCheckHandler getHandler (ProductCheckHandlerConfig config) {
        //配置检查：没有配置处理器链路，则不执行校验逻辑
        if (Objects.isNull(config)) {
            return null;
        }
        //配置错误
        String handler = config.getHandler();
        if (StringUtils.isBlank(handler)) {
            return null;
        }
        //配置了不存在的处理器
        AbstractCheckHandler abstractCheckHandler = handlerMap.get(config.getHandler());
        if (Objects.isNull(abstractCheckHandler)) {
            return null;
        }

        //处理器设置配置Config
        abstractCheckHandler.setConfig(config);

        //递归设置链路处理器
        abstractCheckHandler.setNextHandler(this.getHandler(config.getNext()));

        return abstractCheckHandler;
    }


}
