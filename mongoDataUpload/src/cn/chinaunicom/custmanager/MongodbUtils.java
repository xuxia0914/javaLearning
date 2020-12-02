package cn.chinaunicom.custmanager;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.*;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author xux21
 */
public class MongodbUtils {

    /**
     * 不打印mongo包的debug日志
     */
    static Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
    static {
        root.setLevel(Level.INFO);
    }

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(MongodbUtils.class);

    public static void main(String[] args) {

        //down(50, 0);

        if(args==null||args.length<5) {
            throw new RuntimeException("请按要求添加参数：[文件所在路径] [mongoDB host] [mongoDB port] [mongoDB dbName] [mongoDB collectionName]");
        }

        String directory = args[0];
        String host = args[1];
        int port = Integer.parseInt(args[2]);
        String dbName = args[3];
        String collectionName = args[4];

        upload(directory, host, port, dbName, collectionName);

    }

    private static void upload(String directory, String host, int port,
                               String dbName, String collectionName) {
        log.info("<------ 文件上传开始 ------>");
        Long startTime = System.currentTimeMillis();
        int invalidCnt = 0;
        int exitCnt = 0;
        int successCnt = 0;
        try {
            // 连接目的数据库
            MongoClient tarMongoClient = new MongoClient(host, port);
            DB tarDb = tarMongoClient.getDB(dbName);
            GridFS tarGridFs = new GridFS(tarDb);
            DBCollection collection = tarDb.getCollection(collectionName);

            // 获取filePath文件夹下的文件列表
            String[] fileList = new File(directory).list();
            for (String fileName : fileList) {
                // 文件全路径
                String pathName = directory + fileName;

                int firstP = fileName.indexOf(".");
                int lastP = fileName.lastIndexOf(".");

                if(firstP==-1||lastP==-1||firstP==lastP) {
                    log.info("文件：" + fileName + "不符合命名规范");
                    invalidCnt++;
                    continue;
                }

                // 文件在数据库中的objectId
                String fileId = fileName.substring(0, firstP);
                // 文件在数据库中的名字(不带后缀)
                String fileNameTmp = fileName.substring(firstP+1, lastP);
                // 文件类型(.后缀)
                String type = fileName.substring(lastP);

                if(fileId.contains(")")) {
                    // 文件在数据库中的objectId可能带编号 '(<编号>)'，要去掉
                    fileId = fileId.substring(fileId.indexOf(")")+1);
                }
                // 文件在数据库中的名字(带后缀)
                String realFileName = fileNameTmp+type;

                // 查询是否已经上传过，如果是，则跳过
                ObjectId objectId = new ObjectId(fileId);
                BasicDBObject queryObject = new BasicDBObject("_id", objectId);
                DBObject obj = collection.findOne(queryObject);
                if(null!=obj) {
                    log.info("文件：" + fileName + "已存在");
                    exitCnt++;
                    continue;
                }
                // 开始上传文件
                GridFSInputFile gridFsInputFile = tarGridFs.createFile(new File(pathName));
                gridFsInputFile.setId(new ObjectId(fileId));
                gridFsInputFile.setFilename(realFileName);
                gridFsInputFile.setChunkSize(1000L);
                gridFsInputFile.setMetaData(new BasicDBObject("type", type));
                gridFsInputFile.setContentType("");
                gridFsInputFile.save();
                log.info("文件：" + fileName + "上传成功");
                successCnt++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Long endTime = System.currentTimeMillis();
        log.info("<------ 文件上传结束 ------>");
        log.info("无效文件个数："+invalidCnt+"，已存在文件个数："+exitCnt
                +"，上传成功文件个数："+successCnt
                +"。耗时："+(endTime-startTime)+"ms");
    }

    /*public static void down(String host, int port,
                            String dbName, String collectionName,
                            String path, int cnt, int start) {
        long startTime = System.currentTimeMillis();
        log.error("程序开始执行时间:"+startTime);
        log.error("(下一次start开始数量)cnt数量:"+cnt);
        try{
            //分页个数
            int size=cnt-start;
            //循环获取mongdb中的fileId
            MongoClient mongoClient = new MongoClient(host, port);
            DB db = mongoClient.getDB(dbName);
            GridFS gridFs = new GridFS(db);
            DBCollection collection = db.getCollection(collectionName);
            DBCursor dbCursor = collection.find().limit(size).skip(start);
            //int cnt=collection.find().count();
            String chunksFilePath = path;
            //增加 用于区分同名的文件
            int num2 = 0;
            while(size-->0&&dbCursor.hasNext()) {
                DBObject next = dbCursor.next();
                num2 = start+=1;
                String s = next.toString();
                JSONObject nextJson = JSONObject.parseObject(s);
                //JsonUtils.saveStringToJsonFile(nextJson.toJSONString()+"\r\n", filePath);
                String fileIds = nextJson.getString("_id");
                JSONObject fileJson = JSONObject.parseObject(fileIds);
                String fileId = fileJson.get("$oid").toString();
                ObjectId objectId = new ObjectId(fileId);
                GridFSDBFile gridFSDBFile = gridFs.findOne(objectId);
                String name = (String) gridFSDBFile.get("filename");
                name = new String(name.getBytes("UTF-8"));
                name="("+num2+")"+fileId+"."+name;
                File file = new File(chunksFilePath);
                if (!file.exists()) { // 如果文件不存在，则新建
                    file.mkdir();
                }
                String filePath=chunksFilePath+name;
                file.setExecutable(true,false);//设置可执行权限
                file.setReadable(true, false);
                file.setWritable(true,false);//设置可写权限
                //写入文件
                gridFSDBFile.writeTo(filePath);
            }
            long endTime = System.currentTimeMillis(); // 获取结束时间
            log.error("程序总运行时间： " + (endTime - startTime) + "ms");
        }catch (Exception e){
            log.error("下载文件异常：",e);
        }
    }*/

}
