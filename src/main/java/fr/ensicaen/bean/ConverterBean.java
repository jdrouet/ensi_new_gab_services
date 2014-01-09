package fr.ensicaen.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;
import java.util.HashMap;

/**
 * User: Jérémie Drouet
 * Date: 09/01/14
 */
@ManagedBean
@ViewScoped
public class ConverterBean extends HashMap<String, Object> implements Serializable, Converter {
    private static final long serialVersionUID = -4997842244900240381L;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        return this.get(s);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        this.put(o.toString(), o);
        return o.toString();
    }
}
