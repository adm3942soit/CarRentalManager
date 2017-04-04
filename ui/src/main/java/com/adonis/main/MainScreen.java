package com.adonis.main;


import com.adonis.login.LoginUI;
import com.adonis.view.about.AboutView;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import lombok.NoArgsConstructor;

/**
 * Content of the UI when the user is logged in.
 * 
 * 
 */

@NoArgsConstructor
public class MainScreen extends HorizontalLayout {
    private Menu menu;

    public MainScreen(LoginUI ui) {

        setStyleName("main-screen");

        CssLayout viewContainer = new CssLayout();
        viewContainer.addStyleName("valo-content");
        viewContainer.setSizeFull();

        final Navigator navigator = new Navigator(ui, viewContainer);
        navigator.setErrorView(ErrorView.class);
        menu = new Menu(ui.personService, navigator);
//        menu.addView(new SampleCrudView(), SampleCrudView.VIEW_NAME,
//                SampleCrudView.VIEW_NAME, FontAwesome.EDIT);
        menu.addView(new AboutView(), AboutView.VIEW_NAME, AboutView.VIEW_NAME, null);

        navigator.addViewChangeListener(viewChangeListener);

        addComponent(menu);
        addComponent(viewContainer);
        setExpandRatio(viewContainer, 1);
        setSizeFull();
    }

    // notify the view menu about view changes so that it can display which view
    // is currently active
    ViewChangeListener viewChangeListener = new ViewChangeListener() {

        @Override
        public boolean beforeViewChange(ViewChangeEvent event) {
            return true;
        }

        @Override
        public void afterViewChange(ViewChangeEvent event) {
            menu.setActiveView(event.getViewName());
        }

    };
}
