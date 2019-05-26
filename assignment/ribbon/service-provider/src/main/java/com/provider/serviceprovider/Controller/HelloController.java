package com.provider.serviceprovider.Controller;

import com.provider.serviceprovider.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private final User[] users= {
            new User(0,"zhaoshenglong", "245078728@qq.com", 22),
            new User(0,"liuqingyuan", "liuqingyuan@sjtu.edu.cn", 20),
            new User(0,"jinjiazhen", "jinjiazhen@sjtu.edu.cn", 19),
            new User(0, "daifangyue", "daifangyue@sjtu.edu.cn", 18)
    };

    @Autowired
    private Environment environment;

    @GetMapping("/users/{name}")
    public  User getUser(@PathVariable("name") String name) {
        int index = this.getUserIndex(name);
        if (index >= 0) {
            User user = this.users[getUserIndex(name)];
            user.setPort(Integer.parseInt(this.environment.getProperty("server.port")));
            return user;
        }

        return  null;
    }

    private int getUserIndex(String name) {
        if (name.equals("zhaoshenglong"))
            return 0;
        else if (name.equals("liuqingyuan"))
            return 1;
        else if (name.equals("jinjiazhen"))
            return 2;
        else if (name.equals("daifangyue"))
            return 3;
        else return -1;
    }
}
