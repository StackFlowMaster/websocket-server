package com.example.SpringBoot_webSocketChatting.EntityForm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BetRole {
    @JsonProperty
    private int betAmount;
    @JsonProperty
    private String before_p_wins;
    @JsonProperty
    private String p_bet_role;
    @JsonProperty
    private String before_b_wins;
    @JsonProperty
    private String b_bet_role;
    @JsonProperty
    private int win_next_step;
    @JsonProperty
    private int loss_next_step;
}