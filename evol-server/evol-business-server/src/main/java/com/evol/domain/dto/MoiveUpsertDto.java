package com.evol.domain.dto;

import lombok.Data;

@Data
public class MoiveUpsertDto {

    private String name;

    private String foreignName;

    private String releaseDate;

    private Integer minutes;

    private Integer discountCount;

    private Integer discountPrice;

    private String releaseRegion;

    private Integer spaceType;

    private String coverUri;

    private String images;

    private String description;

    private Integer ratings;

    private String language;
}
