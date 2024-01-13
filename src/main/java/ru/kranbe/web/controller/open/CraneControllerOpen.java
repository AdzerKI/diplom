package ru.kranbe.web.controller.open;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kranbe.service.CraneService;
import ru.kranbe.web.dto.crane.open.RequestPropertyListByType;
import ru.kranbe.web.dto.crane.open.ResponseGetCraneType;
import ru.kranbe.web.dto.crane.open.ResponsePropertyListByType;

@RestController
@RequestMapping("/rest/v1/open/crane")
@RequiredArgsConstructor
@Validated
@Tag(name = "Crane Controller", description = "Crane API")
public class CraneControllerOpen {
    private final CraneService craneService;

    @PostMapping("/get_type")
    @Operation(summary = "Get types, crane")
    public @ResponseBody ResponseGetCraneType getCraneType() {
        return craneService.getCraneType();
    }

    @PostMapping("/get_property_list_by_type")
    @Operation(summary = "Get property list, crane")
    public @ResponseBody ResponsePropertyListByType getListByType(
            @Valid @RequestBody final RequestPropertyListByType request) {
        return craneService.getPropertiesByType(request);
    }
}
