package cn.xux.test.controller;

import cn.xux.test.model.Person;
import cn.xux.test.service.PersonService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PersonController {

    @Autowired
    PersonService personService;

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return personService.get();
    }

    /*@RequestMapping("/insert")
    @ResponseBody
    public String insert() {
        Person person = new Person();
        person.setName("xux21");
        person.setGender("M");
        person.setAge(18);
        personService.insert(person);
        return "result from TestController.insert(), personId=" + person.getId();
    }

    @RequestMapping("/getById")
    @ResponseBody
    public String getById(HttpServletRequest request) {
        String id = request.getParameter("id");
        Person person = personService.get(Integer.parseInt(id));
        return JSONObject.toJSONString(person);
    }

    @RequestMapping("/deleteById")
    @ResponseBody
    public String deleteById(HttpServletRequest request) {
        String id = request.getParameter("id");
        personService.delete(Integer.parseInt(id));
        return "result from PersonController.deleteById()";
    }*/

}
