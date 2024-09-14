package org.cactus.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;


@Entity
@Table(name = "TABLES", schema = "information_schema")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InformationSchemaTable extends PanacheEntityBase {

    @Id
    public String TABLE_NAME;

    @ColumnDefault("''")
    @Column(name = "TABLE_CATALOG", nullable = false, length = 512)
    private String tableCatalog;

    @ColumnDefault("''")
    @Column(name = "TABLE_SCHEMA", nullable = false, length = 64)
    private String tableSchema;

    @ColumnDefault("''")
    @Column(name = "TABLE_TYPE", nullable = false, length = 64)
    private String tableType;

    @Column(name = "ENGINE", length = 64)
    private String engine;

    @Column(name = "VERSION")
    private Long version;

    @Column(name = "ROW_FORMAT", length = 10)
    private String rowFormat;

    @Column(name = "TABLE_ROWS")
    private Long tableRows;

    @Column(name = "AVG_ROW_LENGTH")
    private Long avgRowLength;

    @Column(name = "DATA_LENGTH")
    private Long dataLength;

    @Column(name = "MAX_DATA_LENGTH")
    private Long maxDataLength;

    @Column(name = "INDEX_LENGTH")
    private Long indexLength;

    @Column(name = "DATA_FREE")
    private Long dataFree;

    @Column(name = "AUTO_INCREMENT")
    private Long autoIncrement;

    @Column(name = "CREATE_TIME")
    private Instant createTime;

    @Column(name = "UPDATE_TIME")
    private Instant updateTime;

    @Column(name = "CHECK_TIME")
    private Instant checkTime;

    @Column(name = "TABLE_COLLATION", length = 32)
    private String tableCollation;

    @Column(name = "CHECKSUM")
    private Long checksum;

    @Column(name = "CREATE_OPTIONS")
    private String createOptions;

    @ColumnDefault("''")
    @Column(name = "TABLE_COMMENT", nullable = false, length = 2048)
    private String tableComment;
}