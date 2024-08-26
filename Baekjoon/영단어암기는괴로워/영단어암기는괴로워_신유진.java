import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N, M;
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Map<String, Integer> data = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            if (input.length() < M)
                continue;

            data.put(input, data.getOrDefault(input, 0) + 1);
        }

        List<String> listData = new ArrayList<>(data.keySet());
        listData.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (data.get(o1) != data.get(o2))
                    return data.get(o2).compareTo(data.get(o1));

                if (o1.length() != o2.length())
                    return Integer.compare(o2.length(), o1.length());

                return o1.compareTo(o2);
            }
        });

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (String word : listData) {
            bw.write(word + "\n");
        }

        bw.flush();
        bw.close();
        br.close();

    }
}
