import java.io.*;
import java.nio.charset.Charset;

public class MapViewGenerator {
    private String script_path;
    private ProcessBuilder pb;
    private Process process;
    private OutputStreamWriter writer;
    private BufferedReader reader;

    public MapViewGenerator() throws IOException {
        script_path = System.getenv("DS_SRC_MAIN");
        pb = new ProcessBuilder("python", script_path + "\\backend\\map_view_generator.py");
        process = pb.start();
        writer = new OutputStreamWriter(process.getOutputStream());
        reader = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("GBK")));
    }

    public void creatQuery(String query) throws IOException {
        writer.write(query + "\n");
        writer.flush(); // 刷新缓冲区以确保数据被发送
    }

    public void getStdOut() throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }

    public void endProcess() throws IOException {
        writer.close();
        reader.close();
        process.destroy();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        MapViewGenerator mapViewGenerator = new MapViewGenerator();
        mapViewGenerator.creatQuery(
                "-r 1234 0 0 Harbin_Institute_of_Technology_Weihai 1226934978 425467438 657768326 657768305 6001784001 17344858");
        System.out.println(mapViewGenerator.reader.readLine());
        System.out.println(mapViewGenerator.reader.readLine());
        mapViewGenerator.creatQuery(
                "-r 1234 1 1 Massachusetts_Institute_of_Technology 1884055322 1982225149 3869527706 4378381010 24494029 187565435");
        System.out.println(mapViewGenerator.reader.readLine());
        System.out.println(mapViewGenerator.reader.readLine());
        mapViewGenerator.creatQuery(
                "-r South_China_Agricultural_University 3749349564 8661957694 205503559 205485826 613936879 614119172 693341430");
        System.out.println(mapViewGenerator.reader.readLine());
        System.out.println(mapViewGenerator.reader.readLine());
        mapViewGenerator.creatQuery(
                "-r Beijing_University_of_Posts_and_Telecommunications 582551389 399008789 399008979 399009831 399013935 399015106 6146512085 399046289");
        System.out.println(mapViewGenerator.reader.readLine());
        System.out.println(mapViewGenerator.reader.readLine());
        // mapViewGenerator.getStdOut();
        mapViewGenerator.endProcess();
    }
}
