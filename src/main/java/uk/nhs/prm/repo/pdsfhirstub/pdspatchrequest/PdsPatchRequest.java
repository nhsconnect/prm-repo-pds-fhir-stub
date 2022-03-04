package uk.nhs.prm.repo.pdsfhirstub.pdspatchrequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PdsPatchRequest {
    List<PdsPatch> patches;
}
