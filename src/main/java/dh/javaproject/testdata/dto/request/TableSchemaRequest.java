package dh.javaproject.testdata.dto.request;

import dh.javaproject.testdata.dto.TableSchemaDto;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class TableSchemaRequest {

    private String schemaName;
    private List<SchemaFieldRequest> schemaFields;

    public TableSchemaDto toDto(String userId) {
        return TableSchemaDto.of(
                schemaName,
                userId,
                null,
                schemaFields.stream()
                        .map(SchemaFieldRequest::toDto)
                        .collect(Collectors.toUnmodifiableSet())
        );
    }
}
