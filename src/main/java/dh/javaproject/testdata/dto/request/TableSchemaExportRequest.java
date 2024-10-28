package dh.javaproject.testdata.dto.request;

import dh.javaproject.testdata.domain.constant.ExportFileType;
import dh.javaproject.testdata.dto.TableSchemaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class TableSchemaExportRequest {

    private String schemaName;
    private Integer rowCount;
    private ExportFileType fileType;
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
