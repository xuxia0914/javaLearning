package cn.xux.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class JsonUtils {

    /**
     * 将对象JSON数据格式化并保存到文件中
     * @param content 需要输出的字符串
     * @param filePath 输出的文件地址
     * @return boolean
     */
    public static boolean saveStringToJsonFile(String content, String filePath) {
        // 标记文件生成是否成功
        boolean flag = true;
        // 生成json格式文件
        try {
            // 保证创建一个新文件
            File file = new File(filePath);
            if (!file.exists()) { // 如果文件不存在，则新建
                file.createNewFile();
            }
            // 将格式化后的字符串写入文件
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8");
            write.append(content);
            write.flush();
            write.close();
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 将对象JSON数据格式化并保存到文件中
     * @param object 需要输出的json数
     * @param filePath 输出的文件地址
     * @param postfix 后缀
     * @param format 是否需要格式化
     * @return boolean
     */
    public static boolean saveObjectToJsonFile(Object object, String filePath, String postfix, boolean format) {
        String content = format?
                JSON.toJSONString(object, SerializerFeature.PrettyFormat,
                        SerializerFeature.WriteMapNullValue,
                        SerializerFeature.WriteDateUseDateFormat)
                :JSON.toJSONString(object);
        if(!StringUtils.isEmpty(postfix)) {
            content += postfix;
        }
        return saveStringToJsonFile(content, filePath);
    }

}
