package cn.xux.test.controller;

import cn.xux.common.controller.BaseController;
import cn.xux.test.model.Person;
import cn.xux.test.service.PersonService;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PersonController extends BaseController {

    @Autowired
    PersonService personService;

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return personService.get();
    }

    //@RequestMapping(value = "/insert", method = RequestMethod.POST)
    @RequestMapping("/insert")
    public String insert() {
        Person person = new Person();
        person.setName("xux21");
        person.setGender("M");
        person.setAge(18);
        personService.insert(person);
        return "result from TestController.insert(), personId=" + person.getId();
    }

    @RequestMapping("/getById")
    public String getById(HttpServletRequest request) {
        String id = request.getParameter("id");
        Person person = personService.get(Integer.parseInt(id));
        return JSONObject.toJSONString(person);
    }

    @RequestMapping("/deleteById")
    public String deleteById(HttpServletRequest request) {
        String id = request.getParameter("id");
        personService.delete(Integer.parseInt(id));
        return "result from PersonController.deleteById()";
    }

    @RequestMapping("/selectPageByParams")
    public PageInfo<Person> selectPageByParams(HttpServletRequest request) {
        Map<String, Object> params = getRequestMap();
        return personService.selectPageByParams(params);
    }

}
