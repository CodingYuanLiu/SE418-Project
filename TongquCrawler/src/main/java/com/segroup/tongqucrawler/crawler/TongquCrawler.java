public class TongquCrawler {
    public static void main(String args[]) {
        RequestSender rs = new RequestSender();
        String response = rs.send("https://tongqu.me/act/20350", "GET");
        System.out.println(response);
    }
}
