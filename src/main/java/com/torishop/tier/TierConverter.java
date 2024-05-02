package com.torishop.tier;

import com.torishop.tier.domain.TierEntity;
import com.torishop.tier.dto.Tier;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TierConverter {
    public Tier entityToDto(TierEntity tierEntity){
        return Tier.builder()
                .id(tierEntity.getId())
                .tier(tierEntity.getTier())
                .standardAmount(tierEntity.getStandardAmount())
                .build();
    }
}
