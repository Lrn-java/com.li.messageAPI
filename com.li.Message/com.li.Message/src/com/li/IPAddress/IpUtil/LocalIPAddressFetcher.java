package com.li.IPAddress.IpUtil;

import com.li.IPAddress.IPAddressFetcher;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

/**
 * 使用getIPveAddress()方法查找本机IP地址，A类
 * @author Lrn
 */
public class LocalIPAddressFetcher implements IPAddressFetcher {

    /**
     * 获取本机IPv4地址
     *
     * @return 没有查到IP地址则返回null
     * @throws SocketException
     */
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

    /**
     * 获取本机IPv6地址
     *
     * @return 没有查到则返回No IPv6 address found
     * @throws SocketException
     */
    @Override
    public String getIPv6Address() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    if (inetAddress instanceof java.net.Inet6Address) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //如果没有查找到IPv6则返回null
        return null;
    }

    /**
     * 获取本机B类IPv4地址
     *
     * @return 返回没有查到的结果Null
     */
    @Override
    public InetAddress getBIPAddress() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    if (inetAddress.isSiteLocalAddress() && inetAddress instanceof java.net.Inet4Address) {
                        byte[] addr = inetAddress.getAddress();
                        if ((addr[0] & 0xFF) >= 128 && (addr[0] & 0xFF) <= 191) {
                            return inetAddress;
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null; // No B class address found or an exception occurred
    }

    @Override
    public boolean isBClassIP(String ip) {
        return true;
    }

}