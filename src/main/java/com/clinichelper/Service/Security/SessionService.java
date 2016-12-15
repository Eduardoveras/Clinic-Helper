/**
 * Created by Djidjelly Siclait on 12/14/2016.
 */
package com.clinichelper.Service.Security;

import com.clinichelper.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class SessionService {

    // Attributes
    @Autowired
    private HttpSession session;

    public Object getSessionAttr(String name)
    {
        return session.getAttribute(name);
    }

    public void setSessionAttr(String name,Object obj)
    {
        session.setAttribute(name,obj);
    }

    public boolean isUserLoggedIn() {
        return null != session.getAttribute("user");
    }

    public void logOut()
    {
        session.invalidate();
    }

    public User getCurrentLoggedUser()
    {
        return (User)session.getAttribute("user");
    }
}
