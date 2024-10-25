package dh.javaproject.testdata.dto.request;

import dh.javaproject.testdata.domain.constant.MockDataType;
import dh.javaproject.testdata.dto.SchemaFieldDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class SchemaFieldRequest {

    private String fieldName;
    private MockDataType mockDataType;
    private Integer fieldOrder;
    private Integer blankPercent;
    private String typeOptionJson;
    private String forceValue;

    public SchemaFieldDto toDto() {
        return SchemaFieldDto.of(
                fieldName,
                mockDataType,
                fieldOrder,
                blankPercent,
                typeOptionJson,
                forceValue
        );
    }
}
