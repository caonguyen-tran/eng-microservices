package com.engapp.CollectionService.mapper;

import com.engapp.CollectionService.dto.request.CollectionRequest;
import com.engapp.CollectionService.dto.response.CollectionResponse;
import com.engapp.CollectionService.pojo.Collection;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CollectionMapper {

    public Collection collectionRequestToCollection(CollectionRequest collectionRequest);

    public CollectionResponse collectionToCollectionResponse(Collection collection);
}
