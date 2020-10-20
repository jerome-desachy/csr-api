package com.naturalmotion.csr_api.service.gift;

<<<<<<< HEAD
import com.naturalmotion.csr_api.api.CarElement;
import com.naturalmotion.csr_api.api.FusionColor;
=======
import java.math.BigDecimal;
>>>>>>> dcacf0fcbdb5611773f2468740a453ba0362ec32

import javax.json.Json;
import javax.json.JsonObjectBuilder;

import com.naturalmotion.csr_api.api.EliteToken;

public class GiftBuilder {

    public JsonObjectBuilder buildFusion(String id, String brand, CarElement element, FusionColor color, int amount) {
        return build(id, 13, brand, element.getPartType(), color.getGrade(), amount);
    }

    public JsonObjectBuilder buildEssence(String id) {
        return build(id, 10, "", 7, 0, 2000);
    }

    public JsonObjectBuilder buildEliteToken(EliteToken token, BigDecimal amount) {
        return build("1592077438_24_0", 24, "", 7, token.getGrade(), amount.intValue());
    }

    private JsonObjectBuilder build(String id, int type, String name, int part, int grade, int amount) {
        JsonObjectBuilder gift = Json.createObjectBuilder();

        gift.add("reason", "TEXT_TAG_CS_COMPENSATION");
        gift.add("ttl", 0);
        gift.add("id", id);
        gift.add("rank", 0);
        gift.add("CSR2ApplyableReward", buildReward(type, name, part, grade, amount));
        return gift;
    }

    private JsonObjectBuilder buildReward(int type, String name, int part, int grade, int amount) {
        JsonObjectBuilder reward = Json.createObjectBuilder();
        reward.add("rewardType", type);
        reward.add("name", name);
        reward.add("partType", part);
        reward.add("partGrade", grade);
        reward.add("gachaConfig", -1);
        reward.add("amount", amount);
        return reward;
    }
}
