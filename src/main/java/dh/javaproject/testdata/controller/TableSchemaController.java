package dh.javaproject.testdata.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dh.javaproject.testdata.domain.constant.ExportFileType;
import dh.javaproject.testdata.domain.constant.MockDataType;
import dh.javaproject.testdata.dto.request.TableSchemaExportRequest;
import dh.javaproject.testdata.dto.request.TableSchemaRequest;
import dh.javaproject.testdata.dto.response.SchemaFieldResponse;
import dh.javaproject.testdata.dto.response.SimpleTableSchemaResponse;
import dh.javaproject.testdata.dto.response.TableSchemaResponse;
import dh.javaproject.testdata.dto.security.GithubUser;
import dh.javaproject.testdata.service.TableSchemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class TableSchemaController {

    private final ObjectMapper mapper;
    private final TableSchemaService tableSchemaService;

    @GetMapping("/table-schema")
    public String tableSchema(Model model,
                              @RequestParam(name = "schemaName", required = false) String schemaName,
                              @AuthenticationPrincipal GithubUser githubUser) {

        TableSchemaResponse tableSchema = (githubUser != null && schemaName != null) ?
                TableSchemaResponse.fromDto(tableSchemaService.loadMySchema(githubUser.id(), schemaName))
                : defaultTableSchemas(schemaName);

        model.addAttribute("tableSchema", tableSchema);
        model.addAttribute("mockDataTypes", MockDataType.toObjects());
        model.addAttribute("fileTypes", Arrays.stream(ExportFileType.values()).toList());

        return "table-schema";
    }

    @PostMapping("/table-schema")
    public String createOrUpdateTableSchema(
            TableSchemaRequest tableSchemaRequest,
            RedirectAttributes redirectAttributes,
            @AuthenticationPrincipal GithubUser githubUser
    ) {
        tableSchemaService.upsertTableSchema(tableSchemaRequest.toDto(githubUser.id()));

        redirectAttributes.addAttribute("schemaName", tableSchemaRequest.getSchemaName());

        return "redirect:/table-schema";
    }

    @GetMapping("/table-schema/my-schemas")
    public String mySchemas(Model model,
                            @AuthenticationPrincipal GithubUser githubUser) {

        List<SimpleTableSchemaResponse> tableSchemas =
                tableSchemaService.loadMySchemas(githubUser.id())
                        .stream()
                        .map(SimpleTableSchemaResponse::fromDto)
                        .toList();

        model.addAttribute("tableSchemas", tableSchemas);

        return "my-schemas";
    }

    @PostMapping("/table-schema/my-schemas/{schemaName}")
    public String deleteMySchema
            (@PathVariable(name = "schemaName") String schemaName,
             @AuthenticationPrincipal GithubUser githubUser) {

        tableSchemaService.deleteTableSchema(githubUser.id(), schemaName);

        return "redirect:/table-schema/my-schemas";
    }

    @GetMapping("/table-schema/export")
    public ResponseEntity<String> exportTableSchema(TableSchemaExportRequest tableSchemaExportRequest) {

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=table-schema.txt")
                .body(json(tableSchemaExportRequest));
    }

    private TableSchemaResponse defaultTableSchemas(String schemaName) {
        return new TableSchemaResponse(
                schemaName != null ? schemaName : "schema-name",
                "dh",
                List.of(
                        new SchemaFieldResponse("id", MockDataType.STRING, 1, 0, null, null),
                        new SchemaFieldResponse("name", MockDataType.NUMBER, 2, 10, null, null),
                        new SchemaFieldResponse("age", MockDataType.NAME, 3, 20, null, null),
                        new SchemaFieldResponse("my_car", MockDataType.CAR, 3, 50, null, null)
                )
        );
    }

    private String json(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException jpe) {
            throw new RuntimeException(jpe);
        }
    }
}
