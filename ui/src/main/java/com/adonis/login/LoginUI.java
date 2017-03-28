package com.adonis.login;

import com.adonis.authentication.AccessControl;
import com.adonis.authentication.BasicAccessControl;
import com.adonis.authentication.LoginScreen;
import com.adonis.main.MainScreen;
import com.adonis.person.backend.PersonService;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Viewport;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

import javax.inject.Inject;

/**
 * Created by oksdud on 28.03.2017.
 */
@Viewport("user-scalable=no,initial-scale=1.0")
@Theme("mytheme")
@CDIUI("")
public class LoginUI extends UI {
    @Inject
    PersonService personService;
    private AccessControl accessControl = new BasicAccessControl();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Responsive.makeResponsive(this);
        setLocale(vaadinRequest.getLocale());
        getPage().setTitle("Car Manager");
        if (!accessControl.isUserSignedIn()) {
            setContent(new LoginScreen(personService, accessControl, new LoginScreen.LoginListener() {
                @Override
                public void loginSuccessful() {
                    showMainView();
                }
            }));
        } else {
            showMainView();
        }

    }
    protected void showMainView() {
        addStyleName(ValoTheme.UI_WITH_MENU);
        setContent(new MainScreen(LoginUI.this));
        getNavigator().navigateTo(getNavigator().getState());
    }

    public static LoginUI get() {
        return (LoginUI) UI.getCurrent();
    }

    public AccessControl getAccessControl() {
        return accessControl;
    }

//    @WebServlet(urlPatterns = "/*", name = "LoginUIServlet", asyncSupported = true)
//    @VaadinServletConfiguration(ui = LoginUI.class, productionMode = false)
//    public static class LoginUIServlet extends VaadinServlet {
//    }

}
