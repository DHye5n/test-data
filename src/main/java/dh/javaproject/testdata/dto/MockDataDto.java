package dh.javaproject.testdata.dto;

import dh.javaproject.testdata.domain.MockData;
import dh.javaproject.testdata.domain.constant.MockDataType;

/**
 *   DTO for {@link MockData}
 */

public record MockDataDto(
        Long id,
        MockDataType mockDataType,
        String mockDataValue
) {

    public static MockDataDto fromEntity(MockData entity) {
        return new MockDataDto(
                entity.getId(),
                entity.getMockDataType(),
                entity.getMockDataValue()
        );
    }
}
