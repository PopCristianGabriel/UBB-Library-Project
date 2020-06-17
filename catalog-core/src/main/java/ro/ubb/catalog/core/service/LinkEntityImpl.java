package ro.ubb.catalog.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.catalog.core.model.LinkEntity;
import ro.ubb.catalog.core.repository.ILinkEntityRepository;

import java.util.ArrayList;
@Service
public class LinkEntityImpl implements LinkEntityService{
    @Autowired
    ILinkEntityRepository repository;


    @Override
    public void add_entity(LinkEntity entity) {
        repository.add_entity(entity);
    }

    @Override
    public ArrayList<LinkEntity> get_entities() {
        return repository.get_entities();
    }

    @Override
    public void remove_entity(LinkEntity entity) {
        repository.remove_entity(entity);
    }
}
