package dh.javaproject.testdata.dto.request;

import dh.javaproject.testdata.domain.constant.MockDataType;
import dh.javaproject.testdata.dto.SchemaFieldDto;

public record SchemaFieldRequest(
        String fieldName,
        MockDataType mockDataType,
        Integer fieldOrder,
        Integer blankPercent,
        String typeOptionJson,
        String forceValue
) {

    public SchemaFieldDto toDto() {
        return SchemaFieldDto.of(
                fieldName(),
                mockDataType(),
                fieldOrder(),
                blankPercent(),
                typeOptionJson(),
                forceValue()
        );
    }
}
