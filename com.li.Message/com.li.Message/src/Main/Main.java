package Main;

import com.li.IPAddress.IpUtil.LocalIPAddressFetcher;

import java.net.SocketException;

import static java.lang.System.out;

/**
 * 用来测试写好的方法
 * @author Lrn
 */
public class Main {

    public static void main(String[] args) {

        out.println(new LocalIPAddressFetcher().getIPv6Address());
        try {
            out.println(new LocalIPAddressFetcher().getIPv4Address());
            out.println(new LocalIPAddressFetcher().getBIPAddress());
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

}