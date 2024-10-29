package dh.javaproject.testdata.dto.response;

import dh.javaproject.testdata.dto.TableSchemaDto;

import java.time.LocalDateTime;

public record SimpleTableSchemaResponse(
        String schemaName,
        String userId,
        LocalDateTime modifiedAt
) {

    public static SimpleTableSchemaResponse fromDto(TableSchemaDto dto) {
        return new SimpleTableSchemaResponse(
                dto.schemaName(),
                dto.userId(),
                dto.modifiedAt()
        );
    }
}
