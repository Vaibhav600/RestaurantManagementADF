package com.example.beans;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCDataControl;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.jbo.ApplicationModule;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;


@SessionScoped
@ManagedBean(name="ownerBean")
public class OwnerBean {
    public OwnerBean() {
        super();
    }
    private String userId;
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserId() {
        System.out.println("UserId: "+userId);
        return userId;
    }
    public ApplicationModule getApplicationModule() {
        BindingContext bindingContext = BindingContext.getCurrent();
            if (bindingContext != null) {
        DCBindingContainer bindings = (DCBindingContainer) bindingContext.getCurrentBindingsEntry();
        DCDataControl dataControl = bindings.getDataControl();
        ApplicationModule am = (ApplicationModule)dataControl.getDataProvider();
           return am;
        } else {
            System.out.println("BindingContext is null");
        }
        return null;
    }
    public String getOwner() {
        System.out.println("get owner function: "+userId);
        try{
            ApplicationModule am = getApplicationModule();
            ViewObject userVO = am.findViewObject("G3UsersVO");
            userVO.setNamedWhereClauseParam("bindingOwner", userId);
            userVO.executeQuery();
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}