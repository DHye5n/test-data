package dh.javaproject.testdata.dto.response;

import dh.javaproject.testdata.domain.constant.MockDataType;
import dh.javaproject.testdata.dto.SchemaFieldDto;

public record SchemaFieldResponse(

        String fieldName,
        MockDataType mockDataType,
        Integer fieldOrder,
        Integer blankPercent,
        String typeOptionJson,
        String forceValue
) {

    public static SchemaFieldResponse fromDto(SchemaFieldDto dto) {
        return new SchemaFieldResponse(

                dto.fieldName(),
                dto.mockDataType(),
                dto.fieldOrder(),
                dto.blankPercent(),
                dto.typeOptionJson(),
                dto.forceValue()
        );
    }
}
