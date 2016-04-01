package com.zhaochao.rpc.invoke;

import com.zhaochao.rpc.zookeeper.ZookeeperClient;

import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * Created by version_z on 2015/8/23.
 */
public class ProviderConfig
{
    private String target;
    private Integer port;
    private ZookeeperClient client;


    public ProviderConfig() {
    }

    public ProviderConfig(String target, Integer port) {
        this.target = target;
        this.port = port;
        if (target.toLowerCase().startsWith("zookeeper://"))
        {
            client = new ZookeeperClient(target.toLowerCase().replaceFirst("zookeeper://",""));
        }
    }


    public void register(Class clazz)
    {
        if (client != null)
        {
            client.createPersistent("/rpc/" + clazz.getName().replaceAll("\\.", "/"));
            client.createEphemeral("/rpc/" + clazz.getName().replaceAll("\\.", "/") + "/node", getNodeInfo());
        }
    }


    public String getNodeInfo()
    {
        try {
            return "http://"+Inet4Address.getLocalHost().getHostAddress()+":"+getPort();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
