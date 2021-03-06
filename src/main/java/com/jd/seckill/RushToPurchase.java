package com.jd.seckill;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RushToPurchase implements Runnable {

    volatile static Integer times = 0;
    static Map<String, List<String>> stringListMap = new HashMap<String, List<String>>();

    public void run() {
        JSONObject headers = new JSONObject();
        while (times < Start.ok) {
            headers.put(Start.headerAgent, Start.headerAgentArg);
            headers.put(Start.Referer, Start.RefererArg);
            String gate = null;
            try {
                gate = HttpUrlConnectionUtil.get(headers, "https://cart.jd.com/gate.action?pcount=1&ptype=1&pid=" + Start.pid);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stringListMap.clear();
            try {
                stringListMap = Start.manager.get(new URI("https://trade.jd.com/shopping/order/getOrderInfo.action"), stringListMap);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            List<String> cookie = stringListMap.get("Cookie");
            headers.put("Cookie", cookie.get(0).toString());
            try {
                String orderInfo = HttpUrlConnectionUtil.get(headers, "https://trade.jd.com/shopping/order/getOrderInfo.action");
            } catch (IOException e) {
                e.printStackTrace();
            }
            JSONObject subData = new JSONObject();
            headers = new JSONObject();
            subData.put("overseaPurchaseCookies", "");
            subData.put("vendorRemarks", "[]");
            subData.put("submitOrderParam.sopNotPutInvoice", "false");
            subData.put("submitOrderParam.ignorePriceChange", "1");
            subData.put("submitOrderParam.btSupport", "0");
            subData.put("submitOrderParam.isBestCoupon", "1");
            subData.put("submitOrderParam.jxj", "1");
            subData.put("submitOrderParam.trackID", Login.ticket);
            subData.put("submitOrderParam.eid", Start.eid);
            subData.put("submitOrderParam.fp", Start.fp);
            subData.put("submitOrderParam.needCheck", "1");
            headers.put("Referer", "http://trade.jd.com/shopping/order/getOrderInfo.action");
            headers.put("origin", "https://trade.jd.com");
            headers.put("Content-Type", "application/json");
            headers.put("x-requested-with", "XMLHttpRequest");
            headers.put("upgrade-insecure-requests", "1");
            headers.put("sec-fetch-user", "?1");
            stringListMap.clear();
            try {
                stringListMap = Start.manager.get(new URI("https://trade.jd.com/shopping/order/getOrderInfo.action"), stringListMap);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            cookie = stringListMap.get("Cookie");
            headers.put("Cookie", cookie.get(0).toString());
            String submitOrder = null;
            try {
                if (times < Start.ok) {
                    submitOrder = HttpUrlConnectionUtil.post(headers, "https://trade.jd.com/shopping/order/submitOrder.action", null);
                } else {
                    System.out.println("?????????" + Start.ok + "???????????????????????????");
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (submitOrder.contains("??????????????????") || submitOrder.contains("????????????????????????????????????")) {
                System.out.println("??????????????????,???????????????????????????");
                continue;
            }
            JSONObject jsonObject = JSONObject.parseObject(submitOrder);
            String success = null;
            String message = null;
            if (jsonObject != null && jsonObject.get("success") != null) {
                success = jsonObject.get("success").toString();
            }
            if (jsonObject != null && jsonObject.get("message") != null) {
                message = jsonObject.get("message").toString();
            }

            if (success == "true") {
                System.out.println("????????????????????????????????????");
                times++;
            } else {
                if (message != null) {
                    System.out.println(message);
                } else if (submitOrder.contains("?????????????????????")) {
                    System.out.println("???????????????????????????????????????");
                } else if (submitOrder.contains("??????????????????????????????????????????????????????")) {
                    System.out.println("??????????????????????????????????????????????????????");
                } else if (submitOrder.contains("?????????????????????????????????~~")) {
                    System.out.println("?????????????????????????????????~~");
                } else if (submitOrder.contains("?????????????????????")) {
                    System.out.println("???????????????????????????????????????");
                } else {
                    System.out.println("??????????????????????????????");
                }
            }
        }
    }

}
