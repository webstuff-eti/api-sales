package com.tiagotibaes.domain.converter;

import com.tiagotibaes.domain.enumerated.TypeDocument;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class TypeDocumentConverter implements AttributeConverter<TypeDocument, String> {


    @Override
    public String convertToDatabaseColumn(TypeDocument typeDocument) {

        if (typeDocument == null) {
            return null;
        }
        return typeDocument.getValue();
    }

    @Override
    public TypeDocument convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }

        return Stream.of(TypeDocument.values()).filter(t -> t.getValue().equals(value)).findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
