package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.LinkEntity;

import java.util.ArrayList;

public interface LinkEntityService {
    public void add_entity(LinkEntity entity);
    public ArrayList<LinkEntity> get_entities();
    public void remove_entity(LinkEntity entity);
}
