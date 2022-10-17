package com.aviv.springbootdemo.webapi;

import com.aviv.springbootdemo.model.user.User;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AppContext {
    private ApplicationContext _applicationContext;

    private User user;

    public AppContext(ApplicationContext applicationContext) {
        this._applicationContext = applicationContext;
    }

    public User getUser() {
        String currentUserBeanKey = "currentUser";
        if(this.user == null && this._applicationContext.containsBean(currentUserBeanKey)) {
            this.user = (User) this._applicationContext.getBean(currentUserBeanKey);
        }


        return this.user;
    }
}
