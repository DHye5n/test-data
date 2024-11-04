package dh.javaproject.testdata.service;

import dh.javaproject.testdata.dto.TableSchemaDto;
import dh.javaproject.testdata.repository.TableSchemaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TableSchemaService {

    private final TableSchemaRepository tableSchemaRepository;

    @Transactional(readOnly = true)
    public List<TableSchemaDto> loadMySchemas(String userId) {
        return loadMySchemas(userId, Pageable.unpaged()).toList();
    }

    @Transactional(readOnly = true)
    public Page<TableSchemaDto> loadMySchemas(String userId, Pageable pageable) {
        return tableSchemaRepository.findByUserId(userId, pageable)
                .map(TableSchemaDto::fromEntity);
    }
}
