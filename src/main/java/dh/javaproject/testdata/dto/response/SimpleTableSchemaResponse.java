package dh.javaproject.testdata.dto.response;

import dh.javaproject.testdata.dto.TableSchemaDto;

public record SimpleTableSchemaResponse(
        String schemaName,
        String userId
) {

    public static SimpleTableSchemaResponse fromDto(TableSchemaDto dto) {
        return new SimpleTableSchemaResponse(
                dto.schemaName(),
                dto.userId()
        );
    }
}
