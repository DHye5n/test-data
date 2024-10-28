package dh.javaproject.testdata.domain;

import dh.javaproject.testdata.domain.constant.MockDataType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

/**
 *   특정 {@link TableSchema}의 단위 필드 정보.
 *   이 필드들이 모여서 테이블 스키마를 구성한다.
 *
 *   @author dh
 */

@Getter
@ToString(callSuper = true)
@Entity
public class SchemaField extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(optional = false)
    private TableSchema tableSchema;

    @Column(nullable = false)
    @Setter
    private String fieldName;

    @Column(nullable = false)
    @Setter
    @Enumerated(EnumType.STRING)
    private MockDataType mockDataType;

    @Column(nullable = false)
    @Setter
    private Integer fieldOrder;

    @Column(nullable = false)
    @Setter
    private Integer blankPercent;

    private String typeOptionJson;  // Json format field

    private String forceValue;

    protected SchemaField() {}

    public SchemaField(
            String fieldName,
            MockDataType mockDataType,
            Integer fieldOrder,
            Integer blankPercent,
            String typeOptionJson,
            String forceValue) {
        this.fieldName = fieldName;
        this.mockDataType = mockDataType;
        this.fieldOrder = fieldOrder;
        this.blankPercent = blankPercent;
        this.typeOptionJson = typeOptionJson;
        this.forceValue = forceValue;
    }


    public static SchemaField of(
            String fieldName,
            MockDataType mockDataType,
            Integer fieldOrder,
            Integer blankPercent,
            String typeOptionJson,
            String forceValue) {
        return new SchemaField(fieldName, mockDataType, fieldOrder, blankPercent, typeOptionJson, forceValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SchemaField that)) return false;

        if (this.getId() == null) {
            return Objects.equals(this.getTableSchema().getId(), that.getTableSchema().getId()) &&
                    Objects.equals(this.getMockDataType(), that.getMockDataType()) &&
                    Objects.equals(this.getFieldName(), that.getFieldName()) &&
                    Objects.equals(this.getFieldOrder(), that.getFieldOrder()) &&
                    Objects.equals(this.getBlankPercent(), that.getBlankPercent()) &&
                    Objects.equals(this.getTypeOptionJson(), that.getTypeOptionJson()) &&
                    Objects.equals(this.getForceValue(), that.getForceValue());
        }

        return Objects.equals(this.getId(), that.getId());
    }

    @Override
    public int hashCode() {
        if (getId() == null) {
            return Objects.hash(getTableSchema().getId(), getFieldName(), getMockDataType(), getFieldOrder(), getBlankPercent(), getTypeOptionJson(), getForceValue());
        }

        return Objects.hash(getId());
    }
}
