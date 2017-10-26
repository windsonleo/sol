package br.com.tecsoluction.sol.framework;

import java.beans.PropertyEditorSupport;

/**
 * Created by clebr on 17/07/2016.
 */
public class AbstractEditor<Entity> extends PropertyEditorSupport {

    private final AbstractEntityService<Entity> dao;

    public AbstractEditor(final AbstractEntityService<Entity> dao) {
        this.dao = dao;
    }

    @Override
    public void setAsText(final String id) {

        if (id.equalsIgnoreCase("")) {
            this.setValue(null);
        } else {
            final Entity entity = dao.findOne(Long.parseLong(id));

            this.setValue(entity);
        }
    }

}
