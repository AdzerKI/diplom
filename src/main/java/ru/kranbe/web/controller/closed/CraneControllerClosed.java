package ru.kranbe.web.controller.closed;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kranbe.domain.crane.Crane;
import ru.kranbe.service.CraneService;
import ru.kranbe.web.dto.crane.CraneDTO;
import ru.kranbe.web.mappers.CraneMapper;

@RestController
@RequestMapping("/rest/v1/closed/crane")
@RequiredArgsConstructor
@Validated
@Tag(name = "Crane Controller", description = "Crane API")
public class CraneControllerClosed {
    private final CraneService craneService;
    private final CraneMapper craneMapper;

    @PostMapping("/create")
    @Operation(summary = "Create crane")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#dto.id)")
    public CraneDTO create(@Valid @RequestBody final CraneDTO dto) {
        Crane crane = craneMapper.toEntity(dto);
        Crane updatedCrane = craneService.update(crane);

        return craneMapper.toDto(updatedCrane);
    }

//    @PostMapping("/{id}")
//    @Operation(summary = "Read crane")
//    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
//    public CraneDTO read(@Valid @RequestBody final ReadCraneByIdDTO dto) {
////        Crane crane = craneMapper.toEntity(dto);
////        Crane updatedCrane = craneService.update(crane);
//        return craneMapper.toDto(updatedCrane);
//    }

    @PostMapping("/update/{id}")
    @Operation(summary = "Update crane")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#dto.id)")
    public CraneDTO update(@Valid @RequestBody final CraneDTO dto) {
        Crane crane = craneMapper.toEntity(dto);
        Crane updatedCrane = craneService.update(crane);
        return craneMapper.toDto(updatedCrane);
    }
}
