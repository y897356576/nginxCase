package com;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by 石头 on 2017/6/27.
 */
@Controller
@RequestMapping("/indexHandler")
public class IndexHandler {

    @RequestMapping(method = RequestMethod.GET)
    public String forwardIndexPage(){

        Cache cache = EhcacheUtil.getCache("ehcacheFir");
        Integer count_all = cache.get("count_all")==null ? 0 : (Integer)cache.get("count_all").getObjectValue();
        Integer count_fir = cache.get("count_fir")==null ? 0 : (Integer)cache.get("count_fir").getObjectValue();
        cache.put(new Element("count_all", ++count_all));
        cache.put(new Element("count_fir", ++count_fir));
        System.out.println("[From Fir] Current quantity_all is : " + cache.get("count_all").getObjectValue().toString());
        System.out.println("[From Fir] Current count_fir is : " + cache.get("count_fir").getObjectValue().toString());

        return "/index.html";
    }

}
