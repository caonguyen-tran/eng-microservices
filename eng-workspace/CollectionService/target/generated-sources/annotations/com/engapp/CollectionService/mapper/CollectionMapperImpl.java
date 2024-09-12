package com.engapp.CollectionService.mapper;

import com.engapp.CollectionService.dto.request.CollectionRequest;
import com.engapp.CollectionService.dto.response.CollectionResponse;
import com.engapp.CollectionService.pojo.Collection;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-12T17:08:57+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
public class CollectionMapperImpl implements CollectionMapper {

    @Override
    public Collection collectionRequestToCollection(CollectionRequest collectionRequest) {
        if ( collectionRequest == null ) {
            return null;
        }

        Collection.CollectionBuilder collection = Collection.builder();

        collection.id( collectionRequest.getId() );
        collection.name( collectionRequest.getName() );
        collection.description( collectionRequest.getDescription() );

        return collection.build();
    }

    @Override
    public CollectionResponse collectionToCollectionResponse(Collection collection) {
        if ( collection == null ) {
            return null;
        }

        CollectionResponse collectionResponse = new CollectionResponse();

        collectionResponse.setId( collection.getId() );
        collectionResponse.setName( collection.getName() );
        collectionResponse.setDescription( collection.getDescription() );
        collectionResponse.setImage( collection.getImage() );
        collectionResponse.setCreateAt( collection.getCreateAt() );
        collectionResponse.setUpdateAt( collection.getUpdateAt() );
        collectionResponse.setCreateBy( collection.getCreateBy() );

        return collectionResponse;
    }
}
