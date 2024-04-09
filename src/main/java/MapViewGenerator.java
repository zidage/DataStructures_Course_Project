import java.io.*;

public class MapViewGenerator {
    private String script_path;
    private ProcessBuilder pb;
    private Process process;
    private OutputStreamWriter writer;
    private BufferedReader reader;

    public MapViewGenerator() throws IOException {
        script_path = System.getenv("DS_SRC_MAIN");
        pb = new ProcessBuilder("python", script_path + "\\python\\map_view_generator.py");
        process = pb.start();
        writer = new OutputStreamWriter(process.getOutputStream());
        reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    }

    public void creatQuery(String query) throws IOException {
        writer.write(query + "\n");
        writer.flush(); // 刷新缓冲区以确保数据被发送
        System.out.println("Python output: " + reader.readLine());
    }

    public void endProcess() throws IOException {
        writer.close();
        reader.close();
        process.destroy();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        MapViewGenerator mapViewGenerator = new MapViewGenerator();
        mapViewGenerator.creatQuery("-r South_China_Agricultural_University 1091015028 205503549 614119144 9087904 613936879 957193796"); 
        mapViewGenerator.endProcess();
    }
}
