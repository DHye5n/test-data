package dh.javaproject.testdata.domain.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@RequiredArgsConstructor
public enum MockDataType {

    STRING(Set.of("minLength", "maxLength", "pattern"), null),
    NUMBER(Set.of("min", "max", "decimals"), null),
    BOOLEAN(Set.of(), null),
    DATETIME(Set.of("from", "to"), null),
    ENUM(Set.of("elements"), null),

    SENTENCE(Set.of("minSentences", "maxSententces"), STRING),
    PARAGRAPH(Set.of("minParagraph", "maxParagraph"), STRING),
    UUID(Set.of(), STRING),
    EMAIL(Set.of(), STRING),
    CAR(Set.of(), STRING),
    ROW_NUMBER(Set.of("start", "step"), STRING),
    NAME(Set.of(), STRING),
    ;

    private final Set<String> requiredOptions;
    private final MockDataType baseType;

    public boolean isBaseType() {
        return baseType == null;
    }

}
