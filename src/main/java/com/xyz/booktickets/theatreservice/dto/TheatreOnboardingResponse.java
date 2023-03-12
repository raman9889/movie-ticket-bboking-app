package com.xyz.booktickets.theatreservice.dto;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor()
public class TheatreOnboardingResponse {
    private final long theatreId;
    private final String theatreName;
}
