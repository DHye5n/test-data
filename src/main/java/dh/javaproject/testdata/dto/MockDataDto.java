package dh.javaproject.testdata.dto;

import dh.javaproject.testdata.domain.MockData;
import dh.javaproject.testdata.domain.constant.MockDataType;

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
