package io.github.kimmking.gateway.filter;

import io.netty.handler.ipfilter.IpFilterRule;
import io.netty.handler.ipfilter.IpFilterRuleType;

import java.net.InetSocketAddress;

/**
 * IP过滤器
 */
public class IpFilter implements IpFilterRule {
    @Override
    public boolean matches(InetSocketAddress inetSocketAddress) {
        //获取IP地址
        String ip = inetSocketAddress.getHostString();
        System.out.println(ip);
        if(!"127.0.0.1".equals(ip)){
            return true;
        }
        return false;
    }

    @Override
    public IpFilterRuleType ruleType() {
        return IpFilterRuleType.ACCEPT;
    }
}
