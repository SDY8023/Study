import org.neo4j.driver.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TestBatchInsert2
 * @Description
 * @Author SDY
 * @Date 2025/4/28 10:02
 **/
public class TestBatchInsert2 {

    private static final String URI = "bolt://bigdata03:7687";
    private static final String USER = "neo4j";
    private static final String PASSWORD = "neo4j";
    static int batchSize = 10000;
    public static void main(String[] args) {
        Driver driver = GraphDatabase.driver(URI, AuthTokens.basic(USER, PASSWORD));
        List<String[]> dataList = generateData();
        batchInsertWithUnwind(driver,dataList);
    }


    private static void batchInsertWithUnwind(Driver driver, List<String[]> data) {
        try (Session session = driver.session()) {
            // 将数据分批处理（例如每批次 1000 条）
            for (int i = 0; i < data.size(); i += batchSize) {
                long startTime = System.currentTimeMillis();
                System.out.println("第"+i+"批数据插入，开始时间:"+startTime);
                List<String[]> batch = data.subList(i, Math.min(i + batchSize, data.size()));

                // 构建 Cypher 查询
                String query = "UNWIND $batch AS record " +
                        "MERGE (n1:Person {key: record.node1Key}) " +
                        "MERGE (n2:Company {key: record.node2Key}) " +
                        "MERGE (n1)-[r:WORKS_AT]->(n2) " +
                        "ON CREATE SET r.created_at = timestamp() " +
                        "ON MATCH SET r.updated_at = timestamp()";

                // 准备参数
                List<Map<String, Object>> params = new ArrayList<>();
                for (String[] record : batch) {
                    params.add(Map.of(
                            "node1Key", record[1],
                            "node2Key", record[3]
                    ));
                }

                // 执行查询
                session.writeTransaction(tx -> {
                    tx.run(query, Values.parameters("batch", params));
                    return null;
                });
                long endTime = System.currentTimeMillis();
                System.out.println("第"+i+"批数据结束插入，结束时间:"+endTime+",耗时:"+(endTime - startTime));
            }
        }
    }

    /**
     * 模拟生成 10 万条数据
     */
    private static List<String[]> generateData() {
        List<String[]> data = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            data.add(new String[]{
                    "Person", "Person11" + i, // 节点1标签和键
                    "Company", "Company11" + (i % 100), // 节点2标签和键
                    "WORKS_AT" // 关系类型
            });
        }
        return data;
    }
}
