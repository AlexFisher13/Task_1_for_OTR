import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckIP {

    public static void main(String[] args) throws IOException {

        System.out.println("Введите два IP-адреса:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String ip1 = reader.readLine();
        String ip2 = reader.readLine();
        ipDiapason(ip1, ip2);
    }

    public static void ipDiapason (String ipAddress1, String ipAddress2) {
        Pattern p = Pattern.compile("^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$");
        Matcher m = p.matcher(ipAddress1);
        if (m.matches() == false) {
            System.out.println("Некорректный IP  №1");
            return;
        }
        m = p.matcher(ipAddress2);
        if (m.matches() == false) {
            System.out.println("Некорректный IP  №2");
            return;
        }

        long ip1 = ipToLong(ipAddress1);
        long ip2 = ipToLong(ipAddress2);
        if (ip1 == ip2) {
            System.out.println("IP Адреса равны!");
            return;
        }

        System.out.println("Диапазон IP-адресов:");
        while (ip1 < (ip2 - 1) ) {
            ip1++;
            System.out.println(longToIp(ip1));
        }
    }


    public static long ipToLong(String ipAddress) {
        long result = 0;
        String[] ipAddressInArray = ipAddress.split("\\.");
        for (int i = 3; i >= 0; i--) {
            long ip = Long.parseLong(ipAddressInArray[3 - i]);
            result |= ip << (i * 8);
        }
        return result;
    }

    public static String longToIp (long ip) {
        return ((ip >> 24) & 0xFF) + "."
                + ((ip >> 16) & 0xFF) + "."
                + ((ip >> 8) & 0xFF) + "."
                + (ip & 0xFF);
    }
}
