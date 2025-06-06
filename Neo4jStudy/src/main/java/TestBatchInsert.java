import org.neo4j.driver.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName TestBatchInsert
 * @Description
 * @Author SDY
 * @Date 2025/4/17 11:04
 **/
public class TestBatchInsert {
    private static final String URI = "bolt://bigdata03:7687";
    private static final String USER = "neo4j";
    private static final String PASSWORD = "neo4j";
    private static final int BATCH_SIZE = 1000; // 每批处理1000条数据[8,13](@ref)

    public static void main(String[] args) {
        Driver driver = GraphDatabase.driver(URI, AuthTokens.basic(USER, PASSWORD));
        try (Session session = driver.session()) {
            long startTime = System.currentTimeMillis();

            long startCreateNodes = System.currentTimeMillis();
            System.out.println("开始创建节点时间:"+startCreateNodes);
            // 生成10万条用户节点
            List<Record> userNodes = generateUserNodes(100000);
            long endCreateNodes = System.currentTimeMillis();
            System.out.println("结束创建节点时间:"+endCreateNodes);
            long startInsertNodes = System.currentTimeMillis();
            System.out.println("开始插入节点时间:"+startInsertNodes);
            // 批量插入用户节点
            batchInsertNodes(session, userNodes);
            long endInsertNodes = System.currentTimeMillis();
            System.out.println("插入节点结束时间:"+endInsertNodes);
            userNodes.clear();
            long startCreateRelation = System.currentTimeMillis();
            System.out.println("开始创建关系时间:"+startCreateRelation);
            // 生成用户关系数据
            List<RelationshipRecord> relationships = generateRelationships(userNodes);
            long endCreateRelation = System.currentTimeMillis();
            System.out.println("结束创建关系时间:"+endCreateRelation);

            long startInsertRElation = System.currentTimeMillis();
            System.out.println("开始插入关系时间:"+startInsertRElation);
            // 批量插入关系
            batchInsertRelationships(session, relationships);
            long endTime = System.currentTimeMillis();
            System.out.println("结束插入关系时间:"+endTime);
        } finally {
            driver.close();
        }
    }

    // 生成用户节点数据
    private static List<Record> generateUserNodes(int count) {
        List<Record> nodes = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            Map<String, Value> nodeMap = new HashMap<>();
            nodeMap.put("id",Values.value(String.valueOf(i)));
            Map<String, Value> relationMap = new HashMap<>();
            relationMap.put("name", Values.value("User" + i));
            relationMap.put("age", Values.value(String.valueOf(random.nextInt(30) + 20)));
            nodes.add(
                    new Record(
                            nodeMap,
                            relationMap
                    )
            );
        }
        return nodes;
    }

    // 生成用户关系数据
    private static List<RelationshipRecord> generateRelationships(List<Record> users) {
        List<RelationshipRecord> relations = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < users.size(); i++) {
            for (int j = i + 1; j < users.size(); j++) {
                if (random.nextBoolean()) {
                    relations.add(new RelationshipRecord(
                            users.get(i).getKeys("id").asString(),
                            users.get(j).getKeys("id").asString(),
                            "FRIENDS_WITH",
                            random.nextInt(10) + 1
                    ));
                }
            }
        }
        return relations;
    }

    // 批量插入节点
    private static void batchInsertNodes(Session session, List<Record> nodes) {
        session.writeTransaction(tx -> {
            for (int i = 0; i < nodes.size(); i += BATCH_SIZE) {
                System.out.println("======插入第"+i+"批数据");
                List<Record> batch = nodes.subList(i, Math.min(i + BATCH_SIZE, nodes.size()));
                String query = "UNWIND $batch AS node " +
                        "MERGE (u:User {id: node.id}) " +
                        "SET u.name = node.name, u.age = node.age";
                tx.run(query, Values.parameters("batch", batch.stream().map(dataInfo -> Values.parameters(
                        "id",dataInfo.getKeys("id"),
                        "name",dataInfo.getProperties("name"),
                        "age",dataInfo.getProperties("age")
                        )).collect(Collectors.toList())
                ));
            }
            return null;
        });
    }

    // 批量插入关系
    private static void batchInsertRelationships(Session session, List<RelationshipRecord> relations) {
        session.writeTransaction(tx -> {
            for (int i = 0; i < relations.size(); i += BATCH_SIZE) {
                List<RelationshipRecord> batch = relations.subList(i, Math.min(i + BATCH_SIZE, relations.size()));
                String query = "UNWIND $rels AS rel " +
                        "MATCH (a:User {id: rel.fromId}), (b:User {id: rel.toId}) " +
                        "MERGE (a)-[r:TEST]->(b)" +
                        "SET r.weight = rel.weight";
                tx.run(query, Values.parameters("rels", batch.stream().map(
                        dataInfo -> Values.parameters(
                              "fromId",dataInfo.fromId,
                              "toId",dataInfo.toId,
                              "weight",dataInfo.weight
                        )).collect(Collectors.toList())
                ));
            }
            return null;
        });
    }

    // 节点记录类
    static class Record {
        private final Map<String, Value> keys;
        private final Map<String, Value> properties;

        public Record(Map<String, Value> keys, Map<String, Value> properties) {
            this.keys = keys;
            this.properties = properties;
        }

        public Value getProperties(String key) {
            return properties.get(key);
        }

        public Value getKeys(String key) {
            return keys.get(key);
        }
    }

    // 关系记录类
    static class RelationshipRecord {
        public final String fromId;
        public final String toId;
        public final String type;
        public final int weight;

        public RelationshipRecord(String fromId, String toId, String type, int weight) {
            this.fromId = fromId;
            this.toId = toId;
            this.type = type;
            this.weight = weight;
        }
    }
}
