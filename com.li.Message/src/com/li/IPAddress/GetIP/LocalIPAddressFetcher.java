package com.li.IPAddress.GetIP;

import com.li.IPAddress.IPAddressFetcher;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

/**
 * 使用getIPveAddress()方法查找本机IP地址，A类
 * 使用时需要抛出异常SocketException
 * @author Lrn
 */
public class LocalIPAddressFetcher implements IPAddressFetcher {

    @Override
    public String getIPv4Address() throws SocketException {
        try {
            // 获取所有网络接口的枚举
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface networkInterface : Collections.list(networkInterfaces)) {
                // 检查网络接口是否是上行接口，即连接到外部网络的接口
                if (networkInterface.isUp() && !networkInterface.isLoopback()) {
                    // 获取网络接口的所有IP地址
                    Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                    for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                        // 检查是否是IPv4地址
                        if (inetAddress instanceof java.net.Inet4Address) {
                            return inetAddress.getHostAddress();
                        }
                    }
                }
            }
        } catch (SocketException e) {
            throw new SocketException("Failed to get network interfaces");
        }
        // 如果没有找到IPv4地址，返回null或抛出异常
        return null;
    }
}