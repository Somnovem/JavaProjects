package com.itstep.homework_6.requests;

import com.itstep.homework_6.models.EntityModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = " Entity Model Create Request ")
public class EntityModelCreateRequest
{
    @Schema(description = "Entity name", example = "Hello new Entity")
    @Size(min = 10, max = 100)
    private String name;

    public EntityModel toEntity() {
        EntityModel model = new EntityModel();
        model.setName(name);
        return model;
    }
}