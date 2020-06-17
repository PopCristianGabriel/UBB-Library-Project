package ro.ubb.catalog.web.converter;

import ro.ubb.catalog.core.model.BaseEntity;
import ro.ubb.catalog.core.model.Entity;
import ro.ubb.catalog.web.dto.BaseDto;

/**
 * Created by radu.
 */

public interface Converter<Model extends Entity<Long>, Dto extends BaseDto> {

    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);

}

