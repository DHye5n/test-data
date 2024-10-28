package dh.javaproject.testdata.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 *   단위 테이블 스키마 정보.
 *   식별자({@link #userId})로 특정할 수 있는 회원이 소유한다.
 *
 *   @author dh
 */

@Getter
@ToString(callSuper = true)
@Table(
        uniqueConstraints = {@UniqueConstraint(columnNames = {"userId", "schemaName"})},
        indexes = {
                @Index(columnList = "createdAt"),
                @Index(columnList = "modifiedAt")
        }
)
@Entity
public class TableSchema extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @OrderBy("fieldOrder ASC")
    @OneToMany(mappedBy = "tableSchema", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<SchemaField> schemaFields = new LinkedHashSet<>();

    @Setter
    @Column(nullable = false)
    private String schemaName;

    @Setter
    @Column(nullable = false)
    private String userId;

    @Setter
    private LocalDateTime exportedAt;

    protected TableSchema() {}

    public TableSchema(String schemaName, String userId) {
        this.schemaName = schemaName;
        this.userId = userId;
        this.exportedAt = null;
    }

    public static TableSchema of(String schemaName, String userId) {
        return new TableSchema(schemaName, userId);
    }

    public void markExported() {
        exportedAt = LocalDateTime.now();
    }

    public boolean isExported() {
        return exportedAt != null;
    }

    public void addSchemaField(SchemaField schemaField) {
        schemaField.setTableSchema(this);
        schemaFields.add(schemaField);
    }

    public void addSchemaFields(Collection<SchemaField> schemaFields) {
        schemaFields.forEach(this::addSchemaField);
    }

    public void clearSchemaFields() {
        schemaFields.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TableSchema that)) return false;

        if (this.getId() == null) {
            return Objects.equals(getSchemaFields(), that.getSchemaFields()) &&
                    Objects.equals(getSchemaName(), that.getSchemaName()) &&
                    Objects.equals(getUserId(), that.getUserId()) &&
                    Objects.equals(getExportedAt(), that.getExportedAt());

        }

        return Objects.equals(this.getId(), that.getId());
    }

    @Override
    public int hashCode() {
        if (getId() == null) {
            return Objects.hash(getSchemaFields(), getSchemaName(), getUserId(), getExportedAt());
        }

        return Objects.hash(getId());
    }
}
